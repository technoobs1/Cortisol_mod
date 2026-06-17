package net.tech.cortisolmod.item.custom; // Or your .client package

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ClientAutoInjector {

    public static void register(Consumer<IClientItemExtensions> consumer, int useDuration) {
        consumer.accept(new IClientItemExtensions() {

            private final HumanoidModel.ArmPose INJECT_POSE =
                    HumanoidModel.ArmPose.create("INJECT", true,
                            (model, entity, arm) -> {
                                float x = (float) (entity.getUseItemRemainingTicks() - 2) / useDuration;
                                float progress = 1.0F - x;
                                float anim = Math.max((float) Math.sin(progress * Math.PI) * (float) Math.exp(1 - 2.8 * x), 0f);

                                if (arm == HumanoidArm.RIGHT) {
                                    model.rightArm.yRot = -0.5F;
                                    model.rightArm.xRot = -anim * 2.0F;
                                    model.rightArm.zRot = -0.7F;

                                    model.leftArm.yRot = 0.7F;
                                    model.leftArm.xRot = -0.3F;
                                    model.leftArm.zRot = 0.3F;
                                } else {
                                    model.leftArm.yRot = -0.5F;
                                    model.leftArm.xRot = -anim * 2.0F;
                                    model.leftArm.zRot = -0.7F;

                                    model.rightArm.yRot = 0.7F;
                                    model.rightArm.xRot = -0.3F;
                                    model.rightArm.zRot = 0.3F;
                                }
                            });

            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                if (!itemStack.isEmpty() && entityLiving.getUsedItemHand() == hand && entityLiving.getUseItemRemainingTicks() > 0) {
                    return INJECT_POSE;
                }
                return HumanoidModel.ArmPose.EMPTY;
            }

            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                if (player.isUsingItem() && ItemStack.isSameItemSameTags(player.getUseItem(), itemInHand)) {
                    float x = (float) (player.getUseItemRemainingTicks() - 2) / useDuration;
                    float progress = 1.0F - ((player.getUseItemRemainingTicks() - partialTick) / (float) useDuration);
                    float anim = Math.max((float) Math.sin(progress * Math.PI) * (float) Math.exp(1 - 2.8 * x), -0.5f);

                    // Applies up/down camera hand movement during animation
                    poseStack.translate(0.0F, anim * 0.5f, 0.0F);
                }
                return false;
            }
        });
    }
}
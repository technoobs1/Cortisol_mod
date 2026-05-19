package net.tech.cortisolmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.tech.cortisolmod.cortisol.PlayerCortisolProvider;
import net.tech.cortisolmod.networking.ModMessages;
import net.tech.cortisolmod.networking.packet.CortisolSyncS2CPacket;

public class HighCortisolAutoInjectorItem extends Item {

    private final int cooldown;
    private final float cortisol_amount;

    public HighCortisolAutoInjectorItem(Properties pProperties, int cooldown, float cortisol_add) {
        super(pProperties);
        this.cooldown = cooldown;
        this.cortisol_amount = cortisol_add;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {

            player.getCapability(PlayerCortisolProvider.PLAYER_CORTISOL).ifPresent(cortisol -> {
                cortisol.addCortisol(this.cortisol_amount);

                ModMessages.sendToAllPlayers(
                        new CortisolSyncS2CPacket(player.getId(), cortisol.getCortisol())
                );
            });

            player.getCooldowns().addCooldown(this, cooldown);

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 15;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.CROSSBOW;
    }
}

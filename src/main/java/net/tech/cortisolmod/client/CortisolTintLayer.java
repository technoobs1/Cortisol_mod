package net.tech.cortisolmod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CortisolTintLayer<T extends LivingEntity, M extends EntityModel<T>>
        extends RenderLayer<T, M> {

    public CortisolTintLayer(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight,
                       T entity, float limbSwing, float limbSwingAmount, float partialTick,
                       float ageInTicks, float netHeadYaw, float headPitch) {

//        System.out.println(
//                entity.getName().getString()
//                        + " -> "
//                        + entity.getPersistentData()
//                        .getBoolean("cortisol_mob")
//        );
        //boolean hasCortisol = entity.getEntityData().get(ModEntityData.CORTISOL_MOB);
        //System.out.println("cortisol_mob tag = " + hasCortisol);
        // Même tag NBT que dans CortisolMobEvents
        if (!(entity instanceof Monster)) return;

        if (!ClientCortisolData.CORTISOL_MOBS.contains(entity.getId())) return;


        VertexConsumer vertexConsumer = bufferSource.getBuffer(
                RenderType.entityTranslucentCull(this.getTextureLocation(entity))
        );

        this.getParentModel().renderToBuffer(
                poseStack, vertexConsumer, packedLight,
                OverlayTexture.NO_OVERLAY,
                1.0f,  // red
                0.0f,  // green
                0.0f,  // blue
                0.4f   // alpha (transparence de la teinte)
        );
    }
}
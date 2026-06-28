package net.tech.cortisolmod.event;

import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.tech.cortisolmod.CortisolMod;
import net.tech.cortisolmod.client.CortisolTintLayer;

public class ClientEventRender {

        @SubscribeEvent
        public static void addRenderLayers(EntityRenderersEvent.AddLayers event) {

            ForgeRegistries.ENTITY_TYPES.forEach(entityType -> {



                tryAddLayer(event, entityType);
            });
        }

        private static <T extends net.minecraft.world.entity.LivingEntity,
                M extends net.minecraft.client.model.EntityModel<T>>
        void tryAddLayer(EntityRenderersEvent.AddLayers event, EntityType<?> type) {
            try {
                LivingEntityRenderer<T, M> renderer = (LivingEntityRenderer<T, M>) event.getRenderer((EntityType<T>) type);
                renderer.addLayer(new CortisolTintLayer<>(renderer));

            } catch (Exception ignored) {
            }


    }
}

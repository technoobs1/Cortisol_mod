package net.tech.cortisolmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tech.cortisolmod.CortisolMod;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS=
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CortisolMod.MOD_ID);
    public static final RegistryObject<MobEffect> CORTISOL_STABILIZER_EFFECT = MOB_EFFECTS.register("cortisol_stabilizer", CortisolStabilizerEffect::new);
    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}

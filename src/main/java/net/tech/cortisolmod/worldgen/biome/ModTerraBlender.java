package net.tech.cortisolmod.worldgen.biome;

import net.minecraft.resources.ResourceLocation;
import net.tech.cortisolmod.CortisolMod;
import terrablender.api.Region;
import terrablender.api.Regions;

public class ModTerraBlender {
    public static void registerBiomes(){
        Regions.register(new ModOverworldRegion(new ResourceLocation(CortisolMod.MOD_ID,"overworld"),3));
    }
}

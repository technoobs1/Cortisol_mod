package net.tech.cortisolmod.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.*;

import java.util.List;
import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(ResourceLocation name ,int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }


    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {

        VanillaParameterOverlayBuilder builder= new VanillaParameterOverlayBuilder();
        new ParameterUtils.ParameterPointListBuilder()
                .temperature(ParameterUtils.Temperature.WARM)
                // Humidité
                .humidity(ParameterUtils.Humidity.DRY)
                // bord de mer ou pas ?
                .continentalness(ParameterUtils.Continentalness.INLAND)
                // type terrain
                .erosion(ParameterUtils.Erosion.EROSION_2)
                // Profondeur
                .depth(ParameterUtils.Depth.SURFACE)
                // Forme du relief
                .weirdness(ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING)
                .build().forEach(point -> builder.add(point, ModBiomes.CORTISOL_BIOME));

        builder.build().forEach(mapper);



    }

}

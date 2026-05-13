package net.tech.cortisolmod.util;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class AdvancementHelper {

    /**
     * Grant an advancement fully (all remaining criteria).
     *
     * @param player ServerPlayer
     * @param advancementId example: "cortisolmod:kill_cortisol_mob"
     */
    public static void grant(ServerPlayer player, String advancementId) {
        if (player == null || player.server == null) return;
        ResourceLocation id = ResourceLocation.tryParse(advancementId);
        if (id == null) return;

        Advancement advancement = player.server.getAdvancements().getAdvancement(id);
        if (advancement == null) return;

        AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
        if (progress.isDone()) return;

        for (String criterion : progress.getRemainingCriteria()) {
            player.getAdvancements().award(advancement, criterion);
        }
    }
}
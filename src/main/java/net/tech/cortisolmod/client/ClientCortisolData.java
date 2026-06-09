package net.tech.cortisolmod.client;

import net.minecraft.client.Minecraft;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClientCortisolData {

    private static final Map<Integer, Float> CORTISOL_MAP = new HashMap<>();

    public static final Set<Integer> CORTISOL_MOBS = new HashSet<>();

    public static void set(int entityId, float cortisol) {
        CORTISOL_MAP.put(entityId, cortisol);
    }

    public static float getPlayerCortisol(int entityId) {
        return CORTISOL_MAP.getOrDefault(entityId, 0f);
    }

    public static float getPlayerCortisol() {
        if (Minecraft.getInstance().player == null) {
            return 0f;
        }
        return getPlayerCortisol(Minecraft.getInstance().player.getId());
    }
}
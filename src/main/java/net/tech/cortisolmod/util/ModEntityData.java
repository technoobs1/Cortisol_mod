package net.tech.cortisolmod.util;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Mob;

public class ModEntityData {
    public static final EntityDataAccessor<Boolean> CORTISOL_MOB =
            SynchedEntityData.defineId(Mob.class, EntityDataSerializers.BOOLEAN);
}
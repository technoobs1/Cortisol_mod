package net.tech.cortisolmod.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CortisolMobSyncS2CPacket {
    private final int entityId;
    private final boolean isCortisol;

    public CortisolMobSyncS2CPacket(int entityId, boolean isCortisol) {
        this.entityId = entityId;
        this.isCortisol = isCortisol;
    }

    public CortisolMobSyncS2CPacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.isCortisol = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeBoolean(isCortisol);
    }


    public boolean handle( Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Côté client
            Entity entity = Minecraft.getInstance().level.getEntity(entityId);
            if (entity != null) {
                // On stocke dans persistentData côté client aussi
                entity.getPersistentData().putBoolean("cortisol_mob", isCortisol);
            }
        });

        return true;
    }
}
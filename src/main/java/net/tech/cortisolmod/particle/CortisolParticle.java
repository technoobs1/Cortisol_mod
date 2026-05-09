package net.tech.cortisolmod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import org.jetbrains.annotations.Nullable;

public class CortisolParticle extends TextureSheetParticle {
    protected CortisolParticle(ClientLevel pLevel, double pX, double pY, double pZ,
                               SpriteSet spriteSet, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, 0, 0, 0);

        this.setSpriteFromAge(spriteSet);
        this.lifetime = 12 + this.random.nextInt(6);
        this.quadSize = 0.15f;
        this.xd = 0;
        this.yd = 0;
        this.zd = 0;

        float t = this.random.nextFloat(); // 0 → 1

        float r1 = 94 / 255f;
        float g1 = 191 / 255f;
        float b1 = 75 / 255f;
        float r2 = 234 / 255f;
        float g2 = 35 / 255f;
        float b2 = 64 / 255f;

        this.rCol = r1 + (r2 - r1) * t;
        this.gCol = g1 + (g2 - g1) * t;
        this.bCol = b1 + (b2 - b1) * t;
    }

    @Override
    protected int getLightColor(float partialTick) {
        return 0xF000F0; // full bright
    }

    @Override
    public void tick() {
        super.tick();
        this.x += (this.random.nextDouble() - 0.5) * 0.02;
        this.y += (this.random.nextDouble() - 0.5) * 0.02;
        this.z += (this.random.nextDouble() - 0.5) * 0.02;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }


    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new CortisolParticle(pLevel, pX, pY, pZ, this.spriteSet, pXSpeed, pYSpeed, pZSpeed);
        }
    }
}
package com.github.mammut53.more_babies.mixin.world.entity.animal;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GlowSquid.class)
public abstract class GlowSquidMixin extends Squid {

    private boolean particleBreak = false;

    protected GlowSquidMixin(EntityType<? extends Squid> entityType, Level level) {
        super(entityType, level);
    }

    @Redirect(method = "aiStep()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private void addParticle(Level level, ParticleOptions particleOptions, double d1, double d2, double d3, double d4, double d5, double d6) {

        if (!this.isBaby()) {
            this.level.addParticle(particleOptions, d1, d2, d3, d4, d5, d6);
            return;
        }

        if (!this.particleBreak) {
            this.particleBreak = true;

            d1 = this.getRandomX(0.4D);
            d3 = this.getRandomZ(0.4D);
            this.level.addParticle(particleOptions, d1, d2, d3, d4, d5, d6);
            return;
        }
        this.particleBreak = false;
    }

}

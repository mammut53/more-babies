package com.github.mammut53.more_babies.mixin.world.entity.monster;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Blaze.class)
public abstract class BlazeMixin extends Monster {

    protected BlazeMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Redirect(method = "aiStep()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private void addParticle(Level level, ParticleOptions particleOptions, double d1, double d2, double d3, double d4, double d5, double d6) {

        if (particleOptions.getType() == ParticleTypes.LARGE_SMOKE && this.isBaby()) {
            particleOptions = ParticleTypes.SMOKE;
        }

        this.level.addParticle(particleOptions, d1, d2, d3, d4, d5, d6);
    }
}
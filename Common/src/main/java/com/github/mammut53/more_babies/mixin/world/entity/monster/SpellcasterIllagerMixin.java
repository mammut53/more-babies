package com.github.mammut53.more_babies.mixin.world.entity.monster;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SpellcasterIllager.class)
public abstract class SpellcasterIllagerMixin extends AbstractIllager {

    protected SpellcasterIllagerMixin(EntityType<? extends AbstractIllager> entityType, Level level) {
        super(entityType, level);
    }

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"
            )
    )
    private void addParticle(Level level, ParticleOptions particleOptions, double a, double b, double c, double d, double e, double f) {
        if (this.isBaby()) b -= 0.9;
        level.addParticle(particleOptions, a, b, c, d, e, f);
    }

}

package com.github.mammut53.more_babies.mixin.world.entity.monster;

import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Creeper.class)
public interface CreeperAccessor {

    @Accessor("explosionRadius")
    void setExplosionRadius(int explosionRadius);

}

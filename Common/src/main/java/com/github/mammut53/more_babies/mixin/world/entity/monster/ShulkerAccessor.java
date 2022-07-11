package com.github.mammut53.more_babies.mixin.world.entity.monster;

import net.minecraft.world.entity.monster.Shulker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Shulker.class)
public interface ShulkerAccessor {

    @Accessor("currentPeekAmount")
    float getCurrentPeekAmount();

}

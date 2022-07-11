package com.github.mammut53.more_babies.mixin.world.entity.monster;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(targets = "net.minecraft.world.entity.monster.Evoker$EvokerAttackSpellGoal")
public abstract class EvokerAttackSpellGoalMixin {

    @ModifyConstant(method = "performSpellCasting()V", constant = @Constant(doubleValue = 1.0))
    private double injected(double x) {
        return 0;
    }

}

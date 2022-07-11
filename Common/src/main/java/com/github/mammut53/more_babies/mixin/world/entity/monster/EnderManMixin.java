package com.github.mammut53.more_babies.mixin.world.entity.monster;

import com.github.mammut53.more_babies.world.entity.BabyEnderMan;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EnderMan.class)
public abstract class EnderManMixin extends Monster {

    protected EnderManMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyConstant(method = "aiStep()V", constant = @Constant(intValue = 2), allow = 1)
    private int numberOfParticles(int value) {
        return this.getStandingEyeHeight(this.getPose(), this.getDimensions(this.getPose())) == BabyEnderMan.STANDING_EYE_HEIGHT ? 1 : value;
    }
}

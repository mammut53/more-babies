package io.github.mammut53.more_babies.mixin.world.entity.monster;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Ghast;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "net.minecraft.world.entity.monster.Ghast$GhastShootFireballGoal")
public abstract class GhastShootFireballGoalMixin extends Goal {
    @Final
    @Shadow
    private Ghast ghast;

    @ModifyConstant(
            method = "tick",
            constant = @Constant(doubleValue = 4.0)
    )
    private double modifyTranslateXZ(final double xz) {
        if (this.ghast.isBaby()) {
            return xz * 0.5;
        }
        return xz;
    }

    @ModifyConstant(
            method = "tick",
            constant = @Constant(doubleValue = 0.5)
    )
    private double modifyTranslateY(final double y) {
        if (this.ghast.isBaby()) {
            return y * 0.5;
        }
        return y;
    }
}

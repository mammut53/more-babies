package io.github.mammut53.more_babies.mixin.world.entity.monster.zombie;

import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Drowned.class)
public abstract class DrownedMixin extends Zombie implements RangedAttackMob {

    protected DrownedMixin(final Level level) {
        super(level);
    }

    @Redirect(
            method = "finalizeSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/zombie/Drowned;isBaby()Z"
            )
    )
    private boolean redirectFinalizeSpawnIsBaby(final Drowned instance) {
        return false;
    }

}


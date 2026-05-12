package io.github.mammut53.more_babies.mixin.world.level.block;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.animal.golem.AbstractGolem;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CarvedPumpkinBlock.class)
public abstract class CarvedPumpkinBlockMixin extends HorizontalDirectionalBlock {

    protected CarvedPumpkinBlockMixin(final Properties properties) {
        super(properties);
    }

    @ModifyVariable(
            method = "spawnGolemInWorld",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true
    )
    private static Entity modifySpawnGolemInWorldGolem(final Entity entity, final Level level) {
        final ServerLevel serverLevel = (ServerLevel) level;
        if (entity instanceof IronGolem || entity instanceof SnowGolem) {
            final AbstractGolem golem = (AbstractGolem) entity;
            golem.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(golem.blockPosition()), EntitySpawnReason.TRIGGERED, null);
        }
        return entity;
    }

}

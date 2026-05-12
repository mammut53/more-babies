package io.github.mammut53.more_babies.mixin.world.level.levelgen.structure.structures;

import io.github.mammut53.more_babies.config.MoreBabiesConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.structures.EndCityPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EndCityPieces.EndCityPiece.class)
public abstract class EndCityPieceMixin {

    @ModifyArg(
            method = "handleDataMarker",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/ServerLevelAccessor;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private Entity injectHandleDataMarker(final Entity entity) {
        if (entity instanceof final Shulker sentry) {
            final Level level = sentry.level();
            final RandomSource random = level.getRandom();
            sentry.setBaby(random.nextFloat() < MoreBabiesConfig.shulkerBabySpawnChance);
        }
        return entity;
    }

}

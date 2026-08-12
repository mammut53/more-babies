package io.github.mammut53.more_babies.mixin.world.level.levelgen.structure.structures;

import io.github.mammut53.more_babies.config.MoreBabiesConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.structures.EndCityPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(EndCityPieces.EndCityPiece.class)
public abstract class EndCityPieceMixin {

    @ModifyArgs(
            method = "handleDataMarker",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/ServerLevelAccessor;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private void injectHandleDataMarker(final Args args, final String markerId, final BlockPos position, final ServerLevelAccessor level, final RandomSource random, final BoundingBox chunkBB) {
        final Entity entity = args.get(0);
        if (entity instanceof final Shulker sentry) {
            sentry.setBaby(random.nextFloat() < MoreBabiesConfig.shulkerBabySpawnChance);
        }
    }

}

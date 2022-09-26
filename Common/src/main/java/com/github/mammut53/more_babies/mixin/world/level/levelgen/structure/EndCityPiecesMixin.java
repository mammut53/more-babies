package com.github.mammut53.more_babies.mixin.world.level.levelgen.structure;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.config.MoreBabiesConfig;
import com.github.mammut53.more_babies.world.entity.BabyShulker;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.structures.EndCityPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndCityPieces.EndCityPiece.class)
public abstract class EndCityPiecesMixin {

    @Inject(
            method = "handleDataMarker(Ljava/lang/String;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;startsWith(Ljava/lang/String;)Z",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void createShulker(String string, BlockPos blockPos, ServerLevelAccessor serverLevelAccessor, RandomSource randomSource, BoundingBox boundingBox, CallbackInfo ci) {
        if (!(string.startsWith("Sentry"))) {
            return;
        }

        MoreBabiesConfig.BabySwSmEntry shulkerEntry = (MoreBabiesConfig.BabySwSmEntry) MoreBabiesConfig.BABIES.get("shulker");
        if (!(randomSource.nextFloat() < shulkerEntry.getSpawnWeight().get())) {
            return;
        }

        BabyShulker babyShulker = (BabyShulker) MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.SHULKER).create(serverLevelAccessor.getLevel());
        babyShulker.setPos(blockPos.getX() + 0.5D, blockPos.getY(), blockPos.getZ() + 0.5D);
        serverLevelAccessor.addFreshEntity(babyShulker);
        ci.cancel();
    }

}

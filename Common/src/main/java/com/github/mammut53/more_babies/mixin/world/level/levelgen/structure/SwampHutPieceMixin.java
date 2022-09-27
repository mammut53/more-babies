package com.github.mammut53.more_babies.mixin.world.level.levelgen.structure;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.config.MoreBabiesConfig;
import com.github.mammut53.more_babies.world.entity.BabyWitch;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.structures.SwampHutPiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SwampHutPiece.class)
public abstract class SwampHutPieceMixin extends ScatteredFeaturePiece {

    @Shadow
    private void spawnCat(ServerLevelAccessor serverLevelAccessor, BoundingBox boundingBox) {}

    protected SwampHutPieceMixin(StructurePieceType structurePieceType, CompoundTag compoundTag) {
        super(structurePieceType, compoundTag);
    }

    @Inject(
            method = "postProcess(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Lnet/minecraft/world/level/ChunkPos;Lnet/minecraft/core/BlockPos;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/levelgen/structure/BoundingBox;isInside(Lnet/minecraft/core/Vec3i;)Z",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void spawnWitch(WorldGenLevel worldGenLevel, StructureManager structureManager, ChunkGenerator chunkGenerator, RandomSource randomSource, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos, CallbackInfo ci) {
        MoreBabiesConfig.BabyScSmEntry witchEntry = (MoreBabiesConfig.BabyScSmEntry) MoreBabiesConfig.BABIES.get("witch");
        if (!(randomSource.nextFloat() < witchEntry.getSpawnChance().get())) {
            return;
        }

        BlockPos spawnblockPos = this.getWorldPos(2, 2, 5);
        BabyWitch babyWitch = (BabyWitch) MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.WITCH).create(worldGenLevel.getLevel());
        babyWitch.setPersistenceRequired();
        babyWitch.moveTo((double)spawnblockPos.getX() + 0.5, spawnblockPos.getY(), (double)spawnblockPos.getZ() + 0.5, 0.0F, 0.0F);
        babyWitch.finalizeSpawn(worldGenLevel, worldGenLevel.getCurrentDifficultyAt(spawnblockPos), MobSpawnType.STRUCTURE, null, null);
        worldGenLevel.addFreshEntityWithPassengers(babyWitch);

        this.spawnCat(worldGenLevel, boundingBox);

        ci.cancel();
    }

}

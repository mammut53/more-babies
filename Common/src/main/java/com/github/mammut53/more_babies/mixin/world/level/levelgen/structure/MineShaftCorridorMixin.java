package com.github.mammut53.more_babies.mixin.world.level.levelgen.structure;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.MoreBabiesConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.structures.MineshaftPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(MineshaftPieces.MineShaftCorridor.class)
public abstract class MineShaftCorridorMixin {

    @Inject(
            method = "postProcess",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/BaseSpawner;setEntityId(Lnet/minecraft/world/entity/EntityType;)V",
                    shift = At.Shift.AFTER
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void setEntityId(WorldGenLevel worldGenLevel, StructureManager structureManager, ChunkGenerator chunkGenerator, RandomSource randomSource, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos, CallbackInfo ci, int $$7, int $$8, int $$9, int $$10, int $$11, BlockState $$12, int $$13, int $$14, int $$15, int $$16, BlockPos $$17, BlockEntity blockEntity) {
        if (!(randomSource.nextFloat() < MoreBabiesConstants.BABY_SPAWN_CHANCE)) {
            return;
        }

        ((SpawnerBlockEntity)blockEntity).getSpawner().setEntityId(MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.CAVE_SPIDER));
    }

}

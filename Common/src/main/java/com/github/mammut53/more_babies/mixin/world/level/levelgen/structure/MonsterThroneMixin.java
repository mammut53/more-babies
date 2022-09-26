package com.github.mammut53.more_babies.mixin.world.level.levelgen.structure;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.config.MoreBabiesConfig;
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
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(NetherFortressPieces.MonsterThrone.class)
public abstract class MonsterThroneMixin {

    @Inject(
            method = "postProcess",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/BaseSpawner;setEntityId(Lnet/minecraft/world/entity/EntityType;)V",
                    shift = At.Shift.AFTER
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void setEntityId(WorldGenLevel worldGenLevel, StructureManager structureManager, ChunkGenerator chunkGenerator, RandomSource randomSource, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos, CallbackInfo ci, BlockState $$7, BlockState $$8, BlockPos $$9, BlockEntity blockEntity) {
        MoreBabiesConfig.BabySwSmRsEntry blazeEntry = (MoreBabiesConfig.BabySwSmRsEntry) MoreBabiesConfig.BABIES.get("blaze");
        if (!blazeEntry.getReplaceSpawners().get() || !(randomSource.nextFloat() < blazeEntry.getSpawnWeight().get())) {
            return;
        }

        ((SpawnerBlockEntity)blockEntity).getSpawner().setEntityId(MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.BLAZE));
    }

}

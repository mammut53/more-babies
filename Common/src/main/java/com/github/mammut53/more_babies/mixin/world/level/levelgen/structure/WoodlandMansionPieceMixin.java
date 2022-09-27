package com.github.mammut53.more_babies.mixin.world.level.levelgen.structure;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.config.MoreBabiesConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.structures.WoodlandMansionPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WoodlandMansionPieces.WoodlandMansionPiece.class)
public abstract class WoodlandMansionPieceMixin {

    @Redirect(
            method = "handleDataMarker(Ljava/lang/String;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;",
                    ordinal = 0
            )
    )
    private Entity createEvoker(EntityType<Entity> entityType, Level level) {
        MoreBabiesConfig.BabyScSmEntry evokerEntry = (MoreBabiesConfig.BabyScSmEntry) MoreBabiesConfig.BABIES.get("evoker");
        if (!(level.random.nextFloat() < evokerEntry.getSpawnChance().get())) {
            return entityType.create(level);
        }

        return create(entityType, level);
    }

    @Redirect(
            method = "handleDataMarker(Ljava/lang/String;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;",
                    ordinal = 1
            )
    )
    private Entity createVindicator(EntityType<Entity> entityType, Level level) {
        MoreBabiesConfig.BabyScSmEntry vindicatorEntry = (MoreBabiesConfig.BabyScSmEntry) MoreBabiesConfig.BABIES.get("vindicator");
        if (!(level.random.nextFloat() < vindicatorEntry.getSpawnChance().get())) {
            return entityType.create(level);
        }

        return create(entityType, level);
    }

    private Entity create(EntityType<Entity> entityType, Level level) {
        EntityType<? extends Mob> babyType = MoreBabiesCommon.PARENT_BABY_RELATION.get(entityType);
        if (babyType == null) {
            return entityType.create(level);
        }

        return babyType.create(level);
    }

}

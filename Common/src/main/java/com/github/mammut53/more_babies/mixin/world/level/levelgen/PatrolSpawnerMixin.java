package com.github.mammut53.more_babies.mixin.world.level.levelgen;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.config.MoreBabiesConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.PatrolSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(PatrolSpawner.class)
public abstract class PatrolSpawnerMixin {

    @Redirect(
            method = "spawnPatrolMember",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;"
            )
    )
    private Entity createPillager(EntityType<Entity> entityType, Level level) {
        if (!EntityType.PILLAGER.equals(entityType)) {
            return entityType.create(level);
        }

        MoreBabiesConfig.BabySpawnChance spawnWeightEntry = (MoreBabiesConfig.BabySpawnChance) MoreBabiesConfig.BABIES.get("pillager");
        if (!(level.getRandom().nextFloat() < spawnWeightEntry.getSpawnChance().get())) {
            return entityType.create(level);
        }

        EntityType<? extends Mob> babyType = MoreBabiesCommon.PARENT_BABY_RELATION.get(entityType);
        return Objects.requireNonNullElse(babyType, entityType).create(level);
    }

}

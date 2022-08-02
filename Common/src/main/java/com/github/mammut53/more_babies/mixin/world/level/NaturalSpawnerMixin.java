package com.github.mammut53.more_babies.mixin.world.level;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.MoreBabiesConstants;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(NaturalSpawner.class)
public abstract class NaturalSpawnerMixin {

    @Redirect(
            method = "spawnCategoryForPosition(Lnet/minecraft/world/entity/MobCategory;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/NaturalSpawner$SpawnPredicate;Lnet/minecraft/world/level/NaturalSpawner$AfterSpawnCallback;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Mob;getMaxSpawnClusterSize()I"
            )
    )
    private static int getMaxSpawnClusterSize(Mob mob) {
        if (!(mob.level instanceof ServerLevel serverLevel && (serverLevel.getRandom().nextFloat() < MoreBabiesConstants.BABY_SPAWN_CHANCE))) return mob.getMaxSpawnClusterSize();

        EntityType<? extends Mob> babyType = MoreBabiesCommon.PARENT_BABY_RELATION.get(mob.getType());
        if (babyType == null) return mob.getMaxSpawnClusterSize();
        Mob babyMob;
        if (mob instanceof AgeableMob ageableMob) {
            babyMob = ageableMob.getBreedOffspring((ServerLevel) mob.level, ageableMob);
        } else {
            babyMob = babyType.create(mob.level);
        }
        if (babyMob == null) return mob.getMaxSpawnClusterSize();

        babyMob.moveTo(mob.getX(), mob.getY(), mob.getZ(), Mth.wrapDegrees(mob.level.random.nextFloat() * 360.0F), 0.0F);
        ((ServerLevel) mob.level).addFreshEntityWithPassengers(babyMob);
        babyMob.yHeadRot = babyMob.getYRot();
        babyMob.yBodyRot = babyMob.getYRot();
        babyMob.finalizeSpawn((ServerLevel) mob.level, mob.level.getCurrentDifficultyAt(babyMob.blockPosition()), MobSpawnType.NATURAL, null, null);
        mob.discard();

        return mob.getMaxSpawnClusterSize();
    }

    @Redirect(
            method = "spawnMobsForChunkGeneration",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;"
            )
    )
    private static Entity create(EntityType<Entity> entityType, Level level) {
        if (!(level instanceof ServerLevel serverLevel && (serverLevel.getRandom().nextFloat() < MoreBabiesConstants.BABY_SPAWN_CHANCE))) return entityType.create(level);

        EntityType<? extends Mob> babyType = MoreBabiesCommon.PARENT_BABY_RELATION.get(entityType);
        if (babyType == null) {
            return entityType.create(level);
        }

        return babyType.create(level);
    }

}

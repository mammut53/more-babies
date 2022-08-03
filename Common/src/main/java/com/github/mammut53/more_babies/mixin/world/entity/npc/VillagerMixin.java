package com.github.mammut53.more_babies.mixin.world.entity.npc;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.MoreBabiesConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(Villager.class)
public abstract class VillagerMixin {

    @Redirect(
            method = "thunderHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;"
            )
    )
    private Entity create(EntityType<Entity> entityType, Level level) {
        if (!((Villager) (Object)this).isBaby()) {
            return entityType.create(level);
        }

        return MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.WITCH).create(level);
    }

    @Redirect(
            method = "spawnGolemIfNeeded",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/SpawnUtil;trySpawnMob(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;IIILnet/minecraft/util/SpawnUtil$Strategy;)Ljava/util/Optional;"
            )
    )
    private <T extends Mob> Optional<T> trySpawnMob(EntityType<T> entityType, MobSpawnType mobSpawnType, ServerLevel serverLevel, BlockPos blockPos, int i, int j, int k, SpawnUtil.Strategy strategy) {
        if (!(serverLevel.random.nextFloat() < MoreBabiesConstants.BABY_SPAWN_CHANCE)) {
            return SpawnUtil.trySpawnMob(entityType, mobSpawnType, serverLevel, blockPos, i, j, k, strategy);
        }

        EntityType<T> babyType = (EntityType<T>) MoreBabiesCommon.PARENT_BABY_RELATION.get(entityType);
        if (babyType == null) {
            return SpawnUtil.trySpawnMob(entityType, mobSpawnType, serverLevel, blockPos, i, j, k, strategy);
        }

        return SpawnUtil.trySpawnMob(babyType, mobSpawnType, serverLevel, blockPos, i, j, k, strategy);
    }
}

package com.github.mammut53.more_babies.mixin.world.entity.npc;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.config.MoreBabiesConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.npc.WanderingTraderSpawner;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(WanderingTraderSpawner.class)
public abstract class WanderingTraderSpawnerMixin {

    @Redirect(
            method = "spawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityType;spawn(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/network/chat/Component;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/MobSpawnType;ZZ)Lnet/minecraft/world/entity/Entity;"
            )
    )
    private Entity spawnWanderingTrader(EntityType<Entity> entityType, ServerLevel serverLevel, CompoundTag compoundTag, Component component, Player player, BlockPos blockPos, MobSpawnType mobSpawnType, boolean bl, boolean bl2) {
        if (!EntityType.WANDERING_TRADER.equals(entityType)) {
            return entityType.spawn(serverLevel, null, null, null, blockPos, MobSpawnType.EVENT, false, false);
        }

        MoreBabiesConfig.BabySpawnWeight spawnWeightEntry = (MoreBabiesConfig.BabySpawnWeight) MoreBabiesConfig.BABIES.get("wandering_trader");
        if (!(serverLevel.random.nextFloat() < spawnWeightEntry.getSpawnWeight().get())) {
            return entityType.spawn(serverLevel, null, null, null, blockPos, MobSpawnType.EVENT, false, false);
        }

        EntityType<? extends Mob> babyType = MoreBabiesCommon.PARENT_BABY_RELATION.get(entityType);
        return Objects.requireNonNullElse(babyType, entityType).spawn(serverLevel, null, null, null, blockPos, MobSpawnType.EVENT, false, false);
    }

}

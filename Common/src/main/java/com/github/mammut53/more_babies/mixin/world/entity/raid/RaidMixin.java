package com.github.mammut53.more_babies.mixin.world.entity.raid;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.config.MoreBabiesConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(Raid.class)
public abstract class RaidMixin {

    @Redirect(
            method = "spawnGroup",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;"
            )
    )
    private Entity create(EntityType<Entity> entityType, Level level) {
        if (!EntityType.PILLAGER.equals(entityType) && !EntityType.EVOKER.equals(entityType) && !EntityType.VINDICATOR.equals(entityType) && !EntityType.RAVAGER.equals(entityType)) {
            return entityType.create(level);
        }

        MoreBabiesConfig.BabySpawnWeight spawnWeightEntry = (MoreBabiesConfig.BabySpawnWeight) MoreBabiesConfig.BABIES.get(entityType.toShortString());
        if (!(level.getRandom().nextFloat() < spawnWeightEntry.getSpawnWeight().get())) {
            return entityType.create(level);
        }

        EntityType<? extends Mob> babyType = MoreBabiesCommon.PARENT_BABY_RELATION.get(entityType);
        return Objects.requireNonNullElse(babyType, entityType).create(level);
    }

    @Redirect(
            method = "spawnGroup",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/raid/Raid;joinRaid(ILnet/minecraft/world/entity/raid/Raider;Lnet/minecraft/core/BlockPos;Z)V"
            )
    )
    private void joinRaid(Raid raid, int i, Raider raider, BlockPos blockPos, boolean bl) {
        raid.joinRaid(i, raider, blockPos, false);

        if (raider.getType() == MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.RAVAGER)) {
            Raider raiderRider = null;
            if (i == ((Raid) (Object)this).getNumGroups(Difficulty.NORMAL)) {
                raiderRider = (Raider) MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.PILLAGER).create(((Raid) (Object) this).getLevel());
            } else if (i >= ((Raid) (Object)this).getNumGroups(Difficulty.HARD)) {
                raiderRider = (Raider) MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.VINDICATOR).create(((Raid) (Object) this).getLevel());
            }

            if (raiderRider != null) {
                ((Raid) (Object)this).joinRaid(i, raiderRider, blockPos, false);
                raiderRider.moveTo(blockPos, 0.0F, 0.0F);
                raiderRider.startRiding(raider);
            }
        }
    }

}

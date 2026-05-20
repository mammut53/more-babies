package io.github.mammut53.more_babies.mixin.world.entity.npc.wanderingtrader;

import io.github.mammut53.more_babies.config.MoreBabiesConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.npc.wanderingtrader.WanderingTrader;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WanderingTrader.class)
public abstract class WanderingTraderMixin extends AbstractVillager implements Consumable.OverrideConsumeSound {

    @Unique
    private static final EntityDimensions more_babies$BABY_DIMENSIONS = EntityDimensions
            .scalable(0.49F, 0.99F)
            .withEyeHeight(0.63F);

    protected WanderingTraderMixin(final EntityType<? extends AbstractVillager> type, final Level level) {
        super(type, level);
    }

    @ModifyArg(
            method = "readAdditionalSaveData",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/npc/wanderingtrader/WanderingTrader;setAge(I)V"
            )
    )
    private int injectReadAdditionalSaveDataSetBaby(final int input) {
        return this.getAge();
    }

    @Override
    public @NonNull EntityDimensions getDefaultDimensions(final @NonNull Pose pose) {
        return this.isBaby() ? more_babies$BABY_DIMENSIONS : super.getDefaultDimensions(pose);
    }

    @Override
    public AgeableMob getBreedOffspring(final @NonNull ServerLevel level, final @NonNull AgeableMob partner) {
        return EntityType.WANDERING_TRADER.create(level, EntitySpawnReason.BREEDING);
    }

    @Override
    public SpawnGroupData finalizeSpawn(final @NonNull ServerLevelAccessor level, final @NonNull DifficultyInstance difficulty, final @NonNull EntitySpawnReason spawnReason, @Nullable final SpawnGroupData spawnGroupData) {
        SpawnGroupData groupData = spawnGroupData;
        if (groupData == null) {
            final AgeableMobGroupData ageableMobGroupData = new AgeableMobGroupData(MoreBabiesConfig.wanderingTraderBabySpawnChance);
            ageableMobGroupData.increaseGroupSizeByOne();
            groupData = ageableMobGroupData;
        }

        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

}


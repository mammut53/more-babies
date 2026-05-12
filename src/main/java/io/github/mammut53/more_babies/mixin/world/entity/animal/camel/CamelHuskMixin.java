package io.github.mammut53.more_babies.mixin.world.entity.animal.camel;

import io.github.mammut53.more_babies.config.MoreBabiesConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.camel.CamelHusk;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CamelHusk.class)
public abstract class CamelHuskMixin extends Camel {

    protected CamelHuskMixin(final EntityType<? extends Camel> type, final Level level) {
        super(type, level);
    }

    @Inject(
            method = "isBaby",
            at = @At("RETURN"),
            cancellable = true
    )
    private void injectIsBaby(final CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(super.isBaby());
    }

    @Override
    public Camel getBreedOffspring(final @NonNull ServerLevel level, final @NonNull AgeableMob partner) {
        return EntityType.CAMEL_HUSK.create(level, EntitySpawnReason.BREEDING);
    }

    @Override
    @SuppressWarnings("java:S2637")
    public @NonNull SpawnGroupData finalizeSpawn(final @NonNull ServerLevelAccessor level, final @NonNull DifficultyInstance difficulty, final @NonNull EntitySpawnReason spawnReason, @Nullable final SpawnGroupData spawnGroupData) {
        SpawnGroupData groupData = spawnGroupData;
        if (groupData == null) {
            final AgeableMobGroupData ageableMobGroupData = new AgeableMobGroupData(MoreBabiesConfig.camelHuskBabySpawnChance);
            ageableMobGroupData.increaseGroupSizeByOne();
            groupData = ageableMobGroupData;
        }

        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

}
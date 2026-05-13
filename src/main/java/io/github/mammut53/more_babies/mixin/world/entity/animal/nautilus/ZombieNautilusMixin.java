package io.github.mammut53.more_babies.mixin.world.entity.animal.nautilus;

import io.github.mammut53.more_babies.config.MoreBabiesConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.nautilus.AbstractNautilus;
import net.minecraft.world.entity.animal.nautilus.ZombieNautilus;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieNautilus.class)
public abstract class ZombieNautilusMixin extends AbstractNautilus {

    protected ZombieNautilusMixin(final EntityType<? extends AbstractNautilus> type, final Level level) {
        super(type, level);
    }

    @Override
    public boolean isBaby() {
        return super.isBaby();
    }

    @Override
    public void setBaby(final boolean baby) {
        super.setBaby(baby);
    }

    @Override
    public ZombieNautilus getBreedOffspring(final @NonNull ServerLevel level, final @NonNull AgeableMob partner) {
        final ZombieNautilus baby = EntityType.ZOMBIE_NAUTILUS.create(level, EntitySpawnReason.BREEDING);
        if (baby != null && this.isTame()) {
            baby.setOwnerReference(this.getOwnerReference());
            baby.setTame(true, true);
        }

        return baby;
    }

    @Inject(
            method = "addAdditionalSaveData",
            at = @At("TAIL")
    )
    private void injectAddAdditionalSaveData(final ValueOutput output, final CallbackInfo ci) {
        output.putBoolean("IsBaby", this.isBaby());
    }

    @Inject(
            method = "readAdditionalSaveData",
            at = @At("TAIL")
    )
    private void injectReadAdditionalSaveData(final ValueInput input, final CallbackInfo ci) {
        this.setBaby(input.getBooleanOr("IsBaby", false));
    }

    @ModifyVariable(
            method = "finalizeSpawn",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true
    )
    private SpawnGroupData modifyFinalizeSpawnGroupData(final SpawnGroupData groupData, final ServerLevelAccessor level, final DifficultyInstance difficulty, final EntitySpawnReason spawnReason) {
        SpawnGroupData spawnGroupData = groupData;
        if (spawnGroupData == null) {
            final AgeableMobGroupData ageableMobGroupData = new AgeableMobGroupData(MoreBabiesConfig.zombieNautilusBabySpawnChance);
            // The if-statement below is required to spawn a zombified nautilus jockey as a baby zombified nautilus,
            // while still forcing spawn eggs to only spawn an adult zombified nautilus when clicking on a block.
            // This spawn egg behavior follows the one of other ageable mobs.
            if (spawnReason == EntitySpawnReason.JOCKEY || spawnReason == EntitySpawnReason.NATURAL || spawnReason == EntitySpawnReason.STRUCTURE) {
                ageableMobGroupData.increaseGroupSizeByOne();
            }
            spawnGroupData = ageableMobGroupData;
        }

        return spawnGroupData;
    }

}

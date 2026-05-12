package io.github.mammut53.more_babies.mixin.world.entity.monster.warden;

import io.github.mammut53.more_babies.config.MoreBabiesConfig;
import io.github.mammut53.more_babies.world.entity.monster.warden.WardenGroupData;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Warden.class)
public abstract class WardenMixin extends Monster implements VibrationSystem {
    @Unique
    private static final Identifier more_babies$SPEED_MODIFIER_BABY_ID = Identifier.withDefaultNamespace("baby");
    @Unique
    private static final EntityDataAccessor<Boolean> more_babies$DATA_BABY_ID = SynchedEntityData.defineId(WardenMixin.class, EntityDataSerializers.BOOLEAN);

    @Unique
    private static final EntityDimensions more_babies$BABY_DIMENSIONS = EntityDimensions.scalable(0.6F, 1.6F);

    protected WardenMixin(final EntityType<? extends Monster> type, final Level level) {
        super(type, level);
    }

    @Inject(
            method = "defineSynchedData",
            at = @At("TAIL")
    )
    private void injectDefineSynchedData(final SynchedEntityData.Builder entityData, final CallbackInfo ci) {
        entityData.define(more_babies$DATA_BABY_ID, false);
    }

    @Override
    public boolean isBaby() {
        return this.getEntityData().get(more_babies$DATA_BABY_ID);
    }

    @Override
    public void setBaby(final boolean baby) {
        this.getEntityData().set(more_babies$DATA_BABY_ID, baby);
        if (!this.level().isClientSide()) {
            final AttributeInstance speed = this.getAttribute(Attributes.MOVEMENT_SPEED);
            if (speed != null) {
                speed.removeModifier(more_babies$SPEED_MODIFIER_BABY_ID);
                if (baby) {
                    final AttributeModifier speedModifier = new AttributeModifier(
                            more_babies$SPEED_MODIFIER_BABY_ID,
                            MoreBabiesConfig.wardenBabySpeedModifier,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    );
                    speed.addTransientModifier(speedModifier);
                }
            }
        }
    }

    @Inject(
            method = "onSyncedDataUpdated",
            at = @At("HEAD")
    )
    public void onSyncedDataUpdated(final EntityDataAccessor<?> accessor, final CallbackInfo ci) {
        if (more_babies$DATA_BABY_ID.equals(accessor)) {
            this.refreshDimensions();
        }
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

    @Override
    public @NonNull EntityDimensions getDefaultDimensions(final @NonNull Pose pose) {
        return this.isBaby() ? more_babies$BABY_DIMENSIONS : super.getDefaultDimensions(pose);
    }

    // TODO finalizeSpawn make mixin safer

    @Inject(
            method = "finalizeSpawn",
            at = @At("RETURN"),
            cancellable = true
    )
    private void modifyFinalizeSpawnSpawnGroupData(final ServerLevelAccessor level, final DifficultyInstance difficulty, final EntitySpawnReason spawnReason, final SpawnGroupData spawnGroupData, final CallbackInfoReturnable<SpawnGroupData> cir) {
        final RandomSource random = level.getRandom();

        SpawnGroupData groupData = cir.getReturnValue();
        if (groupData == null) {
            groupData = new WardenGroupData(more_babies$getSpawnAsBabyOdds(random));
        }

        if (groupData instanceof WardenGroupData(boolean isBaby) && isBaby) {
            this.setBaby(true);
        }

        cir.setReturnValue(groupData);
    }

    @Unique
    private static boolean more_babies$getSpawnAsBabyOdds(final RandomSource random) {
        return random.nextFloat() < MoreBabiesConfig.wardenBabySpawnChance;
    }

}
package io.github.mammut53.more_babies.mixin.world.entity.monster.skeleton;

import io.github.mammut53.more_babies.config.MoreBabiesConfig;
import io.github.mammut53.more_babies.world.entity.BabySpawnGroupData;
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
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.monster.skeleton.Parched;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Parched.class)
public abstract class ParchedMixin extends AbstractSkeleton {
    @Unique
    private static final Identifier more_babies$SPEED_MODIFIER_BABY_ID = Identifier.withDefaultNamespace("baby");
    @Unique
    private static final EntityDataAccessor<Boolean> more_babies$DATA_BABY_ID = SynchedEntityData.defineId(ParchedMixin.class, EntityDataSerializers.BOOLEAN);

    @Unique
    private static final EntityDimensions more_babies$BABY_DIMENSIONS = EntityDimensions.scalable(0.49F, 0.99F)
            .withEyeHeight(0.775F)
            .withAttachments(EntityAttachments.builder().attach(EntityAttachment.VEHICLE, 0.0F, 0.1875F, 0.0F));

    protected ParchedMixin(final EntityType<? extends AbstractSkeleton> type, final Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(final SynchedEntityData.@NonNull Builder entityData) {
        super.defineSynchedData(entityData);
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
                            MoreBabiesConfig.parchedBabySpeedModifier,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    );
                    speed.addTransientModifier(speedModifier);
                }
            }
        }
    }

    @Override
    public void onSyncedDataUpdated(final @NonNull EntityDataAccessor<?> accessor) {
        if (more_babies$DATA_BABY_ID.equals(accessor)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(accessor);
    }

    @Override
    protected void addAdditionalSaveData(final @NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("IsBaby", this.isBaby());
    }

    @Override
    protected void readAdditionalSaveData(final @NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setBaby(input.getBooleanOr("IsBaby", false));
    }

    @Override
    public @NonNull EntityDimensions getDefaultDimensions(final @NonNull Pose pose) {
        return this.isBaby() ? more_babies$BABY_DIMENSIONS : super.getDefaultDimensions(pose);
    }

    @Override
    public SpawnGroupData finalizeSpawn(final @NonNull ServerLevelAccessor level, final @NonNull DifficultyInstance difficulty, final @NonNull EntitySpawnReason spawnReason, @Nullable final SpawnGroupData spawnGroupData) {
        final RandomSource random = level.getRandom();

        SpawnGroupData groupData = super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);
        if (groupData == null) {
            groupData = new BabySpawnGroupData(more_babies$getSpawnAsBabyOdds(random));
        }

        if (groupData instanceof BabySpawnGroupData(boolean isBaby) && isBaby) {
            this.setBaby(true);
        }

        return groupData;
    }

    @Unique
    private static boolean more_babies$getSpawnAsBabyOdds(final RandomSource random) {
        return random.nextFloat() < MoreBabiesConfig.parchedBabySpawnChance;
    }

}

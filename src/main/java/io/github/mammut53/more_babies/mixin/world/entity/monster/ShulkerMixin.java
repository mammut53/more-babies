package io.github.mammut53.more_babies.mixin.world.entity.monster;

import io.github.mammut53.more_babies.config.MoreBabiesConfig;
import io.github.mammut53.more_babies.world.entity.monster.shulker.ShulkerGroupData;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.golem.AbstractGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Shulker.class)
public abstract class ShulkerMixin extends AbstractGolem implements Enemy {
    @Unique
    private static final EntityDataAccessor<Boolean> more_babies$DATA_BABY_ID = SynchedEntityData.defineId(ShulkerMixin.class, EntityDataSerializers.BOOLEAN);

    @Shadow
    public abstract Direction getAttachFace();

    protected ShulkerMixin(final EntityType<? extends AbstractGolem> type, final Level level) {
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
    }

    @Inject(
            method = "onSyncedDataUpdated",
            at = @At("HEAD")
    )
    public void onSyncedDataUpdated(final EntityDataAccessor<?> accessor, final CallbackInfo ci) {
        if (more_babies$DATA_BABY_ID.equals(accessor)) {
            this.setBoundingBox(this.makeBoundingBox());
        }

        super.onSyncedDataUpdated(accessor);
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
    public float getScale() {
        return this.isBaby() ? super.getScale() * 0.5F : super.getScale();
    }

    @ModifyVariable(
            method = "getProgressDeltaAabb",
            at = @At("STORE"),
            name = "boundsAtBottomCenter"
    )
    private static AABB modifyGetProgressDeltaAabbBoundsAtBottomCenter(final AABB boundsAtBottomCenter, final float size, final Direction direction, final float progressFrom, final float progressTo, final Vec3 position) {
        final AABB bounds = new AABB(-size * 0.5, -size * 0.5, -size * 0.5, size * 0.5, size * 0.5, size * 0.5);
        return switch (direction) {
            case DOWN -> bounds.move(0, -size * 0.5 + 1, 0);
            case UP -> bounds.move(0, size * 0.5, 0);
            case NORTH -> bounds.move(0, 0.5, -size * 0.5 + 0.5);
            case SOUTH -> bounds.move(0, 0.5, size * 0.5 - 0.5);
            case WEST -> bounds.move(-size * 0.5 + 0.5, 0.5, 0);
            case EAST -> bounds.move(size * 0.5 - 0.5, 0.5, 0);
        };
    }

    @Inject(
            method = "getRenderPosition",
            at = @At("RETURN"),
            cancellable = true
    )
    private void injectGetRenderPosition(final float a, final CallbackInfoReturnable<Vec3> cir) {
        Vec3 original = cir.getReturnValue();
        if (original == null) {
            original = Vec3.ZERO;
        }

        final float scale = this.getScale();
        final float size = this.isBaby() ? scale * 2.0F : scale;

        final Direction direction = this.getAttachFace().getOpposite();
        final Vec3 offset = switch (direction) {
            case DOWN -> original.add(0, -size + 1, 0);
            case UP -> original.add(0, 0, 0);
            case NORTH -> original.add(0, -size * 0.5 + 0.5, -size * 0.5 + 0.5);
            case SOUTH -> original.add(0, -size * 0.5 + 0.5, size * 0.5 - 0.5);
            case WEST -> original.add(-size * 0.5 + 0.5, -size * 0.5 + 0.5, 0);
            case EAST -> original.add(size * 0.5 - 0.5, -size * 0.5 + 0.5, 0);
        };

         cir.setReturnValue(offset);
    }

    // TODO finalizeSpawn make mixin safer

    @Override
    public SpawnGroupData finalizeSpawn(final @NonNull ServerLevelAccessor level, final @NonNull DifficultyInstance difficulty, final @NonNull EntitySpawnReason spawnReason, @Nullable final SpawnGroupData spawnGroupData) {
        final RandomSource random = level.getRandom();

        SpawnGroupData groupData = super.finalizeSpawn(level, difficulty, spawnReason, spawnGroupData);
        if (groupData == null) {
            groupData = new ShulkerGroupData(more_babies$getSpawnAsBabyOdds(random));
        }

        if (groupData instanceof ShulkerGroupData(boolean isBaby) && isBaby) {
            this.setBaby(true);
        }

        return groupData;
    }

    @Unique
    private static boolean more_babies$getSpawnAsBabyOdds(final RandomSource random) {
        return random.nextFloat() < MoreBabiesConfig.shulkerBabySpawnChance;
    }

}
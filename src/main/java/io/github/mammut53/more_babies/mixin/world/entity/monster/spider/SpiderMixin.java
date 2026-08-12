package io.github.mammut53.more_babies.mixin.world.entity.monster.spider;

import io.github.mammut53.more_babies.config.MoreBabiesConfig;
import io.github.mammut53.more_babies.world.entity.spider.BabySpiderEffectsGroupData;
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
import net.minecraft.world.entity.monster.illager.AbstractIllager;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Spider.class)
public abstract class SpiderMixin extends Monster {

    @Unique
    private static final Identifier more_babies$SPEED_MODIFIER_BABY_ID = Identifier.withDefaultNamespace("baby");
    @Unique
    private static final EntityDataAccessor<Boolean> more_babies$DATA_BABY_ID = SynchedEntityData.defineId(SpiderMixin.class, EntityDataSerializers.BOOLEAN);

    @Unique
    private static final EntityDimensions more_babies$BABY_DIMENSIONS = EntityDimensions
            .scalable(0.7F, 0.45F)
            .withEyeHeight(0.35F);

    protected SpiderMixin(final EntityType<? extends AbstractIllager> type, final Level level) {
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
                    final float amount = (((Spider)(Object) this) instanceof CaveSpider)
                            ? MoreBabiesConfig.caveSpiderBabySpeedModifier : MoreBabiesConfig.spiderBabySpeedModifier;
                    final AttributeModifier speedModifier = new AttributeModifier(
                            more_babies$SPEED_MODIFIER_BABY_ID,
                            amount,
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

    @ModifyVariable(
            method = "finalizeSpawn",
            at = @At("STORE"),
            name = "groupData",
            ordinal = 0,
            argsOnly = true
    )
    public SpawnGroupData modifyFinalizeSpawnGroupData(final SpawnGroupData spawnGroupData, final ServerLevelAccessor level, final DifficultyInstance difficulty, final EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        if (spawnGroupData instanceof Spider.SpiderEffectsGroupData spiderEffectsGroupData) {
            final RandomSource random = level.getRandom();
            final BabySpiderEffectsGroupData babyGroupData = new BabySpiderEffectsGroupData(more_babies$getSpawnAsBabyOdds(random));
            babyGroupData.effect = spiderEffectsGroupData.effect;
            return babyGroupData;
        }
        return spawnGroupData;
    }

    @Inject(
            method = "finalizeSpawn",
            at = @At("RETURN")
    )
    public void injectFinalizeSpawnReturn(final ServerLevelAccessor level, final DifficultyInstance difficulty, final EntitySpawnReason spawnReason, final SpawnGroupData groupData, final CallbackInfoReturnable<SpawnGroupData> cir) {
        final SpawnGroupData spawnGroupData = cir.getReturnValue();
        if (spawnGroupData instanceof BabySpiderEffectsGroupData babySpiderEffectsGroupData && babySpiderEffectsGroupData.isBaby()) {
            this.setBaby(true);
        }
    }

    @Unique
    private static boolean more_babies$getSpawnAsBabyOdds(final RandomSource random) {
        return random.nextFloat() < MoreBabiesConfig.spiderBabySpawnChance;
    }

}


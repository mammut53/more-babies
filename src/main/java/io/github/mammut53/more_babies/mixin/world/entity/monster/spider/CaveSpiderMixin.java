package io.github.mammut53.more_babies.mixin.world.entity.monster.spider;

import io.github.mammut53.more_babies.config.MoreBabiesConfig;
import io.github.mammut53.more_babies.world.entity.BabySpawnGroupData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(CaveSpider.class)
public abstract class CaveSpiderMixin extends Spider {

    @Unique
    private static final EntityDimensions more_babies$BABY_DIMENSIONS = EntityDimensions
            .scalable(0.5F, 0.3F)
            .withEyeHeight(0.25F);

    protected CaveSpiderMixin(final EntityType<? extends Spider> type, final Level level) {
        super(type, level);
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
        return random.nextFloat() < MoreBabiesConfig.caveSpiderBabySpawnChance;
    }

}


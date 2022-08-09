package com.github.mammut53.more_babies.mixin.world.level;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.MoreBabiesConstants;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;


@Mixin(NaturalSpawner.class)
public abstract class NaturalSpawnerMixin {

    @Redirect(
            method = "spawnMobsForChunkGeneration",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/random/WeightedRandomList;getRandom(Lnet/minecraft/util/RandomSource;)Ljava/util/Optional;"
            )
    )
    private static Optional<MobSpawnSettings.SpawnerData> getRandom(WeightedRandomList<MobSpawnSettings.SpawnerData> weightedRandomList, RandomSource randomSource) {
        Optional<MobSpawnSettings.SpawnerData> optional = weightedRandomList.getRandom(randomSource);
        if (optional.isEmpty()) return optional;

        if (!(randomSource.nextFloat() < MoreBabiesConstants.BABY_SPAWN_CHANCE)) return optional;

        MobSpawnSettings.SpawnerData spawnerData = optional.get();
        EntityType<? extends Mob> babyType = MoreBabiesCommon.PARENT_BABY_RELATION.get(spawnerData.type);
        if (babyType == null) return optional;

        return Optional.of(new MobSpawnSettings.SpawnerData(babyType, spawnerData.getWeight(), spawnerData.minCount, spawnerData.maxCount));
    }

}

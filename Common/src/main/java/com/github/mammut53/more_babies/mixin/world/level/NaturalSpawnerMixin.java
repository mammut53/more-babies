package com.github.mammut53.more_babies.mixin.world.level;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.MoreBabiesConstants;
import com.github.mammut53.more_babies.config.MoreBabiesConfig;
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

        MobSpawnSettings.SpawnerData spawnerData = optional.get();

        EntityType<?> mobType = spawnerData.type;
        if (!mobType.getDescriptionId().startsWith("entity.minecraft.")) return optional;
        if (!MoreBabiesConstants.BABY_IDS.contains((mobType.toShortString()))) return optional;

        MoreBabiesConfig.BabyEntry babyEntry = MoreBabiesConfig.BABIES.get(mobType.toShortString());
        if (!(babyEntry instanceof MoreBabiesConfig.BabySpawnWeight)) return optional;

        double babySpawnWeight = ((MoreBabiesConfig.BabySpawnWeight) babyEntry).getSpawnWeight().get();
        if (!(randomSource.nextFloat() < babySpawnWeight)) return optional;

        EntityType<? extends Mob> babyType = MoreBabiesCommon.PARENT_BABY_RELATION.get(mobType);
        if (babyType == null) return optional;

        return Optional.of(new MobSpawnSettings.SpawnerData(babyType, spawnerData.getWeight(), spawnerData.minCount, spawnerData.maxCount));
    }

}

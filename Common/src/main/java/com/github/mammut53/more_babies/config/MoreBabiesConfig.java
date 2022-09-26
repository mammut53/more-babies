package com.github.mammut53.more_babies.config;

import com.github.mammut53.more_babies.MoreBabiesConstants;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;

public class MoreBabiesConfig {

    public static final ForgeConfigSpec SPEC;
    public static final MoreBabiesConfig CONFIG;

    static {
        final Pair<MoreBabiesConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(MoreBabiesConfig::new);
        SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

    public static HashMap<String, BabyEntry> BABIES;

    private MoreBabiesConfig(ForgeConfigSpec.Builder builder) {
        BABIES = new HashMap<>();

        builder.push("general");

        builder.pop();

        builder.push("babies");
        for (String baby : MoreBabiesConstants.BABY_IDS) {
            builder.push(baby);

            BabyEntry babyEntry = switch (baby) {
                case "blaze":
                case "cave_spider":
                    yield new BabySwSmRsEntry(builder);

                case "creeper":
                    yield new BabySwSmErEntry(builder);
                default:
                    yield new BabySwSmEntry(builder);
            };
            BABIES.put(baby, babyEntry);

            builder.pop();
        }
    }

    public interface BabyEntry {}

    public interface BabySpawnWeight extends BabyEntry {
        ForgeConfigSpec.DoubleValue getSpawnWeight();
    }

    public interface BabySpeedModifier extends BabyEntry {
        ForgeConfigSpec.DoubleValue getSpeedModifier();
    }

    public interface BabyExplosionRadius extends BabyEntry {
        ForgeConfigSpec.IntValue getExplosionRadius();
    }

    public interface BabyReplaceSpawners extends BabyEntry {
        ForgeConfigSpec.BooleanValue getReplaceSpawners();
    }

    public static class BabySwSmErEntry extends BabySwSmEntry implements BabyExplosionRadius {
        public final BabyExplosionRadiusEntry babyExplosionRadiusEntry;

        public BabySwSmErEntry(ForgeConfigSpec.Builder builder) {
            super(builder);
            babyExplosionRadiusEntry = new BabyExplosionRadiusEntry(builder);
        }

        @Override
        public ForgeConfigSpec.IntValue getExplosionRadius() {
            return babyExplosionRadiusEntry.getExplosionRadius();
        }
    }

    public static class BabySwSmRsEntry extends BabySwSmEntry implements BabyReplaceSpawners {

        public final BabyReplaceSpawnersEntry babyReplaceSpawnersEntry;

        public BabySwSmRsEntry(ForgeConfigSpec.Builder builder) {
            super(builder);
            babyReplaceSpawnersEntry = new BabyReplaceSpawnersEntry(builder);
        }

        @Override
        public ForgeConfigSpec.BooleanValue getReplaceSpawners() {
            return babyReplaceSpawnersEntry.getReplaceSpawners();
        }
    }

    public static class BabySpawnWeightEntry implements BabySpawnWeight {
        public final ForgeConfigSpec.DoubleValue spawnWeight;

        public BabySpawnWeightEntry(ForgeConfigSpec.Builder builder) {
            this.spawnWeight = builder
                    .comment("Percentage of mobs that will be replaced with babies")
                    .defineInRange("spawnWeight", 0.05, 0, 1);
        }

        @Override
        public ForgeConfigSpec.DoubleValue getSpawnWeight() {
            return spawnWeight;
        }
    }

    public static class BabySpeedModifierEntry implements BabySpeedModifier {
        public final ForgeConfigSpec.DoubleValue speedModifier;

        public BabySpeedModifierEntry(ForgeConfigSpec.Builder builder) {
            this.speedModifier = builder
                    .comment("Speed of baby compared to normal mob (is only set on initial spawn)")
                    .worldRestart()
                    .defineInRange("speedModifier", 1.5, 0, 2);
        }

        @Override
        public ForgeConfigSpec.DoubleValue getSpeedModifier() {
            return speedModifier;
        }
    }

    public static class BabyExplosionRadiusEntry implements BabyExplosionRadius {

        public final ForgeConfigSpec.IntValue explosionRadius;

        public BabyExplosionRadiusEntry(ForgeConfigSpec.Builder builder) {
            this.explosionRadius = builder
                    .comment("Explosion radius (reference values: creeper = 3, TNT = 4)")
                    .defineInRange("explosionRadius", 2, 0, 100);
        }

        @Override
        public ForgeConfigSpec.IntValue getExplosionRadius() {
            return explosionRadius;
        }
    }

    public static class BabyReplaceSpawnersEntry implements BabyReplaceSpawners {

        public final ForgeConfigSpec.BooleanValue replaceSpawners;

        public BabyReplaceSpawnersEntry(ForgeConfigSpec.Builder builder) {
            this.replaceSpawners = builder
                    .comment("If spawners in structures should be replaced with baby spawners based on the defined spawn weight")
                    .define("replaceSpawners", true);
        }

        @Override
        public ForgeConfigSpec.BooleanValue getReplaceSpawners() {
            return replaceSpawners;
        }
    }

    public static class BabySwSmEntry implements BabySpawnWeight, BabySpeedModifier {
        public final BabySpawnWeightEntry babySpawnWeight;
        public final BabySpeedModifierEntry babySpeedModifier;

        public BabySwSmEntry(ForgeConfigSpec.Builder builder) {
            this.babySpawnWeight = new BabySpawnWeightEntry(builder);
            this.babySpeedModifier = new BabySpeedModifierEntry(builder);
        }

        @Override
        public ForgeConfigSpec.DoubleValue getSpawnWeight() {
            return this.babySpawnWeight.getSpawnWeight();
        }

        @Override
        public ForgeConfigSpec.DoubleValue getSpeedModifier() {
            return this.babySpeedModifier.getSpeedModifier();
        }
    }

}

package com.github.mammut53.more_babies.registry;

import com.github.mammut53.more_babies.MoreBabiesConstants;
import com.github.mammut53.more_babies.world.entity.*;
import com.github.mammut53.more_babies.world.entity.boss.BabyEnderDragon;
import com.github.mammut53.more_babies.world.entity.boss.BabyWitherBoss;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;

public class MoreBabiesFabricRegistry {

    public static final BiMap<EntityType<? extends Mob>, EntityType<? extends Mob>> PARENT_BABY_RELATION = HashBiMap.create();

    public static final EntityType<BabyBat> BABY_BAT = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "bat"),
            FabricEntityTypeBuilder.create(MobCategory.AMBIENT, BabyBat::new).dimensions(EntityDimensions.fixed(0.25F, 0.45F)).trackRangeChunks(5).build()
    );

    public static final EntityType<BabyBlaze> BABY_BLAZE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "blaze"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyBlaze::new).fireImmune().dimensions(EntityDimensions.fixed(0.3F, 0.9F)).build()
    );

    public static final EntityType<BabyCaveSpider> BABY_CAVE_SPIDER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "cave_spider"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyCaveSpider::new).dimensions(EntityDimensions.fixed(0.35F, 0.25F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabyCreeper> BABY_CREEPER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "creeper"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyCreeper::new).dimensions(EntityDimensions.fixed(0.3F, 0.85F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabyDolphin> BABY_DOLPHIN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "dolphin"),
            FabricEntityTypeBuilder.create(MobCategory.WATER_CREATURE, BabyDolphin::new).dimensions(EntityDimensions.fixed(0.45F, 0.3F)).build()
    );

    public static final EntityType<BabyElderGuardian> BABY_ELDER_GUARDIAN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "elder_guardian"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyElderGuardian::new).dimensions(EntityDimensions.fixed(0.99875F, 0.99875F)).trackRangeChunks(10).build()
    );

    /*
    TODO ADD TO TAGS
    public static final EntityType<BabyEnderDragon> BABY_ENDER_DRAGON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "ender_dragon"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyEnderDragon::new).fireImmune().dimensions(EntityDimensions.fixed(8.0F, 4.0F)).trackRangeChunks(10).build()
    );*/

    public static final EntityType<BabyEnderMan> BABY_ENDERMAN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "enderman"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyEnderMan::new).dimensions(EntityDimensions.fixed(0.3F, 1.45F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabyEvoker> BABY_EVOKER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "evoker"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyEvoker::new).dimensions(EntityDimensions.fixed(0.3F, 0.975F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabyGhast> BABY_GHAST = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "ghast"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyGhast::new).fireImmune().dimensions(EntityDimensions.fixed(2.0F, 2.0F)).trackRangeChunks(10).build()
    );

    public static final EntityType<BabyGiant> BABY_GIANT = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "giant"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyGiant::new).dimensions(EntityDimensions.fixed(1.8F, 5.97F)).trackRangeChunks(10).build()
    );

    public static final EntityType<BabyGlowSquid> BABY_GLOW_SQUID = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "glow_squid"),
            FabricEntityTypeBuilder.create(MobCategory.UNDERGROUND_WATER_CREATURE, BabyGlowSquid::new).dimensions(EntityDimensions.fixed(0.4F, 0.4F)).trackRangeChunks(10).build()
    );

    public static final EntityType<BabyGuardian> BABY_GUARDIAN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "guardian"),
            FabricEntityTypeBuilder.create(MobCategory.WATER_CREATURE, BabyGuardian::new).dimensions(EntityDimensions.fixed(0.425F, 0.425F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabyIllusioner> BABY_ILLUSIONER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "illusioner"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyIllusioner::new).dimensions(EntityDimensions.fixed(0.3F, 0.975F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabyIronGolem> BABY_IRON_GOLEM = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "iron_golem"),
            FabricEntityTypeBuilder.create(MobCategory.MISC, BabyIronGolem::new).dimensions(EntityDimensions.fixed(0.7F, 1.35F)).trackRangeChunks(10).build()
    );

    public static final EntityType<BabyParrot> BABY_PARROT = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "parrot"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, BabyParrot::new).dimensions(EntityDimensions.fixed(0.25F, 0.45F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabyPiglinBrute> BABY_PIGLIN_BRUTE = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "piglin_brute"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyPiglinBrute::new).dimensions(EntityDimensions.fixed(0.3F, 0.975F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabyPillager> BABY_PILLAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "pillager"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyPillager::new).spawnableFarFromPlayer().dimensions(EntityDimensions.fixed(0.3F, 0.975F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabyRavager> BABY_RAVAGER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "ravager"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyRavager::new).dimensions(EntityDimensions.fixed(0.975F, 1.1F)).trackRangeChunks(10).build()
    );

    public static final EntityType<BabySalmon> BABY_SALMON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "salmon"),
            FabricEntityTypeBuilder.create(MobCategory.WATER_AMBIENT, BabySalmon::new).dimensions(EntityDimensions.fixed(0.35F, 0.2F)).trackRangeChunks(4).build()
    );

    public static final EntityType<BabyShulker> BABY_SHULKER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "shulker"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyShulker::new).fireImmune().spawnableFarFromPlayer().dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackRangeChunks(10).build()
    );

    public static final EntityType<BabySkeleton> BABY_SKELETON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "skeleton"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabySkeleton::new).dimensions(EntityDimensions.fixed(0.3F, 0.995F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabySnowGolem> BABY_SNOW_GOLEM = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "snow_golem"),
            FabricEntityTypeBuilder.create(MobCategory.MISC, BabySnowGolem::new).specificSpawnBlocks(Blocks.POWDER_SNOW).dimensions(EntityDimensions.fixed(0.35F, 0.95F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabySpider> BABY_SPIDER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "spider"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabySpider::new).dimensions(EntityDimensions.fixed(0.7F, 0.45F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabySquid> BABY_SQUID = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "squid"),
            FabricEntityTypeBuilder.create(MobCategory.WATER_CREATURE, BabySquid::new).dimensions(EntityDimensions.fixed(0.4F, 0.4F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabyStray> BABY_STRAY = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "stray"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyStray::new).dimensions(EntityDimensions.fixed(0.3F, 0.995F)).specificSpawnBlocks(Blocks.POWDER_SNOW)./*immuneTo(Blocks.POWDER_SNOW).*/trackRangeChunks(8).build()
    );

    public static final EntityType<BabyVindicator> BABY_VINDICATOR = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "vindicator"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyVindicator::new).dimensions(EntityDimensions.fixed(0.3F, 0.975F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabyWanderingTrader> BABY_WANDERING_TRADER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "wandering_trader"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, BabyWanderingTrader::new).dimensions(EntityDimensions.fixed(0.3F, 0.975F)).trackRangeChunks(10).build()
    );

    // TODO ADD TO TAGS
    /*public static final EntityType<BabyWarden> BABY_WARDEN = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "warden"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyWarden::new).dimensions(EntityDimensions.fixed(0.45F, 1.45F)).trackRangeChunks(16).fireImmune().build()
    );*/

    public static final EntityType<BabyWitch> BABY_WITCH = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "witch"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyWitch::new).dimensions(EntityDimensions.fixed(0.3F, 0.975F)).trackRangeChunks(8).build()
    );

    public static final EntityType<BabyWitherBoss> BABY_WITHER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "wither"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyWitherBoss::new).fireImmune().specificSpawnBlocks(Blocks.WITHER_ROSE)./*immuneTo(Blocks.WITHER_ROSE).*/dimensions(EntityDimensions.fixed(0.45F, 1.8F)).trackRangeChunks(10).build()
    );

    public static final EntityType<BabyWitherSkeleton> BABY_WITHER_SKELETON = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(MoreBabiesConstants.MOD_ID, "wither_skeleton"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, BabyWitherSkeleton::new).fireImmune().specificSpawnBlocks(Blocks.WITHER_ROSE)./*immuneTo(Blocks.WITHER_ROSE).*/dimensions(EntityDimensions.fixed(0.35F, 1.2F)).trackRangeChunks(8).build()
    );

    static {
        PARENT_BABY_RELATION.put(EntityType.BAT, BABY_BAT);
        PARENT_BABY_RELATION.put(EntityType.BLAZE, BABY_BLAZE);
        PARENT_BABY_RELATION.put(EntityType.CAVE_SPIDER, BABY_CAVE_SPIDER);
        PARENT_BABY_RELATION.put(EntityType.CREEPER, BABY_CREEPER);
        PARENT_BABY_RELATION.put(EntityType.DOLPHIN, BABY_DOLPHIN);
        PARENT_BABY_RELATION.put(EntityType.ELDER_GUARDIAN, BABY_ELDER_GUARDIAN);
        //PARENT_BABY_RELATION.put(EntityType.ENDER_DRAGON, BABY_ENDER_DRAGON);
        PARENT_BABY_RELATION.put(EntityType.ENDERMAN, BABY_ENDERMAN);
        PARENT_BABY_RELATION.put(EntityType.EVOKER, BABY_EVOKER);
        PARENT_BABY_RELATION.put(EntityType.GHAST, BABY_GHAST);
        PARENT_BABY_RELATION.put(EntityType.GIANT, BABY_GIANT);
        PARENT_BABY_RELATION.put(EntityType.GLOW_SQUID, BABY_GLOW_SQUID);
        PARENT_BABY_RELATION.put(EntityType.GUARDIAN, BABY_GUARDIAN);
        PARENT_BABY_RELATION.put(EntityType.ILLUSIONER, BABY_ILLUSIONER);
        PARENT_BABY_RELATION.put(EntityType.IRON_GOLEM, BABY_IRON_GOLEM);
        PARENT_BABY_RELATION.put(EntityType.PARROT, BABY_PARROT);
        PARENT_BABY_RELATION.put(EntityType.PIGLIN_BRUTE, BABY_PIGLIN_BRUTE);
        PARENT_BABY_RELATION.put(EntityType.PILLAGER, BABY_PILLAGER);
        PARENT_BABY_RELATION.put(EntityType.RAVAGER, BABY_RAVAGER);
        PARENT_BABY_RELATION.put(EntityType.SALMON, BABY_SALMON);
        PARENT_BABY_RELATION.put(EntityType.SHULKER, BABY_SHULKER);
        PARENT_BABY_RELATION.put(EntityType.SKELETON, BABY_SKELETON);
        PARENT_BABY_RELATION.put(EntityType.SNOW_GOLEM, BABY_SNOW_GOLEM);
        PARENT_BABY_RELATION.put(EntityType.SPIDER, BABY_SPIDER);
        PARENT_BABY_RELATION.put(EntityType.SQUID, BABY_SQUID);
        PARENT_BABY_RELATION.put(EntityType.STRAY, BABY_STRAY);
        PARENT_BABY_RELATION.put(EntityType.VINDICATOR, BABY_VINDICATOR);
        PARENT_BABY_RELATION.put(EntityType.WANDERING_TRADER, BABY_WANDERING_TRADER);
        //PARENT_BABY_RELATION.put(EntityType.WARDEN, BABY_WARDEN);
        PARENT_BABY_RELATION.put(EntityType.WITCH, BABY_WITCH);
        PARENT_BABY_RELATION.put(EntityType.WITHER, BABY_WITHER);
        PARENT_BABY_RELATION.put(EntityType.WITHER_SKELETON, BABY_WITHER_SKELETON);
    }
}

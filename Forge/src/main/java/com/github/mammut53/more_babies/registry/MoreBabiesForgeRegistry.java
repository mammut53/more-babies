package com.github.mammut53.more_babies.registry;

import com.github.mammut53.more_babies.MoreBabiesConstants;
import com.github.mammut53.more_babies.world.entity.*;
import com.github.mammut53.more_babies.world.entity.boss.BabyWitherBoss;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MoreBabiesForgeRegistry {

    public static final BiMap<EntityType<? extends Mob>, EntityType<? extends Mob>> PARENT_BABY_RELATION = HashBiMap.create();

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MoreBabiesConstants.MOD_ID);

    public static final RegistryObject<EntityType<BabyBat>> BABY_BAT = ENTITY_TYPES.register(
            "bat",
            () -> EntityType.Builder.of(BabyBat::new, MobCategory.AMBIENT).sized(0.5F, 0.9F).clientTrackingRange(5).build("")
    );

    public static final RegistryObject<EntityType<BabyBlaze>> BABY_BLAZE = ENTITY_TYPES.register(
            "blaze",
            () -> EntityType.Builder.of(BabyBlaze::new, MobCategory.MONSTER).sized(0.6F, 1.8F).build("")
    );

    public static final RegistryObject<EntityType<BabyCaveSpider>> BABY_CAVE_SPIDER = ENTITY_TYPES.register(
            "cave_spider",
            () -> EntityType.Builder.of(BabyCaveSpider::new, MobCategory.MONSTER).sized(0.7F, 0.5F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabyCreeper>> BABY_CREEPER = ENTITY_TYPES.register(
            "creeper",
            () -> EntityType.Builder.of(BabyCreeper::new, MobCategory.MONSTER).sized(0.6F, 1.7F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabyDolphin>> BABY_DOLPHIN = ENTITY_TYPES.register(
            "dolphin",
            () -> EntityType.Builder.of(BabyDolphin::new, MobCategory.WATER_CREATURE).sized(0.9F, 0.6F).build("")
    );

    public static final RegistryObject<EntityType<BabyElderGuardian>> BABY_ELDER_GUARDIAN = ENTITY_TYPES.register(
            "elder_guardian",
            () -> EntityType.Builder.of(BabyElderGuardian::new, MobCategory.MONSTER).sized(1.9975F, 1.9975F).build("")
    );

    /*public static final RegistryObject<EntityType<BabyEnderDragon>> BABY_ENDER_DRAGON = ENTITIES.register(
            "ender_dragon",
            () -> EntityType.Builder.of(BabyEnderDragon::new, MobCategory.MONSTER).fireImmune().sized(16.0F, 4.0F).clientTrackingRange(10).build("")
    );*/

    public static final RegistryObject<EntityType<BabyEnderMan>> BABY_ENDERMAN = ENTITY_TYPES.register(
            "enderman",
            () -> EntityType.Builder.of(BabyEnderMan::new, MobCategory.MONSTER).sized(0.6F, 2.9F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabyEvoker>> BABY_EVOKER = ENTITY_TYPES.register(
            "evoker",
            () -> EntityType.Builder.of(BabyEvoker::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabyGhast>> BABY_GHAST = ENTITY_TYPES.register(
            "ghast",
            () -> EntityType.Builder.of(BabyGhast::new, MobCategory.MONSTER).fireImmune().sized(4.0F, 4.0F).clientTrackingRange(10).build("")
    );

    public static final RegistryObject<EntityType<BabyGiant>> BABY_GIANT = ENTITY_TYPES.register(
            "giant",
            () -> EntityType.Builder.of(BabyGiant::new, MobCategory.MONSTER).fireImmune().sized(3.6F, 12.0F).clientTrackingRange(10).build("")
    );

    public static final RegistryObject<EntityType<BabyGlowSquid>> BABY_GLOW_SQUID = ENTITY_TYPES.register(
            "glow_squid",
            () -> EntityType.Builder.of(BabyGlowSquid::new, MobCategory.UNDERGROUND_WATER_CREATURE).sized(0.8F, 0.8F).clientTrackingRange(10).build("")
    );

    public static final RegistryObject<EntityType<BabyGuardian>> BABY_GUARDIAN = ENTITY_TYPES.register(
            "guardian",
            () -> EntityType.Builder.of(BabyGuardian::new, MobCategory.WATER_CREATURE).sized(0.85F, 0.85F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabyIllusioner>> BABY_ILLUSIONER = ENTITY_TYPES.register(
            "illusioner",
            () -> EntityType.Builder.of(BabyIllusioner::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabyIronGolem>> BABY_IRON_GOLEM = ENTITY_TYPES.register(
            "iron_golem",
            () -> EntityType.Builder.of(BabyIronGolem::new, MobCategory.MISC).sized(1.4F, 2.7F).clientTrackingRange(10).build("")
    );

    public static final RegistryObject<EntityType<BabyParrot>> BABY_PARROT = ENTITY_TYPES.register(
            "parrot",
            () -> EntityType.Builder.of(BabyParrot::new, MobCategory.CREATURE).sized(0.5F, 0.9F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabyPiglinBrute>> BABY_PIGLIN_BRUTE = ENTITY_TYPES.register(
            "piglin_brute",
            () -> EntityType.Builder.of(BabyPiglinBrute::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabyPillager>> BABY_PILLAGER = ENTITY_TYPES.register(
            "pillager",
            () -> EntityType.Builder.of(BabyPillager::new, MobCategory.MONSTER).canSpawnFarFromPlayer().sized(0.6F, 1.95F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabyRavager>> BABY_RAVAGER = ENTITY_TYPES.register(
            "ravager",
            () -> EntityType.Builder.of(BabyRavager::new, MobCategory.MONSTER).sized(1.95F, 2.2F).clientTrackingRange(10).build("")
    );

    public static final RegistryObject<EntityType<BabySalmon>> BABY_SALMON = ENTITY_TYPES.register(
            "salmon",
            () -> EntityType.Builder.of(BabySalmon::new, MobCategory.WATER_AMBIENT).sized(0.7F, 0.4F).clientTrackingRange(4).build("")
    );

    public static final RegistryObject<EntityType<BabyShulker>> BABY_SHULKER = ENTITY_TYPES.register(
            "shulker",
            () -> EntityType.Builder.of(BabyShulker::new, MobCategory.MONSTER).fireImmune().sized(1.0F, 1.0F).clientTrackingRange(10).build("")
    );

    public static final RegistryObject<EntityType<BabySkeleton>> BABY_SKELETON = ENTITY_TYPES.register(
            "skeleton",
            () -> EntityType.Builder.of(BabySkeleton::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabySnowGolem>> BABY_SNOW_GOLEM = ENTITY_TYPES.register(
            "snow_golem",
            () -> EntityType.Builder.of(BabySnowGolem::new, MobCategory.MISC).immuneTo(Blocks.POWDER_SNOW).sized(0.7F, 1.9F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabySpider>> BABY_SPIDER = ENTITY_TYPES.register(
            "spider",
            () -> EntityType.Builder.of(BabySpider::new, MobCategory.MONSTER).sized(1.4F, 0.9F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabySquid>> BABY_SQUID = ENTITY_TYPES.register(
            "squid",
            () -> EntityType.Builder.of(BabySquid::new, MobCategory.WATER_CREATURE).sized(0.8F, 0.8F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabyStray>> BABY_STRAY = ENTITY_TYPES.register(
            "stray",
            () -> EntityType.Builder.of(BabyStray::new, MobCategory.MONSTER).sized(0.6F, 1.99F).immuneTo(Blocks.POWDER_SNOW).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabyVindicator>> BABY_VINDICATOR = ENTITY_TYPES.register(
            "vindicator",
            () -> EntityType.Builder.of(BabyVindicator::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabyWanderingTrader>> BABY_WANDERING_TRADER = ENTITY_TYPES.register(
            "wandering_trader",
            () -> EntityType.Builder.of(BabyWanderingTrader::new, MobCategory.CREATURE).sized(0.6F, 1.95F).clientTrackingRange(10).build("")
    );

    public static final RegistryObject<EntityType<BabyWitch>> BABY_WITCH = ENTITY_TYPES.register(
            "witch",
            () -> EntityType.Builder.of(BabyWitch::new, MobCategory.CREATURE).sized(0.6F, 1.95F).clientTrackingRange(8).build("")
    );

    public static final RegistryObject<EntityType<BabyWitherBoss>> BABY_WITHER = ENTITY_TYPES.register(
            "wither",
            () -> EntityType.Builder.of(BabyWitherBoss::new, MobCategory.MONSTER).fireImmune().immuneTo(Blocks.WITHER_ROSE).sized(0.9F, 3.6F).clientTrackingRange(10).build("")
    );

    public static final RegistryObject<EntityType<BabyWitherSkeleton>> BABY_WITHER_SKELETON = ENTITY_TYPES.register(
            "wither_skeleton",
            () -> EntityType.Builder.of(BabyWitherSkeleton::new, MobCategory.MONSTER).fireImmune().immuneTo(Blocks.WITHER_ROSE).sized(0.7F, 2.4F).clientTrackingRange(8).build("")
    );

    static {}

    public static void register() {
        PARENT_BABY_RELATION.put(EntityType.BAT, BABY_BAT.get());
        PARENT_BABY_RELATION.put(EntityType.BLAZE, BABY_BLAZE.get());
        PARENT_BABY_RELATION.put(EntityType.CAVE_SPIDER, BABY_CAVE_SPIDER.get());
        PARENT_BABY_RELATION.put(EntityType.CREEPER, BABY_CREEPER.get());
        PARENT_BABY_RELATION.put(EntityType.DOLPHIN, BABY_DOLPHIN.get());
        PARENT_BABY_RELATION.put(EntityType.ELDER_GUARDIAN, BABY_ELDER_GUARDIAN.get());
        PARENT_BABY_RELATION.put(EntityType.ENDERMAN, BABY_ENDERMAN.get());
        PARENT_BABY_RELATION.put(EntityType.EVOKER, BABY_EVOKER.get());
        PARENT_BABY_RELATION.put(EntityType.GHAST, BABY_GHAST.get());
        PARENT_BABY_RELATION.put(EntityType.GIANT, BABY_GIANT.get());
        PARENT_BABY_RELATION.put(EntityType.GLOW_SQUID, BABY_GLOW_SQUID.get());
        PARENT_BABY_RELATION.put(EntityType.GUARDIAN, BABY_GUARDIAN.get());
        PARENT_BABY_RELATION.put(EntityType.ILLUSIONER, BABY_ILLUSIONER.get());
        PARENT_BABY_RELATION.put(EntityType.IRON_GOLEM, BABY_IRON_GOLEM.get());
        PARENT_BABY_RELATION.put(EntityType.PARROT, BABY_PARROT.get());
        PARENT_BABY_RELATION.put(EntityType.PIGLIN_BRUTE, BABY_PIGLIN_BRUTE.get());
        PARENT_BABY_RELATION.put(EntityType.PILLAGER, BABY_PILLAGER.get());
        PARENT_BABY_RELATION.put(EntityType.RAVAGER, BABY_RAVAGER.get());
        PARENT_BABY_RELATION.put(EntityType.SALMON, BABY_SALMON.get());
        PARENT_BABY_RELATION.put(EntityType.SHULKER, BABY_SHULKER.get());
        PARENT_BABY_RELATION.put(EntityType.SKELETON, BABY_SKELETON.get());
        PARENT_BABY_RELATION.put(EntityType.SNOW_GOLEM, BABY_SNOW_GOLEM.get());
        PARENT_BABY_RELATION.put(EntityType.SPIDER, BABY_SPIDER.get());
        PARENT_BABY_RELATION.put(EntityType.SQUID, BABY_SQUID.get());
        PARENT_BABY_RELATION.put(EntityType.STRAY, BABY_STRAY.get());
        PARENT_BABY_RELATION.put(EntityType.VINDICATOR, BABY_VINDICATOR.get());
        PARENT_BABY_RELATION.put(EntityType.WANDERING_TRADER, BABY_WANDERING_TRADER.get());
        PARENT_BABY_RELATION.put(EntityType.WITCH, BABY_WITCH.get());
        PARENT_BABY_RELATION.put(EntityType.WITHER, BABY_WITHER.get());
        PARENT_BABY_RELATION.put(EntityType.WITHER_SKELETON, BABY_WITHER_SKELETON.get());
    }

}

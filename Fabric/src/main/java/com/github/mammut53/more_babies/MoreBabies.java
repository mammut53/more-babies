package com.github.mammut53.more_babies;

import com.github.mammut53.more_babies.registry.MoreBabiesFabricRegistry;
import com.github.mammut53.more_babies.world.entity.*;
import com.github.mammut53.more_babies.world.entity.boss.BabyEnderDragon;
import com.github.mammut53.more_babies.world.entity.boss.BabyWitherBoss;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class MoreBabies implements ModInitializer {

    @Override
    public void onInitialize() {

        MoreBabiesCommon.init(MoreBabiesFabricRegistry.PARENT_BABY_RELATION);

        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_BAT, BabyBat.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_BLAZE, BabyBlaze.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.23 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_CAVE_SPIDER, BabyCaveSpider.createCaveSpider().add(Attributes.MOVEMENT_SPEED, 0.3 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_CREEPER, BabyCreeper.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_DOLPHIN, BabyDolphin.createAttributes().add(Attributes.MOVEMENT_SPEED, 1.2 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_ELDER_GUARDIAN, BabyElderGuardian.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.30000001192092896 * 1.5));
        //FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_ENDER_DRAGON, BabyEnderDragon.createAttributes());
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_ENDERMAN, BabyEnderMan.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_EVOKER, BabyEvoker.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_GHAST, BabyGhast.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_GIANT, BabyGiant.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_GLOW_SQUID, BabyGlowSquid.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_GUARDIAN, BabyGuardian.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_ILLUSIONER, BabyIllusioner.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_IRON_GOLEM, BabyIronGolem.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_PARROT, BabyParrot.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.20000000298023224 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_PIGLIN_BRUTE, BabyPiglinBrute.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_PILLAGER, BabyPillager.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.35 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_RAVAGER, BabyRavager.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_SALMON, BabySalmon.createAttributes());
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_SHULKER, BabyShulker.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_SKELETON, BabySkeleton.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_SNOW_GOLEM, BabySnowGolem.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.2 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_SPIDER, BabySpider.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_SQUID, BabySquid.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_STRAY, BabyStray.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_VINDICATOR, BabyVindicator.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.20000000298023224 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_WANDERING_TRADER, BabyWanderingTrader.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_WITCH, BabyWitch.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_WITHER, BabyWitherBoss.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.6000000238418579 * 1.5));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_WITHER_SKELETON, BabyWitherSkeleton.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5));
    }
}

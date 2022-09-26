package com.github.mammut53.more_babies;

import com.github.mammut53.more_babies.config.MoreBabiesConfig;
import com.github.mammut53.more_babies.registry.MoreBabiesFabricRegistry;
import com.github.mammut53.more_babies.registry.MoreBabiesItems;
import com.github.mammut53.more_babies.world.entity.*;
import com.github.mammut53.more_babies.world.entity.boss.BabyWitherBoss;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class MoreBabies implements ModInitializer {

    @Override
    public void onInitialize() {

        ModLoadingContext.registerConfig(MoreBabiesConstants.MOD_ID, ModConfig.Type.COMMON, MoreBabiesConfig.SPEC);

        MoreBabiesCommon.init(MoreBabiesFabricRegistry.PARENT_BABY_RELATION);

        MoreBabiesItems.register();

        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_BAT, BabyBat.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("bat")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_BLAZE, BabyBlaze.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.23 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("blaze")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_CAVE_SPIDER, BabyCaveSpider.createCaveSpider().add(Attributes.MOVEMENT_SPEED, 0.3 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("cave_spider")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_CREEPER, BabyCreeper.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("creeper")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_DOLPHIN, BabyDolphin.createAttributes().add(Attributes.MOVEMENT_SPEED, 1.2 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("dolphin")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_ELDER_GUARDIAN, BabyElderGuardian.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("elder_guardian")).getSpeedModifier().get()));
        //FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_ENDER_DRAGON, BabyEnderDragon.createAttributes());
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_ENDERMAN, BabyEnderMan.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("enderman")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_EVOKER, BabyEvoker.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("evoker")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_GHAST, BabyGhast.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("ghast")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_GIANT, BabyGiant.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("giant")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_GLOW_SQUID, BabyGlowSquid.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("glow_squid")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_GUARDIAN, BabyGuardian.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("guardian")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_ILLUSIONER, BabyIllusioner.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("illusioner")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_IRON_GOLEM, BabyIronGolem.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("iron_golem")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_PARROT, BabyParrot.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.2 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("parrot")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_PIGLIN_BRUTE, BabyPiglinBrute.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("piglin_brute")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_PILLAGER, BabyPillager.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.35 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("pillager")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_RAVAGER, BabyRavager.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("ravager")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_SALMON, BabySalmon.createAttributes());
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_SHULKER, BabyShulker.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("shulker")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_SKELETON, BabySkeleton.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("skeleton")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_SNOW_GOLEM, BabySnowGolem.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.2 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("snow_golem")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_SPIDER, BabySpider.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("spider")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_SQUID, BabySquid.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("squid")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_STRAY, BabyStray.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("stray")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_VINDICATOR, BabyVindicator.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.2 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("vindicator")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_WANDERING_TRADER, BabyWanderingTrader.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("wandering_trader")).getSpeedModifier().get()));
        //FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_WARDEN, BabyWarden.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES_MAP.get("warden")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_WITCH, BabyWitch.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("witch")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_WITHER, BabyWitherBoss.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.6 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("wither")).getSpeedModifier().get()));
        FabricDefaultAttributeRegistry.register(MoreBabiesFabricRegistry.BABY_WITHER_SKELETON, BabyWitherSkeleton.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("wither_skeleton")).getSpeedModifier().get()));
    }
}

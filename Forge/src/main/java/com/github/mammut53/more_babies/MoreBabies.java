package com.github.mammut53.more_babies;

import com.github.mammut53.more_babies.config.MoreBabiesConfig;
import com.github.mammut53.more_babies.event.NaturalSpawning;
import com.github.mammut53.more_babies.registry.MoreBabiesForgeRegistry;
import com.github.mammut53.more_babies.registry.MoreBabiesItems;
import com.github.mammut53.more_babies.world.entity.*;
import com.github.mammut53.more_babies.world.entity.boss.BabyWitherBoss;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ConfigTracker;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(MoreBabiesConstants.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MoreBabies {

    public MoreBabies() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MoreBabiesItems.ITEMS.register(modEventBus);
        MoreBabiesForgeRegistry.ENTITY_TYPES.register(modEventBus);
    }

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent event) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MoreBabiesConfig.SPEC);

        // MAYBE YOU ARE NOT SUPPOSED TO DO THIS
        ConfigTracker.INSTANCE.loadConfigs(ModConfig.Type.COMMON, FMLPaths.CONFIGDIR.get());

        MinecraftForge.EVENT_BUS.addListener(NaturalSpawning::onLivingPackSize);
    }

    @SubscribeEvent
    public static void onEntityAttributeCreation(final EntityAttributeCreationEvent event) {
        MoreBabiesForgeRegistry.register();
        MoreBabiesCommon.init(MoreBabiesForgeRegistry.PARENT_BABY_RELATION);

        event.put(MoreBabiesForgeRegistry.BABY_BAT.get(), BabyBat.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * ((MoreBabiesConfig.BabySpeedModifier) MoreBabiesConfig.BABIES.get("bat")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_BLAZE.get(), BabyBlaze.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.23 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("blaze")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_CAVE_SPIDER.get(), BabyCaveSpider.createCaveSpider().add(Attributes.MOVEMENT_SPEED, 0.3 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("cave_spider")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_CREEPER.get(), BabyCreeper.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("creeper")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_DOLPHIN.get(), BabyDolphin.createAttributes().add(Attributes.MOVEMENT_SPEED, 1.2 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("dolphin")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_ELDER_GUARDIAN.get(), BabyElderGuardian.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("elder_guardian")).getSpeedModifier().get()).build());
        //event.put(MoreBabiesForgeRegistry.BABY_ENDER_DRAGON.get(), BabyEnderDragon.createAttributes().build());
        event.put(MoreBabiesForgeRegistry.BABY_ENDERMAN.get(), BabyEnderMan.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("enderman")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_EVOKER.get(), BabyEvoker.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("evoker")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_GHAST.get(), BabyGhast.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("ghast")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_GIANT.get(), BabyGiant.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("giant")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_GLOW_SQUID.get(), BabyGlowSquid.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("glow_squid")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_GUARDIAN.get(), BabyGuardian.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("guardian")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_ILLUSIONER.get(), BabyIllusioner.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("illusioner")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_IRON_GOLEM.get(), BabyIronGolem.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("iron_golem")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_PARROT.get(), BabyParrot.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.2 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("parrot")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_PIGLIN_BRUTE.get(), BabyPiglinBrute.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("piglin_brute")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_PILLAGER.get(), BabyPillager.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.35 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("pillager")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_RAVAGER.get(), BabyRavager.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("ravager")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_SALMON.get(), BabySalmon.createAttributes().build());
        event.put(MoreBabiesForgeRegistry.BABY_SHULKER.get(), BabyShulker.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("shulker")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_SKELETON.get(), BabySkeleton.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("skeleton")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_SNOW_GOLEM.get(), BabySnowGolem.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.2 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("snow_golem")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_SPIDER.get(), BabySpider.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("spider")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_SQUID.get(), BabySquid.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("squid")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_STRAY.get(), BabyStray.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("stray")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_VINDICATOR.get(), BabyVindicator.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.2 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("vindicator")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_WANDERING_TRADER.get(), BabyWanderingTrader.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("wandering_trader")).getSpeedModifier().get()).build());
        //event.put(MoreBabiesForgeRegistry.BABY_WARDEN.get(), BabyWarden.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES_MAP.get("warden")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_WITCH.get(), BabyWitch.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("witch")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_WITHER.get(), BabyWitherBoss.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.6 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("wither")).getSpeedModifier().get()).build());
        event.put(MoreBabiesForgeRegistry.BABY_WITHER_SKELETON.get(), BabyWitherSkeleton.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * ((MoreBabiesConfig.BabySpeedModifier)MoreBabiesConfig.BABIES.get("wither_skeleton")).getSpeedModifier().get()).build());
    }
}
package com.github.mammut53.more_babies;

import com.github.mammut53.more_babies.event.NaturalSpawning;
import com.github.mammut53.more_babies.registry.MoreBabiesForgeRegistry;
import com.github.mammut53.more_babies.world.entity.*;
import com.github.mammut53.more_babies.world.entity.boss.BabyWitherBoss;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MoreBabiesConstants.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MoreBabies {

    public MoreBabies() {

        MoreBabiesForgeRegistry.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        MinecraftForge.EVENT_BUS.addListener(NaturalSpawning::onLivingPackSize);
    }

    @SubscribeEvent
    public static void onEntityAttributeCreation(final EntityAttributeCreationEvent event) {
        MoreBabiesForgeRegistry.register();
        MoreBabiesCommon.init(MoreBabiesForgeRegistry.PARENT_BABY_RELATION);
        event.put(MoreBabiesForgeRegistry.BABY_BAT.get(), BabyBat.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_BLAZE.get(), BabyBlaze.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.23 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_CAVE_SPIDER.get(), BabyCaveSpider.createCaveSpider().add(Attributes.MOVEMENT_SPEED, 0.3 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_CREEPER.get(), BabyCreeper.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_DOLPHIN.get(), BabyDolphin.createAttributes().add(Attributes.MOVEMENT_SPEED, 1.2 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_ELDER_GUARDIAN.get(), BabyElderGuardian.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.30000001192092896 * 1.5).build());
        //event.put(MoreBabiesForgeRegistry.BABY_ENDER_DRAGON.get(), BabyEnderDragon.createAttributes().build());
        event.put(MoreBabiesForgeRegistry.BABY_ENDERMAN.get(), BabyEnderMan.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_EVOKER.get(), BabyEvoker.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_GHAST.get(), BabyGhast.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_GIANT.get(), BabyGiant.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_GLOW_SQUID.get(), BabyGlowSquid.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_GUARDIAN.get(), BabyGuardian.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_ILLUSIONER.get(), BabyIllusioner.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_IRON_GOLEM.get(), BabyIronGolem.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_PARROT.get(), BabyParrot.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.20000000298023224 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_PIGLIN_BRUTE.get(), BabyPiglinBrute.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_PILLAGER.get(), BabyPillager.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.35 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_RAVAGER.get(), BabyRavager.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_SALMON.get(), BabySalmon.createAttributes().build());
        event.put(MoreBabiesForgeRegistry.BABY_SHULKER.get(), BabyShulker.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_SKELETON.get(), BabySkeleton.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_SNOW_GOLEM.get(), BabySnowGolem.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.2 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_SPIDER.get(), BabySpider.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.3 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_SQUID.get(), BabySquid.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.7 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_STRAY.get(), BabyStray.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_VINDICATOR.get(), BabyVindicator.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.20000000298023224 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_WANDERING_TRADER.get(), BabyWanderingTrader.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.5 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_WITCH.get(), BabyWitch.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_WITHER.get(), BabyWitherBoss.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.6000000238418579 * 1.5).build());
        event.put(MoreBabiesForgeRegistry.BABY_WITHER_SKELETON.get(), BabyWitherSkeleton.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.25 * 1.5).build());
    }
}
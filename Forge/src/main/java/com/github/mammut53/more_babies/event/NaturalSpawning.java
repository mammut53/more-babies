package com.github.mammut53.more_babies.event;

import com.github.mammut53.more_babies.MoreBabiesConstants;
import com.github.mammut53.more_babies.config.MoreBabiesConfig;
import com.github.mammut53.more_babies.registry.MoreBabiesForgeRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraftforge.event.entity.living.LivingPackSizeEvent;

public class NaturalSpawning {

    public static void onLivingPackSize(final LivingPackSizeEvent event) {
        Entity mob = event.getEntity();
        if (!(mob.level instanceof ServerLevel serverLevel)) return;

        EntityType<?> mobType = mob.getType();
        if (!mobType.getDescriptionId().startsWith("entity.minecraft.")) return;
        if (!MoreBabiesConstants.BABY_IDS.contains((mobType.toShortString()))) return;

        MoreBabiesConfig.BabyEntry babyEntry = MoreBabiesConfig.BABIES.get(mobType.toShortString());
        if (!(babyEntry instanceof MoreBabiesConfig.BabySpawnWeight)) return;

        double babySpawnWeight = ((MoreBabiesConfig.BabySpawnWeight) babyEntry).getSpawnWeight().get();
        if (!(serverLevel.getRandom().nextFloat() < babySpawnWeight)) return;

        EntityType<? extends Mob> babyType = MoreBabiesForgeRegistry.PARENT_BABY_RELATION.get(mobType);
        if (babyType == null) return;

        Mob babyMob;
        if (mob instanceof AgeableMob ageableMob) {
            babyMob = ageableMob.getBreedOffspring((ServerLevel) mob.level, ageableMob);
        } else {
            babyMob = babyType.create(mob.level);
        }
        if (babyMob == null) return;

        babyMob.moveTo(mob.getX(), mob.getY(), mob.getZ(), Mth.wrapDegrees(mob.level.random.nextFloat() * 360.0F), 0.0F);
        ((ServerLevel) mob.level).addFreshEntityWithPassengers(babyMob);
        babyMob.yHeadRot = babyMob.getYRot();
        babyMob.yBodyRot = babyMob.getYRot();
        babyMob.finalizeSpawn((ServerLevel) mob.level, mob.level.getCurrentDifficultyAt(babyMob.blockPosition()), MobSpawnType.NATURAL, null, null);
        mob.discard();
    }

}

package com.github.mammut53.more_babies;

import com.google.common.collect.BiMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;

public class MoreBabiesCommon {

    public static BiMap<EntityType<? extends Mob>, EntityType<? extends Mob>> PARENT_BABY_RELATION;

    public static void init(BiMap<EntityType<? extends Mob>, EntityType<? extends Mob>> parentBabyRelation) {
        PARENT_BABY_RELATION = parentBabyRelation;
    }
}
package com.github.mammut53.more_babies.world.entity.boss;

import com.github.mammut53.more_babies.world.entity.MoreBabiesBaby;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;

public class BabyEnderDragon extends EnderDragon implements MoreBabiesBaby {

    public BabyEnderDragon(EntityType<? extends EnderDragon> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isBaby() {
        return true;
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(SpawnEggItem.byId(EntityType.ENDER_DRAGON));
    }
}

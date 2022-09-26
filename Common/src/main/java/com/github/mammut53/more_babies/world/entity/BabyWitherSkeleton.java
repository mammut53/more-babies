package com.github.mammut53.more_babies.world.entity;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;

public class BabyWitherSkeleton extends WitherSkeleton implements EquipedBaby, MoreBabiesBaby {

    public BabyWitherSkeleton(EntityType<? extends WitherSkeleton> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isBaby() {
        return true;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return 1.05F;
    }

    @Override
    public double getMyRidingOffset() {
        return -0.15D;
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(SpawnEggItem.byId(EntityType.WITHER_SKELETON));
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return MoreBabiesCommon.PARENT_BABY_RELATION.inverse().get(this.getType()).getDefaultLootTable();
    }

    @Override
    public void finalizeUnnaturalSpawn() {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
    }
}

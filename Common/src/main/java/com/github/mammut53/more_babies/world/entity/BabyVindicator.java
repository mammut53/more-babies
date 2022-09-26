package com.github.mammut53.more_babies.world.entity;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;

public class BabyVindicator extends Vindicator implements EquipedBaby, MoreBabiesBaby {

    public BabyVindicator(EntityType<? extends Vindicator> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isBaby() {
        return true;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return 0.81F;
    }

    @Override
    public double getMyRidingOffset() {
        return -0.15D;
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(SpawnEggItem.byId(EntityType.VINDICATOR));
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return MoreBabiesCommon.PARENT_BABY_RELATION.inverse().get(this.getType()).getDefaultLootTable();
    }

    @Override
    public void finalizeUnnaturalSpawn() {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
    }
}

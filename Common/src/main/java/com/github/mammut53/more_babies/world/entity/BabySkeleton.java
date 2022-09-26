package com.github.mammut53.more_babies.world.entity;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;

public class BabySkeleton extends Skeleton implements EquipedBaby, MoreBabiesBaby {

    public BabySkeleton(EntityType<? extends Skeleton> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isBaby() {
        return true;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return 0.93F;
    }

    @Override
    public double getMyRidingOffset() {
        return -0.15D;
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(SpawnEggItem.byId(EntityType.SKELETON));
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return MoreBabiesCommon.PARENT_BABY_RELATION.inverse().get(this.getType()).getDefaultLootTable();
    }
    @Override
    public void finalizeUnnaturalSpawn() {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
    }

    @Override
    protected void doFreezeConversion() {
        this.convertTo(MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.STRAY), true);
        if (!this.isSilent()) {
            this.level.levelEvent(null, 1048, this.blockPosition(), 0);
        }
    }
}

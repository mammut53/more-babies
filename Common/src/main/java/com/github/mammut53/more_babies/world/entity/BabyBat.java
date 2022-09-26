package com.github.mammut53.more_babies.world.entity;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;

public class BabyBat extends Bat implements MoreBabiesBaby {

    public BabyBat(EntityType<? extends Bat> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isBaby() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isResting()) {
            this.setPosRaw(this.getX(), (double)Mth.floor(this.getY()) + 1.0D - (double)this.getBbHeight(), this.getZ());
        }
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return entityDimensions.height / 2.0F;
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(SpawnEggItem.byId(EntityType.BAT));
    }

    @Override
    public float getVoicePitch() {
        return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5F) * 0.95F;
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return MoreBabiesCommon.PARENT_BABY_RELATION.inverse().get(this.getType()).getDefaultLootTable();
    }
}

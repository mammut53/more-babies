package com.github.mammut53.more_babies.world.entity;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;

public class BabyGlowSquid extends GlowSquid implements MoreBabiesBaby {

    public BabyGlowSquid(EntityType<? extends GlowSquid> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isBaby() {
        return true;
    }

    @Override
    protected boolean shouldDropLoot() {
        return true;
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(SpawnEggItem.byId(EntityType.GLOW_SQUID));
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return MoreBabiesCommon.PARENT_BABY_RELATION.inverse().get(this.getType()).getDefaultLootTable();
    }
}

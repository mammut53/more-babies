package com.github.mammut53.more_babies.world.entity;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.mixin.world.entity.monster.ShulkerAccessor;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class BabyShulker extends Shulker {

    public BabyShulker(EntityType<? extends Shulker> entityType, Level level) {
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
    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return 0.5F;
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(SpawnEggItem.byId(EntityType.SHULKER));
    }

    @Override
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5F;
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return MoreBabiesCommon.PARENT_BABY_RELATION.inverse().get(this.getType()).getDefaultLootTable();
    }

    @Override
    protected AABB makeBoundingBox() {
        float f = getPhysicalPeek(((ShulkerAccessor) this).getCurrentPeekAmount());
        Direction direction = this.getAttachFace().getOpposite();
        float g = 0.25F;
        return getProgressAabb(direction, f).move(this.getX() - (double)g, this.getY(), this.getZ() - (double)g);
    }

    private static float getPhysicalPeek(float f) {
        return 0.5F - Mth.sin((0.5F + f) * 3.1415927F) * 0.5F;
    }

    public static AABB getProgressAabb(Direction direction, float f) {
        return getProgressDeltaAabb(direction, -1.0F, f);
    }

    public static AABB getProgressDeltaAabb(Direction direction, float f, float g) {
        double d = Math.max(f, g) * 0.5;
        double e = Math.min(f, g) * 0.5;
        AABB aabb = switch (direction) {
            case DOWN -> (new AABB(0, 0.5, 0, 0.5, 1, 0.5));
            case UP -> (new AABB(0, 0, 0, 0.5, 0.5, 0.5));
            case NORTH -> (new AABB(0, 0.25, 0.25, 0.5, 0.75, 0.75));
            case SOUTH -> (new AABB(0, 0.25, -0.25, 0.5, 0.75, 0.25));
            case WEST -> (new AABB(0.25, 0.25, 0, 0.75, 0.75, 0.5));
            case EAST -> (new AABB(-0.25, 0.25, 0, 0.25, 0.75, 0.5));
        };
        return aabb.expandTowards((double)direction.getStepX() * d, (double)direction.getStepY() * d, (double)direction.getStepZ() * d).contract((double)(-direction.getStepX()) * (0.5D + e), (double)(-direction.getStepY()) * (0.5D + e), (double)(-direction.getStepZ()) * (0.5D + e));
    }

}

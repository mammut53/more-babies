package com.github.mammut53.more_babies.mixin.world.entity.monster;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.MoreBabiesConstants;
import com.github.mammut53.more_babies.mixin.world.entity.EntityAccessor;
import com.github.mammut53.more_babies.world.entity.BabyShulker;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Shulker.class)
public abstract class ShulkerMixin extends AbstractGolem {

    @Shadow
    public DyeColor getColor() {
        return null;
    }

    protected ShulkerMixin(EntityType<? extends AbstractGolem> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(
            method = "hitByShulkerBullet",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void spawnShulker(CallbackInfo ci) {
        if (!(this.random.nextFloat() < MoreBabiesConstants.BABY_SPAWN_CHANCE)) {
            return;
        }

        BabyShulker babyShulker = (BabyShulker) MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.SHULKER).create(this.level);
        if (babyShulker == null) {
            return;
        }

        DyeColor dyeColor = this.getColor();
        if (dyeColor != null) {
            ((EntityAccessor) babyShulker).getEntityData().set(ShulkerAccessor.getDATA_COLOR_ID(), (byte)dyeColor.getId());
        }

        babyShulker.moveTo(this.position());
        this.level.addFreshEntity(babyShulker);

        ci.cancel();
    }

}

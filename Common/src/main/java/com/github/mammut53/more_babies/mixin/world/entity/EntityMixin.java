package com.github.mammut53.more_babies.mixin.world.entity;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    public void moveTo(double d, double e, double f, float g, float h) {}

    @Shadow
    public void setYHeadRot(float f) {}

    @Redirect(
            method = "copyPosition",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;moveTo(DDDFF)V"
            )
    )
    public void moveTo(Entity entity, double x, double y, double z, float yRot, float xRot) {
        this.moveTo(x, y, z, yRot, xRot);
        this.setYHeadRot(yRot);
    }

}

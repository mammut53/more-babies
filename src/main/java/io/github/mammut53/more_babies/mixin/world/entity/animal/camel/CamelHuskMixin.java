package io.github.mammut53.more_babies.mixin.world.entity.animal.camel;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.camel.CamelHusk;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CamelHusk.class)
public abstract class CamelHuskMixin extends Camel {

    protected CamelHuskMixin(final EntityType<? extends Camel> type, final Level level) {
        super(type, level);
    }

    @Inject(
            method = "isBaby",
            at = @At("RETURN"),
            cancellable = true
    )
    private void injectIsBaby(final CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(super.isBaby());
    }

}
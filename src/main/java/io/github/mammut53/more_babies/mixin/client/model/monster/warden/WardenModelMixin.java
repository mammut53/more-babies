package io.github.mammut53.more_babies.mixin.client.model.monster.warden;

import io.github.mammut53.more_babies.client.model.monster.warden.WardenResetArmPoses;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.warden.WardenModel;
import net.minecraft.client.renderer.entity.state.WardenRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WardenModel.class)
public abstract class WardenModelMixin extends EntityModel<WardenRenderState> implements WardenResetArmPoses {

    protected WardenModelMixin(final ModelPart root) {
        super(root);
    }

    @Inject(
            method = "resetArmPoses",
            at = @At("TAIL")
    )
    private void injectResetArmPoses(final CallbackInfo ci) {
        this.more_babies$restArmPoses();
    }

    @Override
    public void more_babies$restArmPoses() {
    }
}

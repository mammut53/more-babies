package io.github.mammut53.more_babies.mixin.client.model.monster.blaze;

import io.github.mammut53.more_babies.client.model.monster.blaze.BlazeModelParts;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.blaze.BlazeModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlazeModel.class)
public abstract class BlazeModelMixin extends EntityModel<LivingEntityRenderState> implements BlazeModelParts {

    @Final
    @Shadow
    private ModelPart[] upperBodyParts;

    @Final
    @Shadow
    private ModelPart head;

    protected BlazeModelMixin(final ModelPart root) {
        super(root);
    }

    public ModelPart[] more_babies$getUpperBodyParts() {
        return this.upperBodyParts;
    }

    public ModelPart more_babies$getHead() {
        return this.head;
    }

}

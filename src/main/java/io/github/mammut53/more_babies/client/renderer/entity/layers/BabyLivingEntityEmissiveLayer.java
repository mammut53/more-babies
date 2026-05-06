package io.github.mammut53.more_babies.client.renderer.entity.layers;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

import java.util.function.Function;

public interface BabyLivingEntityEmissiveLayer<S extends LivingEntityRenderState, M extends EntityModel<S>> {
    Function<S, Identifier> more_babies$getOriginalTextureProvider();
    void more_babies$setBabyModel(M babyModel);
}

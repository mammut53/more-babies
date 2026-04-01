package io.github.mammut53.more_babies.mixin.client.renderer.entity.layers;

import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.renderer.entity.layers.EyesLayerRenderTypeBaby;
import net.minecraft.client.model.monster.enderman.EndermanModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnderEyesLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EnderEyesLayer.class)
public abstract class EnderEyesLayerMixin extends EyesLayer<EndermanRenderState, EndermanModel<EndermanRenderState>> implements EyesLayerRenderTypeBaby {
    @Unique
    private static final RenderType more_babies$ENDERMAN_EYES_BABY = RenderTypes.eyes(Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/enderman/enderman_eyes_baby.png"));

    protected EnderEyesLayerMixin(final RenderLayerParent<EndermanRenderState, EndermanModel<EndermanRenderState>> renderer) {
        super(renderer);
    }

    @Override
    public RenderType more_babies$renderTypeBaby() {
        return more_babies$ENDERMAN_EYES_BABY;
    }
}

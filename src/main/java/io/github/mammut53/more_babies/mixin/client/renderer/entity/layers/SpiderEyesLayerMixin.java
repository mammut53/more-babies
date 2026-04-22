package io.github.mammut53.more_babies.mixin.client.renderer.entity.layers;

import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.renderer.entity.layers.EyesLayerRenderTypeBaby;
import net.minecraft.client.model.monster.spider.SpiderModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SpiderEyesLayer.class)
public abstract class SpiderEyesLayerMixin<M extends SpiderModel> extends EyesLayer<LivingEntityRenderState, M> implements EyesLayerRenderTypeBaby {

    @Unique
    private static final RenderType more_babies$SPIDER_EYES_BABY = RenderTypes.eyes(Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/spider/spider_baby_eyes.png"));

    protected SpiderEyesLayerMixin(final RenderLayerParent<LivingEntityRenderState, M> renderer) {
        super(renderer);
    }

    @Override
    public RenderType more_babies$renderTypeBaby() {
        return more_babies$SPIDER_EYES_BABY;
    }

}

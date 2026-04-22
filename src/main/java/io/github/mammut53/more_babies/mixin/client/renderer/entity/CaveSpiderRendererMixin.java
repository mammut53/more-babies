package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import io.github.mammut53.more_babies.MoreBabies;
import net.minecraft.client.renderer.entity.CaveSpiderRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(CaveSpiderRenderer.class)
public abstract class CaveSpiderRendererMixin extends SpiderRenderer<CaveSpider> {

    @Shadow
    private static final Identifier CAVE_SPIDER_LOCATION = Identifier.withDefaultNamespace("textures/entity/spider/cave_spider.png");
    @Unique
    private static final Identifier more_babies$CAVE_SPIDER_BABY_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/spider/cave_spider_baby.png");

    protected CaveSpiderRendererMixin(final EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NonNull Identifier getTextureLocation(final LivingEntityRenderState state) {
        return state.isBaby ? more_babies$CAVE_SPIDER_BABY_LOCATION : CAVE_SPIDER_LOCATION;
    }
}

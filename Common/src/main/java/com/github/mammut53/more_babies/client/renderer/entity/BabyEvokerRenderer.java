package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.world.entity.monster.SpellcasterIllager;

public class BabyEvokerRenderer<T extends SpellcasterIllager> extends EvokerRenderer<T> {

    public BabyEvokerRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(T spellcasterIllager, PoseStack poseStack, float f) {
        poseStack.scale(0.46875F, 0.46875F, 0.46875F);
    }
}

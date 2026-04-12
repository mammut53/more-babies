package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import io.github.mammut53.more_babies.MoreBabies;
import net.minecraft.client.model.monster.piglin.PiglinModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.PiglinRenderer;
import net.minecraft.client.renderer.entity.state.PiglinRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PiglinRenderer.class)
public abstract class PiglinRendererMixin extends HumanoidMobRenderer<AbstractPiglin, PiglinRenderState, PiglinModel> {

    @Shadow
    private static final Identifier PIGLIN_LOCATION = Identifier.withDefaultNamespace("textures/entity/piglin/piglin.png");
    @Shadow
    private static final Identifier PIGLIN_BABY_LOCATION = Identifier.withDefaultNamespace("textures/entity/piglin/piglin_baby.png");
    @Shadow
    private static final Identifier PIGLIN_BRUTE_LOCATION = Identifier.withDefaultNamespace("textures/entity/piglin/piglin_brute.png");
    @Unique
    private static final Identifier more_babies$PIGLIN_BRUTE_BABY_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/piglin/piglin_brute_baby.png");

    protected PiglinRendererMixin(final EntityRendererProvider.Context context, final PiglinModel model, final float shadow) {
        super(context, model, shadow);
    }

    @Override
    public @NonNull Identifier getTextureLocation(final PiglinRenderState state) {
        if (state.isBrute) {
            return state.isBaby ? more_babies$PIGLIN_BRUTE_BABY_LOCATION : PIGLIN_BRUTE_LOCATION;
        } else {
            return state.isBaby ? PIGLIN_BABY_LOCATION : PIGLIN_LOCATION;
        }
    }
}

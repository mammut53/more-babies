package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import io.github.mammut53.more_babies.MoreBabies;
import net.minecraft.client.renderer.entity.ElderGuardianRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GuardianRenderer;
import net.minecraft.client.renderer.entity.state.GuardianRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ElderGuardianRenderer.class)
public abstract class ElderGuardianRendererMixin extends GuardianRenderer {

    @Shadow
    public static final Identifier GUARDIAN_ELDER_LOCATION = Identifier.withDefaultNamespace("textures/entity/guardian/guardian_elder.png");
    @Unique
    private static final Identifier more_babies$GUARDIAN_ELDER_BABY_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/guardian/guardian_elder_baby.png");

    protected ElderGuardianRendererMixin(final EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NonNull Identifier getTextureLocation(final GuardianRenderState state) {
        return state.isBaby ? more_babies$GUARDIAN_ELDER_BABY_LOCATION : GUARDIAN_ELDER_LOCATION;
    }
}

package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.PiglinRenderer;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(EntityRenderers.class)
public abstract class EntityRenderersMixin {

    protected EntityRenderersMixin() {
        throw new UnsupportedOperationException();
    }

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/EntityRenderers;register(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/client/renderer/entity/EntityRendererProvider;)V"
            ),
            index = 1,
            slice = @Slice(
                    from = @At(value = "FIELD", opcode = Opcodes.GETSTATIC, target = "Lnet/minecraft/world/entity/EntityTypes;PIGLIN_BRUTE:Lnet/minecraft/world/entity/EntityType;"),
                    to = @At(value = "FIELD", opcode = Opcodes.GETSTATIC, target = "Lnet/minecraft/world/entity/EntityTypes;PILLAGER:Lnet/minecraft/world/entity/EntityType;")
            )
    )
    private static EntityRendererProvider<? extends AbstractPiglin> injectPiglinBruteRegister(final EntityRendererProvider<? extends AbstractPiglin> input) {
        return context -> new PiglinRenderer(
                context,
                ModelLayers.PIGLIN_BRUTE,
                MoreBabiesModelLayers.PIGLIN_BRUTE_BABY,
                ModelLayers.PIGLIN_BRUTE_ARMOR,
                MoreBabiesModelLayers.PIGLIN_BRUTE_BABY_ARMOR
        );
    }

}

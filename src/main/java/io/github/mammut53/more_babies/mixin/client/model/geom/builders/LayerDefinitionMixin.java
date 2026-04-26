package io.github.mammut53.more_babies.mixin.client.model.geom.builders;

import io.github.mammut53.more_babies.client.model.geom.builders.LayerDefinitionMeshAccessor;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LayerDefinition.class)
public class LayerDefinitionMixin implements LayerDefinitionMeshAccessor {

    @Final
    @Shadow
    private MeshDefinition mesh;

    @Override
    public MeshDefinition more_babies$getMesh() {
        return this.mesh;
    }

}

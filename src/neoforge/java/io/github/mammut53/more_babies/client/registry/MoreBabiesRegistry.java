package io.github.mammut53.more_babies.client.registry;

import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesLayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.Map;

@EventBusSubscriber(modid = MoreBabies.MOD_ID, value = Dist.CLIENT)
public class MoreBabiesRegistry {

    private MoreBabiesRegistry() {
        throw new UnsupportedOperationException();
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        final Map<ModelLayerLocation, LayerDefinition> layers = MoreBabiesLayerDefinitions.createRoots();
        for (final Map.Entry<ModelLayerLocation, LayerDefinition> entry : layers.entrySet()) {
            event.registerLayerDefinition(entry.getKey(), entry::getValue);
        }
    }

}

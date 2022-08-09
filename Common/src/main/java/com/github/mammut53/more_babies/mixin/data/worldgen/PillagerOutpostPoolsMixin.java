package com.github.mammut53.more_babies.mixin.data.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.PillagerOutpostPools;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PillagerOutpostPools.class)
public abstract class PillagerOutpostPoolsMixin {

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/data/worldgen/Pools;register(Lnet/minecraft/world/level/levelgen/structure/pools/StructureTemplatePool;)Lnet/minecraft/core/Holder;",
                    ordinal = 3
            )
    )
    private static Holder<StructureTemplatePool> replacePool(StructureTemplatePool structureTemplatePool) {
        return Pools.register(new StructureTemplatePool(new ResourceLocation("pillager_outpost/features"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_cage1"), 1), Pair.of(StructurePoolElement.legacy("more_babies:pillager_outpost/feature_cage1"), 1), Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_cage2"), 1), Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_cage_with_allays"), 1), Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_logs"), 1), Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_tent1"), 1), Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_tent2"), 1), Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_targets"), 1), Pair.of(StructurePoolElement.empty(), 6)), StructureTemplatePool.Projection.RIGID));
    }

}

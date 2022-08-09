package com.github.mammut53.more_babies.mixin.data.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.PlainVillagePools;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlainVillagePools.class)
public abstract class PlainVillagePoolsMixin {

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/data/worldgen/Pools;register(Lnet/minecraft/world/level/levelgen/structure/pools/StructureTemplatePool;)Lnet/minecraft/core/Holder;",
                    ordinal = 15
            )
    )
    private static Holder<StructureTemplatePool> replacePool(StructureTemplatePool structureTemplatePool) {
        return Pools.register(new StructureTemplatePool(new ResourceLocation("village/common/iron_golem"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.legacy("village/common/iron_golem"), 3), Pair.of(StructurePoolElement.legacy("more_babies:village/common/iron_golem"), 1)), StructureTemplatePool.Projection.RIGID));
    }

}

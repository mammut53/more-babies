package com.github.mammut53.more_babies.mixin.data.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BastionSharedPools;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BastionSharedPools.class)
public abstract class BastionSharedPoolsMixin {

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/data/worldgen/Pools;register(Lnet/minecraft/world/level/levelgen/structure/pools/StructureTemplatePool;)Lnet/minecraft/core/Holder;",
                    ordinal = 3
            )
    )
    private static Holder<StructureTemplatePool> replacePool(StructureTemplatePool structureTemplatePool) {
        return Pools.register(new StructureTemplatePool(new ResourceLocation("bastion/mobs/piglin_melee"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/mobs/melee_piglin_always"), 10), Pair.of(StructurePoolElement.single("bastion/mobs/melee_piglin"), 50), Pair.of(StructurePoolElement.single("more_babies:bastion/mobs/melee_baby_piglin_always"), 1), Pair.of(StructurePoolElement.single("more_babies:bastion/mobs/melee_baby_piglin"), 5)), StructureTemplatePool.Projection.RIGID));
    }

}

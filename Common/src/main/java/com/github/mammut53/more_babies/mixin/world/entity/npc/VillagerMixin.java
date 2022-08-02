package com.github.mammut53.more_babies.mixin.world.entity.npc;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Villager.class)
public abstract class VillagerMixin {

    @Redirect(
            method = "thunderHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;"
            )
    )
    private Entity create(EntityType<Entity> entityType, Level level) {
        if (!((Villager) (Object)this).isBaby()) {
            return entityType.create(level);
        }

        return MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.WITCH).create(level);
    }

}

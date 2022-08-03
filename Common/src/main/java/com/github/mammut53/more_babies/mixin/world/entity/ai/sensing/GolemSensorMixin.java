package com.github.mammut53.more_babies.mixin.world.entity.ai.sensing;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.GolemSensor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(GolemSensor.class)
public abstract class GolemSensorMixin {

    @Shadow
    public static void golemDetected(LivingEntity livingEntity) {}

    @Inject(
            method = "checkForNearbyGolem",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void checkForNearbyGolem(LivingEntity livingEntity, CallbackInfo ci) {
        Optional<List<LivingEntity>> optional = livingEntity.getBrain().getMemory(MemoryModuleType.NEAREST_LIVING_ENTITIES);
        if (optional.isEmpty()) {
            ci.cancel();
            return;
        }

        boolean found = ((List) optional.get()).stream().anyMatch((entry) -> ((LivingEntity)entry).getType().equals(EntityType.IRON_GOLEM) || ((LivingEntity)entry).getType().equals(MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.IRON_GOLEM)));
        if (found) {
            golemDetected(livingEntity);
        }

        ci.cancel();
    }

}

package com.github.mammut53.more_babies.mixin.world.item;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.MoreBabiesConstants;
import com.github.mammut53.more_babies.world.entity.EquipedBaby;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.Optional;


@Mixin(SpawnEggItem.class)
public abstract class SpawnEggItemMixin extends Item {

    @Shadow
    public EntityType<?> getType(CompoundTag compoundTag) {
        return null;
    }

    public SpawnEggItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(
            method = "spawnOffspringFromSpawnEgg(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Mob;Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/item/ItemStack;)Ljava/util/Optional;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Mob;isBaby()Z",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void unknownBaby(Player player, Mob mob, EntityType<? extends Mob> entityType, ServerLevel serverLevel, Vec3 vec3, ItemStack itemStack, CallbackInfoReturnable<Optional<Mob>> cir) {
        MoreBabiesConstants.LOG.info("unknownBaby");
        EntityType<? extends Mob> newEntityType;
        if (!mob.isBaby()) {
            newEntityType = MoreBabiesCommon.PARENT_BABY_RELATION.get(mob.getType());
            if (newEntityType == null) return;
        } else {
            newEntityType = entityType;
        }

        Mob mob2 = newEntityType.create(serverLevel);
        if (mob2 instanceof AgeableMob) {
            Mob mob3 = ((AgeableMob)mob2).getBreedOffspring(serverLevel, (AgeableMob)mob);
            if (mob3 != null) mob2 = mob3;
        }

        if (mob2 instanceof EquipedBaby) {
            ((EquipedBaby) mob2).finalizeUnnaturalSpawn();
        }

        mob2.moveTo(vec3.x(), vec3.y(), vec3.z(), 0.0F, 0.0F);
        serverLevel.addFreshEntityWithPassengers(mob2);
        if (itemStack.hasCustomHoverName()) {
           mob2.setCustomName(itemStack.getHoverName());
        }

        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }
        cir.setReturnValue(Optional.of(mob2));
    }

    @Inject(
            method = "spawnsEntity(Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/world/entity/EntityType;)Z",
            at = @At("RETURN"),
            cancellable = true
    )
    private void spawnsEntitys(CompoundTag compoundTag, EntityType<?> entityType, CallbackInfoReturnable<Boolean> cir) {
        MoreBabiesConstants.LOG.info("spawnsEntitys");
        if (Objects.equals(this.getType(compoundTag), entityType)) {
            MoreBabiesConstants.LOG.info("true 1");
            cir.setReturnValue(true);
            return;
        }

        EntityType<? extends Mob> babyType = MoreBabiesCommon.PARENT_BABY_RELATION.get(this.getType(compoundTag));
        if (babyType != null && Objects.equals(babyType, entityType)) {
            MoreBabiesConstants.LOG.info("true 2");
            cir.setReturnValue(true);
            return;
        }
        MoreBabiesConstants.LOG.info("false");
        cir.setReturnValue(false);
    }
}

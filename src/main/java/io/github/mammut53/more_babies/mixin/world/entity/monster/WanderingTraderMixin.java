package io.github.mammut53.more_babies.mixin.world.entity.monster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.npc.wanderingtrader.WanderingTrader;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WanderingTrader.class)
public abstract class WanderingTraderMixin extends AbstractVillager implements Consumable.OverrideConsumeSound {

    protected WanderingTraderMixin(final EntityType<? extends AbstractVillager> type, final Level level) {
        super(type, level);
    }

    @ModifyArg(
            method = "readAdditionalSaveData",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/npc/wanderingtrader/WanderingTrader;setAge(I)V"
            )
    )
    private int injectReadAdditionalSaveDataSetBaby(final int par1) {
        return this.getAge();
    }

}

package io.github.mammut53.more_babies.mixin.world.entity.monster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.illager.AbstractIllager;
import net.minecraft.world.entity.monster.illager.SpellcasterIllager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SpellcasterIllager.class)
public abstract class SpellcasterIllagerMixin extends AbstractIllager {

    protected SpellcasterIllagerMixin(final EntityType<? extends AbstractIllager> type, final Level level) {
        super(type, level);
    }

    @ModifyVariable(
            method = "tick",
            at = @At("STORE"),
            name = "handDistance"
    )
    private double modifyHandDistance(final double handDistance) {
        if (this.isBaby()) {
            return handDistance * 0.75;
        }
        return handDistance;
    }

    @ModifyVariable(
            method = "tick",
            at = @At("STORE"),
            name = "handHeight"
    )
    private double modifyHandHeight(final double handHeight) {
        if (this.isBaby()) {
            return handHeight * 0.4;
        }
        return handHeight;
    }
}

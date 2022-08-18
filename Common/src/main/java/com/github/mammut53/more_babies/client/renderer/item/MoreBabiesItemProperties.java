package com.github.mammut53.more_babies.client.renderer.item;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MoreBabiesItemProperties {

    public static final ClampedItemPropertyFunction CURSED_CLOCK_ITEM_PROPERTY_FUNCTION = new ClampedItemPropertyFunction() {
        private double rotation;
        private double rota;
        private long lastUpdateTick;

        public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i) {
            Entity entity = livingEntity != null ? livingEntity : itemStack.getEntityRepresentation();
            if (entity == null) {
                return 0.0F;
            } else {
                if (clientLevel == null && entity.level instanceof ClientLevel) {
                    clientLevel = (ClientLevel) entity.level;
                }

                if (clientLevel == null) {
                    return 0.0F;
                } else {
                    double d;
                    d = Math.random();
                    d = this.wobble(clientLevel, d);
                    return (float) d;
                }
            }
        }

        private double wobble(Level level, double d) {
            if (level.getGameTime() != this.lastUpdateTick) {
                this.lastUpdateTick = level.getGameTime();
                double e = d - this.rotation;
                e = Mth.positiveModulo(e + 0.5, 1.0) - 0.5;
                this.rota += e * 0.1;
                this.rota *= 0.9;
                this.rotation = Mth.positiveModulo(this.rotation + this.rota, 1.0);
            }

            return this.rotation;
        }
    };

}

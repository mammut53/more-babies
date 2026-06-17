package io.github.mammut53.more_babies.mixin.client.model.monster.cubemob;

import net.minecraft.world.entity.Bucketable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.monster.cubemob.AbstractCubeMob;
import net.minecraft.world.entity.monster.cubemob.SulfurCube;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SulfurCube.class)
public abstract class SulfurCubeMixin extends AbstractCubeMob implements Bucketable, Shearable {

    protected SulfurCubeMixin(final EntityType<? extends AbstractCubeMob> type, final Level level) {
        super(type, level);
    }

    @SuppressWarnings("java:S5803")
    @Override
    public void setBaby(final boolean baby) {
        super.setBaby(baby);
        if (baby) {
            this.setSize(0, false);
        }
    }

}

package io.github.mammut53.more_babies.world.entity.spider;

import net.minecraft.world.entity.monster.spider.Spider;

public class BabySpiderEffectsGroupData extends Spider.SpiderEffectsGroupData {
    private final boolean isBaby;

    public BabySpiderEffectsGroupData(final boolean isBaby) {
        this.isBaby = isBaby;
    }

    public boolean isBaby() {
        return this.isBaby;
    }

}

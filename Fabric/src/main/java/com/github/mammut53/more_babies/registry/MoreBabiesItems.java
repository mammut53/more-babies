package com.github.mammut53.more_babies.registry;

import com.github.mammut53.more_babies.MoreBabiesConstants;
import com.github.mammut53.more_babies.world.item.CursedClockOnAStickItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class MoreBabiesItems {

    public static final Item CURSED_CLOCK = new Item(new FabricItemSettings().group(CreativeModeTab.TAB_TOOLS));
    public static final Item CURSED_CLOCK_ON_A_STICK = new CursedClockOnAStickItem(new FabricItemSettings().group(CreativeModeTab.TAB_TOOLS).stacksTo(1));


    public static void register() {
        Registry.register(Registry.ITEM, new ResourceLocation(MoreBabiesConstants.MOD_ID, "cursed_clock"), CURSED_CLOCK);
        Registry.register(Registry.ITEM, new ResourceLocation(MoreBabiesConstants.MOD_ID, "cursed_clock_on_a_stick"), CURSED_CLOCK_ON_A_STICK);
    }
}

package com.github.mammut53.more_babies.registry;

import com.github.mammut53.more_babies.MoreBabiesConstants;
import com.github.mammut53.more_babies.world.item.CursedClockOnAStickItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MoreBabiesItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreBabiesConstants.MOD_ID);

    public static final RegistryObject<Item> CURSED_CLOCK = ITEMS.register("cursed_clock", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> CURSED_CLOCK_ON_A_STICK = ITEMS.register("cursed_clock_on_a_stick", () -> new CursedClockOnAStickItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1)));
}

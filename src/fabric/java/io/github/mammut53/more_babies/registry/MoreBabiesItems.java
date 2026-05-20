package io.github.mammut53.more_babies.registry;

import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.world.item.CursedClockOnAStickItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class MoreBabiesItems {

    public static final Item CURSED_CLOCK = register("cursed_clock");
    public static final Item CURSED_CLOCK_ON_A_STICK = register(
            "cursed_clock_on_a_stick",
            CursedClockOnAStickItem::new,
            new Item.Properties()
                    .stacksTo(1)
                    .durability(16)
    );

    public static Item register(final String name) {
        return register(name, Item::new);
    }

    public static <T extends Item> T register(final String name, final Function<Item.Properties, T> itemFactory) {
        return register(name, itemFactory, new Item.Properties());
    }

    public static <T extends Item> T register(final String name, final Function<Item.Properties, T> itemFactory, final Item.Properties settings) {
        final ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, name));

        final T item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    private MoreBabiesItems() {
        throw new UnsupportedOperationException();
    }

    public static void init() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(
                        (creativeTab) -> {
                            creativeTab.accept(CURSED_CLOCK);
                            creativeTab.accept(CURSED_CLOCK_ON_A_STICK);
                        }
                );
    }
}

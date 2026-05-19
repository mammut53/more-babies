package io.github.mammut53.more_babies.registry;

import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.world.item.CursedClockOnAStickItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = MoreBabies.MOD_ID)
public class MoreBabiesItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MoreBabies.MOD_ID);

    public static final DeferredItem<Item> CURSED_CLOCK = ITEMS.registerSimpleItem("cursed_clock");
    public static final DeferredItem<CursedClockOnAStickItem> CURSED_CLOCK_ON_A_STICK = ITEMS.registerItem(
            "cursed_clock_on_a_stick",
            CursedClockOnAStickItem::new,
            () -> new Item.Properties()
                    .stacksTo(1)
                    .durability(32)
    );

    public static void register(final IEventBus modBus) {
        ITEMS.register(modBus);
    }

    @SubscribeEvent
    public static void buildCreativeTab(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            for (final DeferredHolder<Item, ? extends Item> item : ITEMS.getEntries()) {
                event.accept(item.get());
            }
        }
    }

    private MoreBabiesItems() {
        throw new UnsupportedOperationException();
    }

}

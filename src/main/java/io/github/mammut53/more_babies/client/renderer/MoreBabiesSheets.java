package io.github.mammut53.more_babies.client.renderer;

import com.google.common.collect.ImmutableList;
import io.github.mammut53.more_babies.MoreBabies;
import net.minecraft.client.renderer.SpriteMapper;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MoreBabiesSheets {

    public static final Identifier SHULKER_SHEET = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/atlas/shulker_boxes.png");

    public static final SpriteMapper SHULKER_MAPPER = new SpriteMapper(SHULKER_SHEET, "entity/shulker");

    public static final SpriteId DEFAULT_SHULKER_BABY_TEXTURE_LOCATION = SHULKER_MAPPER.apply(Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "shulker_baby"));
    public static final List<SpriteId> SHULKER_BABY_TEXTURE_LOCATION = Arrays.stream(DyeColor.values())
            .sorted(Comparator.comparingInt(DyeColor::getId))
            .map(MoreBabiesSheets::createShulkerSprite)
            .collect(ImmutableList.toImmutableList());

    public static Identifier colorToShulkerSprite(final DyeColor color) {
        return Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "shulker_baby_" + color.getName());
    }

    public static SpriteId createShulkerSprite(final DyeColor color) {
        return SHULKER_MAPPER.apply(colorToShulkerSprite(color));
    }

    protected MoreBabiesSheets() {
        throw new UnsupportedOperationException();
    }

}

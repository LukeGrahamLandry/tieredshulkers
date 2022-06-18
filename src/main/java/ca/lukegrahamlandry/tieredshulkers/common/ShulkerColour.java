package ca.lukegrahamlandry.tieredshulkers.common;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

import java.util.Locale;

public enum ShulkerColour {
  WHITE,
  ORANGE,
  MAGENTA,
  LIGHT_BLUE,
  YELLOW,
  LIME,
  PINK,
  GRAY,
  LIGHT_GRAY,
  CYAN,
  PURPLE,
  BLUE,
  BROWN,
  GREEN,
  RED,
  BLACK,
  VANILLA;

  public String getName() {
    return this.name().toLowerCase(Locale.ROOT);
  }

  public static ShulkerColour fromVanilla(DyeColor color){
    if (color == null) return VANILLA;
    return ShulkerColour.valueOf(color.name());
  }

  public static ShulkerColour getColor(ItemStack stack) {
    DyeColor color = DyeColor.getColor(stack);
    if (color != null) return fromVanilla(color);
    return VANILLA;
  }

  public int getId() {
    return this.ordinal();
  }

  public DyeColor toVanilla() {
    if (this == VANILLA) return null;
    return DyeColor.valueOf(this.name());
  }
}

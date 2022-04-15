package com.progwml6.ironshulkerbox.common.recipes;

import com.progwml6.ironshulkerbox.common.block.GenericIronShulkerBlock;
import net.minecraft.block.Block;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ShulkerBoxColoringRecipe extends SpecialRecipe {

  public ShulkerBoxColoringRecipe(ResourceLocation idIn) {
    super(idIn);
  }

  /**
   * Used to check if a recipe matches current crafting inventory
   */
  @Override
  public boolean matches(CraftingInventory inv, World worldIn) {
    int i = 0;
    int j = 0;

    for (int k = 0; k < inv.getContainerSize(); ++k) {
      ItemStack itemstack = inv.getItem(k);
      if (!itemstack.isEmpty()) {
        if (Block.byItem(itemstack.getItem()) instanceof GenericIronShulkerBlock) {
          ++i;
        }
        else {
          if (!itemstack.getItem().is(net.minecraftforge.common.Tags.Items.DYES)) {
            return false;
          }

          ++j;
        }

        if (j > 1 || i > 1) {
          return false;
        }
      }
    }

    return i == 1 && j == 1;
  }

  /**
   * Returns an Item that is the result of this recipe
   */
  @Override
  public ItemStack assemble(CraftingInventory inv) {
    ItemStack itemstack = ItemStack.EMPTY;
    net.minecraft.item.DyeColor dyecolor = net.minecraft.item.DyeColor.WHITE;

    for (int i = 0; i < inv.getContainerSize(); ++i) {
      ItemStack itemstack1 = inv.getItem(i);
      if (!itemstack1.isEmpty()) {
        Item item = itemstack1.getItem();
        if (Block.byItem(item) instanceof GenericIronShulkerBlock) {
          itemstack = itemstack1;
        }
        else {
          net.minecraft.item.DyeColor tmp = net.minecraft.item.DyeColor.getColor(itemstack1);
          if (tmp != null) { dyecolor = tmp; }
        }
      }
    }

    ItemStack itemstack2 = GenericIronShulkerBlock.getColoredItemStack(dyecolor, GenericIronShulkerBlock.getTypeFromItem(itemstack.getItem()));

    if (itemstack.hasTag()) {
      itemstack2.setTag(itemstack.getTag().copy());
    }

    return itemstack2;
  }

  /**
   * Used to determine if this recipe can fit in a grid of the given width/height
   */
  @Override
  public boolean canCraftInDimensions(int width, int height) {
    return width * height >= 2;
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return IronShulkerBoxRecipes.SHULKER_BOX_COLORING;
  }
}

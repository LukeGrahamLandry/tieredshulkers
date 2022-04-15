package com.progwml6.ironshulkerbox.common.inventory;

import com.progwml6.ironshulkerbox.common.block.GenericIronShulkerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ShulkerBoxSlot extends Slot {

  public ShulkerBoxSlot(Container inventoryIn, int slotIndexIn, int xPosition, int yPosition) {
    super(inventoryIn, slotIndexIn, xPosition, yPosition);
  }

  @Override
  public boolean mayPlace(ItemStack stack) {
    return !(Block.byItem(stack.getItem()) instanceof GenericIronShulkerBlock) && !(Block.byItem(stack.getItem()) instanceof ShulkerBoxBlock);
  }
}

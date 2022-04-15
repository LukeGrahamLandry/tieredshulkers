package com.progwml6.ironshulkerbox.common.block.tileentity;

import com.progwml6.ironshulkerbox.common.IronShulkerBoxesTypes;
import com.progwml6.ironshulkerbox.common.block.ShulkerBoxesBlocks;
import com.progwml6.ironshulkerbox.common.inventory.IronShulkerBoxContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeColor;

import javax.annotation.Nullable;

public class CopperShulkerBoxTileEntity extends GenericIronShulkerBoxTileEntity {

  public CopperShulkerBoxTileEntity() {
    this(null);
  }

  public CopperShulkerBoxTileEntity(@Nullable DyeColor colorIn) {
    super(IronShulkerBoxesTileEntityTypes.COPPER_SHULKER_BOX.get(), colorIn, IronShulkerBoxesTypes.COPPER, IronShulkerBoxesTileEntityTypes.createBlockList(IronShulkerBoxesTileEntityTypes.createBlockSet(ShulkerBoxesBlocks.COPPER_SHULKER_BOXES)));
  }

  @Override
  protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
    return IronShulkerBoxContainer.createCopperContainer(id, playerInventory, this);
  }
}

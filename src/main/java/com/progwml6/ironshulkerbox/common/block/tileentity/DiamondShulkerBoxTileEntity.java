package com.progwml6.ironshulkerbox.common.block.tileentity;

import com.progwml6.ironshulkerbox.common.IronShulkerBoxesTypes;
import com.progwml6.ironshulkerbox.common.block.ShulkerBoxesBlocks;
import com.progwml6.ironshulkerbox.common.inventory.IronShulkerBoxContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeColor;

import javax.annotation.Nullable;

public class DiamondShulkerBoxTileEntity extends GenericIronShulkerBoxTileEntity {

  public DiamondShulkerBoxTileEntity() {
    this(null);
  }

  public DiamondShulkerBoxTileEntity(@Nullable DyeColor colorIn) {
    super(IronShulkerBoxesTileEntityTypes.DIAMOND_SHULKER_BOX.get(), colorIn, IronShulkerBoxesTypes.DIAMOND, IronShulkerBoxesTileEntityTypes.createBlockList(IronShulkerBoxesTileEntityTypes.createBlockSet(ShulkerBoxesBlocks.DIAMOND_SHULKER_BOXES)));
  }

  @Override
  protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
    return IronShulkerBoxContainer.createDiamondContainer(id, playerInventory, this);
  }
}

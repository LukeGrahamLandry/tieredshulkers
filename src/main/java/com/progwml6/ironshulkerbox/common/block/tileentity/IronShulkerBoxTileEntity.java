package com.progwml6.ironshulkerbox.common.block.tileentity;

import com.progwml6.ironshulkerbox.common.IronShulkerBoxesTypes;
import com.progwml6.ironshulkerbox.common.block.ShulkerBoxesBlocks;
import com.progwml6.ironshulkerbox.common.inventory.IronShulkerBoxContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeColor;

import javax.annotation.Nullable;

public class IronShulkerBoxTileEntity extends GenericIronShulkerBoxTileEntity {

  public IronShulkerBoxTileEntity() {
    this(null);
  }

  public IronShulkerBoxTileEntity(@Nullable DyeColor colorIn) {
    super(IronShulkerBoxesTileEntityTypes.IRON_SHULKER_BOX.get(), colorIn, IronShulkerBoxesTypes.IRON,
      IronShulkerBoxesTileEntityTypes.createBlockList(IronShulkerBoxesTileEntityTypes.createBlockSet(ShulkerBoxesBlocks.IRON_SHULKER_BOXES)));
  }

  @Override
  protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
    return IronShulkerBoxContainer.createIronContainer(id, playerInventory, this);
  }
}

package com.progwml6.ironshulkerbox.common.block;

import com.progwml6.ironshulkerbox.common.IronShulkerBoxesTypes;
import com.progwml6.ironshulkerbox.common.block.tileentity.DiamondShulkerBoxTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;

public class DiamondShulkerBoxBlock extends GenericIronShulkerBlock {

  public DiamondShulkerBoxBlock(DyeColor color, Properties properties) {
    super(color, properties, IronShulkerBoxesTypes.DIAMOND);
  }

  @Override
  public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
    return new DiamondShulkerBoxTileEntity(this.color);
  }
}

package com.progwml6.ironshulkerbox.common.block;

import com.progwml6.ironshulkerbox.common.IronShulkerBoxesTypes;
import com.progwml6.ironshulkerbox.common.block.tileentity.CopperShulkerBoxTileEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CopperShulkerBoxBlock extends GenericIronShulkerBlock {

  public CopperShulkerBoxBlock(DyeColor color, Properties properties) {
    super(color, properties, IronShulkerBoxesTypes.COPPER);
  }

  @Override
  public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
    return new CopperShulkerBoxTileEntity(this.color);
  }
}

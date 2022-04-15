package com.progwml6.ironshulkerbox.common.block;

import com.progwml6.ironshulkerbox.common.IronShulkerBoxesTypes;
import com.progwml6.ironshulkerbox.common.block.tileentity.IronShulkerBoxTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;

public class IronShulkerBoxBlock extends GenericIronShulkerBlock {

  public IronShulkerBoxBlock(DyeColor color, Properties properties) {
    super(color, properties, IronShulkerBoxesTypes.IRON);
  }

  @Override
  public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
    return new IronShulkerBoxTileEntity(this.color);
  }
}

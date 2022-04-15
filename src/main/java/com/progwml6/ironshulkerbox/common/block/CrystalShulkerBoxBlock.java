package com.progwml6.ironshulkerbox.common.block;

import com.progwml6.ironshulkerbox.common.IronShulkerBoxesTypes;
import com.progwml6.ironshulkerbox.common.block.tileentity.CrystalShulkerBoxTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;

public class CrystalShulkerBoxBlock extends GenericIronShulkerBlock {

  public CrystalShulkerBoxBlock(DyeColor color, Properties properties) {
    super(color, properties, IronShulkerBoxesTypes.CRYSTAL);
  }

  @Override
  public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
    return new CrystalShulkerBoxTileEntity(this.color);
  }
}

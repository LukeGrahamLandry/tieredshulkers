package com.progwml6.ironshulkerbox.common.blocks;

import com.progwml6.ironshulkerbox.common.tileentity.TileEntityCopperShulkerBox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockCopperShulkerBox extends BlockShulkerBox
{
    public BlockCopperShulkerBox(EnumDyeColor color, Properties properties)
    {
        super(color, properties, IronShulkerBoxType.COPPER);
    }

    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world)
    {
        return new TileEntityCopperShulkerBox(this.color);
    }
}

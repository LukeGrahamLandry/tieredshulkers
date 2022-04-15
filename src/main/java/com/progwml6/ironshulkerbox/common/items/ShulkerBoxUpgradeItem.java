package com.progwml6.ironshulkerbox.common.items;

import com.progwml6.ironshulkerbox.common.block.GenericIronShulkerBlock;
import com.progwml6.ironshulkerbox.common.block.IronShulkerBoxesTypes;
import com.progwml6.ironshulkerbox.common.block.tileentity.GenericIronShulkerBoxTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.ShulkerBoxTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.item.Item.Properties;

public class ShulkerBoxUpgradeItem extends Item {

  private final IronShulkerBoxesUpgradeType type;

  public ShulkerBoxUpgradeItem(IronShulkerBoxesUpgradeType type, Properties properties) {
    super(properties);
    this.type = type;
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    if (I18n.exists("ironshulkerbox.upgrade.tooltip")) {
      if (I18n.exists("ironshulkerbox." + this.type.source.name) && I18n.exists("ironshulkerbox." + this.type.target.name)) {
        tooltip.add((new TranslationTextComponent("ironshulkerbox.upgrade.tooltip", new TranslationTextComponent("ironshulkerbox." + this.type.source.name).withStyle(TextFormatting.BOLD), new TranslationTextComponent("ironshulkerbox." + this.type.target.name).withStyle(TextFormatting.BOLD))).withStyle(TextFormatting.DARK_RED));
      }
    }
    if (I18n.exists("ironshulkerbox.color.tooltip")) {
      tooltip.add((new TranslationTextComponent("ironshulkerbox.color.tooltip", 0)).withStyle(TextFormatting.GOLD));
    }

    super.appendHoverText(stack, worldIn, tooltip, flagIn);
  }

  @Override
  public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
    PlayerEntity entityPlayer = context.getPlayer();
    BlockPos blockPos = context.getClickedPos();
    World world = context.getLevel();
    ItemStack itemStack = context.getItemInHand();

    if (world.isClientSide) {
      return ActionResultType.PASS;
    }

    if (this.type.canUpgrade(IronShulkerBoxesTypes.VANILLA)) {
      if (!(world.getBlockState(blockPos).getBlock() instanceof ShulkerBoxBlock)) {
        return ActionResultType.PASS;
      }
    } else {
      if (!(world.getBlockState(blockPos).getBlock() instanceof GenericIronShulkerBlock)) {
        return ActionResultType.PASS;
      } else {
        GenericIronShulkerBlock block = (GenericIronShulkerBlock) world.getBlockState(blockPos).getBlock();
        if (block.defaultBlockState() != IronShulkerBoxesTypes.get(this.type.source, block.getColor())) {
          return ActionResultType.PASS;
        }
      }
    }

    TileEntity tileEntity = world.getBlockEntity(blockPos);
    GenericIronShulkerBoxTileEntity newShulkerBox = null;

    ITextComponent customName = null;

    NonNullList<ItemStack> shulkerBoxContents = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
    Direction shulkerBoxFacing = Direction.UP;
    DyeColor shulkerBoxColor = DyeColor.PURPLE;

    if (tileEntity != null) {
      if (tileEntity instanceof GenericIronShulkerBoxTileEntity) {
        GenericIronShulkerBoxTileEntity shulkerBox = (GenericIronShulkerBoxTileEntity) tileEntity;
        BlockState shulkerBoxState = world.getBlockState(blockPos);

        shulkerBoxContents = NonNullList.<ItemStack>withSize(shulkerBox.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < shulkerBoxContents.size(); i++) {
          shulkerBoxContents.set(i, shulkerBox.getItem(i));
        }

        shulkerBoxFacing = shulkerBoxState.getValue(ShulkerBoxBlock.FACING);
        customName = shulkerBox.getCustomName();
        shulkerBoxColor = shulkerBox.getColor();
        newShulkerBox = this.type.target.makeEntity(shulkerBoxColor);

        shulkerBox.clearContent();
        //shulkerBox.setDestroyedByCreativePlayer(true);

        if (newShulkerBox == null) {
          return ActionResultType.PASS;
        }
      } else if (tileEntity instanceof ShulkerBoxTileEntity) {
        BlockState shulkerBoxState = world.getBlockState(blockPos);
        shulkerBoxFacing = shulkerBoxState.getValue(net.minecraft.block.ShulkerBoxBlock.FACING);
        ShulkerBoxTileEntity shulkerBox = (ShulkerBoxTileEntity) tileEntity;

        if (!this.type.canUpgrade(IronShulkerBoxesTypes.VANILLA)) {
          return ActionResultType.PASS;
        }

        shulkerBoxContents = NonNullList.<ItemStack>withSize(shulkerBox.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < shulkerBoxContents.size(); i++) {
          shulkerBoxContents.set(i, shulkerBox.getItem(i));
        }

        shulkerBoxColor = ((net.minecraft.block.ShulkerBoxBlock) world.getBlockState(blockPos).getBlock()).getColor();

        if (shulkerBoxColor == null) {
          shulkerBoxColor = DyeColor.PURPLE;
        }

        customName = shulkerBox.getCustomName();

        shulkerBox.clearContent();
        //shulkerBox.setDestroyedByCreativePlayer(true);

        newShulkerBox = this.type.target.makeEntity(shulkerBoxColor);
      }
    }

    tileEntity.clearCache();

    world.removeBlock(blockPos, false);
    world.removeBlockEntity(blockPos);

    BlockState iBlockState = IronShulkerBoxesTypes.get(this.type.target, shulkerBoxColor).setValue(ShulkerBoxBlock.FACING, shulkerBoxFacing);

    world.setBlockEntity(blockPos, newShulkerBox);
    world.setBlock(blockPos, iBlockState, 3);

    world.sendBlockUpdated(blockPos, iBlockState, iBlockState, 3);

    TileEntity tileEntity2 = world.getBlockEntity(blockPos);

    if (tileEntity2 instanceof GenericIronShulkerBoxTileEntity) {
      if (customName != null) {
        ((GenericIronShulkerBoxTileEntity) tileEntity2).setCustomName(customName);
      }

      ((GenericIronShulkerBoxTileEntity) tileEntity2).setItems(shulkerBoxContents);
    }

    if (!entityPlayer.abilities.instabuild) {
      itemStack.shrink(1);
    }

    return ActionResultType.SUCCESS;
  }
}

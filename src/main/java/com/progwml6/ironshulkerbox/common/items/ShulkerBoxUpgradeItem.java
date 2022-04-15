package com.progwml6.ironshulkerbox.common.items;

import com.progwml6.ironshulkerbox.common.block.GenericIronShulkerBlock;
import com.progwml6.ironshulkerbox.common.IronShulkerBoxesTypes;
import com.progwml6.ironshulkerbox.common.block.tileentity.GenericIronShulkerBoxTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ShulkerBoxUpgradeItem extends Item {

  private final IronShulkerBoxesUpgradeType type;

  public ShulkerBoxUpgradeItem(IronShulkerBoxesUpgradeType type, Properties properties) {
    super(properties);
    this.type = type;
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
    if (I18n.exists("ironshulkerbox.upgrade.tooltip")) {
      if (I18n.exists("ironshulkerbox." + this.type.source.name) && I18n.exists("ironshulkerbox." + this.type.target.name)) {
        tooltip.add((new TranslatableComponent("ironshulkerbox.upgrade.tooltip", new TranslatableComponent("ironshulkerbox." + this.type.source.name).withStyle(ChatFormatting.BOLD), new TranslatableComponent("ironshulkerbox." + this.type.target.name).withStyle(ChatFormatting.BOLD))).withStyle(ChatFormatting.DARK_RED));
      }
    }
    if (I18n.exists("ironshulkerbox.color.tooltip")) {
      tooltip.add((new TranslatableComponent("ironshulkerbox.color.tooltip", 0)).withStyle(ChatFormatting.GOLD));
    }

    super.appendHoverText(stack, worldIn, tooltip, flagIn);
  }

  @Override
  public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
    Player entityPlayer = context.getPlayer();
    BlockPos blockPos = context.getClickedPos();
    Level world = context.getLevel();
    ItemStack itemStack = context.getItemInHand();

    if (world.isClientSide) {
      return InteractionResult.PASS;
    }

    if (this.type.canUpgrade(IronShulkerBoxesTypes.VANILLA)) {
      if (!(world.getBlockState(blockPos).getBlock() instanceof ShulkerBoxBlock)) {
        return InteractionResult.PASS;
      }
    } else {
      if (!(world.getBlockState(blockPos).getBlock() instanceof GenericIronShulkerBlock)) {
        return InteractionResult.PASS;
      } else {
        GenericIronShulkerBlock block = (GenericIronShulkerBlock) world.getBlockState(blockPos).getBlock();
        if (block.defaultBlockState() != IronShulkerBoxesTypes.get(this.type.source, block.getColor())) {
          return InteractionResult.PASS;
        }
      }
    }

    BlockEntity tileEntity = world.getBlockEntity(blockPos);
    GenericIronShulkerBoxTileEntity newShulkerBox = null;

    Component customName = null;

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
          return InteractionResult.PASS;
        }
      } else if (tileEntity instanceof ShulkerBoxBlockEntity) {
        BlockState shulkerBoxState = world.getBlockState(blockPos);
        shulkerBoxFacing = shulkerBoxState.getValue(net.minecraft.world.level.block.ShulkerBoxBlock.FACING);
        ShulkerBoxBlockEntity shulkerBox = (ShulkerBoxBlockEntity) tileEntity;

        if (!this.type.canUpgrade(IronShulkerBoxesTypes.VANILLA)) {
          return InteractionResult.PASS;
        }

        shulkerBoxContents = NonNullList.<ItemStack>withSize(shulkerBox.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < shulkerBoxContents.size(); i++) {
          shulkerBoxContents.set(i, shulkerBox.getItem(i));
        }

        shulkerBoxColor = ((net.minecraft.world.level.block.ShulkerBoxBlock) world.getBlockState(blockPos).getBlock()).getColor();

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

    BlockEntity tileEntity2 = world.getBlockEntity(blockPos);

    if (tileEntity2 instanceof GenericIronShulkerBoxTileEntity) {
      if (customName != null) {
        ((GenericIronShulkerBoxTileEntity) tileEntity2).setCustomName(customName);
      }

      ((GenericIronShulkerBoxTileEntity) tileEntity2).setItems(shulkerBoxContents);
    }

    if (!entityPlayer.abilities.instabuild) {
      itemStack.shrink(1);
    }

    return InteractionResult.SUCCESS;
  }
}

package ca.lukegrahamlandry.tieredshulkers.common.items;

import ca.lukegrahamlandry.tieredshulkers.common.ShulkerColour;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.UpgradableBoxBlock;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.tile.UpgradableBoxTile;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class BoxUpgradeItem extends Item {

  private final BoxUpgradeType type;

  public BoxUpgradeItem(BoxUpgradeType type, Properties properties) {
    super(properties);
    this.type = type;
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
    if (I18n.exists("tieredshulkers.upgrade.tooltip")) {
      String sourceName = this.type.source == null ? "vanilla" : this.type.source.name;
      String targetName = this.type.target == null ? "vanilla" : this.type.target.name;
      if (I18n.exists("tieredshulkers." + sourceName) && I18n.exists("tieredshulkers." + targetName)) {
        tooltip.add((new TranslatableComponent("tieredshulkers.upgrade.tooltip", new TranslatableComponent("tieredshulkers." + sourceName).withStyle(ChatFormatting.BOLD), new TranslatableComponent("tieredshulkers." + targetName).withStyle(ChatFormatting.BOLD))).withStyle(ChatFormatting.DARK_RED));
      }
    }
    if (I18n.exists("tieredshulkers.color.tooltip")) {
      tooltip.add((new TranslatableComponent("tieredshulkers.color.tooltip", 0)).withStyle(ChatFormatting.GOLD));
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

    if (this.type.canUpgrade(null)) {
      if (!(world.getBlockState(blockPos).getBlock() instanceof ShulkerBoxBlock)) {
        return InteractionResult.PASS;
      }
    } else {
      if (!(world.getBlockState(blockPos).getBlock() instanceof UpgradableBoxBlock)) {
        return InteractionResult.PASS;
      } else {
        UpgradableBoxBlock block = (UpgradableBoxBlock) world.getBlockState(blockPos).getBlock();

        if (!block.getTier().equals(this.type.source)) return InteractionResult.PASS;
      }
    }

    BlockEntity tileEntity = world.getBlockEntity(blockPos);
    UpgradableBoxTile newShulkerBox = null;

    Component customName = null;

    NonNullList<ItemStack> shulkerBoxContents = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
    Direction shulkerBoxFacing = Direction.UP;
    ShulkerColour shulkerBoxColor = ShulkerColour.PURPLE;

    if (tileEntity != null) {
      if (tileEntity instanceof UpgradableBoxTile) {
        UpgradableBoxTile shulkerBox = (UpgradableBoxTile) tileEntity;
        BlockState shulkerBoxState = world.getBlockState(blockPos);

        shulkerBoxContents = NonNullList.<ItemStack>withSize(shulkerBox.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < shulkerBoxContents.size(); i++) {
          shulkerBoxContents.set(i, shulkerBox.getItem(i));
        }

        shulkerBoxFacing = shulkerBoxState.getValue(ShulkerBoxBlock.FACING);
        customName = shulkerBox.getCustomName();
        shulkerBoxColor = shulkerBox.getColor();
        newShulkerBox = this.type.target.tiles.get(shulkerBoxColor).get().create(blockPos, this.type.target.blocks.get(shulkerBoxColor).get().defaultBlockState());

        shulkerBox.clearContent();
        //shulkerBox.setDestroyedByCreativePlayer(true);

        if (newShulkerBox == null) {
          return InteractionResult.PASS;
        }
      } else if (tileEntity instanceof ShulkerBoxBlockEntity) {
        BlockState shulkerBoxState = world.getBlockState(blockPos);
        shulkerBoxFacing = shulkerBoxState.getValue(net.minecraft.world.level.block.ShulkerBoxBlock.FACING);
        ShulkerBoxBlockEntity shulkerBox = (ShulkerBoxBlockEntity) tileEntity;

        if (!this.type.canUpgrade(null)) {
          return InteractionResult.PASS;
        }

        shulkerBoxContents = NonNullList.<ItemStack>withSize(shulkerBox.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < shulkerBoxContents.size(); i++) {
          shulkerBoxContents.set(i, shulkerBox.getItem(i));
        }

        shulkerBoxColor = ShulkerColour.fromVanilla(((net.minecraft.world.level.block.ShulkerBoxBlock) world.getBlockState(blockPos).getBlock()).getColor());

        customName = shulkerBox.getCustomName();

        shulkerBox.clearContent();
        //shulkerBox.setDestroyedByCreativePlayer(true);

        newShulkerBox = this.type.target.tiles.get(shulkerBoxColor).get().create(blockPos, this.type.target.blocks.get(shulkerBoxColor).get().defaultBlockState());
      }
    }

    // tileEntity.clearCache();

    world.removeBlock(blockPos, false);
    world.removeBlockEntity(blockPos);

    BlockState iBlockState = this.type.target.blocks.get(shulkerBoxColor).get().defaultBlockState().setValue(ShulkerBoxBlock.FACING, shulkerBoxFacing);

    // world.setBlockEntity(blockPos, newShulkerBox);
    world.setBlock(blockPos, iBlockState, 3);

    world.sendBlockUpdated(blockPos, iBlockState, iBlockState, 3);

    BlockEntity tileEntity2 = world.getBlockEntity(blockPos);

    if (tileEntity2 instanceof UpgradableBoxTile) {
      if (customName != null) {
        ((UpgradableBoxTile) tileEntity2).setCustomName(customName);
      }

      ((UpgradableBoxTile) tileEntity2).setItems(shulkerBoxContents);
    }

    if (!entityPlayer.getAbilities().instabuild) {
      itemStack.shrink(1);
    }

    return InteractionResult.SUCCESS;
  }
}

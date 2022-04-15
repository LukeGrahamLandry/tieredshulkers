package com.progwml6.ironshulkerbox.common.block;

import com.progwml6.ironshulkerbox.IronShulkerBoxes;
import com.progwml6.ironshulkerbox.common.block.tileentity.GenericIronShulkerBoxTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.block.AbstractBlock.Properties;

public class GenericIronShulkerBlock extends Block {

  public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
  public static final ResourceLocation CONTENTS = new ResourceLocation(IronShulkerBoxes.MOD_ID, "contents");
  private final IronShulkerBoxesTypes type;
  protected final DyeColor color;

  public GenericIronShulkerBlock(DyeColor color, Properties properties, IronShulkerBoxesTypes type) {
    super(properties);
    this.color = color;
    this.type = type;
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
  }

  @Override
  public BlockRenderType getRenderShape(BlockState state) {
    return BlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  @Override
  public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
    if (worldIn.isClientSide) {
      return ActionResultType.SUCCESS;
    }
    else if (player.isSpectator()) {
      return ActionResultType.SUCCESS;
    }
    else {
      TileEntity tileentity = worldIn.getBlockEntity(pos);

      if (tileentity instanceof GenericIronShulkerBoxTileEntity) {
        Direction direction = state.getValue(FACING);
        GenericIronShulkerBoxTileEntity genericIronShulkerBoxTileEntity = (GenericIronShulkerBoxTileEntity) tileentity;
        boolean flag;

        if (genericIronShulkerBoxTileEntity.getAnimationStatus() == GenericIronShulkerBoxTileEntity.AnimationStatus.CLOSED) {
          AxisAlignedBB axisalignedbb = VoxelShapes.block().bounds().expandTowards((double) (0.5F * (float) direction.getStepX()), (double) (0.5F * (float) direction.getStepY()), (double) (0.5F * (float) direction.getStepZ())).contract((double) direction.getStepX(), (double) direction.getStepY(), (double) direction.getStepZ());
          flag = worldIn.noCollision(axisalignedbb.move(pos.relative(direction)));
        }
        else {
          flag = true;
        }

        if (flag) {
          player.openMenu(genericIronShulkerBoxTileEntity);
          player.awardStat(Stats.OPEN_SHULKER_BOX);
        }

        return ActionResultType.SUCCESS;
      }
      else {
        return ActionResultType.PASS;
      }
    }
  }

  @Override
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    return this.defaultBlockState().setValue(FACING, context.getClickedFace());
  }

  @Override
  protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }

  @Override
  public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
    TileEntity tileentity = worldIn.getBlockEntity(pos);

    if (tileentity instanceof GenericIronShulkerBoxTileEntity) {
      GenericIronShulkerBoxTileEntity genericIronShulkerBoxTileEntity = (GenericIronShulkerBoxTileEntity) tileentity;

      if (!worldIn.isClientSide && player.isCreative() && !genericIronShulkerBoxTileEntity.isEmpty()) {
        ItemStack itemstack = getColoredItemStack(this.getColor(), this.getType());
        CompoundNBT compoundnbt = genericIronShulkerBoxTileEntity.saveToNbt(new CompoundNBT());

        if (!compoundnbt.isEmpty()) {
          itemstack.addTagElement("BlockEntityTag", compoundnbt);
        }

        if (genericIronShulkerBoxTileEntity.hasCustomName()) {
          itemstack.setHoverName(genericIronShulkerBoxTileEntity.getCustomName());
        }

        ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), itemstack);
        itementity.setDefaultPickUpDelay();
        worldIn.addFreshEntity(itementity);
      }
      else {
        genericIronShulkerBoxTileEntity.unpackLootTable(player);
      }
    }

    super.playerWillDestroy(worldIn, pos, state, player);
  }

  @Override
  public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
    TileEntity tileentity = builder.getOptionalParameter(LootParameters.BLOCK_ENTITY);
    if (tileentity instanceof GenericIronShulkerBoxTileEntity) {
      GenericIronShulkerBoxTileEntity genericIronShulkerBoxTileEntity = (GenericIronShulkerBoxTileEntity) tileentity;
      builder = builder.withDynamicDrop(CONTENTS, (p_220168_1_, p_220168_2_) -> {
        for (int i = 0; i < genericIronShulkerBoxTileEntity.getContainerSize(); ++i) {
          p_220168_2_.accept(genericIronShulkerBoxTileEntity.getItem(i));
        }
      });
    }

    return super.getDrops(state, builder);
  }


  @Override
  public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
    if (stack.hasCustomHoverName()) {
      TileEntity tileentity = worldIn.getBlockEntity(pos);

      if (tileentity instanceof GenericIronShulkerBoxTileEntity) {
        ((GenericIronShulkerBoxTileEntity) tileentity).setCustomName(stack.getHoverName());
      }
    }
  }

  @Override
  public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
    if (state.getBlock() != newState.getBlock()) {
      TileEntity tileentity = worldIn.getBlockEntity(pos);

      if (tileentity instanceof GenericIronShulkerBoxTileEntity) {
        worldIn.updateNeighbourForOutputSignal(pos, state.getBlock());
      }

      super.onRemove(state, worldIn, pos, newState, isMoving);
    }
  }

  @OnlyIn(Dist.CLIENT)
  @Override
  public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    super.appendHoverText(stack, worldIn, tooltip, flagIn);
    CompoundNBT compoundnbt = stack.getTagElement("BlockEntityTag");

    if (compoundnbt != null) {
      if (compoundnbt.contains("LootTable", 8)) {
        tooltip.add(new StringTextComponent("???????"));
      }

      if (compoundnbt.contains("Items", 9)) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compoundnbt, nonnulllist);
        int i = 0;
        int j = 0;

        for (ItemStack itemstack : nonnulllist) {
          if (!itemstack.isEmpty()) {
            ++j;
            if (i <= 4) {
              ++i;
              IFormattableTextComponent itextcomponent = itemstack.getHoverName().copy();
              itextcomponent.append(" x").append(String.valueOf(itemstack.getCount()));
              tooltip.add(itextcomponent);
            }
          }
        }

        if (j - i > 0) {
          tooltip.add((new TranslationTextComponent("container.shulkerBox.more", j - i)).withStyle(TextFormatting.ITALIC));
        }
      }
    }

  }

  @Override
  public PushReaction getPistonPushReaction(BlockState state) {
    return PushReaction.DESTROY;
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    TileEntity tileentity = worldIn.getBlockEntity(pos);
    return tileentity instanceof GenericIronShulkerBoxTileEntity ? VoxelShapes.create(((GenericIronShulkerBoxTileEntity) tileentity).getBoundingBox(state)) : VoxelShapes.block();
  }

  @Override
  public boolean hasAnalogOutputSignal(BlockState state) {
    return true;
  }

  @Override
  public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
    return Container.getRedstoneSignalFromContainer((IInventory) worldIn.getBlockEntity(pos));
  }

  @Override
  public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
    ItemStack itemstack = super.getCloneItemStack(worldIn, pos, state);
    GenericIronShulkerBoxTileEntity genericIronShulkerBoxTileEntity = (GenericIronShulkerBoxTileEntity) worldIn.getBlockEntity(pos);
    CompoundNBT compoundnbt = genericIronShulkerBoxTileEntity.saveToNbt(new CompoundNBT());
    if (!compoundnbt.isEmpty()) {
      itemstack.addTagElement("BlockEntityTag", compoundnbt);
    }

    return itemstack;
  }

  public static IronShulkerBoxesTypes getTypeFromItem(Item itemIn) {
    return getTypeFromBlock(Block.byItem(itemIn));
  }

  public static IronShulkerBoxesTypes getTypeFromBlock(Block blockIn) {
    return blockIn instanceof GenericIronShulkerBlock ? ((GenericIronShulkerBlock) blockIn).getType() : null;
  }

  public IronShulkerBoxesTypes getType() {
    return this.type;
  }

  @Nullable
  public static DyeColor getColorFromItem(Item itemIn) {
    return getColorFromBlock(Block.byItem(itemIn));
  }

  @Nullable
  public static DyeColor getColorFromBlock(Block blockIn) {
    return blockIn instanceof GenericIronShulkerBlock ? ((GenericIronShulkerBlock) blockIn).getColor() : null;
  }

  public static Block getBlockByColor(DyeColor colorIn, IronShulkerBoxesTypes typeIn) {
    switch (typeIn) {
      case IRON:
        return ShulkerBoxesBlocks.IRON_SHULKER_BOXES.get(colorIn.getId()).get();
      case GOLD:
        return ShulkerBoxesBlocks.GOLD_SHULKER_BOXES.get(colorIn.getId()).get();
      case DIAMOND:
        return ShulkerBoxesBlocks.DIAMOND_SHULKER_BOXES.get(colorIn.getId()).get();
      case COPPER:
        return ShulkerBoxesBlocks.COPPER_SHULKER_BOXES.get(colorIn.getId()).get();
      case SILVER:
        return ShulkerBoxesBlocks.SILVER_SHULKER_BOXES.get(colorIn.getId()).get();
      case CRYSTAL:
        return ShulkerBoxesBlocks.CRYSTAL_SHULKER_BOXES.get(colorIn.getId()).get();
      case OBSIDIAN:
        return ShulkerBoxesBlocks.OBSIDIAN_SHULKER_BOXES.get(colorIn.getId()).get();
      default:
        return ShulkerBoxesBlocks.BLACK_IRON_SHULKER_BOX.get();
    }
  }

  @Nullable
  public DyeColor getColor() {
    return this.color;
  }

  public static ItemStack getColoredItemStack(DyeColor colorIn, IronShulkerBoxesTypes typeIn) {
    return new ItemStack(getBlockByColor(colorIn, typeIn));
  }

  @Override
  public BlockState rotate(BlockState state, Rotation rot) {
    return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
  }

  @Override
  public BlockState mirror(BlockState state, Mirror mirrorIn) {
    return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
  }

  @Override
  public boolean triggerEvent(BlockState state, World worldIn, BlockPos pos, int id, int param) {
    super.triggerEvent(state, worldIn, pos, id, param);
    TileEntity tileentity = worldIn.getBlockEntity(pos);
    return tileentity == null ? false : tileentity.triggerEvent(id, param);
  }

  @Override
  @Nullable
  public INamedContainerProvider getMenuProvider(BlockState state, World world, BlockPos pos) {
    TileEntity tileentity = world.getBlockEntity(pos);
    return tileentity instanceof INamedContainerProvider ? (INamedContainerProvider) tileentity : null;
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return true;
  }
}

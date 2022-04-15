package com.progwml6.ironshulkerbox.common.block;

import com.progwml6.ironshulkerbox.IronShulkerBoxes;
import com.progwml6.ironshulkerbox.common.IronShulkerBoxesTypes;
import com.progwml6.ironshulkerbox.common.block.tileentity.GenericIronShulkerBoxTileEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

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
  public RenderShape getRenderShape(BlockState state) {
    return RenderShape.ENTITYBLOCK_ANIMATED;
  }

  @Override
  public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult p_225533_6_) {
    if (worldIn.isClientSide) {
      return InteractionResult.SUCCESS;
    }
    else if (player.isSpectator()) {
      return InteractionResult.SUCCESS;
    }
    else {
      BlockEntity tileentity = worldIn.getBlockEntity(pos);

      if (tileentity instanceof GenericIronShulkerBoxTileEntity) {
        Direction direction = state.getValue(FACING);
        GenericIronShulkerBoxTileEntity genericIronShulkerBoxTileEntity = (GenericIronShulkerBoxTileEntity) tileentity;
        boolean flag;

        if (genericIronShulkerBoxTileEntity.getAnimationStatus() == GenericIronShulkerBoxTileEntity.AnimationStatus.CLOSED) {
          AABB axisalignedbb = Shapes.block().bounds().expandTowards((double) (0.5F * (float) direction.getStepX()), (double) (0.5F * (float) direction.getStepY()), (double) (0.5F * (float) direction.getStepZ())).contract((double) direction.getStepX(), (double) direction.getStepY(), (double) direction.getStepZ());
          flag = worldIn.noCollision(axisalignedbb.move(pos.relative(direction)));
        }
        else {
          flag = true;
        }

        if (flag) {
          player.openMenu(genericIronShulkerBoxTileEntity);
          player.awardStat(Stats.OPEN_SHULKER_BOX);
        }

        return InteractionResult.SUCCESS;
      }
      else {
        return InteractionResult.PASS;
      }
    }
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    return this.defaultBlockState().setValue(FACING, context.getClickedFace());
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }

  @Override
  public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
    BlockEntity tileentity = worldIn.getBlockEntity(pos);

    if (tileentity instanceof GenericIronShulkerBoxTileEntity) {
      GenericIronShulkerBoxTileEntity genericIronShulkerBoxTileEntity = (GenericIronShulkerBoxTileEntity) tileentity;

      if (!worldIn.isClientSide && player.isCreative() && !genericIronShulkerBoxTileEntity.isEmpty()) {
        ItemStack itemstack = getColoredItemStack(this.getColor(), this.getType());
        CompoundTag compoundnbt = genericIronShulkerBoxTileEntity.saveToNbt(new CompoundTag());

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
    BlockEntity tileentity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
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
  public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
    if (stack.hasCustomHoverName()) {
      BlockEntity tileentity = worldIn.getBlockEntity(pos);

      if (tileentity instanceof GenericIronShulkerBoxTileEntity) {
        ((GenericIronShulkerBoxTileEntity) tileentity).setCustomName(stack.getHoverName());
      }
    }
  }

  @Override
  public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
    if (state.getBlock() != newState.getBlock()) {
      BlockEntity tileentity = worldIn.getBlockEntity(pos);

      if (tileentity instanceof GenericIronShulkerBoxTileEntity) {
        worldIn.updateNeighbourForOutputSignal(pos, state.getBlock());
      }

      super.onRemove(state, worldIn, pos, newState, isMoving);
    }
  }

  @OnlyIn(Dist.CLIENT)
  @Override
  public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn) {
    super.appendHoverText(stack, worldIn, tooltip, flagIn);
    CompoundTag compoundnbt = stack.getTagElement("BlockEntityTag");

    if (compoundnbt != null) {
      if (compoundnbt.contains("LootTable", 8)) {
        tooltip.add(new TextComponent("???????"));
      }

      if (compoundnbt.contains("Items", 9)) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compoundnbt, nonnulllist);
        int i = 0;
        int j = 0;

        for (ItemStack itemstack : nonnulllist) {
          if (!itemstack.isEmpty()) {
            ++j;
            if (i <= 4) {
              ++i;
              MutableComponent itextcomponent = itemstack.getHoverName().copy();
              itextcomponent.append(" x").append(String.valueOf(itemstack.getCount()));
              tooltip.add(itextcomponent);
            }
          }
        }

        if (j - i > 0) {
          tooltip.add((new TranslatableComponent("container.shulkerBox.more", j - i)).withStyle(ChatFormatting.ITALIC));
        }
      }
    }

  }

  @Override
  public PushReaction getPistonPushReaction(BlockState state) {
    return PushReaction.DESTROY;
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
    BlockEntity tileentity = worldIn.getBlockEntity(pos);
    return tileentity instanceof GenericIronShulkerBoxTileEntity ? Shapes.create(((GenericIronShulkerBoxTileEntity) tileentity).getBoundingBox(state)) : Shapes.block();
  }

  @Override
  public boolean hasAnalogOutputSignal(BlockState state) {
    return true;
  }

  @Override
  public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos) {
    return AbstractContainerMenu.getRedstoneSignalFromContainer((Container) worldIn.getBlockEntity(pos));
  }

  @Override
  public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
    ItemStack itemstack = super.getCloneItemStack(worldIn, pos, state);
    GenericIronShulkerBoxTileEntity genericIronShulkerBoxTileEntity = (GenericIronShulkerBoxTileEntity) worldIn.getBlockEntity(pos);
    CompoundTag compoundnbt = genericIronShulkerBoxTileEntity.saveToNbt(new CompoundTag());
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
  public boolean triggerEvent(BlockState state, Level worldIn, BlockPos pos, int id, int param) {
    super.triggerEvent(state, worldIn, pos, id, param);
    BlockEntity tileentity = worldIn.getBlockEntity(pos);
    return tileentity == null ? false : tileentity.triggerEvent(id, param);
  }

  @Override
  @Nullable
  public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
    BlockEntity tileentity = world.getBlockEntity(pos);
    return tileentity instanceof MenuProvider ? (MenuProvider) tileentity : null;
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return true;
  }
}

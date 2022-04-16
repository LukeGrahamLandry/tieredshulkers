package ca.lukegrahamlandry.tieredshulkers.common.boxes.tile;

import ca.lukegrahamlandry.tieredshulkers.common.boxes.UpgradableBoxTier;
import ca.lukegrahamlandry.tieredshulkers.common.network.PacketHandler;
import ca.lukegrahamlandry.tieredshulkers.common.network.PacketTopStackSyncShulkerBox;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;

public class CrystalShulkerBoxTileEntity extends UpgradableBoxTile {

  private NonNullList<ItemStack> topStacks;

  private boolean inventoryTouched;

  private boolean hadStuff;

  public CrystalShulkerBoxTileEntity(@Nullable DyeColor colorIn, BlockPos pos, BlockState state) {
    super(colorIn, UpgradableBoxTier.CRYSTAL, pos, state);
    this.topStacks = NonNullList.<ItemStack>withSize(8, ItemStack.EMPTY);
  }

  @Override
  public void tick() {
    super.tick();

    if (!this.level.isClientSide && this.inventoryTouched) {
      this.inventoryTouched = false;

      this.sortTopStacks();
    }
  }

  @Override
  public void setItems(NonNullList<ItemStack> contents) {
    super.setItems(contents);

    this.inventoryTouched = true;
  }

  @Override
  public ItemStack getItem(int index) {
    this.inventoryTouched = true;

    return super.getItem(index);
  }

  public NonNullList<ItemStack> getTopItems() {
    return this.topStacks;
  }

  private void sortTopStacks() {
    if (!this.getShulkerBoxType().isTransparent() || (this.level != null && this.level.isClientSide)) {
      return;
    }

    NonNullList<ItemStack> tempCopy = NonNullList.<ItemStack>withSize(this.getContainerSize(), ItemStack.EMPTY);

    boolean hasStuff = false;

    int compressedIdx = 0;

    mainLoop:
    for (int i = 0; i < this.getContainerSize(); i++) {
      ItemStack itemStack = this.getItems().get(i);

      if (!itemStack.isEmpty()) {
        for (int j = 0; j < compressedIdx; j++) {
          ItemStack tempCopyStack = tempCopy.get(j);

          if (ItemStack.isSameIgnoreDurability(tempCopyStack, itemStack)) {
            if (itemStack.getCount() != tempCopyStack.getCount()) {
              tempCopyStack.grow(itemStack.getCount());
            }

            continue mainLoop;
          }
        }

        tempCopy.set(compressedIdx, itemStack.copy());

        compressedIdx++;

        hasStuff = true;
      }
    }

    if (!hasStuff && this.hadStuff) {
      this.hadStuff = false;

      for (int i = 0; i < this.getTopItems().size(); i++) {
        this.getTopItems().set(i, ItemStack.EMPTY);
      }

      if (this.level != null) {
        BlockState iblockstate = this.level.getBlockState(this.worldPosition);

        this.level.sendBlockUpdated(this.worldPosition, iblockstate, iblockstate, 3);
      }

      return;
    }

    this.hadStuff = true;

    tempCopy.sort((stack1, stack2) -> {
      if (stack1.isEmpty()) {
        return 1;
      }
      else if (stack2.isEmpty()) {
        return -1;
      }
      else {
        return stack2.getCount() - stack1.getCount();
      }
    });

    int p = 0;

    for (ItemStack element : tempCopy) {
      if (!element.isEmpty() && element.getCount() > 0) {
        if (p == this.getTopItems().size()) {
          break;
        }

        this.getTopItems().set(p, element);

        p++;
      }
    }

    for (int i = p; i < this.getTopItems().size(); i++) {
      this.getTopItems().set(i, ItemStack.EMPTY);
    }

    if (this.level != null) {
      BlockState iblockstate = this.level.getBlockState(this.worldPosition);

      this.level.sendBlockUpdated(this.worldPosition, iblockstate, iblockstate, 3);
    }

    this.sendTopStacksPacket();
  }

  public NonNullList<ItemStack> buildItemStackDataList() {
    if (this.getShulkerBoxType().isTransparent()) {
      NonNullList<ItemStack> sortList = NonNullList.<ItemStack>withSize(this.getTopItems().size(), ItemStack.EMPTY);

      int pos = 0;

      for (ItemStack is : this.topStacks) {
        if (!is.isEmpty()) {
          sortList.set(pos, is);
        }
        else {
          sortList.set(pos, ItemStack.EMPTY);
        }

        pos++;
      }

      return sortList;
    }

    return NonNullList.<ItemStack>withSize(this.getTopItems().size(), ItemStack.EMPTY);
  }

  protected void sendTopStacksPacket() {
    NonNullList<ItemStack> stacks = this.buildItemStackDataList();

    PacketHandler.send(PacketDistributor.TRACKING_CHUNK.with(() -> (LevelChunk) this.getLevel().getChunk(this.getBlockPos())), new PacketTopStackSyncShulkerBox(this.getBlockPos(), stacks));
  }

  public void receiveMessageFromServer(NonNullList<ItemStack> topStacks) {
    this.topStacks = topStacks;
  }
}

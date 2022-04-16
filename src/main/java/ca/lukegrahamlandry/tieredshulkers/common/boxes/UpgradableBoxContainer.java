package ca.lukegrahamlandry.tieredshulkers.common.boxes;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class UpgradableBoxContainer extends AbstractContainerMenu {

  private final Container inventory;
  private final UpgradableBoxTier shulkerBoxType;

  public UpgradableBoxContainer(UpgradableBoxTier shulkerBoxType, int windowId, Inventory playerInventory, Container inventory) {
    super(shulkerBoxType.menu.get(), windowId);
    checkContainerSize(inventory, shulkerBoxType.size);

    this.inventory = inventory;
    this.shulkerBoxType = shulkerBoxType;

    inventory.startOpen(playerInventory.player);

    for (int shulkerBoxRow = 0; shulkerBoxRow < shulkerBoxType.getRowCount(); shulkerBoxRow++) {
      for (int shulkerBoxCol = 0; shulkerBoxCol < shulkerBoxType.rowLength; shulkerBoxCol++) {
        this.addSlot(new ShulkerBoxSlot(inventory, shulkerBoxCol + shulkerBoxRow * shulkerBoxType.rowLength, 12 + shulkerBoxCol * 18, 18 + shulkerBoxRow * 18));
      }
    }

    int leftCol = (shulkerBoxType.xSize - 162) / 2 + 1;

    for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
      for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
        this.addSlot(new Slot(playerInventory, playerInvCol + playerInvRow * 9 + 9, leftCol + playerInvCol * 18, shulkerBoxType.ySize - (4 - playerInvRow) * 18 - 10));
      }

    }

    for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
      this.addSlot(new Slot(playerInventory, hotbarSlot, leftCol + hotbarSlot * 18, shulkerBoxType.ySize - 24));
    }
  }

  @Override
  public boolean stillValid(Player playerIn) {
    return this.inventory.stillValid(playerIn);
  }

  @Override
  public ItemStack quickMoveStack(Player playerIn, int index) {
    ItemStack itemstack = ItemStack.EMPTY;
    Slot slot = this.slots.get(index);

    if (slot != null && slot.hasItem()) {
      ItemStack itemstack1 = slot.getItem();
      itemstack = itemstack1.copy();

      if (index < this.shulkerBoxType.size) {
        if (!this.moveItemStackTo(itemstack1, this.shulkerBoxType.size, this.slots.size(), true)) {
          return ItemStack.EMPTY;
        }
      } else if (!this.moveItemStackTo(itemstack1, 0, this.shulkerBoxType.size, false)) {
        return ItemStack.EMPTY;
      }

      if (itemstack1.isEmpty()) {
        slot.set(ItemStack.EMPTY);
      } else {
        slot.setChanged();
      }
    }

    return itemstack;
  }

  @Override
  public void removed(Player playerIn) {
    super.removed(playerIn);
    this.inventory.stopOpen(playerIn);
  }

  @OnlyIn(Dist.CLIENT)
  public UpgradableBoxTier getShulkerBoxType() {
    return this.shulkerBoxType;
  }


  public static class ShulkerBoxSlot extends Slot {
    public ShulkerBoxSlot(Container inventoryIn, int slotIndexIn, int xPosition, int yPosition) {
      super(inventoryIn, slotIndexIn, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
      return !(Block.byItem(stack.getItem()) instanceof UpgradableBoxBlock) && !(Block.byItem(stack.getItem()) instanceof ShulkerBoxBlock);
    }
  }
}

package com.progwml6.ironshulkerbox.common.inventory;

import com.progwml6.ironshulkerbox.common.IronShulkerBoxesTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IronShulkerBoxContainer extends AbstractContainerMenu {

  private final Container inventory;

  private final IronShulkerBoxesTypes shulkerBoxType;

  private IronShulkerBoxContainer(MenuType<?> containerType, int windowId, Inventory playerInventory) {
    this(containerType, windowId, playerInventory, new SimpleContainer(IronShulkerBoxesTypes.VANILLA.size), IronShulkerBoxesTypes.VANILLA);
  }

  public static IronShulkerBoxContainer createIronContainer(int windowId, Inventory playerInventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.IRON_SHULKER_BOX.get(), windowId, playerInventory, new SimpleContainer(IronShulkerBoxesTypes.IRON.size), IronShulkerBoxesTypes.IRON);
  }

  public static IronShulkerBoxContainer createIronContainer(int windowId, Inventory playerInventory, Container inventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.IRON_SHULKER_BOX.get(), windowId, playerInventory, inventory, IronShulkerBoxesTypes.IRON);
  }

  public static IronShulkerBoxContainer createGoldContainer(int windowId, Inventory playerInventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.GOLD_SHULKER_BOX.get(), windowId, playerInventory, new SimpleContainer(IronShulkerBoxesTypes.GOLD.size), IronShulkerBoxesTypes.GOLD);
  }

  public static IronShulkerBoxContainer createGoldContainer(int windowId, Inventory playerInventory, Container inventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.GOLD_SHULKER_BOX.get(), windowId, playerInventory, inventory, IronShulkerBoxesTypes.GOLD);
  }

  public static IronShulkerBoxContainer createDiamondContainer(int windowId, Inventory playerInventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.DIAMOND_SHULKER_BOX.get(), windowId, playerInventory, new SimpleContainer(IronShulkerBoxesTypes.DIAMOND.size), IronShulkerBoxesTypes.DIAMOND);
  }

  public static IronShulkerBoxContainer createDiamondContainer(int windowId, Inventory playerInventory, Container inventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.DIAMOND_SHULKER_BOX.get(), windowId, playerInventory, inventory, IronShulkerBoxesTypes.DIAMOND);
  }

  public static IronShulkerBoxContainer createCrystalContainer(int windowId, Inventory playerInventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.CRYSTAL_SHULKER_BOX.get(), windowId, playerInventory, new SimpleContainer(IronShulkerBoxesTypes.CRYSTAL.size), IronShulkerBoxesTypes.CRYSTAL);
  }

  public static IronShulkerBoxContainer createCrystalContainer(int windowId, Inventory playerInventory, Container inventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.CRYSTAL_SHULKER_BOX.get(), windowId, playerInventory, inventory, IronShulkerBoxesTypes.CRYSTAL);
  }

  public static IronShulkerBoxContainer createCopperContainer(int windowId, Inventory playerInventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.COPPER_SHULKER_BOX.get(), windowId, playerInventory, new SimpleContainer(IronShulkerBoxesTypes.COPPER.size), IronShulkerBoxesTypes.COPPER);
  }

  public static IronShulkerBoxContainer createCopperContainer(int windowId, Inventory playerInventory, Container inventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.COPPER_SHULKER_BOX.get(), windowId, playerInventory, inventory, IronShulkerBoxesTypes.COPPER);
  }

  public static IronShulkerBoxContainer createSilverContainer(int windowId, Inventory playerInventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.SILVER_SHULKER_BOX.get(), windowId, playerInventory, new SimpleContainer(IronShulkerBoxesTypes.CRYSTAL.size), IronShulkerBoxesTypes.SILVER);
  }

  public static IronShulkerBoxContainer createSilverContainer(int windowId, Inventory playerInventory, Container inventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.SILVER_SHULKER_BOX.get(), windowId, playerInventory, inventory, IronShulkerBoxesTypes.SILVER);
  }

  public static IronShulkerBoxContainer createObsidianContainer(int windowId, Inventory playerInventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.OBSIDIAN_SHULKER_BOX.get(), windowId, playerInventory, new SimpleContainer(IronShulkerBoxesTypes.OBSIDIAN.size), IronShulkerBoxesTypes.OBSIDIAN);
  }

  public static IronShulkerBoxContainer createObsidianContainer(int windowId, Inventory playerInventory, Container inventory) {
    return new IronShulkerBoxContainer(IronShulkerBoxesContainerTypes.OBSIDIAN_SHULKER_BOX.get(), windowId, playerInventory, inventory, IronShulkerBoxesTypes.OBSIDIAN);
  }

  public IronShulkerBoxContainer(MenuType<?> containerType, int windowId, Inventory playerInventory, Container inventory, IronShulkerBoxesTypes shulkerBoxType) {
    super(containerType, windowId);
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
  public IronShulkerBoxesTypes getShulkerBoxType() {
    return this.shulkerBoxType;
  }
}

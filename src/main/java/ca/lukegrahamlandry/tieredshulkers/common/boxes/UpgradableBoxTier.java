package ca.lukegrahamlandry.tieredshulkers.common.boxes;

import ca.lukegrahamlandry.tieredshulkers.TieredShulkersMain;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.tile.UpgradableBoxTile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.HashMap;
import java.util.function.Supplier;

public enum UpgradableBoxTier implements StringRepresentable {
  IRON(54, 9, 184, 222, 256, 256),
  GOLD(81, 9, 184, 276, 256, 276),
  DIAMOND(108, 12, 238, 276, 256, 276),
  COPPER(45, 9, 184, 204, 256, 256),
  SILVER(72, 9, 184, 258, 256, 276),
  CRYSTAL(108, 12, 238, 276, 256, 276),
  OBSIDIAN(108, 12, 238, 276, 256, 276);

  public final String name;
  public final int size;
  public final int rowLength;
  public final String modelTexture;
  public final int xSize;
  public final int ySize;
  public final ResourceLocation guiTexture;
  public final int textureXSize;
  public final int textureYSize;
  public HashMap<DyeColor, Supplier<BlockItem>> items;
  public HashMap<DyeColor, Supplier<Block>> blocks;
  public HashMap<DyeColor, Supplier<BlockEntityType<UpgradableBoxTile>>> tiles = new HashMap<>();
  public Supplier<MenuType<UpgradableBoxContainer>> menu;


  UpgradableBoxTier(int size, int rowLength, int xSize, int ySize, int textureXSize, int textureYSize) {
    this.name = this.name().toLowerCase();
    this.size = size;
    this.rowLength = rowLength;
    this.modelTexture = "_" + name + ".png";
    this.xSize = xSize;
    this.ySize = ySize;
    this.guiTexture = new ResourceLocation(TieredShulkersMain.MOD_ID, "textures/gui/" + name + "_container.png");
    this.textureXSize = textureXSize;
    this.textureYSize = textureYSize;
  }

  public String getEnglishName() {
    return this.name;
  }

  @Override
  public String getSerializedName() {
    return this.getEnglishName();
  }

  public int getRowCount() {
    return this.size / this.rowLength;
  }

  public boolean isTransparent() {
    return this == CRYSTAL;
  }

}

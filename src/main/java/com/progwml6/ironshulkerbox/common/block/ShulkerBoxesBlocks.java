package com.progwml6.ironshulkerbox.common.block;

import com.google.common.collect.ImmutableList;
import com.progwml6.ironshulkerbox.IronShulkerBoxes;
import com.progwml6.ironshulkerbox.client.tileentity.IronShulkerBoxItemStackRenderer;
import com.progwml6.ironshulkerbox.common.block.tileentity.CopperShulkerBoxTileEntity;
import com.progwml6.ironshulkerbox.common.block.tileentity.CrystalShulkerBoxTileEntity;
import com.progwml6.ironshulkerbox.common.block.tileentity.DiamondShulkerBoxTileEntity;
import com.progwml6.ironshulkerbox.common.block.tileentity.GenericIronShulkerBoxTileEntity;
import com.progwml6.ironshulkerbox.common.block.tileentity.GoldShulkerBoxTileEntity;
import com.progwml6.ironshulkerbox.common.block.tileentity.IronShulkerBoxTileEntity;
import com.progwml6.ironshulkerbox.common.block.tileentity.ObsidianShulkerBoxTileEntity;
import com.progwml6.ironshulkerbox.common.block.tileentity.SilverShulkerBoxTileEntity;
import com.progwml6.ironshulkerbox.common.items.IronShulkerBoxesItems;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

public class ShulkerBoxesBlocks {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IronShulkerBoxes.MOD_ID);
  public static final DeferredRegister<Item> ITEMS = IronShulkerBoxesItems.ITEMS;

  static BlockBehaviour.StatePredicate positionPredicate = (p_235444_0_, world, pos) -> {
    BlockEntity tileentity = world.getBlockEntity(pos);

    if (!(tileentity instanceof GenericIronShulkerBoxTileEntity)) {
      return true;
    }
    else {
      GenericIronShulkerBoxTileEntity genericIronShulkerBoxTileEntity = (GenericIronShulkerBoxTileEntity) tileentity;
      return genericIronShulkerBoxTileEntity.isClosed();
    }
  };

  public static final BlockBehaviour.Properties VIEW_BLOCKING = BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 3.0F).dynamicShape().noOcclusion()
    .isSuffocating(positionPredicate).isViewBlocking(positionPredicate);
  // Iron Start
  public static final RegistryObject<IronShulkerBoxBlock> WHITE_IRON_SHULKER_BOX = register(
    "iron_shulker_box_white", () -> new IronShulkerBoxBlock(DyeColor.WHITE,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.WHITE));
  public static final RegistryObject<IronShulkerBoxBlock> ORANGE_IRON_SHULKER_BOX = register(
    "iron_shulker_box_orange", () -> new IronShulkerBoxBlock(DyeColor.ORANGE,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.ORANGE));
  public static final RegistryObject<IronShulkerBoxBlock> MAGENTA_IRON_SHULKER_BOX = register(
    "iron_shulker_box_magenta", () -> new IronShulkerBoxBlock(DyeColor.MAGENTA,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.MAGENTA));
  public static final RegistryObject<IronShulkerBoxBlock> LIGHT_BLUE_IRON_SHULKER_BOX = register(
    "iron_shulker_box_light_blue", () -> new IronShulkerBoxBlock(DyeColor.LIGHT_BLUE,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.LIGHT_BLUE));
  public static final RegistryObject<IronShulkerBoxBlock> YELLOW_IRON_SHULKER_BOX = register(
    "iron_shulker_box_yellow", () -> new IronShulkerBoxBlock(DyeColor.YELLOW,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.YELLOW));
  public static final RegistryObject<IronShulkerBoxBlock> LIME_IRON_SHULKER_BOX = register(
    "iron_shulker_box_lime", () -> new IronShulkerBoxBlock(DyeColor.LIME,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.LIME));
  public static final RegistryObject<IronShulkerBoxBlock> PINK_IRON_SHULKER_BOX = register(
    "iron_shulker_box_pink", () -> new IronShulkerBoxBlock(DyeColor.PINK,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.PINK));
  public static final RegistryObject<IronShulkerBoxBlock> GRAY_IRON_SHULKER_BOX = register(
    "iron_shulker_box_gray", () -> new IronShulkerBoxBlock(DyeColor.GRAY,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.GRAY));
  public static final RegistryObject<IronShulkerBoxBlock> LIGHT_GRAY_IRON_SHULKER_BOX = register(
    "iron_shulker_box_light_gray", () -> new IronShulkerBoxBlock(DyeColor.LIGHT_GRAY,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.LIGHT_GRAY));
  public static final RegistryObject<IronShulkerBoxBlock> CYAN_IRON_SHULKER_BOX = register(
    "iron_shulker_box_cyan", () -> new IronShulkerBoxBlock(DyeColor.CYAN,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.CYAN));
  public static final RegistryObject<IronShulkerBoxBlock> PURPLE_IRON_SHULKER_BOX = register(
    "iron_shulker_box_purple", () -> new IronShulkerBoxBlock(DyeColor.PURPLE,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.PURPLE));
  public static final RegistryObject<IronShulkerBoxBlock> BLUE_IRON_SHULKER_BOX = register(
    "iron_shulker_box_blue", () -> new IronShulkerBoxBlock(DyeColor.BLUE,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.BLUE));
  public static final RegistryObject<IronShulkerBoxBlock> BROWN_IRON_SHULKER_BOX = register(
    "iron_shulker_box_brown", () -> new IronShulkerBoxBlock(DyeColor.BROWN,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.BROWN));
  public static final RegistryObject<IronShulkerBoxBlock> GREEN_IRON_SHULKER_BOX = register(
    "iron_shulker_box_green", () -> new IronShulkerBoxBlock(DyeColor.GREEN,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.GREEN));
  public static final RegistryObject<IronShulkerBoxBlock> RED_IRON_SHULKER_BOX = register(
    "iron_shulker_box_red", () -> new IronShulkerBoxBlock(DyeColor.RED,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.RED));
  public static final RegistryObject<IronShulkerBoxBlock> BLACK_IRON_SHULKER_BOX = register(
    "iron_shulker_box_black", () -> new IronShulkerBoxBlock(DyeColor.BLACK,
      VIEW_BLOCKING),
    () -> ironShulkerBoxRenderer(DyeColor.BLACK));

  public static final List<RegistryObject<? extends GenericIronShulkerBlock>> IRON_SHULKER_BOXES = ImmutableList.<RegistryObject<? extends GenericIronShulkerBlock>>builder()
    .add(WHITE_IRON_SHULKER_BOX, ORANGE_IRON_SHULKER_BOX, MAGENTA_IRON_SHULKER_BOX, LIGHT_BLUE_IRON_SHULKER_BOX,
      YELLOW_IRON_SHULKER_BOX, LIME_IRON_SHULKER_BOX, PINK_IRON_SHULKER_BOX, GRAY_IRON_SHULKER_BOX)
    .add(LIGHT_GRAY_IRON_SHULKER_BOX, CYAN_IRON_SHULKER_BOX, PURPLE_IRON_SHULKER_BOX, BLUE_IRON_SHULKER_BOX,
      BROWN_IRON_SHULKER_BOX, GREEN_IRON_SHULKER_BOX, RED_IRON_SHULKER_BOX, BLACK_IRON_SHULKER_BOX)
    .build();
  // Iron End

  // Gold Start
  public static final RegistryObject<GoldShulkerBoxBlock> WHITE_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_white", () -> new GoldShulkerBoxBlock(DyeColor.WHITE,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.WHITE));
  public static final RegistryObject<GoldShulkerBoxBlock> ORANGE_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_orange", () -> new GoldShulkerBoxBlock(DyeColor.ORANGE,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.ORANGE));
  public static final RegistryObject<GoldShulkerBoxBlock> MAGENTA_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_magenta", () -> new GoldShulkerBoxBlock(DyeColor.MAGENTA,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.MAGENTA));
  public static final RegistryObject<GoldShulkerBoxBlock> LIGHT_BLUE_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_light_blue", () -> new GoldShulkerBoxBlock(DyeColor.LIGHT_BLUE,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.LIGHT_BLUE));
  public static final RegistryObject<GoldShulkerBoxBlock> YELLOW_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_yellow", () -> new GoldShulkerBoxBlock(DyeColor.YELLOW,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.YELLOW));
  public static final RegistryObject<GoldShulkerBoxBlock> LIME_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_lime", () -> new GoldShulkerBoxBlock(DyeColor.LIME,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.LIME));
  public static final RegistryObject<GoldShulkerBoxBlock> PINK_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_pink", () -> new GoldShulkerBoxBlock(DyeColor.PINK,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.PINK));
  public static final RegistryObject<GoldShulkerBoxBlock> GRAY_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_gray", () -> new GoldShulkerBoxBlock(DyeColor.GRAY,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.GRAY));
  public static final RegistryObject<GoldShulkerBoxBlock> LIGHT_GRAY_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_light_gray", () -> new GoldShulkerBoxBlock(DyeColor.LIGHT_GRAY,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.LIGHT_GRAY));
  public static final RegistryObject<GoldShulkerBoxBlock> CYAN_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_cyan", () -> new GoldShulkerBoxBlock(DyeColor.CYAN,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.CYAN));
  public static final RegistryObject<GoldShulkerBoxBlock> PURPLE_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_purple", () -> new GoldShulkerBoxBlock(DyeColor.PURPLE,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.PURPLE));
  public static final RegistryObject<GoldShulkerBoxBlock> BLUE_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_blue", () -> new GoldShulkerBoxBlock(DyeColor.BLUE,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.BLUE));
  public static final RegistryObject<GoldShulkerBoxBlock> BROWN_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_brown", () -> new GoldShulkerBoxBlock(DyeColor.BROWN,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.BROWN));
  public static final RegistryObject<GoldShulkerBoxBlock> GREEN_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_green", () -> new GoldShulkerBoxBlock(DyeColor.GREEN,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.GREEN));
  public static final RegistryObject<GoldShulkerBoxBlock> RED_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_red", () -> new GoldShulkerBoxBlock(DyeColor.RED,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.RED));
  public static final RegistryObject<GoldShulkerBoxBlock> BLACK_GOLD_SHULKER_BOX = register(
    "gold_shulker_box_black", () -> new GoldShulkerBoxBlock(DyeColor.BLACK,
      VIEW_BLOCKING),
    () -> goldShulkerBoxRenderer(DyeColor.BLACK));

  public static final List<RegistryObject<? extends GenericIronShulkerBlock>> GOLD_SHULKER_BOXES = ImmutableList.<RegistryObject<? extends GenericIronShulkerBlock>>builder()
    .add(WHITE_GOLD_SHULKER_BOX, ORANGE_GOLD_SHULKER_BOX, MAGENTA_GOLD_SHULKER_BOX, LIGHT_BLUE_GOLD_SHULKER_BOX,
      YELLOW_GOLD_SHULKER_BOX, LIME_GOLD_SHULKER_BOX, PINK_GOLD_SHULKER_BOX, GRAY_GOLD_SHULKER_BOX)
    .add(LIGHT_GRAY_GOLD_SHULKER_BOX, CYAN_GOLD_SHULKER_BOX, PURPLE_GOLD_SHULKER_BOX, BLUE_GOLD_SHULKER_BOX,
      BROWN_GOLD_SHULKER_BOX, GREEN_GOLD_SHULKER_BOX, RED_GOLD_SHULKER_BOX, BLACK_GOLD_SHULKER_BOX)
    .build();
  // Gold End

  // Diamond Start
  public static final RegistryObject<DiamondShulkerBoxBlock> WHITE_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_white", () -> new DiamondShulkerBoxBlock(DyeColor.WHITE,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.WHITE));
  public static final RegistryObject<DiamondShulkerBoxBlock> ORANGE_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_orange", () -> new DiamondShulkerBoxBlock(DyeColor.ORANGE,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.ORANGE));
  public static final RegistryObject<DiamondShulkerBoxBlock> MAGENTA_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_magenta", () -> new DiamondShulkerBoxBlock(DyeColor.MAGENTA,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.MAGENTA));
  public static final RegistryObject<DiamondShulkerBoxBlock> LIGHT_BLUE_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_light_blue", () -> new DiamondShulkerBoxBlock(DyeColor.LIGHT_BLUE,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.LIGHT_BLUE));
  public static final RegistryObject<DiamondShulkerBoxBlock> YELLOW_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_yellow", () -> new DiamondShulkerBoxBlock(DyeColor.YELLOW,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.YELLOW));
  public static final RegistryObject<DiamondShulkerBoxBlock> LIME_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_lime", () -> new DiamondShulkerBoxBlock(DyeColor.LIME,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.LIME));
  public static final RegistryObject<DiamondShulkerBoxBlock> PINK_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_pink", () -> new DiamondShulkerBoxBlock(DyeColor.PINK,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.PINK));
  public static final RegistryObject<DiamondShulkerBoxBlock> GRAY_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_gray", () -> new DiamondShulkerBoxBlock(DyeColor.GRAY,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.GRAY));
  public static final RegistryObject<DiamondShulkerBoxBlock> LIGHT_GRAY_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_light_gray", () -> new DiamondShulkerBoxBlock(DyeColor.LIGHT_GRAY,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.LIGHT_GRAY));
  public static final RegistryObject<DiamondShulkerBoxBlock> CYAN_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_cyan", () -> new DiamondShulkerBoxBlock(DyeColor.CYAN,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.CYAN));
  public static final RegistryObject<DiamondShulkerBoxBlock> PURPLE_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_purple", () -> new DiamondShulkerBoxBlock(DyeColor.PURPLE,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.PURPLE));
  public static final RegistryObject<DiamondShulkerBoxBlock> BLUE_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_blue", () -> new DiamondShulkerBoxBlock(DyeColor.BLUE,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.BLUE));
  public static final RegistryObject<DiamondShulkerBoxBlock> BROWN_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_brown", () -> new DiamondShulkerBoxBlock(DyeColor.BROWN,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.BROWN));
  public static final RegistryObject<DiamondShulkerBoxBlock> GREEN_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_green", () -> new DiamondShulkerBoxBlock(DyeColor.GREEN,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.GREEN));
  public static final RegistryObject<DiamondShulkerBoxBlock> RED_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_red", () -> new DiamondShulkerBoxBlock(DyeColor.RED,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.RED));
  public static final RegistryObject<DiamondShulkerBoxBlock> BLACK_DIAMOND_SHULKER_BOX = register(
    "diamond_shulker_box_black", () -> new DiamondShulkerBoxBlock(DyeColor.BLACK,
      VIEW_BLOCKING),
    () -> diamondShulkerBoxRenderer(DyeColor.BLACK));

  public static final List<RegistryObject<? extends GenericIronShulkerBlock>> DIAMOND_SHULKER_BOXES = ImmutableList.<RegistryObject<? extends GenericIronShulkerBlock>>builder()
    .add(WHITE_DIAMOND_SHULKER_BOX, ORANGE_DIAMOND_SHULKER_BOX, MAGENTA_DIAMOND_SHULKER_BOX,
      LIGHT_BLUE_DIAMOND_SHULKER_BOX, YELLOW_DIAMOND_SHULKER_BOX, LIME_DIAMOND_SHULKER_BOX, PINK_DIAMOND_SHULKER_BOX,
      GRAY_DIAMOND_SHULKER_BOX)
    .add(LIGHT_GRAY_DIAMOND_SHULKER_BOX, CYAN_DIAMOND_SHULKER_BOX, PURPLE_DIAMOND_SHULKER_BOX, BLUE_DIAMOND_SHULKER_BOX,
      BROWN_DIAMOND_SHULKER_BOX, GREEN_DIAMOND_SHULKER_BOX, RED_DIAMOND_SHULKER_BOX, BLACK_DIAMOND_SHULKER_BOX)
    .build();
  // Diamond End

  // Copper Start
  public static final RegistryObject<CopperShulkerBoxBlock> WHITE_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_white", () -> new CopperShulkerBoxBlock(DyeColor.WHITE,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.WHITE));
  public static final RegistryObject<CopperShulkerBoxBlock> ORANGE_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_orange", () -> new CopperShulkerBoxBlock(DyeColor.ORANGE,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.ORANGE));
  public static final RegistryObject<CopperShulkerBoxBlock> MAGENTA_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_magenta", () -> new CopperShulkerBoxBlock(DyeColor.MAGENTA,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.MAGENTA));
  public static final RegistryObject<CopperShulkerBoxBlock> LIGHT_BLUE_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_light_blue", () -> new CopperShulkerBoxBlock(DyeColor.LIGHT_BLUE,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.LIGHT_BLUE));
  public static final RegistryObject<CopperShulkerBoxBlock> YELLOW_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_yellow", () -> new CopperShulkerBoxBlock(DyeColor.YELLOW,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.YELLOW));
  public static final RegistryObject<CopperShulkerBoxBlock> LIME_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_lime", () -> new CopperShulkerBoxBlock(DyeColor.LIME,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.LIME));
  public static final RegistryObject<CopperShulkerBoxBlock> PINK_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_pink", () -> new CopperShulkerBoxBlock(DyeColor.PINK,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.PINK));
  public static final RegistryObject<CopperShulkerBoxBlock> GRAY_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_gray", () -> new CopperShulkerBoxBlock(DyeColor.GRAY,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.GRAY));
  public static final RegistryObject<CopperShulkerBoxBlock> LIGHT_GRAY_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_light_gray", () -> new CopperShulkerBoxBlock(DyeColor.LIGHT_GRAY,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.LIGHT_GRAY));
  public static final RegistryObject<CopperShulkerBoxBlock> CYAN_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_cyan", () -> new CopperShulkerBoxBlock(DyeColor.CYAN,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.CYAN));
  public static final RegistryObject<CopperShulkerBoxBlock> PURPLE_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_purple", () -> new CopperShulkerBoxBlock(DyeColor.PURPLE,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.PURPLE));
  public static final RegistryObject<CopperShulkerBoxBlock> BLUE_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_blue", () -> new CopperShulkerBoxBlock(DyeColor.BLUE,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.BLUE));
  public static final RegistryObject<CopperShulkerBoxBlock> BROWN_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_brown", () -> new CopperShulkerBoxBlock(DyeColor.BROWN,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.BROWN));
  public static final RegistryObject<CopperShulkerBoxBlock> GREEN_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_green", () -> new CopperShulkerBoxBlock(DyeColor.GREEN,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.GREEN));
  public static final RegistryObject<CopperShulkerBoxBlock> RED_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_red", () -> new CopperShulkerBoxBlock(DyeColor.RED,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.RED));
  public static final RegistryObject<CopperShulkerBoxBlock> BLACK_COPPER_SHULKER_BOX = register(
    "copper_shulker_box_black", () -> new CopperShulkerBoxBlock(DyeColor.BLACK,
      VIEW_BLOCKING),
    () -> copperShulkerBoxRenderer(DyeColor.BLACK));

  public static final List<RegistryObject<? extends GenericIronShulkerBlock>> COPPER_SHULKER_BOXES = ImmutableList.<RegistryObject<? extends GenericIronShulkerBlock>>builder()
    .add(WHITE_COPPER_SHULKER_BOX, ORANGE_COPPER_SHULKER_BOX, MAGENTA_COPPER_SHULKER_BOX, LIGHT_BLUE_COPPER_SHULKER_BOX,
      YELLOW_COPPER_SHULKER_BOX, LIME_COPPER_SHULKER_BOX, PINK_COPPER_SHULKER_BOX, GRAY_COPPER_SHULKER_BOX)
    .add(LIGHT_GRAY_COPPER_SHULKER_BOX, CYAN_COPPER_SHULKER_BOX, PURPLE_COPPER_SHULKER_BOX, BLUE_COPPER_SHULKER_BOX,
      BROWN_COPPER_SHULKER_BOX, GREEN_COPPER_SHULKER_BOX, RED_COPPER_SHULKER_BOX, BLACK_COPPER_SHULKER_BOX)
    .build();
  // Copper End

  // Silver Start
  public static final RegistryObject<SilverShulkerBoxBlock> WHITE_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_white", () -> new SilverShulkerBoxBlock(DyeColor.WHITE,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.WHITE));
  public static final RegistryObject<SilverShulkerBoxBlock> ORANGE_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_orange", () -> new SilverShulkerBoxBlock(DyeColor.ORANGE,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.ORANGE));
  public static final RegistryObject<SilverShulkerBoxBlock> MAGENTA_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_magenta", () -> new SilverShulkerBoxBlock(DyeColor.MAGENTA,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.MAGENTA));
  public static final RegistryObject<SilverShulkerBoxBlock> LIGHT_BLUE_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_light_blue", () -> new SilverShulkerBoxBlock(DyeColor.LIGHT_BLUE,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.LIGHT_BLUE));
  public static final RegistryObject<SilverShulkerBoxBlock> YELLOW_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_yellow", () -> new SilverShulkerBoxBlock(DyeColor.YELLOW,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.YELLOW));
  public static final RegistryObject<SilverShulkerBoxBlock> LIME_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_lime", () -> new SilverShulkerBoxBlock(DyeColor.LIME,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.LIME));
  public static final RegistryObject<SilverShulkerBoxBlock> PINK_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_pink", () -> new SilverShulkerBoxBlock(DyeColor.PINK,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.PINK));
  public static final RegistryObject<SilverShulkerBoxBlock> GRAY_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_gray", () -> new SilverShulkerBoxBlock(DyeColor.GRAY,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.GRAY));
  public static final RegistryObject<SilverShulkerBoxBlock> LIGHT_GRAY_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_light_gray", () -> new SilverShulkerBoxBlock(DyeColor.LIGHT_GRAY,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.LIGHT_GRAY));
  public static final RegistryObject<SilverShulkerBoxBlock> CYAN_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_cyan", () -> new SilverShulkerBoxBlock(DyeColor.CYAN,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.CYAN));
  public static final RegistryObject<SilverShulkerBoxBlock> PURPLE_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_purple", () -> new SilverShulkerBoxBlock(DyeColor.PURPLE,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.PURPLE));
  public static final RegistryObject<SilverShulkerBoxBlock> BLUE_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_blue", () -> new SilverShulkerBoxBlock(DyeColor.BLUE,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.BLUE));
  public static final RegistryObject<SilverShulkerBoxBlock> BROWN_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_brown", () -> new SilverShulkerBoxBlock(DyeColor.BROWN,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.BROWN));
  public static final RegistryObject<SilverShulkerBoxBlock> GREEN_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_green", () -> new SilverShulkerBoxBlock(DyeColor.GREEN,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.GREEN));
  public static final RegistryObject<SilverShulkerBoxBlock> RED_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_red", () -> new SilverShulkerBoxBlock(DyeColor.RED,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.RED));
  public static final RegistryObject<SilverShulkerBoxBlock> BLACK_SILVER_SHULKER_BOX = register(
    "silver_shulker_box_black", () -> new SilverShulkerBoxBlock(DyeColor.BLACK,
      VIEW_BLOCKING),
    () -> silverShulkerBoxRenderer(DyeColor.BLACK));

  public static final List<RegistryObject<? extends GenericIronShulkerBlock>> SILVER_SHULKER_BOXES = ImmutableList.<RegistryObject<? extends GenericIronShulkerBlock>>builder()
    .add(WHITE_SILVER_SHULKER_BOX, ORANGE_SILVER_SHULKER_BOX, MAGENTA_SILVER_SHULKER_BOX, LIGHT_BLUE_SILVER_SHULKER_BOX,
      YELLOW_SILVER_SHULKER_BOX, LIME_SILVER_SHULKER_BOX, PINK_SILVER_SHULKER_BOX, GRAY_SILVER_SHULKER_BOX)
    .add(LIGHT_GRAY_SILVER_SHULKER_BOX, CYAN_SILVER_SHULKER_BOX, PURPLE_SILVER_SHULKER_BOX, BLUE_SILVER_SHULKER_BOX,
      BROWN_SILVER_SHULKER_BOX, GREEN_SILVER_SHULKER_BOX, RED_SILVER_SHULKER_BOX, BLACK_SILVER_SHULKER_BOX)
    .build();
  // Silver End

  // Crystal Start
  public static final RegistryObject<CrystalShulkerBoxBlock> WHITE_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_white", () -> new CrystalShulkerBoxBlock(DyeColor.WHITE,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.WHITE));
  public static final RegistryObject<CrystalShulkerBoxBlock> ORANGE_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_orange", () -> new CrystalShulkerBoxBlock(DyeColor.ORANGE,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.ORANGE));
  public static final RegistryObject<CrystalShulkerBoxBlock> MAGENTA_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_magenta", () -> new CrystalShulkerBoxBlock(DyeColor.MAGENTA,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.MAGENTA));
  public static final RegistryObject<CrystalShulkerBoxBlock> LIGHT_BLUE_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_light_blue", () -> new CrystalShulkerBoxBlock(DyeColor.LIGHT_BLUE,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.LIGHT_BLUE));
  public static final RegistryObject<CrystalShulkerBoxBlock> YELLOW_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_yellow", () -> new CrystalShulkerBoxBlock(DyeColor.YELLOW,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.YELLOW));
  public static final RegistryObject<CrystalShulkerBoxBlock> LIME_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_lime", () -> new CrystalShulkerBoxBlock(DyeColor.LIME,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.LIME));
  public static final RegistryObject<CrystalShulkerBoxBlock> PINK_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_pink", () -> new CrystalShulkerBoxBlock(DyeColor.PINK,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.PINK));
  public static final RegistryObject<CrystalShulkerBoxBlock> GRAY_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_gray", () -> new CrystalShulkerBoxBlock(DyeColor.GRAY,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.GRAY));
  public static final RegistryObject<CrystalShulkerBoxBlock> LIGHT_GRAY_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_light_gray", () -> new CrystalShulkerBoxBlock(DyeColor.LIGHT_GRAY,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.LIGHT_GRAY));
  public static final RegistryObject<CrystalShulkerBoxBlock> CYAN_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_cyan", () -> new CrystalShulkerBoxBlock(DyeColor.CYAN,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.CYAN));
  public static final RegistryObject<CrystalShulkerBoxBlock> PURPLE_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_purple", () -> new CrystalShulkerBoxBlock(DyeColor.PURPLE,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.PURPLE));
  public static final RegistryObject<CrystalShulkerBoxBlock> BLUE_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_blue", () -> new CrystalShulkerBoxBlock(DyeColor.BLUE,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.BLUE));
  public static final RegistryObject<CrystalShulkerBoxBlock> BROWN_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_brown", () -> new CrystalShulkerBoxBlock(DyeColor.BROWN,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.BROWN));
  public static final RegistryObject<CrystalShulkerBoxBlock> GREEN_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_green", () -> new CrystalShulkerBoxBlock(DyeColor.GREEN,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.GREEN));
  public static final RegistryObject<CrystalShulkerBoxBlock> RED_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_red", () -> new CrystalShulkerBoxBlock(DyeColor.RED,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.RED));
  public static final RegistryObject<CrystalShulkerBoxBlock> BLACK_CRYSTAL_SHULKER_BOX = register(
    "crystal_shulker_box_black", () -> new CrystalShulkerBoxBlock(DyeColor.BLACK,
      VIEW_BLOCKING),
    () -> crystalShulkerBoxRenderer(DyeColor.BLACK));

  public static final List<RegistryObject<? extends GenericIronShulkerBlock>> CRYSTAL_SHULKER_BOXES = ImmutableList.<RegistryObject<? extends GenericIronShulkerBlock>>builder()
    .add(WHITE_CRYSTAL_SHULKER_BOX, ORANGE_CRYSTAL_SHULKER_BOX, MAGENTA_CRYSTAL_SHULKER_BOX,
      LIGHT_BLUE_CRYSTAL_SHULKER_BOX, YELLOW_CRYSTAL_SHULKER_BOX, LIME_CRYSTAL_SHULKER_BOX, PINK_CRYSTAL_SHULKER_BOX,
      GRAY_CRYSTAL_SHULKER_BOX)
    .add(LIGHT_GRAY_CRYSTAL_SHULKER_BOX, CYAN_CRYSTAL_SHULKER_BOX, PURPLE_CRYSTAL_SHULKER_BOX, BLUE_CRYSTAL_SHULKER_BOX,
      BROWN_CRYSTAL_SHULKER_BOX, GREEN_CRYSTAL_SHULKER_BOX, RED_CRYSTAL_SHULKER_BOX, BLACK_CRYSTAL_SHULKER_BOX)
    .build();
  // Crystal End

  // Obsidian Start
  public static final RegistryObject<ObsidianShulkerBoxBlock> WHITE_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_white", () -> new ObsidianShulkerBoxBlock(DyeColor.WHITE,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.WHITE));
  public static final RegistryObject<ObsidianShulkerBoxBlock> ORANGE_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_orange", () -> new ObsidianShulkerBoxBlock(DyeColor.ORANGE,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.ORANGE));
  public static final RegistryObject<ObsidianShulkerBoxBlock> MAGENTA_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_magenta", () -> new ObsidianShulkerBoxBlock(DyeColor.MAGENTA,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.MAGENTA));
  public static final RegistryObject<ObsidianShulkerBoxBlock> LIGHT_BLUE_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_light_blue", () -> new ObsidianShulkerBoxBlock(DyeColor.LIGHT_BLUE,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.LIGHT_BLUE));
  public static final RegistryObject<ObsidianShulkerBoxBlock> YELLOW_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_yellow", () -> new ObsidianShulkerBoxBlock(DyeColor.YELLOW,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, Blocks.OBSIDIAN.getExplosionResistance()).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.YELLOW));


  public static final RegistryObject<ObsidianShulkerBoxBlock> LIME_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_lime", () -> new ObsidianShulkerBoxBlock(DyeColor.LIME,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.LIME));
  public static final RegistryObject<ObsidianShulkerBoxBlock> PINK_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_pink", () -> new ObsidianShulkerBoxBlock(DyeColor.PINK,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.PINK));
  public static final RegistryObject<ObsidianShulkerBoxBlock> GRAY_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_gray", () -> new ObsidianShulkerBoxBlock(DyeColor.GRAY,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.GRAY));
  public static final RegistryObject<ObsidianShulkerBoxBlock> LIGHT_GRAY_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_light_gray", () -> new ObsidianShulkerBoxBlock(DyeColor.LIGHT_GRAY,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.LIGHT_GRAY));
  public static final RegistryObject<ObsidianShulkerBoxBlock> CYAN_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_cyan", () -> new ObsidianShulkerBoxBlock(DyeColor.CYAN,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.CYAN));
  public static final RegistryObject<ObsidianShulkerBoxBlock> PURPLE_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_purple", () -> new ObsidianShulkerBoxBlock(DyeColor.PURPLE,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.PURPLE));
  public static final RegistryObject<ObsidianShulkerBoxBlock> BLUE_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_blue", () -> new ObsidianShulkerBoxBlock(DyeColor.BLUE,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.BLUE));
  public static final RegistryObject<ObsidianShulkerBoxBlock> BROWN_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_brown", () -> new ObsidianShulkerBoxBlock(DyeColor.BROWN,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.BROWN));
  public static final RegistryObject<ObsidianShulkerBoxBlock> GREEN_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_green", () -> new ObsidianShulkerBoxBlock(DyeColor.GREEN,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.GREEN));
  public static final RegistryObject<ObsidianShulkerBoxBlock> RED_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_red", () -> new ObsidianShulkerBoxBlock(DyeColor.RED,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.RED));
  public static final RegistryObject<ObsidianShulkerBoxBlock> BLACK_OBSIDIAN_SHULKER_BOX = register(
    "obsidian_shulker_box_black", () -> new ObsidianShulkerBoxBlock(DyeColor.BLACK,
      BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 10000.0F).dynamicShape().noOcclusion()
        .isSuffocating(positionPredicate).isViewBlocking(positionPredicate)),
    () -> obsidianShulkerBoxRenderer(DyeColor.BLACK));

  public static final List<RegistryObject<? extends GenericIronShulkerBlock>> OBSIDIAN_SHULKER_BOXES = ImmutableList.<RegistryObject<? extends GenericIronShulkerBlock>>builder()
    .add(WHITE_OBSIDIAN_SHULKER_BOX, ORANGE_OBSIDIAN_SHULKER_BOX, MAGENTA_OBSIDIAN_SHULKER_BOX,
      LIGHT_BLUE_OBSIDIAN_SHULKER_BOX, YELLOW_OBSIDIAN_SHULKER_BOX, LIME_OBSIDIAN_SHULKER_BOX,
      PINK_OBSIDIAN_SHULKER_BOX, GRAY_OBSIDIAN_SHULKER_BOX)
    .add(LIGHT_GRAY_OBSIDIAN_SHULKER_BOX, CYAN_OBSIDIAN_SHULKER_BOX, PURPLE_OBSIDIAN_SHULKER_BOX,
      BLUE_OBSIDIAN_SHULKER_BOX, BROWN_OBSIDIAN_SHULKER_BOX, GREEN_OBSIDIAN_SHULKER_BOX, RED_OBSIDIAN_SHULKER_BOX,
      BLACK_OBSIDIAN_SHULKER_BOX)
    .build();
  // Obsidian End

  public static final List<RegistryObject<? extends GenericIronShulkerBlock>> IRON_SHULKER_BOXES_LIST = ImmutableList.<RegistryObject<? extends GenericIronShulkerBlock>>builder()
    .addAll(IRON_SHULKER_BOXES)
    .addAll(GOLD_SHULKER_BOXES)
    .addAll(DIAMOND_SHULKER_BOXES)
    .addAll(COPPER_SHULKER_BOXES)
    .addAll(SILVER_SHULKER_BOXES)
    .addAll(CRYSTAL_SHULKER_BOXES)
    .addAll(OBSIDIAN_SHULKER_BOXES)
    .build();

  private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup,
    Supplier<Callable<BlockEntityWithoutLevelRenderer>> renderMethod) {
    return register(name, sup, block -> item(block, renderMethod));
  }

  private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup,
    Function<RegistryObject<T>, Supplier<? extends Item>> itemCreator) {
    RegistryObject<T> ret = registerNoItem(name, sup);
    ITEMS.register(name, itemCreator.apply(ret));
    return ret;
  }

  private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<? extends T> sup) {
    return BLOCKS.register(name, sup);
  }

  private static Supplier<BlockItem> item(final RegistryObject<? extends Block> block,
    final Supplier<Callable<BlockEntityWithoutLevelRenderer>> renderMethod) {
    return () -> new BlockItem(block.get(),
      new Item.Properties().tab(IronShulkerBoxes.IRONSHULKERBOX_ITEM_GROUP).setISTER(renderMethod).stacksTo(1));
  }

  @OnlyIn(Dist.CLIENT)
  private static Callable<BlockEntityWithoutLevelRenderer> ironShulkerBoxRenderer(DyeColor dyeColor) {
    return () -> new IronShulkerBoxItemStackRenderer(() -> new IronShulkerBoxTileEntity(dyeColor));
  }

  @OnlyIn(Dist.CLIENT)
  private static Callable<BlockEntityWithoutLevelRenderer> goldShulkerBoxRenderer(DyeColor dyeColor) {
    return () -> new IronShulkerBoxItemStackRenderer(() -> new GoldShulkerBoxTileEntity(dyeColor));
  }

  @OnlyIn(Dist.CLIENT)
  private static Callable<BlockEntityWithoutLevelRenderer> diamondShulkerBoxRenderer(DyeColor dyeColor) {
    return () -> new IronShulkerBoxItemStackRenderer(() -> new DiamondShulkerBoxTileEntity(dyeColor));
  }

  @OnlyIn(Dist.CLIENT)
  private static Callable<BlockEntityWithoutLevelRenderer> copperShulkerBoxRenderer(DyeColor dyeColor) {
    return () -> new IronShulkerBoxItemStackRenderer(() -> new CopperShulkerBoxTileEntity(dyeColor));
  }

  @OnlyIn(Dist.CLIENT)
  private static Callable<BlockEntityWithoutLevelRenderer> silverShulkerBoxRenderer(DyeColor dyeColor) {
    return () -> new IronShulkerBoxItemStackRenderer(() -> new SilverShulkerBoxTileEntity(dyeColor));
  }

  @OnlyIn(Dist.CLIENT)
  private static Callable<BlockEntityWithoutLevelRenderer> crystalShulkerBoxRenderer(DyeColor dyeColor) {
    return () -> new IronShulkerBoxItemStackRenderer(() -> new CrystalShulkerBoxTileEntity(dyeColor));
  }

  @OnlyIn(Dist.CLIENT)
  private static Callable<BlockEntityWithoutLevelRenderer> obsidianShulkerBoxRenderer(DyeColor dyeColor) {
    return () -> new IronShulkerBoxItemStackRenderer(() -> new ObsidianShulkerBoxTileEntity(dyeColor));
  }
}

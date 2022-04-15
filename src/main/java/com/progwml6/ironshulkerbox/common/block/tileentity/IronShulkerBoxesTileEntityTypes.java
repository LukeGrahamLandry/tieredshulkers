package com.progwml6.ironshulkerbox.common.block.tileentity;

import com.google.common.collect.Sets;
import com.progwml6.ironshulkerbox.IronShulkerBoxes;
import com.progwml6.ironshulkerbox.common.block.GenericIronShulkerBlock;
import com.progwml6.ironshulkerbox.common.block.ShulkerBoxesBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class IronShulkerBoxesTileEntityTypes {

  public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, IronShulkerBoxes.MOD_ID);

  public static final RegistryObject<BlockEntityType<IronShulkerBoxTileEntity>> IRON_SHULKER_BOX = TILE_ENTITIES.register("iron_shulker_box",
    () -> new BlockEntityType<>(IronShulkerBoxTileEntity::new, createBlockSet(ShulkerBoxesBlocks.IRON_SHULKER_BOXES), null));

  public static final RegistryObject<BlockEntityType<GoldShulkerBoxTileEntity>> GOLD_SHULKER_BOX = TILE_ENTITIES.register("gold_shulker_box",
    () -> new BlockEntityType<>(GoldShulkerBoxTileEntity::new, createBlockSet(ShulkerBoxesBlocks.GOLD_SHULKER_BOXES), null));

  public static final RegistryObject<BlockEntityType<DiamondShulkerBoxTileEntity>> DIAMOND_SHULKER_BOX = TILE_ENTITIES.register("diamond_shulker_box",
    () -> new BlockEntityType<>(DiamondShulkerBoxTileEntity::new, createBlockSet(ShulkerBoxesBlocks.DIAMOND_SHULKER_BOXES), null));

  public static final RegistryObject<BlockEntityType<CopperShulkerBoxTileEntity>> COPPER_SHULKER_BOX = TILE_ENTITIES.register("copper_shulker_box",
    () -> new BlockEntityType<>(CopperShulkerBoxTileEntity::new, createBlockSet(ShulkerBoxesBlocks.COPPER_SHULKER_BOXES), null));

  public static final RegistryObject<BlockEntityType<SilverShulkerBoxTileEntity>> SILVER_SHULKER_BOX = TILE_ENTITIES.register("silver_shulker_box",
    () -> new BlockEntityType<>(SilverShulkerBoxTileEntity::new, createBlockSet(ShulkerBoxesBlocks.SILVER_SHULKER_BOXES), null));

  public static final RegistryObject<BlockEntityType<CrystalShulkerBoxTileEntity>> CRYSTAL_SHULKER_BOX = TILE_ENTITIES.register("crystal_shulker_box",
    () -> new BlockEntityType<>(CrystalShulkerBoxTileEntity::new, createBlockSet(ShulkerBoxesBlocks.CRYSTAL_SHULKER_BOXES), null));

  public static final RegistryObject<BlockEntityType<ObsidianShulkerBoxTileEntity>> OBSIDIAN_SHULKER_BOX = TILE_ENTITIES.register("obsidian_shulker_box",
    () -> new BlockEntityType<>(ObsidianShulkerBoxTileEntity::new, createBlockSet(ShulkerBoxesBlocks.OBSIDIAN_SHULKER_BOXES), null));

  public static Set<Block> createBlockSet(Collection<RegistryObject<? extends GenericIronShulkerBlock>> values) {
    Set<Block> blocks = Sets.newHashSet();

    for (RegistryObject<? extends GenericIronShulkerBlock> block : values) {
      blocks.add(block.get());
    }

    return blocks;
  }

  public static List<Block> createBlockList(Set<Block> blocks) {
    return new ArrayList<>(blocks);
  }
}

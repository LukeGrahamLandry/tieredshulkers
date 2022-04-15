package com.progwml6.ironshulkerbox.common.boxes;

import com.progwml6.ironshulkerbox.IronShulkerBoxes;
import com.progwml6.ironshulkerbox.common.boxes.tile.UpgradableBoxTile;
import com.progwml6.ironshulkerbox.common.items.IronShulkerBoxesItems;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.function.Supplier;

public class ShulkerBoxesRegistry {
  private static final DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister .create(ForgeRegistries.BLOCKS, IronShulkerBoxes.MOD_ID);
  private static final DeferredRegister<BlockEntityType<?>> TILE_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, IronShulkerBoxes.MOD_ID);
  private static final DeferredRegister<Item> ITEM_REGISTRY = IronShulkerBoxesItems.ITEMS;
  public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, IronShulkerBoxes.MOD_ID);

  private static BlockBehaviour.StatePredicate positionPredicate = (p_235444_0_, world, pos) -> !(world.getBlockEntity(pos) instanceof UpgradableBoxTile) || ((UpgradableBoxTile)world.getBlockEntity(pos)).isClosed();
  private static final BlockBehaviour.Properties PROPS = BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 3.0F).dynamicShape().noOcclusion().isSuffocating(positionPredicate).isViewBlocking(positionPredicate);
  private static final BlockBehaviour.Properties OBSIDIAN_PROPS = BlockBehaviour.Properties.of(Material.METAL).strength(3.0F, 1000.0F).dynamicShape().noOcclusion().isSuffocating(positionPredicate).isViewBlocking(positionPredicate);
  private static final Item.Properties ITEM_PROPS = new Item.Properties().tab(IronShulkerBoxes.IRONSHULKERBOX_ITEM_GROUP).stacksTo(1);

  public static void register(IEventBus bus){
    BLOCK_REGISTRY.register(bus);
    ITEM_REGISTRY.register(bus);
    TILE_REGISTRY.register(bus);
    CONTAINERS.register(bus);
    createObjects();
  }

  private static void createObjects(){
    for (UpgradableBoxTier tier : UpgradableBoxTier.values()){
      HashMap<DyeColor, Supplier<Block>> blocks = new HashMap<>();
      HashMap<DyeColor, Supplier<BlockEntityType<UpgradableBoxTile>>> tiles = new HashMap<>();
      String key = tier.name + "_shulker_box";

      for (DyeColor color : DyeColor.values()){
        String blockKey = key + "_" + color.getName();
        BlockBehaviour.Properties props = tier == UpgradableBoxTier.OBSIDIAN ? OBSIDIAN_PROPS : PROPS;

        Supplier<Block> block = BLOCK_REGISTRY.register(blockKey,  () -> new UpgradableBoxBlock(color, props, tier));
        blocks.put(color, block);
        ITEM_REGISTRY.register(blockKey,  () -> new UpgradableBoxItem(block.get(), ITEM_PROPS));

        tiles.put(color, TILE_REGISTRY.register(blockKey,  () -> BlockEntityType.Builder.of((pos, state) -> new UpgradableBoxTile(color, tier, pos, state), blocks.values().stream().map(Supplier::get).toArray(Block[]::new)).build(null)));
      }

      tier.blocks = blocks;
      tier.tiles = tiles;
      tier.menu = CONTAINERS.register(key, () -> new MenuType<>((windowId, playerInv) -> new UpgradableBoxContainer(tier, windowId, playerInv, new SimpleContainer(tier.size))));
    }
  }
}

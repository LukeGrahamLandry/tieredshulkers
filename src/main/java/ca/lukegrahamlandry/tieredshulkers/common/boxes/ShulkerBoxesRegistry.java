package ca.lukegrahamlandry.tieredshulkers.common.boxes;

import ca.lukegrahamlandry.tieredshulkers.common.ShulkerColour;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.tile.UpgradableBoxTile;
import ca.lukegrahamlandry.tieredshulkers.common.items.BoxUpgradeItem;
import ca.lukegrahamlandry.tieredshulkers.common.items.BoxUpgradeType;
import ca.lukegrahamlandry.tieredshulkers.TieredShulkersMain;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ShulkerBoxesRegistry {
  private static final DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister .create(ForgeRegistries.BLOCKS, TieredShulkersMain.MOD_ID);
  private static final DeferredRegister<BlockEntityType<?>> TILE_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TieredShulkersMain.MOD_ID);
  private static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, TieredShulkersMain.MOD_ID);
  public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TieredShulkersMain.MOD_ID);

  private static final BlockBehaviour.StatePredicate positionPredicate = (p_235444_0_, world, pos) -> !(world.getBlockEntity(pos) instanceof UpgradableBoxTile) || ((UpgradableBoxTile)world.getBlockEntity(pos)).isClosed();
  private static final BlockBehaviour.Properties PROPS = BlockBehaviour.Properties.of(Material.METAL).strength(1F, 3.0F).dynamicShape().noOcclusion().isSuffocating(positionPredicate).isViewBlocking(positionPredicate);
  private static final BlockBehaviour.Properties OBSIDIAN_PROPS = BlockBehaviour.Properties.of(Material.METAL).strength(2.0F, 1000.0F).dynamicShape().noOcclusion().isSuffocating(positionPredicate).isViewBlocking(positionPredicate);
  private static final Item.Properties ITEM_PROPS = new Item.Properties().tab(TieredShulkersMain.ITEM_GROUP).stacksTo(1);

  public static void register(IEventBus bus){
    BLOCK_REGISTRY.register(bus);
    ITEM_REGISTRY.register(bus);
    TILE_REGISTRY.register(bus);
    CONTAINERS.register(bus);
    createObjects();
  }

  private static void createObjects(){
    for (UpgradableBoxTier tier : UpgradableBoxTier.values()){
      HashMap<ShulkerColour, Supplier<Block>> blocks = new HashMap<>();
      HashMap<ShulkerColour, Supplier<BlockEntityType<UpgradableBoxTile>>> tiles = new HashMap<>();
      String key = tier.name + "_shulker_box";

      for (ShulkerColour color : ShulkerColour.values()){
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

  public static final ImmutableMap<BoxUpgradeType, RegistryObject<BoxUpgradeItem>> UPGRADES = ImmutableMap.copyOf(Arrays.stream(BoxUpgradeType.values())
    .collect(Collectors.toMap(Function.identity(), type -> ITEM_REGISTRY.register(type.name().toLowerCase(Locale.ROOT) + "_shulker_box_upgrade",
      () -> new BoxUpgradeItem(type, new Item.Properties().tab(TieredShulkersMain.ITEM_GROUP).stacksTo(1))))));
}

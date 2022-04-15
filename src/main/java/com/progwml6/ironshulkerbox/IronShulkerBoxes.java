package com.progwml6.ironshulkerbox;

import com.progwml6.ironshulkerbox.client.screen.IronShulkerBoxScreen;
import com.progwml6.ironshulkerbox.client.tileentity.IronShulkerBoxTileEntityRenderer;
import com.progwml6.ironshulkerbox.common.block.ShulkerBoxesBlocks;
import com.progwml6.ironshulkerbox.common.block.tileentity.IronShulkerBoxesTileEntityTypes;
import com.progwml6.ironshulkerbox.common.data.IronShulkerBoxesRecipeProvider;
import com.progwml6.ironshulkerbox.common.inventory.IronShulkerBoxesContainerTypes;
import com.progwml6.ironshulkerbox.common.items.IronShulkerBoxesItems;
import com.progwml6.ironshulkerbox.common.network.PacketHandler;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmlclient.registry.ClientRegistry;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(IronShulkerBoxes.MOD_ID)
public class IronShulkerBoxes {

  public static final String MOD_ID = "ironshulkerbox";

  public static final CreativeModeTab IRONSHULKERBOX_ITEM_GROUP = (new CreativeModeTab("ironshulkerbox") {
    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack makeIcon() {
      return new ItemStack(ShulkerBoxesBlocks.BLACK_IRON_SHULKER_BOX.get());
    }
  });

  public IronShulkerBoxes() {
    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

    // General mod setup
    modBus.addListener(this::setup);
    modBus.addListener(this::gatherData);

    DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
      // Client setup
      modBus.addListener(this::setupClient);
    });

    // Registry objects
    ShulkerBoxesBlocks.BLOCKS.register(modBus);
    IronShulkerBoxesItems.ITEMS.register(modBus);
    IronShulkerBoxesTileEntityTypes.TILE_ENTITIES.register(modBus);
    IronShulkerBoxesContainerTypes.CONTAINERS.register(modBus);
  }

  @OnlyIn(Dist.CLIENT)
  private void setupClient(final FMLClientSetupEvent event) {
    MenuScreens.register(IronShulkerBoxesContainerTypes.IRON_SHULKER_BOX.get(), IronShulkerBoxScreen::new);
    MenuScreens.register(IronShulkerBoxesContainerTypes.GOLD_SHULKER_BOX.get(), IronShulkerBoxScreen::new);
    MenuScreens.register(IronShulkerBoxesContainerTypes.DIAMOND_SHULKER_BOX.get(), IronShulkerBoxScreen::new);
    MenuScreens.register(IronShulkerBoxesContainerTypes.CRYSTAL_SHULKER_BOX.get(), IronShulkerBoxScreen::new);
    MenuScreens.register(IronShulkerBoxesContainerTypes.COPPER_SHULKER_BOX.get(), IronShulkerBoxScreen::new);
    MenuScreens.register(IronShulkerBoxesContainerTypes.SILVER_SHULKER_BOX.get(), IronShulkerBoxScreen::new);
    MenuScreens.register(IronShulkerBoxesContainerTypes.OBSIDIAN_SHULKER_BOX.get(), IronShulkerBoxScreen::new);

    ClientRegistry.bindTileEntityRenderer(IronShulkerBoxesTileEntityTypes.IRON_SHULKER_BOX.get(), IronShulkerBoxTileEntityRenderer::new);
    ClientRegistry.bindTileEntityRenderer(IronShulkerBoxesTileEntityTypes.GOLD_SHULKER_BOX.get(), IronShulkerBoxTileEntityRenderer::new);
    ClientRegistry.bindTileEntityRenderer(IronShulkerBoxesTileEntityTypes.GOLD_SHULKER_BOX.get(), IronShulkerBoxTileEntityRenderer::new);
    ClientRegistry.bindTileEntityRenderer(IronShulkerBoxesTileEntityTypes.DIAMOND_SHULKER_BOX.get(), IronShulkerBoxTileEntityRenderer::new);
    ClientRegistry.bindTileEntityRenderer(IronShulkerBoxesTileEntityTypes.COPPER_SHULKER_BOX.get(), IronShulkerBoxTileEntityRenderer::new);
    ClientRegistry.bindTileEntityRenderer(IronShulkerBoxesTileEntityTypes.SILVER_SHULKER_BOX.get(), IronShulkerBoxTileEntityRenderer::new);
    ClientRegistry.bindTileEntityRenderer(IronShulkerBoxesTileEntityTypes.CRYSTAL_SHULKER_BOX.get(), IronShulkerBoxTileEntityRenderer::new);
    ClientRegistry.bindTileEntityRenderer(IronShulkerBoxesTileEntityTypes.OBSIDIAN_SHULKER_BOX.get(), IronShulkerBoxTileEntityRenderer::new);
  }

  private void setup(final FMLCommonSetupEvent event) {
    PacketHandler.register();
  }

  private void gatherData(GatherDataEvent event) {
    DataGenerator datagenerator = event.getGenerator();

    if (event.includeServer()) {
      datagenerator.addProvider(new IronShulkerBoxesRecipeProvider(datagenerator));
    }
  }
}

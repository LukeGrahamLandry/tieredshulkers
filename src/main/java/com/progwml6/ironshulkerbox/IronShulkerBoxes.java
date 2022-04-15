package com.progwml6.ironshulkerbox;

import com.progwml6.ironshulkerbox.client.screen.UpgradableBoxScreen;
import com.progwml6.ironshulkerbox.client.tileentity.UpgradableBoxTileEntityRenderer;
import com.progwml6.ironshulkerbox.common.boxes.ShulkerBoxesRegistry;
import com.progwml6.ironshulkerbox.common.boxes.UpgradableBoxTier;
import com.progwml6.ironshulkerbox.common.data.IronShulkerBoxesRecipeProvider;
import com.progwml6.ironshulkerbox.common.data.ShulkerBlockStateProvider;
import com.progwml6.ironshulkerbox.common.data.ShulkerItemModelProvider;
import com.progwml6.ironshulkerbox.common.data.ShulkerLootTableProvider;
import com.progwml6.ironshulkerbox.common.network.PacketHandler;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(IronShulkerBoxes.MOD_ID)
public class IronShulkerBoxes {

  public static final String MOD_ID = "ironshulkerbox";

  public static final CreativeModeTab IRONSHULKERBOX_ITEM_GROUP = (new CreativeModeTab(MOD_ID) {
    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack makeIcon() {
      return new ItemStack(UpgradableBoxTier.IRON.blocks.get(DyeColor.BLACK).get().asItem());
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
    ShulkerBoxesRegistry.register(modBus);
  }

  @OnlyIn(Dist.CLIENT)
  private void setupClient(final FMLClientSetupEvent event) {
    for (UpgradableBoxTier tier : UpgradableBoxTier.values()){
      MenuScreens.register(tier.menu.get(), UpgradableBoxScreen::new);

      for (DyeColor color : DyeColor.values()){
        BlockEntityRenderers.register(tier.tiles.get(color).get(), UpgradableBoxTileEntityRenderer::new);
      }
    }
  }

  private void setup(final FMLCommonSetupEvent event) {
    PacketHandler.register();
  }

  private void gatherData(GatherDataEvent event) {
    DataGenerator datagenerator = event.getGenerator();

    if (event.includeServer()) {
      datagenerator.addProvider(new IronShulkerBoxesRecipeProvider(datagenerator));
      datagenerator.addProvider(new ShulkerLootTableProvider(datagenerator));
    }

    if (event.includeClient()){
      datagenerator.addProvider(new ShulkerBlockStateProvider(datagenerator, MOD_ID, event.getExistingFileHelper()));
      datagenerator.addProvider(new ShulkerItemModelProvider(datagenerator, MOD_ID, event.getExistingFileHelper()));
    }
  }
}

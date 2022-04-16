package ca.lukegrahamlandry.tieredshulkers;

import ca.lukegrahamlandry.tieredshulkers.client.screen.UpgradableBoxScreen;
import ca.lukegrahamlandry.tieredshulkers.client.tileentity.UpgradableBoxTileEntityRenderer;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.ShulkerBoxesRegistry;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.UpgradableBoxTier;
import ca.lukegrahamlandry.tieredshulkers.common.data.BoxesRecipeProvider;
import ca.lukegrahamlandry.tieredshulkers.common.data.BoxBlockStateProvider;
import ca.lukegrahamlandry.tieredshulkers.common.data.BoxItemModelProvider;
import ca.lukegrahamlandry.tieredshulkers.common.data.BoxLootTableProvider;
import ca.lukegrahamlandry.tieredshulkers.common.network.PacketHandler;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.ShulkerBoxDispenseBehavior;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(TieredShulkersMain.MOD_ID)
public class TieredShulkersMain {
  public static final String MOD_ID = "tieredshulkers";

  public static final CreativeModeTab ITEM_GROUP = (new CreativeModeTab(MOD_ID) {
    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack makeIcon() {
      return new ItemStack(UpgradableBoxTier.IRON.blocks.get(DyeColor.BLACK).get().asItem());
    }
  });

  public TieredShulkersMain() {
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

    DispenseItemBehavior behaviour = new ShulkerBoxDispenseBehavior();
    for (UpgradableBoxTier tier : UpgradableBoxTier.values()){
      for (DyeColor color : DyeColor.values()){
        DispenserBlock.registerBehavior(tier.blocks.get(color).get().asItem(), behaviour);
      }
    }
  }

  private void gatherData(GatherDataEvent event) {
    DataGenerator datagenerator = event.getGenerator();

    if (event.includeServer()) {
      datagenerator.addProvider(new BoxesRecipeProvider(datagenerator));
      datagenerator.addProvider(new BoxLootTableProvider(datagenerator));
    }

    if (event.includeClient()){
      datagenerator.addProvider(new BoxBlockStateProvider(datagenerator, MOD_ID, event.getExistingFileHelper()));
      datagenerator.addProvider(new BoxItemModelProvider(datagenerator, MOD_ID, event.getExistingFileHelper()));
    }
  }
}

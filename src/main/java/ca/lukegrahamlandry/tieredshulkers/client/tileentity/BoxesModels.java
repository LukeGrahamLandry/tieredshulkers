package ca.lukegrahamlandry.tieredshulkers.client.tileentity;

import ca.lukegrahamlandry.tieredshulkers.common.ShulkerColour;
import com.google.common.collect.ImmutableList;
import ca.lukegrahamlandry.tieredshulkers.TieredShulkersMain;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.UpgradableBoxTier;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = TieredShulkersMain.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BoxesModels {

  
  public static final List<ResourceLocation> IRON_SHULKER_BOX_TEXTURES = Arrays.stream(ShulkerColour.values()).map(ShulkerColour::getName).map((color) -> getShulkerBoxResourceLocation("iron", color)).collect(ImmutableList.toImmutableList());
  public static final List<ResourceLocation> GOLD_SHULKER_BOX_TEXTURES = Arrays.stream(ShulkerColour.values()).map(ShulkerColour::getName).map((color) -> getShulkerBoxResourceLocation("gold", color)).collect(ImmutableList.toImmutableList());
  public static final List<ResourceLocation> DIAMOND_SHULKER_BOX_TEXTURES = Arrays.stream(ShulkerColour.values()).map(ShulkerColour::getName).map((color) -> getShulkerBoxResourceLocation("diamond", color)).collect(ImmutableList.toImmutableList());
  public static final List<ResourceLocation> COPPER_SHULKER_BOX_TEXTURES = Arrays.stream(ShulkerColour.values()).map(ShulkerColour::getName).map((color) -> getShulkerBoxResourceLocation("copper", color)).collect(ImmutableList.toImmutableList());
  public static final List<ResourceLocation> SILVER_SHULKER_BOX_TEXTURES = Arrays.stream(ShulkerColour.values()).map(ShulkerColour::getName).map((color) -> getShulkerBoxResourceLocation("silver", color)).collect(ImmutableList.toImmutableList());
  public static final List<ResourceLocation> CRYSTAL_SHULKER_BOX_TEXTURES = Arrays.stream(ShulkerColour.values()).map(ShulkerColour::getName).map((color) -> getShulkerBoxResourceLocation("crystal", color)).collect(ImmutableList.toImmutableList());
  public static final List<ResourceLocation> OBSIDIAN_SHULKER_BOX_TEXTURES = Arrays.stream(ShulkerColour.values()).map(ShulkerColour::getName).map((color) -> getShulkerBoxResourceLocation("obsidian", color)).collect(ImmutableList.toImmutableList());
  public static final List<ResourceLocation> VANILLA_SHULKER_BOX_TEXTURES = Arrays.stream(ShulkerColour.values()).map(ShulkerColour::getName).map(BoxesModels::getShulkerBoxResourceLocation).collect(ImmutableList.toImmutableList());

  private static ResourceLocation getShulkerBoxResourceLocation(String typeName, String colorName) {
    return new ResourceLocation(TieredShulkersMain.MOD_ID, "model/" + colorName + "/shulker_" + colorName + "_" + typeName);
  }

  private static ResourceLocation getShulkerBoxResourceLocation(String colorName) {
    return new ResourceLocation("entity/shulker/shulker_" + colorName);
  }

  public static ResourceLocation chooseShulkerBoxModel(UpgradableBoxTier type, int dyeColor) {
    switch (type) {
      case IRON:
        return IRON_SHULKER_BOX_TEXTURES.get(dyeColor);
      case GOLD:
        return GOLD_SHULKER_BOX_TEXTURES.get(dyeColor);
      case DIAMOND:
        return DIAMOND_SHULKER_BOX_TEXTURES.get(dyeColor);
      case COPPER:
        return COPPER_SHULKER_BOX_TEXTURES.get(dyeColor);
      case SILVER:
        return SILVER_SHULKER_BOX_TEXTURES.get(dyeColor);
      case CRYSTAL:
        return CRYSTAL_SHULKER_BOX_TEXTURES.get(dyeColor);
      case OBSIDIAN:
        return OBSIDIAN_SHULKER_BOX_TEXTURES.get(dyeColor);
      default:
        return VANILLA_SHULKER_BOX_TEXTURES.get(dyeColor);
    }
  }

  @SubscribeEvent
  public static void onStitch(final TextureStitchEvent.Pre event) {
    if (!event.getAtlas().location().equals(Sheets.SHULKER_SHEET)) {
      return;
    }

    IRON_SHULKER_BOX_TEXTURES.forEach(event::addSprite);
    GOLD_SHULKER_BOX_TEXTURES.forEach(event::addSprite);
    DIAMOND_SHULKER_BOX_TEXTURES.forEach(event::addSprite);
    COPPER_SHULKER_BOX_TEXTURES.forEach(event::addSprite);
    SILVER_SHULKER_BOX_TEXTURES.forEach(event::addSprite);
    CRYSTAL_SHULKER_BOX_TEXTURES.forEach(event::addSprite);
    OBSIDIAN_SHULKER_BOX_TEXTURES.forEach(event::addSprite);
  }
}

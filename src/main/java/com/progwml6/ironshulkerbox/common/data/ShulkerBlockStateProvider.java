package com.progwml6.ironshulkerbox.common.data;

import com.progwml6.ironshulkerbox.IronShulkerBoxes;
import com.progwml6.ironshulkerbox.common.boxes.UpgradableBoxTier;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ShulkerBlockStateProvider extends BlockStateProvider {
  private final ExistingFileHelper fileHelper;

  public ShulkerBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
    super(gen, modid, exFileHelper);
    this.fileHelper = exFileHelper;
  }

  @Override
  protected void registerStatesAndModels() {
    for (UpgradableBoxTier tier : UpgradableBoxTier.values()){
      ModelFile model = new ModelFile.ExistingModelFile(new ResourceLocation(IronShulkerBoxes.MOD_ID, "block/" + tier.name + "_shulker_box"), this.fileHelper);
      for (DyeColor color : DyeColor.values()){
        simpleBlock(tier.blocks.get(color).get(), model);
      }
    }
  }
}

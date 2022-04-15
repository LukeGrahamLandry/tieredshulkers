package com.progwml6.ironshulkerbox.common.data;

import com.progwml6.ironshulkerbox.IronShulkerBoxes;
import com.progwml6.ironshulkerbox.common.boxes.UpgradableBoxTier;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ShulkerItemModelProvider extends ItemModelProvider {
  public ShulkerItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
    super(generator, modid, existingFileHelper);
  }

  @Override
  protected void registerModels() {for (UpgradableBoxTier tier : UpgradableBoxTier.values()){
    for (DyeColor color : DyeColor.values()){
      withExistingParent(tier.blocks.get(color).get().getRegistryName().getPath(), "item/template_shulker_box");
    }
  }
  }
}

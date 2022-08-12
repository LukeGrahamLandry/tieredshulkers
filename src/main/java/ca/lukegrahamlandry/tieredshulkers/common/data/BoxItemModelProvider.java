package ca.lukegrahamlandry.tieredshulkers.common.data;

import ca.lukegrahamlandry.tieredshulkers.common.ShulkerColour;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.UpgradableBoxTier;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class BoxItemModelProvider extends ItemModelProvider {
  public BoxItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
    super(generator, modid, existingFileHelper);
  }

  @Override
  protected void registerModels() {for (UpgradableBoxTier tier : UpgradableBoxTier.values()){
    for (ShulkerColour color : ShulkerColour.values()){
      withExistingParent(ForgeRegistries.BLOCKS.getKey(tier.blocks.get(color).get()).getPath(), "item/template_shulker_box");
    }
  }
  }
}

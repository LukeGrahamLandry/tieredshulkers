package ca.lukegrahamlandry.tieredshulkers.common.data;

import ca.lukegrahamlandry.tieredshulkers.common.boxes.UpgradableBoxTier;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BoxItemModelProvider extends ItemModelProvider {
  public BoxItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
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

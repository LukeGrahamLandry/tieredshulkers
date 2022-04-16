package ca.lukegrahamlandry.tieredshulkers.common.data;

import ca.lukegrahamlandry.tieredshulkers.common.boxes.UpgradableBoxTier;
import ca.lukegrahamlandry.tieredshulkers.TieredShulkersMain;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BoxBlockStateProvider extends BlockStateProvider {
  private final ExistingFileHelper fileHelper;

  public BoxBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
    super(gen, modid, exFileHelper);
    this.fileHelper = exFileHelper;
  }

  @Override
  protected void registerStatesAndModels() {
    for (UpgradableBoxTier tier : UpgradableBoxTier.values()){
      ModelFile model = new ModelFile.ExistingModelFile(new ResourceLocation(TieredShulkersMain.MOD_ID, "block/" + tier.name + "_shulker_box"), this.fileHelper);
      for (DyeColor color : DyeColor.values()){
        simpleBlock(tier.blocks.get(color).get(), model);
      }
    }
  }
}

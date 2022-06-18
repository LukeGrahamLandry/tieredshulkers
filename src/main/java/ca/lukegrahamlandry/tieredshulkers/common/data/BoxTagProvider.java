package ca.lukegrahamlandry.tieredshulkers.common.data;

import ca.lukegrahamlandry.tieredshulkers.common.ShulkerColour;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.UpgradableBoxTier;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BoxTagProvider extends BlockTagsProvider {
  private final ExistingFileHelper fileHelper;

  public BoxTagProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
    super(gen, modid, exFileHelper);
    this.fileHelper = exFileHelper;
  }

  @Override
  protected void addTags() {
    TagsProvider.TagAppender<Block> mineable = this.tag(BlockTags.MINEABLE_WITH_PICKAXE);
    for (UpgradableBoxTier tier : UpgradableBoxTier.values()){
      for (ShulkerColour color : ShulkerColour.values()){
        mineable.add(tier.blocks.get(color).get());
      }
    }
  }
}

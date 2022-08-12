package ca.lukegrahamlandry.tieredshulkers.common.boxes;

import ca.lukegrahamlandry.tieredshulkers.client.tileentity.UpgradableBoxItemStackRenderer;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.tile.UpgradableBoxTile;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class UpgradableBoxItem extends BlockItem {
  public UpgradableBoxItem(Block p_40565_, Properties p_40566_) {
    super(p_40565_, p_40566_);
  }


  @Override
  public void initializeClient(Consumer<IClientItemExtensions> consumer) {
    UpgradableBoxItemStackRenderer<UpgradableBoxTile> renderer = new UpgradableBoxItemStackRenderer<>();
    consumer.accept(new IClientItemExtensions() {
      @Override
      public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return renderer;
      }
    });
  }
}

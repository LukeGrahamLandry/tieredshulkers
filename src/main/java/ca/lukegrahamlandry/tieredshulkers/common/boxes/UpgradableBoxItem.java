package ca.lukegrahamlandry.tieredshulkers.common.boxes;

import ca.lukegrahamlandry.tieredshulkers.client.tileentity.UpgradableBoxItemStackRenderer;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.tile.UpgradableBoxTile;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.function.Consumer;

public class UpgradableBoxItem extends BlockItem {
  public UpgradableBoxItem(Block p_40565_, Properties p_40566_) {
    super(p_40565_, p_40566_);
  }


  @Override
  public void initializeClient(Consumer<IItemRenderProperties> consumer) {
    UpgradableBoxItemStackRenderer<UpgradableBoxTile> renderer = new UpgradableBoxItemStackRenderer<>();
    consumer.accept(new IItemRenderProperties() {
      @Override
      public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
        return renderer;
      }
    });
  }
}
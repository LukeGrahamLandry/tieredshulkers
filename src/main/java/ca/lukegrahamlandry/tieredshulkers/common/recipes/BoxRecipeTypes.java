package ca.lukegrahamlandry.tieredshulkers.common.recipes;

import ca.lukegrahamlandry.tieredshulkers.TieredShulkersMain;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;


public class BoxRecipeTypes
{
  @ObjectHolder("tieredshulkers:shulker_box_coloring")
  public static RecipeSerializer<?> SHULKER_BOX_COLORING;

  @ObjectHolder("tieredshulkers:shulker_box_crafting")
  public static RecipeSerializer<?> SHULKER_BOX_CRAFTING;

  @Mod.EventBusSubscriber(modid = TieredShulkersMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class Registration
  {
    @SubscribeEvent
    public static void onRecipeRegister(final RegistryEvent.Register<RecipeSerializer<?>> e)
    {
      e.getRegistry().registerAll(
        new SimpleRecipeSerializer<>(BoxColoringRecipe::new).setRegistryName("tieredshulkers:shulker_box_coloring"),
        new BoxCraftingRecipe.Serializer()
      );
    }
  }
}

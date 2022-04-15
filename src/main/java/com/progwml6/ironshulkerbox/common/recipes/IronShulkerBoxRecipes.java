package com.progwml6.ironshulkerbox.common.recipes;

import com.progwml6.ironshulkerbox.IronShulkerBoxes;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;


public class IronShulkerBoxRecipes
{
  @ObjectHolder("ironshulkerbox:shulker_box_coloring")
  public static RecipeSerializer<?> SHULKER_BOX_COLORING;

  @ObjectHolder("ironshulkerbox:shulker_box_crafting")
  public static RecipeSerializer<?> SHULKER_BOX_CRAFTING;

  @Mod.EventBusSubscriber(modid = IronShulkerBoxes.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class Registration
  {
    @SubscribeEvent
    public static void onRecipeRegister(final RegistryEvent.Register<RecipeSerializer<?>> e)
    {
      e.getRegistry().registerAll(
        new SimpleRecipeSerializer<>(ShulkerBoxColoringRecipe::new).setRegistryName("ironshulkerbox:shulker_box_coloring"),
        new IronShulkerBoxRecipe.Serializer()
      );
    }
  }
}

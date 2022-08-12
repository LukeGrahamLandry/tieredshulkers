package ca.lukegrahamlandry.tieredshulkers.common.recipes;

import ca.lukegrahamlandry.tieredshulkers.TieredShulkersMain;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BoxRecipeTypes
{
  public static final DeferredRegister<RecipeSerializer<?>> RECIPIES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TieredShulkersMain.MOD_ID);

  public static Supplier<RecipeSerializer<?>> SHULKER_BOX_COLORING = RECIPIES.register("shulker_box_coloring", () -> new SimpleRecipeSerializer<>(BoxColoringRecipe::new));

  public static Supplier<RecipeSerializer<?>> SHULKER_BOX_CRAFTING = RECIPIES.register("shulker_box_crafting", BoxCraftingRecipe.Serializer::new);

}

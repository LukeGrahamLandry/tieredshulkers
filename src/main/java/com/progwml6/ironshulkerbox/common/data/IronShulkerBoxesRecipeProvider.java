package com.progwml6.ironshulkerbox.common.data;

import com.progwml6.ironshulkerbox.IronShulkerBoxes;
import com.progwml6.ironshulkerbox.common.boxes.ShulkerBoxesRegistry;
import com.progwml6.ironshulkerbox.common.items.IronShulkerBoxesItems;
import com.progwml6.ironshulkerbox.common.items.BoxUpgradeType;
import com.progwml6.ironshulkerbox.common.recipes.IronShulkerBoxRecipeBuilder;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalAdvancement;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;

import java.util.Objects;
import java.util.function.Consumer;

public class IronShulkerBoxesRecipeProvider extends RecipeProvider implements IConditionBuilder {

  public IronShulkerBoxesRecipeProvider(DataGenerator generatorIn) {
    super(generatorIn);
  }

  /*
  @Override
  protected void buildShapelessRecipes(Consumer<FinishedRecipe> consumer) {
    this.addBlackShulkerBoxRecipes(consumer);
    this.addBlueShulkerBoxRecipes(consumer);
    this.addBrownShulkerBoxRecipes(consumer);
    this.addCyanShulkerBoxRecipes(consumer);
    this.addGrayShulkerBoxRecipes(consumer);
    this.addGreenShulkerBoxRecipes(consumer);
    this.addLightBlueShulkerBoxRecipes(consumer);
    this.addLightGrayShulkerBoxRecipes(consumer);
    this.addLimeShulkerBoxRecipes(consumer);
    this.addMagentaShulkerBoxRecipes(consumer);
    this.addOrangeShulkerBoxRecipes(consumer);
    this.addPinkShulkerBoxRecipes(consumer);
    this.addPurpleShulkerBoxRecipes(consumer);
    this.addRedShulkerBoxRecipes(consumer);
    this.addWhiteShulkerBoxRecipes(consumer);
    this.addYellowShulkerBoxRecipes(consumer);

    this.addUpgradesRecipes(consumer);
  }

  private void addBlackShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "black/";
    String group = "ironshulkerbox:black_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.BLACK_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLACK_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLACK_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.BLACK_COPPER_SHULKER_BOX.get(), Items.BLACK_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.BLACK_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLACK_COPPER_SHULKER_BOX.get(), Items.BLACK_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.BLACK_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLACK_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLACK_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.BLACK_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLACK_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLACK_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.BLACK_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLACK_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.BLACK_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLACK_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addBlueShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "blue/";
    String group = "ironshulkerbox:blue_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.BLUE_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLUE_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLUE_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.BLUE_COPPER_SHULKER_BOX.get(), Items.BLUE_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.BLUE_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLUE_COPPER_SHULKER_BOX.get(), Items.BLUE_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.BLUE_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLUE_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLUE_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.BLUE_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLUE_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLUE_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.BLUE_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLUE_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.BLUE_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.BLUE_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addBrownShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "brown/";
    String group = "ironshulkerbox:brown_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.BROWN_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.BROWN_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.BROWN_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.BROWN_COPPER_SHULKER_BOX.get(), Items.BROWN_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.BROWN_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.BROWN_COPPER_SHULKER_BOX.get(), Items.BROWN_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.BROWN_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.BROWN_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.BROWN_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.BROWN_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.BROWN_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.BROWN_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.BROWN_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.BROWN_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.BROWN_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.BROWN_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addCyanShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "cyan/";
    String group = "ironshulkerbox:cyan_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.CYAN_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.CYAN_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.CYAN_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.CYAN_COPPER_SHULKER_BOX.get(), Items.CYAN_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.CYAN_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.CYAN_COPPER_SHULKER_BOX.get(), Items.CYAN_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.CYAN_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.CYAN_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.CYAN_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.CYAN_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.CYAN_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.CYAN_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.CYAN_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.CYAN_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.CYAN_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.CYAN_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addGrayShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "gray/";
    String group = "ironshulkerbox:gray_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.GRAY_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.GRAY_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.GRAY_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.GRAY_COPPER_SHULKER_BOX.get(), Items.GRAY_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.GRAY_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.GRAY_COPPER_SHULKER_BOX.get(), Items.GRAY_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.GRAY_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.GRAY_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.GRAY_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.GRAY_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.GRAY_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.GRAY_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.GRAY_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.GRAY_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.GRAY_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.GRAY_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addGreenShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "green/";
    String group = "ironshulkerbox:green_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.GREEN_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.GREEN_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.GREEN_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.GREEN_COPPER_SHULKER_BOX.get(), Items.GREEN_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.GREEN_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.GREEN_COPPER_SHULKER_BOX.get(), Items.GREEN_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.GREEN_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.GREEN_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.GREEN_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.GREEN_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.GREEN_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.GREEN_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.GREEN_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.GREEN_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.GREEN_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.GREEN_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addLightBlueShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "light_blue/";
    String group = "ironshulkerbox:light_blue_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_BLUE_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_BLUE_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_BLUE_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_BLUE_COPPER_SHULKER_BOX.get(), Items.LIGHT_BLUE_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_BLUE_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_BLUE_COPPER_SHULKER_BOX.get(), Items.LIGHT_BLUE_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_BLUE_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_BLUE_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_BLUE_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_BLUE_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_BLUE_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_BLUE_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_BLUE_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_BLUE_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_BLUE_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_BLUE_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addLightGrayShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "light_gray/";
    String group = "ironshulkerbox:light_gray_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_GRAY_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_GRAY_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_GRAY_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_GRAY_COPPER_SHULKER_BOX.get(), Items.LIGHT_GRAY_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_GRAY_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_GRAY_COPPER_SHULKER_BOX.get(), Items.LIGHT_GRAY_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_GRAY_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_GRAY_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_GRAY_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_GRAY_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_GRAY_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_GRAY_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_GRAY_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_GRAY_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.LIGHT_GRAY_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIGHT_GRAY_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addLimeShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "lime/";
    String group = "ironshulkerbox:lime_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.LIME_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIME_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIME_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.LIME_COPPER_SHULKER_BOX.get(), Items.LIME_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.LIME_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIME_COPPER_SHULKER_BOX.get(), Items.LIME_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.LIME_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIME_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIME_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.LIME_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIME_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIME_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.LIME_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIME_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.LIME_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.LIME_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addMagentaShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "magenta/";
    String group = "ironshulkerbox:magenta_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.MAGENTA_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.MAGENTA_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.MAGENTA_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.MAGENTA_COPPER_SHULKER_BOX.get(), Items.MAGENTA_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.MAGENTA_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.MAGENTA_COPPER_SHULKER_BOX.get(), Items.MAGENTA_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.MAGENTA_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.MAGENTA_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.MAGENTA_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.MAGENTA_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.MAGENTA_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.MAGENTA_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.MAGENTA_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.MAGENTA_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.MAGENTA_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.MAGENTA_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addOrangeShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "orange/";
    String group = "ironshulkerbox:orange_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.ORANGE_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.ORANGE_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.ORANGE_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.ORANGE_COPPER_SHULKER_BOX.get(), Items.ORANGE_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.ORANGE_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.ORANGE_COPPER_SHULKER_BOX.get(), Items.ORANGE_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.ORANGE_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.ORANGE_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.ORANGE_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.ORANGE_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.ORANGE_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.ORANGE_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.ORANGE_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.ORANGE_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.ORANGE_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.ORANGE_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addPinkShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "pink/";
    String group = "ironshulkerbox:pink_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.PINK_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.PINK_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.PINK_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.PINK_COPPER_SHULKER_BOX.get(), Items.PINK_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.PINK_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.PINK_COPPER_SHULKER_BOX.get(), Items.PINK_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.PINK_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.PINK_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.PINK_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.PINK_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.PINK_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.PINK_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.PINK_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.PINK_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.PINK_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.PINK_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addPurpleShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "purple/";
    String group = "ironshulkerbox:purple_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.PURPLE_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.PURPLE_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.PURPLE_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.PURPLE_COPPER_SHULKER_BOX.get(), Items.PURPLE_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.PURPLE_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.PURPLE_COPPER_SHULKER_BOX.get(), Items.PURPLE_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.PURPLE_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.PURPLE_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.PURPLE_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.PURPLE_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.PURPLE_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.PURPLE_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.PURPLE_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.PURPLE_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.PURPLE_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.PURPLE_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addRedShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "red/";
    String group = "ironshulkerbox:red_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.RED_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.RED_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.RED_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.RED_COPPER_SHULKER_BOX.get(), Items.RED_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.RED_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.RED_COPPER_SHULKER_BOX.get(), Items.RED_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.RED_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.RED_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.RED_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.RED_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.RED_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.RED_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.RED_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.RED_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.RED_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.RED_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addWhiteShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "white/";
    String group = "ironshulkerbox:white_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.WHITE_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.WHITE_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.WHITE_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.WHITE_COPPER_SHULKER_BOX.get(), Items.WHITE_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.WHITE_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.WHITE_COPPER_SHULKER_BOX.get(), Items.WHITE_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.WHITE_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.WHITE_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.WHITE_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.WHITE_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.WHITE_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.WHITE_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.WHITE_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.WHITE_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.WHITE_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.WHITE_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addYellowShulkerBoxRecipes(Consumer<FinishedRecipe> consumer) {
    String color = "yellow/";
    String group = "ironshulkerbox:yellow_shulker_box";

    this.registerSilverBoxRecipe(consumer, ShulkerBoxesRegistry.YELLOW_SILVER_SHULKER_BOX.get(), ShulkerBoxesRegistry.YELLOW_COPPER_SHULKER_BOX.get(), ShulkerBoxesRegistry.YELLOW_IRON_SHULKER_BOX.get(), color, group);
    this.registerCopperBoxRecipe(consumer, ShulkerBoxesRegistry.YELLOW_COPPER_SHULKER_BOX.get(), Items.YELLOW_SHULKER_BOX, color, group);
    this.registerIronBoxRecipe(consumer, ShulkerBoxesRegistry.YELLOW_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.YELLOW_COPPER_SHULKER_BOX.get(), Items.YELLOW_SHULKER_BOX, color, group);
    this.registerGoldBoxRecipe(consumer, ShulkerBoxesRegistry.YELLOW_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.YELLOW_IRON_SHULKER_BOX.get(), ShulkerBoxesRegistry.YELLOW_SILVER_SHULKER_BOX.get(), color, group);
    this.registerDiamondBoxRecipe(consumer, ShulkerBoxesRegistry.YELLOW_DIAMOND_SHULKER_BOX.get(), ShulkerBoxesRegistry.YELLOW_GOLD_SHULKER_BOX.get(), ShulkerBoxesRegistry.YELLOW_SILVER_SHULKER_BOX.get(), color, group);
    this.registerCrystalBoxRecipe(consumer, ShulkerBoxesRegistry.YELLOW_CRYSTAL_SHULKER_BOX.get(), ShulkerBoxesRegistry.YELLOW_DIAMOND_SHULKER_BOX.get(), color, group);
    this.registerObsidianBoxRecipe(consumer, ShulkerBoxesRegistry.YELLOW_OBSIDIAN_SHULKER_BOX.get(), ShulkerBoxesRegistry.YELLOW_DIAMOND_SHULKER_BOX.get(), color, group);
  }

  private void addUpgradesRecipes(Consumer<FinishedRecipe> consumer) {
    String folder = "upgrades/";

    ShapedRecipeBuilder
      .shaped(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.VANILLA_TO_IRON).get())
      .define('M', Tags.Items.INGOTS_IRON)
      .define('S', Items.SHULKER_SHELL)
      .pattern("MMM")
      .pattern("MSM")
      .pattern("MMM")
      .unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
      .save(consumer,
        prefix(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.VANILLA_TO_IRON).get(), folder));

    ShapedRecipeBuilder.shaped(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.IRON_TO_GOLD).get())
      .define('S', Tags.Items.INGOTS_IRON)
      .define('M', Tags.Items.INGOTS_GOLD)
      .pattern("MSM")
      .pattern("MMM")
      .pattern("MMM")
      .unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
      .save(consumer,
        prefix(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.IRON_TO_GOLD).get(), folder));

    ShapedRecipeBuilder
      .shaped(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.GOLD_TO_DIAMOND).get())
      .define('M', Tags.Items.GEMS_DIAMOND)
      .define('S', Tags.Items.INGOTS_GOLD)
      .define('G', Tags.Items.GLASS)
      .pattern("GMG")
      .pattern("GSG")
      .pattern("GMG")
      .unlockedBy("has_glass", has(Tags.Items.GLASS))
      .save(consumer,
        prefix(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.GOLD_TO_DIAMOND).get(), folder));

    ShapedRecipeBuilder
      .shaped(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.DIAMOND_TO_OBSIDIAN).get())
      .define('M', Blocks.OBSIDIAN)
      .define('G', Tags.Items.GLASS)
      .pattern("MGM")
      .pattern("MMM")
      .pattern("MMM")
      .unlockedBy("has_glass", has(Tags.Items.GLASS))
      .save(consumer,
        prefix(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.DIAMOND_TO_OBSIDIAN).get(), folder));

    ShapedRecipeBuilder
      .shaped(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.DIAMOND_TO_CRYSTAL).get())
      .define('S', Blocks.OBSIDIAN)
      .define('G', Tags.Items.GLASS)
      .pattern("GSG")
      .pattern("GGG")
      .pattern("GGG")
      .unlockedBy("has_glass", has(Tags.Items.GLASS))
      .save(consumer,
        prefix(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.DIAMOND_TO_CRYSTAL).get(), folder));

    ResourceLocation woodToCopperChestUpgradeId = prefix(
      IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.VANILLA_TO_COPPER).get(), folder);
    ConditionalRecipe.builder()
      .addCondition(not(new TagEmptyCondition("forge:ingots/copper")))
      .addRecipe(ShapedRecipeBuilder
        .shaped(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.VANILLA_TO_COPPER).get())
        .define('M', ItemTags.bind("forge:ingots/copper"))
        .define('S', Items.SHULKER_SHELL)
        .pattern("MMM")
        .pattern("MSM")
        .pattern("MMM")
        .unlockedBy("has_item", has(Items.SHULKER_SHELL))::save)
      .setAdvancement(location("recipes/ironshulkerbox/upgrades/vanilla_to_copper_shulker_box_upgrade"),
        ConditionalAdvancement.builder()
          .addCondition(not(new TagEmptyCondition("forge:ingots/copper")))
          .addAdvancement(Advancement.Builder.advancement()
            .parent(new ResourceLocation("recipes/root"))
            .rewards(AdvancementRewards.Builder.recipe(woodToCopperChestUpgradeId))
            .addCriterion("has_item", has(Items.SHULKER_SHELL))
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(woodToCopperChestUpgradeId))
            .requirements(RequirementsStrategy.OR))
      ).build(consumer, woodToCopperChestUpgradeId);

    ResourceLocation copperToIronChestUpgrade = prefix(
      IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.COPPER_TO_IRON).get(), folder);
    ConditionalRecipe.builder()
      .addCondition(not(new TagEmptyCondition("forge:ingots/copper")))
      .addRecipe(ShapedRecipeBuilder
        .shaped(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.COPPER_TO_IRON).get())
        .define('M', Tags.Items.INGOTS_IRON)
        .define('S', ItemTags.bind("forge:ingots/copper"))
        .define('G', Tags.Items.GLASS)
        .pattern("GGG")
        .pattern("MSM")
        .pattern("MGM")
        .unlockedBy("has_item", has(ItemTags.PLANKS))::save)
      .setAdvancement(location("recipes/ironshulkerbox/upgrades/copper_to_iron_shulker_box_upgrade"),
        ConditionalAdvancement.builder()
          .addCondition(not(new TagEmptyCondition("forge:ingots/copper")))
          .addAdvancement(Advancement.Builder.advancement()
            .parent(new ResourceLocation("recipes/root"))
            .rewards(AdvancementRewards.Builder.recipe(copperToIronChestUpgrade))
            .addCriterion("has_item", has(Tags.Items.GLASS))
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(copperToIronChestUpgrade))
            .requirements(RequirementsStrategy.OR))
      ).build(consumer, copperToIronChestUpgrade);

    ResourceLocation copperToSilverChestUpgrade = prefix(
      IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.COPPER_TO_SILVER).get(), folder);
    ConditionalRecipe.builder()
      .addCondition(
        and(not(new TagEmptyCondition("forge:ingots/copper")), not(new TagEmptyCondition("forge:ingots/silver"))))
      .addRecipe(ShapedRecipeBuilder
        .shaped(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.COPPER_TO_SILVER).get())
        .define('M', ItemTags.bind("forge:ingots/silver"))
        .define('S', ItemTags.bind("forge:ingots/copper"))
        .pattern("MSM")
        .pattern("MMM")
        .pattern("MMM")
        .unlockedBy("has_item", has(ItemTags.bind("forge:ingots/copper")))::save)
      .setAdvancement(location("recipes/ironshulkerbox/upgrades/copper_to_silver_shulker_box_upgrade"),
        ConditionalAdvancement.builder()
          .addCondition(
            and(not(new TagEmptyCondition("forge:ingots/copper")), not(new TagEmptyCondition("forge:ingots/silver"))))
          .addAdvancement(Advancement.Builder.advancement()
            .parent(new ResourceLocation("recipes/root"))
            .rewards(AdvancementRewards.Builder.recipe(copperToSilverChestUpgrade))
            .addCriterion("has_item", has(ItemTags.bind("forge:ingots/copper")))
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(copperToSilverChestUpgrade))
            .requirements(RequirementsStrategy.OR))
      ).build(consumer, copperToSilverChestUpgrade);

    ResourceLocation silverToGoldChestUpgrade = prefix(
      IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.SILVER_TO_GOLD).get(), folder);
    ConditionalRecipe.builder()
      .addCondition(not(new TagEmptyCondition("forge:ingots/silver")))
      .addRecipe(ShapedRecipeBuilder
        .shaped(IronShulkerBoxesItems.UPGRADES.get(BoxUpgradeType.SILVER_TO_GOLD).get())
        .define('M', Tags.Items.INGOTS_GOLD)
        .define('S', ItemTags.bind("forge:ingots/copper"))
        .define('G', Tags.Items.GLASS)
        .pattern("MSM")
        .pattern("GGG")
        .pattern("MGM")
        .unlockedBy("has_item", has(Tags.Items.GLASS))::save)
      .setAdvancement(location("recipes/ironshulkerbox/upgrades/silver_to_gold_shulker_box_upgrade"),
        ConditionalAdvancement.builder()
          .addCondition(not(new TagEmptyCondition("forge:ingots/silver")))
          .addAdvancement(Advancement.Builder.advancement()
            .parent(new ResourceLocation("recipes/root"))
            .rewards(AdvancementRewards.Builder.recipe(silverToGoldChestUpgrade))
            .addCriterion("has_item", has(Tags.Items.GLASS))
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(silverToGoldChestUpgrade))
            .requirements(RequirementsStrategy.OR))
      ).build(consumer, silverToGoldChestUpgrade);
  }

  protected static ResourceLocation prefix(ItemLike item, String prefix) {
    ResourceLocation loc = Objects.requireNonNull(item.asItem().getRegistryName());
    return location(prefix + loc.getPath());
  }

  private static ResourceLocation location(String id) {
    return new ResourceLocation(IronShulkerBoxes.MOD_ID, id);
  }

  private void registerCopperBoxRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike input, String color, String group) {
    ResourceLocation vanillaToCopperShulkerBox = location("shulkerboxes/" + color + "copper/vanilla_copper_shulker_box");
    ConditionalRecipe.builder()
      .addCondition(not(new TagEmptyCondition("forge:ingots/copper")))
      .addRecipe(IronShulkerBoxRecipeBuilder.shapedRecipe(result)
        .setGroup(group)
        .key('M', ItemTags.bind("forge:ingots/copper"))
        .key('S', input)
        .patternLine("MMM")
        .patternLine("MSM")
        .patternLine("MMM")
        .addCriterion("has_item", has(ItemTags.bind("forge:ingots/copper")))::build)
      .setAdvancement(location("recipes/ironshulkerbox/shulkerboxes/" + color + "copper/vanilla_copper_shulker_box"),
        ConditionalAdvancement.builder()
          .addCondition(not(new TagEmptyCondition("forge:ingots/copper")))
          .addAdvancement(Advancement.Builder.advancement()
            .parent(new ResourceLocation("recipes/root"))
            .rewards(AdvancementRewards.Builder.recipe(vanillaToCopperShulkerBox))
            .addCriterion("has_item", has(ItemTags.bind("forge:ingots/copper")))
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(vanillaToCopperShulkerBox))
            .requirements(RequirementsStrategy.OR))
      ).build(consumer, vanillaToCopperShulkerBox);
  }

  private void registerSilverBoxRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike input, ItemLike inputTwo, String color, String group) {
    ResourceLocation copperToSilverShulkerBox = location("shulkerboxes/" + color + "silver/copper_silver_shulker_box");
    ConditionalRecipe.builder()
      .addCondition(not(new TagEmptyCondition("forge:ingots/copper")))
      .addRecipe(IronShulkerBoxRecipeBuilder.shapedRecipe(result)
        .setGroup(group)
        .key('M', ItemTags.bind("forge:ingots/silver"))
        .key('S', input)
        .patternLine("MMM")
        .patternLine("MSM")
        .patternLine("MMM")
        .addCriterion("has_item", has(ItemTags.bind("forge:ingots/silver")))::build)
      .setAdvancement(location("recipes/ironshulkerbox/shulkerboxes/" + color + "silver/copper_silver_shulker_box"),
        ConditionalAdvancement.builder()
          .addCondition(not(new TagEmptyCondition("forge:ingots/silver")))
          .addAdvancement(Advancement.Builder.advancement()
            .parent(new ResourceLocation("recipes/root"))
            .rewards(AdvancementRewards.Builder.recipe(copperToSilverShulkerBox))
            .addCriterion("has_item", has(ItemTags.bind("forge:ingots/silver")))
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(copperToSilverShulkerBox))
            .requirements(RequirementsStrategy.OR))
      ).build(consumer, copperToSilverShulkerBox);

    ResourceLocation ironToSilverShulkerBox = location("shulkerboxes/" + color + "silver/iron_silver_shulker_box");
    ConditionalRecipe.builder()
      .addCondition(not(new TagEmptyCondition("forge:ingots/copper")))
      .addRecipe(IronShulkerBoxRecipeBuilder.shapedRecipe(result)
        .setGroup(group)
        .key('M', ItemTags.bind("forge:ingots/silver"))
        .key('S', inputTwo)
        .key('G', Tags.Items.GLASS)
        .patternLine("MGM")
        .patternLine("GSG")
        .patternLine("MGM")
        .addCriterion("has_item", has(ItemTags.bind("forge:ingots/silver")))::build)
      .setAdvancement(location("recipes/ironshulkerbox/shulkerboxes/" + color + "silver/iron_silver_shulker_box"),
        ConditionalAdvancement.builder()
          .addCondition(not(new TagEmptyCondition("forge:ingots/silver")))
          .addAdvancement(Advancement.Builder.advancement()
            .parent(new ResourceLocation("recipes/root"))
            .rewards(AdvancementRewards.Builder.recipe(ironToSilverShulkerBox))
            .addCriterion("has_item", has(ItemTags.bind("forge:ingots/silver")))
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(ironToSilverShulkerBox))
            .requirements(RequirementsStrategy.OR))
      ).build(consumer, ironToSilverShulkerBox);
  }

  private void registerIronBoxRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike input, ItemLike inputTwo, String color, String group) {
    IronShulkerBoxRecipeBuilder.shapedRecipe(result)
      .setGroup(group)
      .key('G', Tags.Items.GLASS)
      .key('S', input)
      .key('M', Tags.Items.INGOTS_IRON)
      .patternLine("MGM")
      .patternLine("GSG")
      .patternLine("MGM")
      .addCriterion("has_gold", has(Tags.Items.INGOTS_IRON))
      .build(consumer, location("shulkerboxes/" + color + "iron/copper_iron_shulker_box"));

    IronShulkerBoxRecipeBuilder.shapedRecipe(result)
      .setGroup(group)
      .key('S', inputTwo)
      .key('M', Tags.Items.INGOTS_IRON)
      .patternLine("MMM")
      .patternLine("MSM")
      .patternLine("MMM")
      .addCriterion("has_gold", has(Tags.Items.INGOTS_GOLD))
      .build(consumer, location("shulkerboxes/" + color + "iron/vanilla_iron_shulker_box"));
  }

  private void registerGoldBoxRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike input, ItemLike inputTwo, String color, String group) {
    IronShulkerBoxRecipeBuilder.shapedRecipe(result)
      .setGroup(group)
      .key('S', input)
      .key('M', Tags.Items.INGOTS_GOLD)
      .patternLine("MMM")
      .patternLine("MSM")
      .patternLine("MMM")
      .addCriterion("has_gold", has(Tags.Items.INGOTS_GOLD))
      .build(consumer, location("shulkerboxes/" + color + "gold/iron_gold_shulker_box"));

    IronShulkerBoxRecipeBuilder.shapedRecipe(result)
      .setGroup(group)
      .key('G', Tags.Items.GLASS)
      .key('S', inputTwo)
      .key('M', Tags.Items.INGOTS_GOLD)
      .patternLine("MGM")
      .patternLine("GSG")
      .patternLine("MGM")
      .addCriterion("has_gold", has(Tags.Items.INGOTS_GOLD))
      .build(consumer, location("shulkerboxes/" + color + "gold/silver_gold_shulker_box"));
  }

  private void registerDiamondBoxRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike input, ItemLike inputTwo, String color, String group) {
    IronShulkerBoxRecipeBuilder.shapedRecipe(result)
      .setGroup(group)
      .key('G', Tags.Items.GLASS)
      .key('S', input)
      .key('M', Tags.Items.GEMS_DIAMOND)
      .patternLine("GGG")
      .patternLine("MSM")
      .patternLine("GGG")
      .addCriterion("has_diamonds", has(Tags.Items.GEMS_DIAMOND))
      .build(consumer, location("shulkerboxes/" + color + "diamond/gold_diamond_shulker_box"));

    ResourceLocation silverToDiamondShulkerBox = location("shulkerboxes/" + color + "diamond/silver_diamond_shulker_box");
    ConditionalRecipe.builder()
      .addCondition(not(new TagEmptyCondition("forge:ingots/silver")))
      .addRecipe(IronShulkerBoxRecipeBuilder.shapedRecipe(result)
        .setGroup(group)
        .key('G', Tags.Items.GLASS)
        .key('M', Tags.Items.GEMS_DIAMOND)
        .key('S', inputTwo)
        .patternLine("GGG")
        .patternLine("GSG")
        .patternLine("MMM")
        .addCriterion("has_item", has(Tags.Items.GEMS_DIAMOND))::build)
      .setAdvancement(location("recipes/ironshulkerbox/shulkerboxes/" + color + "diamond/silver_diamond_shulker_box"),
        ConditionalAdvancement.builder()
          .addCondition(not(new TagEmptyCondition("forge:ingots/silver")))
          .addAdvancement(Advancement.Builder.advancement()
            .parent(new ResourceLocation("recipes/root"))
            .rewards(AdvancementRewards.Builder.recipe(silverToDiamondShulkerBox))
            .addCriterion("has_item", has(Tags.Items.GEMS_DIAMOND))
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(silverToDiamondShulkerBox))
            .requirements(RequirementsStrategy.OR))
      ).build(consumer, silverToDiamondShulkerBox);
  }

  private void registerCrystalBoxRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike input, String color, String group) {
    IronShulkerBoxRecipeBuilder.shapedRecipe(result)
      .setGroup(group)
      .key('G', Tags.Items.GLASS)
      .key('S', input)
      .patternLine("GGG")
      .patternLine("GSG")
      .patternLine("GGG")
      .addCriterion("has_glass", has(Tags.Items.GLASS))
      .build(consumer, location("shulkerboxes/" + color + "crystal/diamond_crystal_shulker_box"));
  }

  private void registerObsidianBoxRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike input, String color, String group) {
    IronShulkerBoxRecipeBuilder.shapedRecipe(result)
      .setGroup(group)
      .key('M', Items.OBSIDIAN)
      .key('S', input)
      .patternLine("MMM")
      .patternLine("MSM")
      .patternLine("MMM")
      .addCriterion("has_obsidian", has(Items.OBSIDIAN))
      .build(consumer, location("shulkerboxes/" + color + "obsidian/diamond_obsidian_shulker_box"));
  }
  */
}

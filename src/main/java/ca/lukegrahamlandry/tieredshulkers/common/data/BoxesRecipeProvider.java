package ca.lukegrahamlandry.tieredshulkers.common.data;

import ca.lukegrahamlandry.tieredshulkers.TieredShulkersMain;
import ca.lukegrahamlandry.tieredshulkers.common.ShulkerColour;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.ShulkerBoxesRegistry;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.UpgradableBoxTier;
import ca.lukegrahamlandry.tieredshulkers.common.items.BoxUpgradeType;
import ca.lukegrahamlandry.tieredshulkers.common.recipes.BoxCraftingRecipeBuilder;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalAdvancement;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Consumer;

public class BoxesRecipeProvider extends RecipeProvider implements IConditionBuilder {
  private static final TagKey<Item> SILVER_TAG = ItemTags.create(new ResourceLocation("forge", "ingots/silver"));
  
  public BoxesRecipeProvider(DataGenerator generatorIn) {
    super(generatorIn);
  }
  
  @Override
  protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
    for (ShulkerColour dyeColor : ShulkerColour.values()){
      String color = dyeColor.getName() + "/";
      String group = "tieredshulkers:" + dyeColor.getName() + "_shulker_box";
      
      this.registerSilverBoxRecipe(consumer, getFor(UpgradableBoxTier.SILVER, dyeColor), getFor(UpgradableBoxTier.COPPER, dyeColor), getFor(UpgradableBoxTier.IRON, dyeColor), color, group);
      this.registerCopperBoxRecipe(consumer, getFor(UpgradableBoxTier.COPPER, dyeColor), ShulkerBoxBlock.getBlockByColor(dyeColor.toVanilla()).asItem(), color, group);
      this.registerIronBoxRecipe(consumer, getFor(UpgradableBoxTier.IRON, dyeColor), getFor(UpgradableBoxTier.COPPER, dyeColor), ShulkerBoxBlock.getBlockByColor(dyeColor.toVanilla()).asItem(), color, group);
      this.registerGoldBoxRecipe(consumer, getFor(UpgradableBoxTier.GOLD, dyeColor), getFor(UpgradableBoxTier.IRON, dyeColor), getFor(UpgradableBoxTier.SILVER, dyeColor), color, group);
      this.registerDiamondBoxRecipe(consumer, getFor(UpgradableBoxTier.DIAMOND, dyeColor), getFor(UpgradableBoxTier.GOLD, dyeColor), getFor(UpgradableBoxTier.SILVER, dyeColor), color, group);
      this.registerCrystalBoxRecipe(consumer, getFor(UpgradableBoxTier.CRYSTAL, dyeColor), getFor(UpgradableBoxTier.DIAMOND, dyeColor), color, group);
      this.registerObsidianBoxRecipe(consumer, getFor(UpgradableBoxTier.OBSIDIAN, dyeColor), getFor(UpgradableBoxTier.DIAMOND, dyeColor), color, group);
    }

    this.addUpgradesRecipes(consumer);
  }
  
  private Block getFor(UpgradableBoxTier tier, ShulkerColour color){
    return tier.blocks.get(color).get();
  }

  private void addUpgradesRecipes(Consumer<FinishedRecipe> consumer) {
    String folder = "upgrades/";

    ShapedRecipeBuilder
      .shaped(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.VANILLA_TO_IRON).get())
      .define('M', Tags.Items.INGOTS_IRON)
      .define('S', Items.SHULKER_SHELL)
      .pattern("MMM")
      .pattern("MSM")
      .pattern("MMM")
      .unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
      .save(consumer,
        prefix(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.VANILLA_TO_IRON).get(), folder));

    ShapedRecipeBuilder.shaped(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.IRON_TO_GOLD).get())
      .define('S', Tags.Items.INGOTS_IRON)
      .define('M', Tags.Items.INGOTS_GOLD)
      .pattern("MSM")
      .pattern("MMM")
      .pattern("MMM")
      .unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
      .save(consumer,
        prefix(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.IRON_TO_GOLD).get(), folder));

    ShapedRecipeBuilder
      .shaped(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.GOLD_TO_DIAMOND).get())
      .define('M', Tags.Items.GEMS_DIAMOND)
      .define('S', Tags.Items.INGOTS_GOLD)
      .define('G', Tags.Items.GLASS)
      .pattern("GMG")
      .pattern("GSG")
      .pattern("GMG")
      .unlockedBy("has_glass", has(Tags.Items.GLASS))
      .save(consumer,
        prefix(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.GOLD_TO_DIAMOND).get(), folder));

    ShapedRecipeBuilder
      .shaped(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.DIAMOND_TO_OBSIDIAN).get())
      .define('M', Blocks.OBSIDIAN)
      .define('G', Tags.Items.GLASS)
      .pattern("MGM")
      .pattern("MMM")
      .pattern("MMM")
      .unlockedBy("has_glass", has(Tags.Items.GLASS))
      .save(consumer,
        prefix(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.DIAMOND_TO_OBSIDIAN).get(), folder));

    ShapedRecipeBuilder
      .shaped(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.DIAMOND_TO_CRYSTAL).get())
      .define('S', Blocks.OBSIDIAN)
      .define('G', Tags.Items.GLASS)
      .pattern("GSG")
      .pattern("GGG")
      .pattern("GGG")
      .unlockedBy("has_glass", has(Tags.Items.GLASS))
      .save(consumer,
        prefix(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.DIAMOND_TO_CRYSTAL).get(), folder));

    ResourceLocation woodToCopperChestUpgradeId = prefix(
      ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.VANILLA_TO_COPPER).get(), folder);
    ShapedRecipeBuilder
        .shaped(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.VANILLA_TO_COPPER).get())
        .define('M', Tags.Items.INGOTS_COPPER)
        .define('S', Items.SHULKER_SHELL)
        .pattern("MMM")
        .pattern("MSM")
        .pattern("MMM")
        .unlockedBy("has_item", has(Items.SHULKER_SHELL))
        .save(consumer, woodToCopperChestUpgradeId);

    ResourceLocation copperToIronChestUpgrade = prefix(
      ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.COPPER_TO_IRON).get(), folder);
    ShapedRecipeBuilder
        .shaped(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.COPPER_TO_IRON).get())
        .define('M', Tags.Items.INGOTS_IRON)
        .define('S', Tags.Items.INGOTS_COPPER)
        .define('G', Tags.Items.GLASS)
        .pattern("GGG")
        .pattern("MSM")
        .pattern("MGM")
        .unlockedBy("has_item", has(ItemTags.PLANKS))
        .save(consumer, copperToIronChestUpgrade);

    ResourceLocation copperToSilverChestUpgrade = prefix(
      ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.COPPER_TO_SILVER).get(), folder);
    ConditionalRecipe.builder()
      .addCondition(
        and(not(new TagEmptyCondition("forge:ingots/copper")), not(new TagEmptyCondition("forge:ingots/silver"))))
      .addRecipe(ShapedRecipeBuilder
        .shaped(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.COPPER_TO_SILVER).get())
        .define('M', SILVER_TAG)
        .define('S', Tags.Items.INGOTS_COPPER)
        .pattern("MSM")
        .pattern("MMM")
        .pattern("MMM")
        .unlockedBy("has_item", has(Tags.Items.INGOTS_COPPER))::save)
      .setAdvancement(location("recipes/tieredshulkers/upgrades/copper_to_silver_shulker_box_upgrade"),
        ConditionalAdvancement.builder()
          .addCondition(
            and(not(new TagEmptyCondition("forge:ingots/copper")), not(new TagEmptyCondition("forge:ingots/silver"))))
          .addAdvancement(Advancement.Builder.advancement()
            .parent(new ResourceLocation("recipes/root"))
            .rewards(AdvancementRewards.Builder.recipe(copperToSilverChestUpgrade))
            .addCriterion("has_item", has(Tags.Items.INGOTS_COPPER))
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(copperToSilverChestUpgrade))
            .requirements(RequirementsStrategy.OR))
      ).build(consumer, copperToSilverChestUpgrade);

    ResourceLocation silverToGoldChestUpgrade = prefix(
      ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.SILVER_TO_GOLD).get(), folder);
    ConditionalRecipe.builder()
      .addCondition(not(new TagEmptyCondition("forge:ingots/silver")))
      .addRecipe(ShapedRecipeBuilder
        .shaped(ShulkerBoxesRegistry.UPGRADES.get(BoxUpgradeType.SILVER_TO_GOLD).get())
        .define('M', Tags.Items.INGOTS_GOLD)
        .define('S', Tags.Items.INGOTS_COPPER)
        .define('G', Tags.Items.GLASS)
        .pattern("MSM")
        .pattern("GGG")
        .pattern("MGM")
        .unlockedBy("has_item", has(Tags.Items.GLASS))::save)
      .setAdvancement(location("recipes/tieredshulkers/upgrades/silver_to_gold_shulker_box_upgrade"),
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
    ResourceLocation loc = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.asItem()));
    return location(prefix + loc.getPath());
  }

  private static ResourceLocation location(String id) {
    return new ResourceLocation(TieredShulkersMain.MOD_ID, id);
    
  }

  private void registerCopperBoxRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike input, String color, String group) {
    ResourceLocation vanillaToCopperShulkerBox = location("shulkerboxes/" + color + "copper/vanilla_copper_shulker_box");
    BoxCraftingRecipeBuilder.shapedRecipe(result)
        .setGroup(group)
        .key('M', Tags.Items.INGOTS_COPPER)
        .key('S', input)
        .patternLine("MMM")
        .patternLine("MSM")
        .patternLine("MMM")
        .addCriterion("has_item", has(Tags.Items.INGOTS_COPPER))
        .build(consumer, vanillaToCopperShulkerBox);
  }

  private void registerSilverBoxRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike input, ItemLike inputTwo, String color, String group) {
    ResourceLocation copperToSilverShulkerBox = location("shulkerboxes/" + color + "silver/copper_silver_shulker_box");
    ConditionalRecipe.builder()
      .addCondition(not(new TagEmptyCondition("forge:ingots/copper")))
      .addRecipe(BoxCraftingRecipeBuilder.shapedRecipe(result)
        .setGroup(group)
        .key('M', SILVER_TAG)
        .key('S', input)
        .patternLine("MMM")
        .patternLine("MSM")
        .patternLine("MMM")
        .addCriterion("has_item", has(SILVER_TAG))::build)
      .setAdvancement(location("recipes/tieredshulkers/shulkerboxes/" + color + "silver/copper_silver_shulker_box"),
        ConditionalAdvancement.builder()
          .addCondition(not(new TagEmptyCondition("forge:ingots/silver")))
          .addAdvancement(Advancement.Builder.advancement()
            .parent(new ResourceLocation("recipes/root"))
            .rewards(AdvancementRewards.Builder.recipe(copperToSilverShulkerBox))
            .addCriterion("has_item", has(SILVER_TAG))
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(copperToSilverShulkerBox))
            .requirements(RequirementsStrategy.OR))
      ).build(consumer, copperToSilverShulkerBox);

    ResourceLocation ironToSilverShulkerBox = location("shulkerboxes/" + color + "silver/iron_silver_shulker_box");
    ConditionalRecipe.builder()
      .addCondition(not(new TagEmptyCondition("forge:ingots/copper")))
      .addRecipe(BoxCraftingRecipeBuilder.shapedRecipe(result)
        .setGroup(group)
        .key('M', SILVER_TAG)
        .key('S', inputTwo)
        .key('G', Tags.Items.GLASS)
        .patternLine("MGM")
        .patternLine("GSG")
        .patternLine("MGM")
        .addCriterion("has_item", has(SILVER_TAG))::build)
      .setAdvancement(location("recipes/tieredshulkers/shulkerboxes/" + color + "silver/iron_silver_shulker_box"),
        ConditionalAdvancement.builder()
          .addCondition(not(new TagEmptyCondition("forge:ingots/silver")))
          .addAdvancement(Advancement.Builder.advancement()
            .parent(new ResourceLocation("recipes/root"))
            .rewards(AdvancementRewards.Builder.recipe(ironToSilverShulkerBox))
            .addCriterion("has_item", has(SILVER_TAG))
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(ironToSilverShulkerBox))
            .requirements(RequirementsStrategy.OR))
      ).build(consumer, ironToSilverShulkerBox);
  }

  private void registerIronBoxRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike input, ItemLike inputTwo, String color, String group) {
    BoxCraftingRecipeBuilder.shapedRecipe(result)
      .setGroup(group)
      .key('G', Tags.Items.GLASS)
      .key('S', input)
      .key('M', Tags.Items.INGOTS_IRON)
      .patternLine("MGM")
      .patternLine("GSG")
      .patternLine("MGM")
      .addCriterion("has_gold", has(Tags.Items.INGOTS_IRON))
      .build(consumer, location("shulkerboxes/" + color + "iron/copper_iron_shulker_box"));

    if (inputTwo == null) return;

    BoxCraftingRecipeBuilder.shapedRecipe(result)
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
    BoxCraftingRecipeBuilder.shapedRecipe(result)
      .setGroup(group)
      .key('S', input)
      .key('M', Tags.Items.INGOTS_GOLD)
      .patternLine("MMM")
      .patternLine("MSM")
      .patternLine("MMM")
      .addCriterion("has_gold", has(Tags.Items.INGOTS_GOLD))
      .build(consumer, location("shulkerboxes/" + color + "gold/iron_gold_shulker_box"));

    BoxCraftingRecipeBuilder.shapedRecipe(result)
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
    BoxCraftingRecipeBuilder.shapedRecipe(result)
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
      .addRecipe(BoxCraftingRecipeBuilder.shapedRecipe(result)
        .setGroup(group)
        .key('G', Tags.Items.GLASS)
        .key('M', Tags.Items.GEMS_DIAMOND)
        .key('S', inputTwo)
        .patternLine("GGG")
        .patternLine("GSG")
        .patternLine("MMM")
        .addCriterion("has_item", has(Tags.Items.GEMS_DIAMOND))::build)
      .setAdvancement(location("recipes/tieredshulkers/shulkerboxes/" + color + "diamond/silver_diamond_shulker_box"),
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
    BoxCraftingRecipeBuilder.shapedRecipe(result)
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
    BoxCraftingRecipeBuilder.shapedRecipe(result)
      .setGroup(group)
      .key('M', Items.OBSIDIAN)
      .key('S', input)
      .patternLine("MMM")
      .patternLine("MSM")
      .patternLine("MMM")
      .addCriterion("has_obsidian", has(Items.OBSIDIAN))
      .build(consumer, location("shulkerboxes/" + color + "obsidian/diamond_obsidian_shulker_box"));
  }

  protected static InventoryChangeTrigger.TriggerInstance has(TagKey<Item> p_206407_) {
    return inventoryTrigger(ItemPredicate.Builder.item().of(p_206407_).build());
  }
}

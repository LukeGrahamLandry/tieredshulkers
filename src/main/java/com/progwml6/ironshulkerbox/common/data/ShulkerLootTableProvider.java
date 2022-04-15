package com.progwml6.ironshulkerbox.common.data;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.datafixers.util.Pair;
import com.progwml6.ironshulkerbox.common.boxes.UpgradableBoxBlock;
import com.progwml6.ironshulkerbox.common.boxes.UpgradableBoxTier;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ShulkerLootTableProvider extends LootTableProvider{
  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
  protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
  public static Map<ResourceLocation, LootTable> tables = new HashMap<>();
  private final DataGenerator generator;

  public ShulkerLootTableProvider(DataGenerator p_124437_) {
    super(p_124437_);
    this.generator = p_124437_;
  }

  @Override
  public void run(HashCache cache) {
    for (UpgradableBoxTier tier : UpgradableBoxTier.values()){
      for (DyeColor color : DyeColor.values()){
        lootTables.put(tier.blocks.get(color).get(), createShulkerBoxDrop(tier.blocks.get(color).get()));
      }
    }

    for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
      tables.put(entry.getKey().getLootTable(), entry.getValue().build());
    }

    writeTables(cache, tables);
  }

  private void writeTables(HashCache cache, Map<ResourceLocation, LootTable> tables) {
    Path outputFolder = this.generator.getOutputFolder();
    tables.forEach((key, lootTable) -> {
      Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
      try {
        DataProvider.save(GSON, cache, LootTables.serialize(lootTable), path);
      } catch (IOException e) {
        System.out.println("Couldn't write loot table " + path);
      }
    });
  }

  static LootTable.Builder createShulkerBoxDrop(Block p_124295_) {
    return LootTable.lootTable().withPool(applyExplosionCondition(p_124295_, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(p_124295_).apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)).apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Lock", "BlockEntityTag.Lock").copy("LootTable", "BlockEntityTag.LootTable").copy("LootTableSeed", "BlockEntityTag.LootTableSeed")).apply(SetContainerContents.setContents(BlockEntityType.SHULKER_BOX).withEntry(DynamicLoot.dynamicEntry(UpgradableBoxBlock.CONTENTS))))));
  }

  private static LootPool.Builder applyExplosionCondition(Block p_124295_, LootPool.Builder add) {
    return add.when(ExplosionCondition.survivesExplosion());
  }


}

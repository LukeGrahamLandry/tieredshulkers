package com.progwml6.ironshulkerbox.common.inventory;

import com.progwml6.ironshulkerbox.IronShulkerBoxes;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class IronShulkerBoxesContainerTypes {

  public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, IronShulkerBoxes.MOD_ID);

  public static final RegistryObject<MenuType<IronShulkerBoxContainer>> IRON_SHULKER_BOX = CONTAINERS.register("iron_shulker_box", () -> new MenuType<>(IronShulkerBoxContainer::createIronContainer));

  public static final RegistryObject<MenuType<IronShulkerBoxContainer>> GOLD_SHULKER_BOX = CONTAINERS.register("gold_shulker_box", () -> new MenuType<>(IronShulkerBoxContainer::createGoldContainer));

  public static final RegistryObject<MenuType<IronShulkerBoxContainer>> DIAMOND_SHULKER_BOX = CONTAINERS.register("diamond_shulker_box", () -> new MenuType<>(IronShulkerBoxContainer::createDiamondContainer));

  public static final RegistryObject<MenuType<IronShulkerBoxContainer>> CRYSTAL_SHULKER_BOX = CONTAINERS.register("crystal_shulker_box", () -> new MenuType<>(IronShulkerBoxContainer::createCrystalContainer));

  public static final RegistryObject<MenuType<IronShulkerBoxContainer>> COPPER_SHULKER_BOX = CONTAINERS.register("copper_shulker_box", () -> new MenuType<>(IronShulkerBoxContainer::createCopperContainer));

  public static final RegistryObject<MenuType<IronShulkerBoxContainer>> SILVER_SHULKER_BOX = CONTAINERS.register("silver_shulker_box", () -> new MenuType<>(IronShulkerBoxContainer::createSilverContainer));

  public static final RegistryObject<MenuType<IronShulkerBoxContainer>> OBSIDIAN_SHULKER_BOX = CONTAINERS.register("obsidian_shulker_box", () -> new MenuType<>(IronShulkerBoxContainer::createObsidianContainer));
}

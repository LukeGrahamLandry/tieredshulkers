package ca.lukegrahamlandry.tieredshulkers.common.items;

import ca.lukegrahamlandry.tieredshulkers.common.Util;
import ca.lukegrahamlandry.tieredshulkers.common.boxes.UpgradableBoxTier;

import static ca.lukegrahamlandry.tieredshulkers.common.boxes.UpgradableBoxTier.*;

public enum BoxUpgradeType {
  IRON_TO_GOLD(IRON, GOLD),
  GOLD_TO_DIAMOND(GOLD, DIAMOND),
  COPPER_TO_SILVER(COPPER, SILVER),
  SILVER_TO_GOLD(SILVER, GOLD),
  COPPER_TO_IRON(COPPER, IRON),
  DIAMOND_TO_CRYSTAL(DIAMOND, CRYSTAL),
  VANILLA_TO_IRON(null, IRON),
  VANILLA_TO_COPPER(null, COPPER),
  DIAMOND_TO_OBSIDIAN(DIAMOND, OBSIDIAN);

  public final String name;
  public final UpgradableBoxTier source;
  public final UpgradableBoxTier target;

  BoxUpgradeType(UpgradableBoxTier source, UpgradableBoxTier target) {
    this.name = Util.toEnglishName(this.name());
    this.source = source;
    this.target = target;
  }

  public boolean canUpgrade(UpgradableBoxTier from) {
    return from == this.source;
  }
}

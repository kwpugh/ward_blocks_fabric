package com.kwpugh.ward_blocks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.entity.mob.MobEntity;

@Config(name = "ward_blocks")
public class WardBlocksConfig extends PartitioningSerializer.GlobalData
{
	public General GENERAL = new General();

	@Config(name = "general")
	public static class General implements ConfigData {
		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nEnable-Disable Blocks"
				+ "\n***********************")
		public boolean enableGrowthBlock = true;
		public boolean enableHealthBlock = true;
		public boolean enableDefenseBlock = true;
		public boolean enableExpBlock = true;
		public boolean enableAttackBlock = true;
		public boolean enableLootBlock = true;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nBlock Loot Chances"
				+ "\n***********************")
		public float growthBlockChance = 0.07F;
		public float healthBlockChance = 0.10F;
		public float defenseBlockChance = 0.08F;
		public float expBlockChance = 0.05F;
		public float attackBlockChance = 0.05F;
		public float lootBlockChance = 0.05F;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nHealth Ward"
				+ "\n***********************")
		public int healthHorizRadius = 12;
		public int healthVertRadius = 4;
		public int healthLevel = 0;
		public boolean enableYellowHearts = true;
		public float healthYellowHearts = 20;
		public int effectTickInterval = 8;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nDefense Ward"
				+ "\n***********************")
		public int defenseHorizRadius = 12;
		public int defenseVertRadius = 8;
		public int defenseLevel = 0;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nGrowth Ward"
				+ "\n***********************")
		public int growthHoriz = 10;
		public int growthHeight = 5;
		public int baseTickDelay = 90;
		public int cactusTickDelay = 20;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nExp Ward"
				+ "\n***********************")
		public int expHorizRadius = 12;
		public int expVertRadius = 8;
		public int expLevel = 1;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nAttack Ward"
				+ "\n***********************")
		public int attackHorizRadius = 12;
		public int attackVertRadius = 8;
		public int damageAmount = 10;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nLoot Ward"
				+ "\n***********************")
		public int lootHorizRadius = 12;
		public int lootVertRadius = 8;
		public boolean skipNamedEndermite = false;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nLoot Ward - Drops"
				+ "\n- only use values between .01 to .99"
				+ "\n- values cannot overlap"
				+ "\n- values should be in decending order from"
				+ "\ncutoff, common, uncommon, and rare"
				+ "\n- values above cutoff threshold get a carrot"
				+ "\n***********************")
		public String defaultDrop = "minecraft:carrot";
		public double cutoffThreshold = .48;
		public double commonThreshold = .28;
		public double uncommonThreshold = .12;
		public double rareThreshold = .04;
	}
}
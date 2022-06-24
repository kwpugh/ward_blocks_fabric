package com.kwpugh.ward_blocks.init;

import com.kwpugh.ward_blocks.WardBlocks;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class LootTableInit
{
	static boolean enableGrowth = WardBlocks.CONFIG.GENERAL.enableGrowthBlock;
	static boolean enableHealth = WardBlocks.CONFIG.GENERAL.enableHealthBlock;
	static boolean enableDefense = WardBlocks.CONFIG.GENERAL.enableDefenseBlock;
	static boolean enableExp = WardBlocks.CONFIG.GENERAL.enableExpBlock;
	static boolean enableAttack = WardBlocks.CONFIG.GENERAL.enableAttackBlock;
	static boolean enableLoot = WardBlocks.CONFIG.GENERAL.enableLootBlock;

	static float growthChance = WardBlocks.CONFIG.GENERAL.growthBlockChance;
	static float healthChance = WardBlocks.CONFIG.GENERAL.healthBlockChance;
	static float defenseChance = WardBlocks.CONFIG.GENERAL.defenseBlockChance;
	static float expChance = WardBlocks.CONFIG.GENERAL.expBlockChance;
	static float attackChance = WardBlocks.CONFIG.GENERAL.attackBlockChance;
	static float lootChance = WardBlocks.CONFIG.GENERAL.lootBlockChance;

	public static void registerLoot()
	{
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if(enableGrowth)
			{
				if(id.equals(LootTables.DESERT_PYRAMID_CHEST) ||
						id.equals(LootTables.JUNGLE_TEMPLE_CHEST))
				{
					LootPool GROWTH_WARD_BLOCK = LootPool.builder()
							.with(ItemEntry.builder(BlockInit.GROWTH_WARD_BLOCK))
							.rolls(ConstantLootNumberProvider.create(1))
							.conditionally(RandomChanceLootCondition.builder(growthChance).build())
							.build();

					tableBuilder.pool(GROWTH_WARD_BLOCK);
				}
			}

			if(enableHealth)
			{
				if(id.equals(LootTables.BURIED_TREASURE_CHEST))
				{
					LootPool HEALTH_WARD_BLOCK = LootPool.builder()
							.with(ItemEntry.builder(BlockInit.HEALTH_WARD_BLOCK))
							.rolls(ConstantLootNumberProvider.create(1))
							.conditionally(RandomChanceLootCondition.builder(healthChance).build())
							.build();

					tableBuilder.pool(HEALTH_WARD_BLOCK);
				}
			}

			if(enableDefense)
			{
				if(id.equals(LootTables.PILLAGER_OUTPOST_CHEST))
				{
					LootPool DEFENSE_WARD_BLOCK = LootPool.builder()
							.with(ItemEntry.builder(BlockInit.DEFENSE_WARD_BLOCK))
							.rolls(ConstantLootNumberProvider.create(1))
							.conditionally(RandomChanceLootCondition.builder(defenseChance).build())
							.build();

					tableBuilder.pool(DEFENSE_WARD_BLOCK);
				}
			}

			if(enableExp)
			{
				if(id.equals(LootTables.STRONGHOLD_LIBRARY_CHEST))
				{
					LootPool EXP_WARD_BLOCK = LootPool.builder()
							.with(ItemEntry.builder(BlockInit.EXP_WARD_BLOCK))
							.rolls(ConstantLootNumberProvider.create(1))
							.conditionally(RandomChanceLootCondition.builder(expChance).build())
							.build();

					tableBuilder.pool(EXP_WARD_BLOCK);
				}
			}

			if(enableAttack)
			{
				if(id.equals(LootTables.BASTION_TREASURE_CHEST))
				{
					LootPool ATTACK_WARD_BLOCK = LootPool.builder()
							.with(ItemEntry.builder(BlockInit.ATTACK_WARD_BLOCK))
							.rolls(ConstantLootNumberProvider.create(1))
							.conditionally(RandomChanceLootCondition.builder(attackChance).build())
							.build();

					tableBuilder.pool(ATTACK_WARD_BLOCK);
				}
			}

			if(enableLoot)
			{
				if(id.equals(LootTables.END_CITY_TREASURE_CHEST))
				{
					LootPool LOOT_WARD_BLOCK = LootPool.builder()
							.with(ItemEntry.builder(BlockInit.LOOT_WARD_BLOCK))
							.rolls(ConstantLootNumberProvider.create(1))
							.conditionally(RandomChanceLootCondition.builder(lootChance).build())
							.build();

					tableBuilder.pool(LOOT_WARD_BLOCK);
				}
			}
		});
	}
}
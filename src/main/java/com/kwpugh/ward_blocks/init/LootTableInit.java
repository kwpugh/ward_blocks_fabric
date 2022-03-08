package com.kwpugh.ward_blocks.init;

import java.util.List;

import com.kwpugh.ward_blocks.WardBlocks;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.Lists;
import com.kwpugh.ward_blocks.init.BlockInit;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;

import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

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

	private static final List<LootTableInsert> INSERTS = Lists.newArrayList();

	public static void init()
	{
		if(enableGrowth)
		{
			FabricLootPoolBuilder GROWTH_WARD_BLOCK = FabricLootPoolBuilder.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.with(ItemEntry.builder(BlockInit.GROWTH_WARD_BLOCK))
					.withCondition(RandomChanceLootCondition.builder(growthChance).build());

			insert(new LootTableInsert(GROWTH_WARD_BLOCK,
					new Identifier("minecraft", "chests/buried_treasure")
			));

			LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
				INSERTS.forEach(i->{
					if(ArrayUtils.contains(i.tables, identifier))
					{
						i.insert(supplier);
					}
				});
			}));
		}

		if(enableHealth)
		{
			FabricLootPoolBuilder HEALTH_WARD_BLOCK = FabricLootPoolBuilder.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.with(ItemEntry.builder(BlockInit.HEALTH_WARD_BLOCK))
					.withCondition(RandomChanceLootCondition.builder(healthChance).build());

			insert(new LootTableInsert(HEALTH_WARD_BLOCK,
					new Identifier("minecraft", "chests/buried_treasure")
			));

			LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
				INSERTS.forEach(i->{
					if(ArrayUtils.contains(i.tables, identifier))
					{
						i.insert(supplier);
					}
				});
			}));
		}

		if(enableDefense)
		{
			FabricLootPoolBuilder DEFENSE_WARD_BLOCK = FabricLootPoolBuilder.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.with(ItemEntry.builder(BlockInit.DEFENSE_WARD_BLOCK))
					.withCondition(RandomChanceLootCondition.builder(defenseChance).build());

			insert(new LootTableInsert(DEFENSE_WARD_BLOCK,
					new Identifier("minecraft", "chests/pillager_outpost")
			));

			LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
				INSERTS.forEach(i->{
					if(ArrayUtils.contains(i.tables, identifier))
					{
						i.insert(supplier);
					}
				});
			}));
		}


		if(enableExp)
		{
			FabricLootPoolBuilder EXP_WARD_BLOCK = FabricLootPoolBuilder.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.with(ItemEntry.builder(BlockInit.EXP_WARD_BLOCK))
					.withCondition(RandomChanceLootCondition.builder(expChance).build());

			insert(new LootTableInsert(EXP_WARD_BLOCK,
					new Identifier("minecraft", "chests/stronghold_library")
			));

			LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
				INSERTS.forEach(i->{
					if(ArrayUtils.contains(i.tables, identifier))
					{
						i.insert(supplier);
					}
				});
			}));
		}

		if(enableAttack)
		{
			FabricLootPoolBuilder ATTACK_WARD_BLOCK = FabricLootPoolBuilder.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.with(ItemEntry.builder(BlockInit.ATTACK_WARD_BLOCK))
					.withCondition(RandomChanceLootCondition.builder(attackChance).build());

			insert(new LootTableInsert(ATTACK_WARD_BLOCK,
					new Identifier("minecraft", "chests/bastion_treasure")
			));

			LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
				INSERTS.forEach(i->{
					if(ArrayUtils.contains(i.tables, identifier))
					{
						i.insert(supplier);
					}
				});
			}));
		}


		if(enableLoot)
		{
			FabricLootPoolBuilder LOOT_WARD_BLOCK = FabricLootPoolBuilder.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.with(ItemEntry.builder(BlockInit.LOOT_WARD_BLOCK))
					.withCondition(RandomChanceLootCondition.builder(lootChance).build());

			insert(new LootTableInsert(LOOT_WARD_BLOCK,
					new Identifier("minecraft", "chests/end_city_treasure")
			));

			LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
				INSERTS.forEach(i->{
					if(ArrayUtils.contains(i.tables, identifier))
					{
						i.insert(supplier);
					}
				});
			}));
		}
	}

	public static void insert(LootTableInsert insert)
	{
		INSERTS.add(insert);
	}

	public static class LootTableInsert
	{
		public final Identifier[] tables;
		public final FabricLootPoolBuilder lootPool;

		public LootTableInsert(FabricLootPoolBuilder lootPool, Identifier... tables)
		{
			this.tables = tables;
			this.lootPool = lootPool;
		}

		public void insert(FabricLootSupplierBuilder supplier)
		{
			supplier.pool(lootPool);
		}
	}
}
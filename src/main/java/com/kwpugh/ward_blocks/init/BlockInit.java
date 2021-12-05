package com.kwpugh.ward_blocks.init;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.blocks.*;
import com.kwpugh.ward_blocks.blocks.entities.*;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit
{
	static boolean enableGrowth = WardBlocks.CONFIG.GENERAL.enableGrowthBlock;
	static boolean enableHealth = WardBlocks.CONFIG.GENERAL.enableHealthBlock;
	static boolean enableDefense = WardBlocks.CONFIG.GENERAL.enableDefenseBlock;
	static boolean enableExp = WardBlocks.CONFIG.GENERAL.enableExpBlock;
	static boolean enableAttack = WardBlocks.CONFIG.GENERAL.enableAttackBlock;
	static boolean enableLoot = WardBlocks.CONFIG.GENERAL.enableLootBlock;
	
	public static BlockEntityType<GrowthWardBlockEntity> GROWTH_WARD_BLOCK_ENTITY;
	public static BlockEntityType<HealthWardBlockEntity> HEALTH_WARD_BLOCK_ENTITY;
	public static BlockEntityType<DefenseWardBlockEntity> DEFENSE_WARD_BLOCK_ENTITY;
	public static BlockEntityType<ExpWardBlockEntity> EXP_WARD_BLOCK_ENTITY;
	public static BlockEntityType<AttackWardBlockEntity> ATTACK_WARD_BLOCK_ENTITY;
	public static BlockEntityType<LootWardBlockEntity> LOOT_WARD_BLOCK_ENTITY;
	
	public static final Block GROWTH_WARD_BLOCK = new GrowthWardBlock(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F));
	public static final Block HEALTH_WARD_BLOCK = new HealthWardBlock(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F));
	public static final Block DEFENSE_WARD_BLOCK = new DefenseWardBlock(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F));
	public static final Block EXP_WARD_BLOCK = new ExpWardBlock(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F));
	public static final Block ATTACK_WARD_BLOCK = new AttackWardBlock(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F));
	public static final Block LOOT_WARD_BLOCK = new LootWardBlock(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F));
	
	public static void registerBlocks()
	{
		if(enableGrowth)
		{
			Registry.register(Registry.BLOCK, new Identifier("ward_blocks", "growth_ward_block"), GROWTH_WARD_BLOCK);
		}
		
		if(enableHealth)
		{
			Registry.register(Registry.BLOCK, new Identifier("ward_blocks", "health_ward_block"), HEALTH_WARD_BLOCK);
		}

		if(enableDefense)
		{
			Registry.register(Registry.BLOCK, new Identifier("ward_blocks", "defense_ward_block"), DEFENSE_WARD_BLOCK);
		}

		if(enableExp)
		{
			Registry.register(Registry.BLOCK, new Identifier("ward_blocks", "exp_ward_block"), EXP_WARD_BLOCK);
		}

		if(enableAttack)
		{
			Registry.register(Registry.BLOCK, new Identifier("ward_blocks", "attack_ward_block"), ATTACK_WARD_BLOCK);
		}

		if(enableLoot)
		{
			Registry.register(Registry.BLOCK, new Identifier("ward_blocks", "loot_ward_block"), LOOT_WARD_BLOCK);
		}
	}
	
	public static void registerBlockItems()
	{
		if(enableGrowth)
		{
			Registry.register(Registry.ITEM, new Identifier("ward_blocks", "growth_ward_block"), new BlockItem(GROWTH_WARD_BLOCK, new Item.Settings().maxCount(1).group(WardBlocks.WARD_BLOCKS_GROUP)));
		}
		
		if(enableHealth)
		{
			Registry.register(Registry.ITEM, new Identifier("ward_blocks", "health_ward_block"), new BlockItem(HEALTH_WARD_BLOCK, new Item.Settings().maxCount(1).group(WardBlocks.WARD_BLOCKS_GROUP)));
		}

		if(enableDefense)
		{
			Registry.register(Registry.ITEM, new Identifier("ward_blocks", "defense_ward_block"), new BlockItem(DEFENSE_WARD_BLOCK, new Item.Settings().maxCount(1).group(WardBlocks.WARD_BLOCKS_GROUP)));
		}

		if(enableExp)
		{
			Registry.register(Registry.ITEM, new Identifier("ward_blocks", "exp_ward_block"), new BlockItem(EXP_WARD_BLOCK, new Item.Settings().maxCount(1).group(WardBlocks.WARD_BLOCKS_GROUP)));
		}

		if(enableAttack)
		{
			Registry.register(Registry.ITEM, new Identifier("ward_blocks", "attack_ward_block"), new BlockItem(ATTACK_WARD_BLOCK, new Item.Settings().maxCount(1).group(WardBlocks.WARD_BLOCKS_GROUP)));
		}

		if(enableLoot)
		{
			Registry.register(Registry.ITEM, new Identifier("ward_blocks", "loot_ward_block"), new BlockItem(LOOT_WARD_BLOCK, new Item.Settings().maxCount(1).group(WardBlocks.WARD_BLOCKS_GROUP)));
		}
	}
	
	public static void registerBlockEntities()
	{
		if(enableGrowth)
		{
			GROWTH_WARD_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "ward_blocks:growth_ward_block", FabricBlockEntityTypeBuilder.create(GrowthWardBlockEntity::new, GROWTH_WARD_BLOCK).build(null));
		}
		
		if(enableHealth)
		{
			HEALTH_WARD_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "ward_blocks:health_ward_block", FabricBlockEntityTypeBuilder.create(HealthWardBlockEntity::new, HEALTH_WARD_BLOCK).build(null));
		}

		if(enableDefense)
		{
			DEFENSE_WARD_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "ward_blocks:defense_ward_block", FabricBlockEntityTypeBuilder.create(DefenseWardBlockEntity::new, DEFENSE_WARD_BLOCK).build(null));
		}

		if(enableExp)
		{
			EXP_WARD_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "ward_blocks:exp_ward_block", FabricBlockEntityTypeBuilder.create(ExpWardBlockEntity::new, EXP_WARD_BLOCK).build(null));
		}

		if(enableAttack)
		{
			ATTACK_WARD_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "ward_blocks:attack_ward_block", FabricBlockEntityTypeBuilder.create(AttackWardBlockEntity::new, ATTACK_WARD_BLOCK).build(null));
		}

		if(enableLoot)
		{
			LOOT_WARD_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "ward_blocks:loot_ward_block", FabricBlockEntityTypeBuilder.create(LootWardBlockEntity::new, LOOT_WARD_BLOCK).build(null));
		}
	}
}
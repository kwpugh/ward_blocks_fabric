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
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class BlockInit
{
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
		Registry.register(Registries.BLOCK, new Identifier(WardBlocks.MOD_ID, "growth_ward_block"), GROWTH_WARD_BLOCK);
		Registry.register(Registries.BLOCK, new Identifier(WardBlocks.MOD_ID, "health_ward_block"), HEALTH_WARD_BLOCK);
		Registry.register(Registries.BLOCK, new Identifier(WardBlocks.MOD_ID, "defense_ward_block"), DEFENSE_WARD_BLOCK);
		Registry.register(Registries.BLOCK, new Identifier(WardBlocks.MOD_ID, "exp_ward_block"), EXP_WARD_BLOCK);
		Registry.register(Registries.BLOCK, new Identifier(WardBlocks.MOD_ID, "attack_ward_block"), ATTACK_WARD_BLOCK);
		Registry.register(Registries.BLOCK, new Identifier(WardBlocks.MOD_ID, "loot_ward_block"), LOOT_WARD_BLOCK);
	}
	
	public static void registerBlockItems()
	{
		Registry.register(Registries.ITEM, new Identifier(WardBlocks.MOD_ID, "growth_ward_block"), new BlockItem(GROWTH_WARD_BLOCK, new Item.Settings().maxCount(1)));
		Registry.register(Registries.ITEM, new Identifier(WardBlocks.MOD_ID, "health_ward_block"), new BlockItem(HEALTH_WARD_BLOCK, new Item.Settings().maxCount(1)));
		Registry.register(Registries.ITEM, new Identifier(WardBlocks.MOD_ID, "defense_ward_block"), new BlockItem(DEFENSE_WARD_BLOCK, new Item.Settings().maxCount(1)));
		Registry.register(Registries.ITEM, new Identifier(WardBlocks.MOD_ID, "exp_ward_block"), new BlockItem(EXP_WARD_BLOCK, new Item.Settings().maxCount(1)));
		Registry.register(Registries.ITEM, new Identifier(WardBlocks.MOD_ID, "attack_ward_block"), new BlockItem(ATTACK_WARD_BLOCK, new Item.Settings().maxCount(1)));
		Registry.register(Registries.ITEM, new Identifier(WardBlocks.MOD_ID, "loot_ward_block"), new BlockItem(LOOT_WARD_BLOCK, new Item.Settings().maxCount(1)));
	}
	
	public static void registerBlockEntities()
	{
		GROWTH_WARD_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, "ward_blocks:growth_ward_block", FabricBlockEntityTypeBuilder.create(GrowthWardBlockEntity::new, GROWTH_WARD_BLOCK).build(null));
		HEALTH_WARD_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, "ward_blocks:health_ward_block", FabricBlockEntityTypeBuilder.create(HealthWardBlockEntity::new, HEALTH_WARD_BLOCK).build(null));
		DEFENSE_WARD_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, "ward_blocks:defense_ward_block", FabricBlockEntityTypeBuilder.create(DefenseWardBlockEntity::new, DEFENSE_WARD_BLOCK).build(null));
		EXP_WARD_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, "ward_blocks:exp_ward_block", FabricBlockEntityTypeBuilder.create(ExpWardBlockEntity::new, EXP_WARD_BLOCK).build(null));
		ATTACK_WARD_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, "ward_blocks:attack_ward_block", FabricBlockEntityTypeBuilder.create(AttackWardBlockEntity::new, ATTACK_WARD_BLOCK).build(null));
		LOOT_WARD_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, "ward_blocks:loot_ward_block", FabricBlockEntityTypeBuilder.create(LootWardBlockEntity::new, LOOT_WARD_BLOCK).build(null));
	}
}
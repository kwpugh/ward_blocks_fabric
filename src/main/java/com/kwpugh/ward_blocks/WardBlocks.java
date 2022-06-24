package com.kwpugh.ward_blocks;

import com.kwpugh.ward_blocks.config.WardBlocksConfig;
import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.init.LootTableInit;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class WardBlocks implements ModInitializer
{	
	public static final String MOD_ID = "ward_blocks";
	public static final ItemGroup WARD_BLOCKS_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "ward_blocks_group"), () -> new ItemStack(BlockInit.GROWTH_WARD_BLOCK));
	public static final WardBlocksConfig CONFIG = AutoConfig.register(WardBlocksConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new)).getConfig();

	@Override
    public void onInitialize()
    {
    	BlockInit.registerBlocks();
    	BlockInit.registerBlockItems();
    	BlockInit.registerBlockEntities();
    	LootTableInit.registerLoot();
    }
}
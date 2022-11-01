package com.kwpugh.ward_blocks;

import com.kwpugh.ward_blocks.config.WardBlocksConfig;
import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.init.LootTableInit;
import com.kwpugh.ward_blocks.util.WardBlocksGroup;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;

public class WardBlocks implements ModInitializer
{	
	public static final String MOD_ID = "ward_blocks";
	public static final WardBlocksConfig CONFIG = AutoConfig.register(WardBlocksConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new)).getConfig();

	@Override
    public void onInitialize()
    {
    	BlockInit.registerBlocks();
    	BlockInit.registerBlockItems();
    	BlockInit.registerBlockEntities();
		WardBlocksGroup.addGroup();
    	LootTableInit.registerLoot();
    }
}
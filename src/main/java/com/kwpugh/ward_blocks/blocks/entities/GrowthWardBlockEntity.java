package com.kwpugh.ward_blocks.blocks.entities;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrowthWardBlockEntity extends BlockEntity
{	
	static int growthHoriz = WardBlocks.CONFIG.GENERAL.growthHoriz;
	static int growthHeight = WardBlocks.CONFIG.GENERAL.growthHeight;
	static int baseTickDelay = WardBlocks.CONFIG.GENERAL.baseTickDelay;
	static int cactusTickDelay = WardBlocks.CONFIG.GENERAL.cactusTickDelay;

	public GrowthWardBlockEntity(BlockPos pos, BlockState state)
	{
		super(BlockInit.GROWTH_WARD_BLOCK_ENTITY, pos, state);
	}

	public static void tick(World world, BlockPos pos, BlockState state, BlockEntity be)
	{
		if(!world.isClient && world.isReceivingRedstonePower(pos))
		{
			WardBlockEffects.growCrops(world, pos, baseTickDelay, cactusTickDelay, growthHoriz, growthHeight);
		}
	}
}
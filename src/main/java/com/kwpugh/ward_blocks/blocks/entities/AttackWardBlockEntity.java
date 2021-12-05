package com.kwpugh.ward_blocks.blocks.entities;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AttackWardBlockEntity extends BlockEntity
{
	static int attackHorizontal = WardBlocks.CONFIG.GENERAL.attackHorizRadius;
	static int attackVertical = WardBlocks.CONFIG.GENERAL.attackVertRadius;
	static float damageAmount = WardBlocks.CONFIG.GENERAL.damageAmount;

	public AttackWardBlockEntity(BlockPos pos, BlockState state)
	{
		super(BlockInit.ATTACK_WARD_BLOCK_ENTITY, pos, state);
	}

	public static void tick(World world, BlockPos pos, BlockState state, BlockEntity be)
	{
		if(!world.isClient && world.isReceivingRedstonePower(pos))
		{
			WardBlockEffects.attackMobs(world, pos, attackHorizontal, attackVertical, damageAmount);
		}
	}
}
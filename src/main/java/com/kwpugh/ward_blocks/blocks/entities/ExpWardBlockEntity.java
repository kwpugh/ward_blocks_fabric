package com.kwpugh.ward_blocks.blocks.entities;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExpWardBlockEntity extends BlockEntity
{
	static int expHorizontal = WardBlocks.CONFIG.GENERAL.expHorizRadius;
	static int expVertical = WardBlocks.CONFIG.GENERAL.expVertRadius;
	static int expLevel = WardBlocks.CONFIG.GENERAL.expLevel;

    public ExpWardBlockEntity(BlockPos pos, BlockState state)
    {
        super(BlockInit.EXP_WARD_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, BlockEntity be)
    {
        if(!world.isClient && world.isReceivingRedstonePower(pos))
        {
            WardBlockEffects.giveExp(world, pos, expHorizontal, expVertical, expLevel);
        }
    }
}
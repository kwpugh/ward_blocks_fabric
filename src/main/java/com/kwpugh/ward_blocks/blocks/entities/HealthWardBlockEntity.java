package com.kwpugh.ward_blocks.blocks.entities;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HealthWardBlockEntity extends BlockEntity
{
    static int healthHorizontal = WardBlocks.CONFIG.GENERAL.healthHorizRadius;
    static int healthVertical = WardBlocks.CONFIG.GENERAL.healthVertRadius;
	static int healthLevel = WardBlocks.CONFIG.GENERAL.healthLevel;
	static float healthYellowHearts = WardBlocks.CONFIG.GENERAL.healthYellowHearts;
    static int effectTickInterval = WardBlocks.CONFIG.GENERAL.effectTickInterval;
    static boolean enableExtraHearts = WardBlocks.CONFIG.GENERAL.enableYellowHearts;

    public HealthWardBlockEntity(BlockPos pos, BlockState state)
    {
        super(BlockInit.HEALTH_WARD_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, BlockEntity be)
    {
        if(!world.isClient && world.isReceivingRedstonePower(pos))
        {
            WardBlockEffects.giveHealth(world, pos, healthHorizontal, healthVertical, healthLevel, effectTickInterval, enableExtraHearts, healthYellowHearts);
        }
    }
}

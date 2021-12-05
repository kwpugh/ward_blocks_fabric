package com.kwpugh.ward_blocks.blocks.entities;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LootWardBlockEntity extends BlockEntity
{
    static int lootHorizontal = WardBlocks.CONFIG.GENERAL.lootHorizRadius;
    static int lootVertical = WardBlocks.CONFIG.GENERAL.lootVertRadius;

    public LootWardBlockEntity(BlockPos pos, BlockState state)
    {
        super(BlockInit.LOOT_WARD_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, BlockEntity be)
    {
        if(!world.isClient && world.isReceivingRedstonePower(pos))
        {
            WardBlockEffects.giveLoot(world, pos, lootHorizontal, lootVertical);
        }
    }
}
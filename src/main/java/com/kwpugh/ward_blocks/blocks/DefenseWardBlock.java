package com.kwpugh.ward_blocks.blocks;

import com.kwpugh.ward_blocks.blocks.entities.DefenseWardBlockEntity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class DefenseWardBlock extends Block implements BlockEntityProvider
{
	public DefenseWardBlock(Settings settings)
	{
		super(settings);
	}

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new DefenseWardBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return DefenseWardBlockEntity::tick;
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options)
    {
        tooltip.add(new TranslatableText("block.ward_blocks.defense_ward_block.tip1").formatted(Formatting.GREEN));
    }
}
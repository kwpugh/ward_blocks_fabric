package com.kwpugh.ward_blocks.util;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.BlockInit;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;

public class WardBlocksGroup
{
    public static void addGroup()
    {
        // force class run when we want
    }

    private static final ItemGroup WARD_BLOCKS_GROUP = FabricItemGroup.builder(new Identifier(WardBlocks.MOD_ID, "ward_blocks_group"))
            .icon(() -> new ItemStack(BlockInit.EXP_WARD_BLOCK))
            .entries((enabledFeatures, entries, operatorEnabled) -> {
                entries.add(BlockInit.GROWTH_WARD_BLOCK);
                entries.add(BlockInit.HEALTH_WARD_BLOCK);
                entries.add(BlockInit.DEFENSE_WARD_BLOCK);
                entries.add(BlockInit.EXP_WARD_BLOCK);
                entries.add(BlockInit.ATTACK_WARD_BLOCK);
                entries.add(BlockInit.LOOT_WARD_BLOCK);
            })
            .build();

//    public static final ItemGroup WARD_BLOCKS_GROUP = new FabricItemGroup(new Identifier(WardBlocks.MOD_ID, "ward_blocks_group"))
//    {
//        @Override
//        public ItemStack createIcon()
//        {
//            return new ItemStack(BlockInit.EXP_WARD_BLOCK);
//        }
//
//        @Override
//        protected void addItems(FeatureSet enabledFeatures, Entries entries, boolean hasPermission)
//        {
//            entries.add(BlockInit.GROWTH_WARD_BLOCK);
//            entries.add(BlockInit.HEALTH_WARD_BLOCK);
//            entries.add(BlockInit.DEFENSE_WARD_BLOCK);
//            entries.add(BlockInit.EXP_WARD_BLOCK);
//            entries.add(BlockInit.ATTACK_WARD_BLOCK);
//            entries.add(BlockInit.LOOT_WARD_BLOCK);
//        }
//    };
}

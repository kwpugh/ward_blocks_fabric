package com.kwpugh.ward_blocks.util;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.TagInit;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Random;

public class DropUtil
{
    public static ItemStack getDrops()
    {
        Random random = new Random();
        double r = random.nextDouble();  //generate a random double between 0 and 1

        // Get the default item string from config and cast to ItemStack
        Identifier id = Identifier.tryParse(WardBlocks.CONFIG.GENERAL.defaultDrop);
        ItemStack stack = Registry.ITEM.get(id).getDefaultStack();

        if(r >= WardBlocks.CONFIG.GENERAL.cutoffThreshold)  // cutoff to get default
        {
            // Give the default item
        }
        else if(r >= WardBlocks.CONFIG.GENERAL.commonThreshold) // Common
        {
            stack = TagInit.LOOTER_COMMON.getRandom(random).getDefaultStack();
        }
        else if(r >= WardBlocks.CONFIG.GENERAL.uncommonThreshold)  //Uncommon
        {
            stack = TagInit.LOOTER_UNCOMMON.getRandom(random).getDefaultStack();
        }
        else if(r >= WardBlocks.CONFIG.GENERAL.rareThreshold)  //Rare
        {
            stack = TagInit.LOOTER_RARE.getRandom(random).getDefaultStack();
        }
        else
        {
            stack = TagInit.LOOTER_VERY_RARE.getRandom(random).getDefaultStack();
        }

        return stack;
    }
}

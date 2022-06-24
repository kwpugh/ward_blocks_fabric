package com.kwpugh.ward_blocks.util;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.TagInit;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;

public class DropUtil
{
    public static ItemStack getDrops()
    {
        Random random = Random.create(); // AbstractRandom ??

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
            stack = Registry.ITEM.getEntryList(TagInit.LOOTER_COMMON).flatMap((items) ->
                    items.getRandom(random)).map((itemEntry) ->
                    (itemEntry.value()).getDefaultStack()).orElse(stack);
        }
        else if(r >= WardBlocks.CONFIG.GENERAL.uncommonThreshold)  //Uncommon
        {
            stack = Registry.ITEM.getEntryList(TagInit.LOOTER_UNCOMMON).flatMap((items) ->
                    items.getRandom(random)).map((itemEntry) ->
                    (itemEntry.value()).getDefaultStack()).orElse(stack);
        }
        else if(r >= WardBlocks.CONFIG.GENERAL.rareThreshold)  //Rare
        {
            stack = Registry.ITEM.getEntryList(TagInit.LOOTER_RARE).flatMap((items) ->
                    items.getRandom(random)).map((itemEntry) ->
                    (itemEntry.value()).getDefaultStack()).orElse(stack);
        }
        else
        {
            stack = Registry.ITEM.getEntryList(TagInit.LOOTER_VERY_RARE).flatMap((items) ->
                    items.getRandom(random)).map((itemEntry) ->
                    (itemEntry.value()).getDefaultStack()).orElse(stack);
        }

        return stack;
    }
}

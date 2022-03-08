package com.kwpugh.ward_blocks.init;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TagInit
{
    public static final TagKey<EntityType<?>> ATTACK_INCLUDE = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier("ward_blocks", "attack_include"));
    public static final TagKey<EntityType<?>> ATTACK_EXCLUDE = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier("ward_blocks", "attack_exclude"));

    public static final TagKey<EntityType<?>> EXP_INCLUDE = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier("ward_blocks", "exp_include"));
    public static final TagKey<EntityType<?>> EXP_EXCLUDE = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier("ward_blocks", "exp_exclude"));

    public static final TagKey<EntityType<?>> LOOT_INCLUDE = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier("ward_blocks", "loot_include"));
    public static final TagKey<EntityType<?>> LOOT_EXCLUDE = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier("ward_blocks", "loot_exclude"));

    public static final TagKey<Item> LOOTER_COMMON = TagKey.of(Registry.ITEM_KEY, new Identifier("ward_blocks", "looter_common"));
    public static final TagKey<Item> LOOTER_UNCOMMON = TagKey.of(Registry.ITEM_KEY, new Identifier("ward_blocks", "looter_uncommon"));
    public static final TagKey<Item> LOOTER_RARE = TagKey.of(Registry.ITEM_KEY, new Identifier("ward_blocks", "looter_rare"));
    public static final TagKey<Item> LOOTER_VERY_RARE = TagKey.of(Registry.ITEM_KEY, new Identifier("ward_blocks", "looter_very_rare"));

    public static void registerTags()
    {
        // No Op
    }
}
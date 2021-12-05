package com.kwpugh.ward_blocks.init;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class TagInit
{
    public static final Tag.Identified<EntityType<?>> ATTACK_INCLUDE = TagFactory.ENTITY_TYPE.create(new Identifier("ward_blocks", "attack_include"));
    public static final Tag.Identified<EntityType<?>> ATTACK_EXCLUDE = TagFactory.ENTITY_TYPE.create(new Identifier("ward_blocks", "attack_exclude"));

    public static final Tag.Identified<EntityType<?>> EXP_INCLUDE = TagFactory.ENTITY_TYPE.create(new Identifier("ward_blocks", "exp_include"));
    public static final Tag.Identified<EntityType<?>> EXP_EXCLUDE = TagFactory.ENTITY_TYPE.create(new Identifier("ward_blocks", "exp_exclude"));

    public static final Tag.Identified<EntityType<?>> LOOT_INCLUDE = TagFactory.ENTITY_TYPE.create(new Identifier("ward_blocks", "loot_include"));
    public static final Tag.Identified<EntityType<?>> LOOT_EXCLUDE = TagFactory.ENTITY_TYPE.create(new Identifier("ward_blocks", "loot_exclude"));

    public static final Tag<Item> LOOTER_COMMON = TagFactory.ITEM.create(new Identifier("ward_blocks", "looter_common"));
    public static final Tag<Item> LOOTER_UNCOMMON = TagFactory.ITEM.create(new Identifier("ward_blocks", "looter_uncommon"));
    public static final Tag<Item> LOOTER_RARE = TagFactory.ITEM.create(new Identifier("ward_blocks", "looter_rare"));
    public static final Tag<Item> LOOTER_VERY_RARE = TagFactory.ITEM.create(new Identifier("ward_blocks", "looter_very_rare"));

    public static void registerTags()
    {
        // No Op
    }
}
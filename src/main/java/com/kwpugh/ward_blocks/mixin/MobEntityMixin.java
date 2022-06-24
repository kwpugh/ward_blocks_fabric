package com.kwpugh.ward_blocks.mixin;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.TagInit;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.random.Random;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin
{
    @Inject(method = "canMobSpawn", at = @At("RETURN"), cancellable = true)
    private static void wardBlockCanMobSpawn(EntityType<? extends MobEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> cir)
    {
        RegistryEntry<Biome> biome = world.getBiome(pos);

        // Disable mob spawning in all Overworld biomes
        if(WardBlocks.CONFIG.GENERAL.enableNoMobOverworld  && biome.isIn(BiomeTags.IS_OVERWORLD))
        {
            cir.setReturnValue(false);
        }

        // Use values in tag to disable mob spawning in 1 or more biomes
        if(WardBlocks.CONFIG.GENERAL.enableNoMobBiomes  && biome.isIn(TagInit.NO_SPAWN_BIOMES))
        {
            cir.setReturnValue(false);
        }

        // Use values in tag to disable mob spawning in every other Overworld biome
        if(biome.isIn(BiomeTags.IS_OVERWORLD))
        {
            if(WardBlocks.CONFIG.GENERAL.enableOnlyMobBiomes && !biome.isIn(TagInit.ONLY_SPAWN_BIOMES)) // biome NOT in tag
            {
                cir.setReturnValue(false);
            }
        }

        // Use values in tag to disable mob spawning within a specified distance of 1 or more blocks
        // USe with caution since this causes each mob to search the box for a specified block(s)
        if(WardBlocks.CONFIG.GENERAL.enablenNoSpawnBlock)
        {
            for (BlockPos targetPos : BlockPos.iterateOutwards(pos, WardBlocks.CONFIG.GENERAL.noSpawnBlockHorizRadius, WardBlocks.CONFIG.GENERAL.noSpawnBlockVertRadius, WardBlocks.CONFIG.GENERAL.noSpawnBlockHorizRadius))
            {
                BlockState blockstate = world.getBlockState(targetPos);

                if (blockstate.isIn(TagInit.NO_SPAWN_BLOCkS))
                {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
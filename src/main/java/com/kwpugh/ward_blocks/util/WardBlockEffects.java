package com.kwpugh.ward_blocks.util;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.TagInit;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class WardBlockEffects
{
	static boolean skippedEndermite = WardBlocks.CONFIG.GENERAL.skipNamedEndermite;
	static boolean enableSinkingMobs = WardBlocks.CONFIG.GENERAL.enableSinkingMobs;

	// Removes most mobs and spawns defined loot above block
	public static void giveLoot(World world, BlockPos pos, int vRadius, int hRadius)
	{
		ItemStack drop;

		// Scan for hostile mobs
		Box mobBoxLoot = (new Box(pos)).expand(hRadius, vRadius, hRadius);
		List<Entity> listLoot = world.getNonSpectatingEntities(Entity.class, mobBoxLoot);
		Iterator<Entity> iteratorLoot = listLoot.iterator();

		Entity targetEntity;

		// Cycle through, kill the mob and spawn loot
		while(iteratorLoot.hasNext())
		{
			drop = DropUtil.getDrops();
			targetEntity = iteratorLoot.next();

			EntityType<?> entityType = targetEntity.getType();

			boolean toKill = entityType.isIn(TagInit.LOOT_INCLUDE);
			boolean toSpare = entityType.isIn(TagInit.LOOT_EXCLUDE);

			// Make sure not to work on Dragon, Wither, or excluded
			if(targetEntity instanceof EnderDragonEntity ||
					targetEntity instanceof WitherEntity ||
					toSpare) break;

			// Remove mobs that are in the include list
			if(toKill)
			{
				targetEntity.remove(Entity.RemovalReason.KILLED);
				world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY()+1, pos.getZ(), drop));
			}

			// Core filter
			if(targetEntity instanceof HostileEntity ||
					targetEntity instanceof SlimeEntity ||
					targetEntity instanceof PhantomEntity)
			{
				//Special case for named endermite used for endermen farms
				if(targetEntity instanceof EndermiteEntity
						&& skippedEndermite
						&& targetEntity.getCustomName() != null) break;

				// Remove filtered mobs
				targetEntity.remove(Entity.RemovalReason.KILLED);
				((MobEntity) targetEntity).playSpawnEffects();
				world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY()+1, pos.getZ(), drop));
			}
		}
	}

	// Removes most mobs and spawns XP by above block
	public static void giveExp(World world, BlockPos pos, int vRadius, int hRadius, int orbAmount)
	{
		// Scan for mobs from whitelist/blacklist
		Box mobBoxExp1 = (new Box(pos)).expand(hRadius, hRadius, hRadius);
		List<Entity> listExp1 = world.getNonSpectatingEntities(Entity.class, mobBoxExp1);
		Iterator<Entity> iteratorExp1 = listExp1.iterator();

		Entity targetEntity;

		// Cycle through and effect entities
		while(iteratorExp1.hasNext())
		{
			targetEntity = iteratorExp1.next();
			EntityType<?> entityType = targetEntity.getType();

			boolean toKill = entityType.isIn(TagInit.EXP_INCLUDE);
			boolean toSpare = entityType.isIn(TagInit.EXP_EXCLUDE);

			// Have some fun with incoming projectiles
			if(targetEntity instanceof ProjectileEntity)
			{
				ProjectileEntity projectile = (ProjectileEntity) targetEntity;
				world.spawnEntity(new ItemEntity(world, projectile.getX(), projectile.getY(), projectile.getZ(), Items.FEATHER.getDefaultStack()));
				projectile.remove(Entity.RemovalReason.DISCARDED);
			}

			// Make sure not to work on Dragon or Wither
			if(targetEntity instanceof EnderDragonEntity ||
					targetEntity instanceof WitherEntity ||
					toSpare) break;

			if(toKill)
			{
				targetEntity.remove(Entity.RemovalReason.KILLED);
				world.spawnEntity(new ExperienceOrbEntity(world, pos.getX(), pos.getY()+1, pos.getZ(), orbAmount));
			}

			if(targetEntity instanceof HostileEntity ||
					targetEntity instanceof SlimeEntity ||
					targetEntity instanceof PhantomEntity)
			{
				//Special case for named endermite used for endermen farms
				if( targetEntity instanceof EndermiteEntity  && skippedEndermite && targetEntity.getCustomName() != null ) break;

				targetEntity.remove(Entity.RemovalReason.KILLED);
				((MobEntity) targetEntity).playSpawnEffects();
				world.spawnEntity(new ExperienceOrbEntity(world, pos.getX(), pos.getY()+1, pos.getZ(), orbAmount));
			}
		}
	}


	// Cause mobs to target themselves, disable AI on creepers and skeleton
	public static void attackMobs(World world, BlockPos pos, int hRadius, int vRadius, float damageAmount)
	{
		// Scan for mobs from whitelist/blacklist
		Box mobBoxAttack = (new Box(pos)).expand(hRadius, hRadius, hRadius);
		List<Entity> listAttack = world.getNonSpectatingEntities(Entity.class, mobBoxAttack);
		Iterator<Entity> iteratorAttack = listAttack.iterator();

		Entity targetEntity;

		// Cycle through and effect entities
		while(iteratorAttack.hasNext())
		{
			targetEntity = iteratorAttack.next();
			EntityType<?> entityType = targetEntity.getType();

			boolean toKill = entityType.isIn(TagInit.ATTACK_INCLUDE);
			boolean toSpare = entityType.isIn(TagInit.ATTACK_EXCLUDE);

			// Have some fun with incoming projectiles
			if(targetEntity instanceof ProjectileEntity)
			{
				ProjectileEntity projectile = (ProjectileEntity) targetEntity;
				world.spawnEntity(new ItemEntity(world, projectile.getX(), projectile.getY(), projectile.getZ(), Items.FEATHER.getDefaultStack()));
				projectile.remove(Entity.RemovalReason.DISCARDED);
			}

			// Make sure not to work on Dragon or Wither
			if(targetEntity instanceof EnderDragonEntity ||
					targetEntity instanceof WitherEntity ||
					toSpare) break;

			if(toKill)
			{
				targetEntity.damage(DamageSource.GENERIC, damageAmount);
			}

			if(targetEntity instanceof HostileEntity)
			{
				targetEntity.damage(DamageSource.GENERIC, damageAmount);
				((HostileEntity) targetEntity).setTarget((LivingEntity) targetEntity);
				if(targetEntity instanceof CreeperEntity || targetEntity instanceof SkeletonEntity)
				{
					((HostileEntity) targetEntity).setAiDisabled(true);
				}
			}
		}
	}

	//  Accelerates growth in area of effect
	public static void growCrops(World world, BlockPos pos, int baseTickDelay, int cactusTickDelay, int radius, int height)
	{
		// For blocks that use a grow() method and implement Fertilizable
		for (BlockPos target : BlockPos.iterateOutwards(pos, radius, height, radius))
		{
			BlockState blockstate = world.getBlockState(target);
			Block block = blockstate.getBlock();

			if (world.getTime() % (baseTickDelay) == 0)
			{
				if(   (block instanceof CropBlock) ||  //Beets Carrots Potatoes
						block instanceof BambooSaplingBlock ||
						block instanceof BambooBlock ||
						block instanceof CocoaBlock ||
						block instanceof StemBlock ||
						block instanceof SweetBerryBushBlock ||
						block instanceof FungusBlock ||
						block instanceof SaplingBlock  || //all sapling
						block instanceof KelpBlock ||
						block instanceof KelpPlantBlock ||
						block instanceof AzaleaBlock ||
						block instanceof SmallDripleafBlock ||
						block instanceof BigDripleafStemBlock)
				{
					Fertilizable fertilizable = (Fertilizable)blockstate.getBlock();
					if (fertilizable.isFertilizable(world, target, blockstate, world.isClient))
					{
						if (fertilizable.canGrow(world, world.random, target, blockstate))
						{
							fertilizable.grow((ServerWorld)world, world.random, target, blockstate);
						}
					}
				}
			}
		}

		// For those blocks that lack grow() we fall back to randomTick()
		if (world.getTime() % (cactusTickDelay) == 0)
		{
			for (BlockPos tickTarget : BlockPos.iterateOutwards(pos, radius, height, radius))
			{
				BlockState blockstate2 = world.getBlockState(tickTarget);
				Block blockToTick = blockstate2.getBlock();

				if(blockToTick instanceof SugarCaneBlock ||
						blockToTick instanceof CactusBlock ||
						blockToTick instanceof BuddingAmethystBlock ||
						blockToTick instanceof NetherWartBlock ||
						blockToTick instanceof ChorusFlowerBlock)
				{
					blockToTick.randomTick(blockstate2, (ServerWorld) world, tickTarget, world.random);
				}
			}
		}

		// Handling Dripstone
		if (world.getTime() % (baseTickDelay) == 0)
		{
			for (BlockPos tickTarget : BlockPos.iterateOutwards(pos, radius, height, radius))
			{
				BlockState blockstate2 = world.getBlockState(tickTarget);
				Block blockToTick = blockstate2.getBlock();

				if(blockToTick instanceof PointedDripstoneBlock)
				{
					blockToTick.randomTick(blockstate2, (ServerWorld) world, tickTarget, world.random);
					PointedDripstoneBlock.dripTick(blockstate2, (ServerWorld) world, tickTarget,.9f);
					PointedDripstoneBlock.tryGrow(blockstate2, (ServerWorld) world, tickTarget, world.random);
				}
			}
		}
	}

	// Gives player strength and resistance
	public static void giveDefense(World world, BlockPos pos, int hRadius, int vRadius, int effectLevel, int effectTickInterval)
	{
		// Scan for players in range
		Box playerBox = (new Box(pos)).expand(hRadius, vRadius, hRadius);
		List<ServerPlayerEntity> listDef = world.getNonSpectatingEntities(ServerPlayerEntity.class, playerBox);
		Iterator<ServerPlayerEntity> iteratorDef = listDef.iterator();

		ServerPlayerEntity targetPlayer;

		// Cycle through list and give effects to players
		while(iteratorDef.hasNext())
		{
			targetPlayer = iteratorDef.next();

			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.RESISTANCE, effectTickInterval, effectLevel, false, false);
			StatusEffectInstance effect2 = new StatusEffectInstance(StatusEffects.STRENGTH, effectTickInterval, effectLevel, false, false);

			targetPlayer.addStatusEffect(effect);
			targetPlayer.addStatusEffect(effect2);
		}
	}

	// Gives player heal, regeneration, and yellow hearts
	public static void giveHealth(World world, BlockPos pos, int hRadius, int vRadius, int effectLevel, int effectTickInterval, boolean enableExtraHearts, float yellowHearts)
	{
		// Scan for players in range
		Box playerBox = (new Box(pos)).expand(hRadius, vRadius, hRadius);

		List<ServerPlayerEntity> listHealth = world.getNonSpectatingEntities(ServerPlayerEntity.class, playerBox);
		Iterator<ServerPlayerEntity> iteratorHealth = listHealth.iterator();

		ServerPlayerEntity targetPlayer;

		// Cycle through list and give effects to players
		while(iteratorHealth.hasNext())
		{
			targetPlayer = iteratorHealth.next();

			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.REGENERATION, effectTickInterval, effectLevel, false, false);
			StatusEffectInstance effect2 = new StatusEffectInstance(StatusEffects.SATURATION, effectTickInterval, effectLevel, false, false);

			targetPlayer.addStatusEffect(effect);
			targetPlayer.addStatusEffect(effect2);

			if(enableExtraHearts)
			{
				giveGreaterExtraHearts(world, targetPlayer, yellowHearts);
			}
		}

		// if enabled in config, cycle through list of mobs and "sink" them
		if(enableSinkingMobs)
		{
			MobEntity targetMob;
			List<MobEntity> listHealth2 = world.getNonSpectatingEntities(MobEntity.class, playerBox);
			Iterator<MobEntity> iteratorHealth2 = listHealth2.iterator();

			while(iteratorHealth2.hasNext())
			{
				targetMob = iteratorHealth2.next();

				if(targetMob instanceof HostileEntity)
				{
					targetMob.setPos(targetMob.getX(), targetMob.getY()-0.1D, targetMob.getZ());
					break;
				}
			}
		}
	}
	
	//Increase of yellow hearts on tick update
	public static void giveGreaterExtraHearts(World world, LivingEntity player, float yellowHearts)
	{
		if(!world.isClient)
		{
			float current = player.getAbsorptionAmount();
		
			if(player.getHealth() < yellowHearts || current >= yellowHearts)
			{
				return;
			}
		
			if(current >= (yellowHearts - 1.0F))
			{
				if (player.age % 20 == 0)
				{
					player.setAbsorptionAmount(yellowHearts);
				}
			}
			if(current < (yellowHearts - 1.0F))
			{
				if (player.age % 20 == 0)
				{
					player.setAbsorptionAmount(current + 1.0F);
				}
			}
		}	
	}
}

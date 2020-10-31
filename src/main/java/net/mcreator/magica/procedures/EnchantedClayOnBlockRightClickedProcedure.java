package net.mcreator.magica.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Explosion;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.magica.entity.EnchantedGolemEntity;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class EnchantedClayOnBlockRightClickedProcedure extends MagicaModElements.ModElement {
	public EnchantedClayOnBlockRightClickedProcedure(MagicaModElements instance) {
		super(instance, 336);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure EnchantedClayOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure EnchantedClayOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure EnchantedClayOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure EnchantedClayOnBlockRightClicked!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((Blocks.CLAY.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x - 0), (int) (y - 0), (int) (z - 1))))
				.getBlock())
				&& (Blocks.CLAY.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y - 0), (int) (z + 1))))
						.getBlock()))
				|| ((Blocks.CLAY.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 0), (int) (z - 0))))
						.getBlock())
						&& (Blocks.CLAY.getDefaultState()
								.getBlock() == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 0), (int) (z - 0)))).getBlock())))
				&& ((Blocks.CLAY.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x - 0), (int) (y - 1), (int) (z - 0))))
						.getBlock())
						&& (Blocks.CLAY.getDefaultState()
								.getBlock() == (world.getBlockState(new BlockPos((int) (x - 0), (int) (y + 1), (int) (z - 0)))).getBlock())))) {
			if (world instanceof World && !world.getWorld().isRemote) {
				world.getWorld().createExplosion(null, (int) x, (int) y, (int) z, (float) 4, Explosion.Mode.BREAK);
			}
			if (world instanceof World && !world.getWorld().isRemote) {
				Entity entityToSpawn = new EnchantedGolemEntity.CustomEntity(EnchantedGolemEntity.entity, world.getWorld());
				entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
		}
	}
}

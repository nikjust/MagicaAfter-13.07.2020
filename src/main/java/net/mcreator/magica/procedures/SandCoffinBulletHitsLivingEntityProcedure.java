package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.Collections;

@MagicaModElements.ModElement.Tag
public class SandCoffinBulletHitsLivingEntityProcedure extends MagicaModElements.ModElement {
	public SandCoffinBulletHitsLivingEntityProcedure(MagicaModElements instance) {
		super(instance, 419);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure SandCoffinBulletHitsLivingEntity!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure SandCoffinBulletHitsLivingEntity!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure SandCoffinBulletHitsLivingEntity!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure SandCoffinBulletHitsLivingEntity!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure SandCoffinBulletHitsLivingEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		{
			Entity _ent = entity;
			_ent.setPositionAndUpdate(x, y, z);
			if (_ent instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) _ent).connection.setPlayerLocation(x, y, z, _ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
			}
		}
		world.setBlockState(new BlockPos((int) (x + 1), (int) (y + 0), (int) (z + 0)), Blocks.SANDSTONE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x + 0), (int) (y + 0), (int) (z + 1)), Blocks.SANDSTONE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x + 0), (int) (y + 0), (int) (z - 1)), Blocks.SANDSTONE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x - 1), (int) (y + 0), (int) (z - 0)), Blocks.SANDSTONE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) (z + 0)), Blocks.SANDSTONE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z + 1)), Blocks.SANDSTONE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z - 1)), Blocks.SANDSTONE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) (z - 0)), Blocks.SANDSTONE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x - 0), (int) (y + 2), (int) (z - 0)), Blocks.SANDSTONE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x - 0), (int) (y + 1), (int) (z - 0)), Blocks.SAND.getDefaultState(), 3);
	}
}

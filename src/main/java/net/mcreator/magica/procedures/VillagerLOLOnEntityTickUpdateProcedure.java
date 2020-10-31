package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class VillagerLOLOnEntityTickUpdateProcedure extends MagicaModElements.ModElement {
	public VillagerLOLOnEntityTickUpdateProcedure(MagicaModElements instance) {
		super(instance, 251);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure VillagerLOLOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure VillagerLOLOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure VillagerLOLOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure VillagerLOLOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure VillagerLOLOnEntityTickUpdate!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(world.getWorld().isDaytime()))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.WHITE_BED.getDefaultState(), 3);
			entity.getPersistentData().putDouble("SleepingX", (entity.posX));
			entity.getPersistentData().putDouble("SleepingY", (entity.posY));
			entity.getPersistentData().putDouble("SleepingZ", (entity.posZ));
			entity.getPersistentData().putDouble("Sleeping", 1);
		} else {
			entity.getPersistentData().putDouble("Sleeping", 0);
		}
	}
}

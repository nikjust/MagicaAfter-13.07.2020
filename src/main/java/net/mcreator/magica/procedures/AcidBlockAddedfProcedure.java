package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.magica.block.AcidBlock;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class AcidBlockAddedfProcedure extends MagicaModElements.ModElement {
	public AcidBlockAddedfProcedure(MagicaModElements instance) {
		super(instance, 425);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure AcidBlockAddedf!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure AcidBlockAddedf!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure AcidBlockAddedf!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure AcidBlockAddedf!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), Blocks.AIR.getDefaultState(), 3);
		if ((Math.random() < 0.3)) {
			world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), AcidBlock.block.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) (z - 0)), AcidBlock.block.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z - 0)), AcidBlock.block.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) (x + 0), (int) (y - 1), (int) (z - 1)), AcidBlock.block.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) (x + 0), (int) (y - 1), (int) (z + 1)), AcidBlock.block.getDefaultState(), 3);
		} else {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
	}
}

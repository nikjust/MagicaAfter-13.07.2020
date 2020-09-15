package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.magica.block.CorruptionBlock;
import net.mcreator.magica.block.AntiCorruptWaveBlock;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class AcWaveTickProcedure extends MagicaModElements.ModElement {
	public AcWaveTickProcedure(MagicaModElements instance) {
		super(instance, 257);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure AcWaveTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure AcWaveTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure AcWaveTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure AcWaveTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((CorruptionBlock.block.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z + 0))))
				.getBlock())) {
			world.setBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z + 0)), AntiCorruptWaveBlock.block.getDefaultState(), 3);
		}
		if ((CorruptionBlock.block.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y - 1), (int) (z + 0))))
				.getBlock())) {
			world.setBlockState(new BlockPos((int) (x + 0), (int) (y - 1), (int) (z + 0)), AntiCorruptWaveBlock.block.getDefaultState(), 3);
		}
		if ((CorruptionBlock.block.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 0), (int) (z + 0))))
				.getBlock())) {
			world.setBlockState(new BlockPos((int) (x + 1), (int) (y + 0), (int) (z + 0)), AntiCorruptWaveBlock.block.getDefaultState(), 3);
		}
		if ((CorruptionBlock.block.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 0), (int) (z + 0))))
				.getBlock())) {
			world.setBlockState(new BlockPos((int) (x - 1), (int) (y + 0), (int) (z + 0)), AntiCorruptWaveBlock.block.getDefaultState(), 3);
		}
		if ((CorruptionBlock.block.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x - 0), (int) (y - 0), (int) (z + 1))))
				.getBlock())) {
			world.setBlockState(new BlockPos((int) (x - 0), (int) (y + 0), (int) (z + 1)), AntiCorruptWaveBlock.block.getDefaultState(), 3);
		}
		if ((CorruptionBlock.block.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x - 0), (int) (y - 0), (int) (z - 1))))
				.getBlock())) {
			world.setBlockState(new BlockPos((int) (x - 0), (int) (y + 0), (int) (z - 1)), AntiCorruptWaveBlock.block.getDefaultState(), 3);
		}
		world.setBlockState(new BlockPos((int) (x - 0), (int) (y + 0), (int) (z - 0)), Blocks.DIRT.getDefaultState(), 3);
	}
}

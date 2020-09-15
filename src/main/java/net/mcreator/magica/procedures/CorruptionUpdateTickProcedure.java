package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.magica.block.CorruptionBlock;
import net.mcreator.magica.block.AntiCorruptWaveBlock;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class CorruptionUpdateTickProcedure extends MagicaModElements.ModElement {
	public CorruptionUpdateTickProcedure(MagicaModElements instance) {
		super(instance, 256);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure CorruptionUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure CorruptionUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure CorruptionUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure CorruptionUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(((AntiCorruptWaveBlock.block.getDefaultState()
				.getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z + 0)))).getBlock())
				|| (AntiCorruptWaveBlock.block.getDefaultState()
						.getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y - 1), (int) (z + 0)))).getBlock()))
				|| (((AntiCorruptWaveBlock.block.getDefaultState()
						.getBlock() == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 0), (int) (z + 0)))).getBlock())
						|| (AntiCorruptWaveBlock.block.getDefaultState()
								.getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z + 1)))).getBlock()))
						|| ((AntiCorruptWaveBlock.block.getDefaultState()
								.getBlock() == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) (z + 0)))).getBlock())
								|| (AntiCorruptWaveBlock.block.getDefaultState()
										.getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z - 1))))
												.getBlock())))))) {
			if ((!(Blocks.AIR.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z + 0))))
					.getBlock()))) {
				world.setBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z + 0)), CorruptionBlock.block.getDefaultState(), 3);
			}
			if ((!(Blocks.AIR.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y - 1), (int) (z + 0))))
					.getBlock()))) {
				world.setBlockState(new BlockPos((int) (x + 0), (int) (y - 1), (int) (z + 0)), CorruptionBlock.block.getDefaultState(), 3);
			}
			if ((!(Blocks.AIR.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 0), (int) (z + 0))))
					.getBlock()))) {
				world.setBlockState(new BlockPos((int) (x + 1), (int) (y + 0), (int) (z + 0)), CorruptionBlock.block.getDefaultState(), 3);
			}
			if ((!(Blocks.AIR.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 0), (int) (z + 0))))
					.getBlock()))) {
				world.setBlockState(new BlockPos((int) (x - 1), (int) (y + 0), (int) (z + 0)), CorruptionBlock.block.getDefaultState(), 3);
			}
			if ((!(Blocks.AIR.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x - 0), (int) (y - 0), (int) (z + 1))))
					.getBlock()))) {
				world.setBlockState(new BlockPos((int) (x - 0), (int) (y + 0), (int) (z + 1)), CorruptionBlock.block.getDefaultState(), 3);
			}
			if ((!(Blocks.AIR.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x - 0), (int) (y - 0), (int) (z - 1))))
					.getBlock()))) {
				world.setBlockState(new BlockPos((int) (x - 0), (int) (y + 0), (int) (z - 1)), CorruptionBlock.block.getDefaultState(), 3);
			}
		}
	}
}

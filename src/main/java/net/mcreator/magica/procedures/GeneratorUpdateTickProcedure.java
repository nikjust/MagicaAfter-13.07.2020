package net.mcreator.magica.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.magica.block.GeneratorBlock;
import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class GeneratorUpdateTickProcedure extends MagicaModElements.ModElement {
	public GeneratorUpdateTickProcedure(MagicaModElements instance) {
		super(instance, 160);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure GeneratorUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure GeneratorUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure GeneratorUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure GeneratorUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((((((Blocks.MAGMA_BLOCK.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) (z - 1))))
				.getBlock())
				&& (Blocks.EMERALD_BLOCK.getDefaultState()
						.getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z - 1)))).getBlock()))
				&& ((Blocks.EMERALD_BLOCK.getDefaultState()
						.getBlock() == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) (z + 0)))).getBlock())
						&& (Blocks.MAGMA_BLOCK.getDefaultState()
								.getBlock() == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) (z - 1)))).getBlock())))
				&& (((Blocks.EMERALD_BLOCK.getDefaultState()
						.getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z + 1)))).getBlock())
						&& (Blocks.MAGMA_BLOCK.getDefaultState()
								.getBlock() == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) (z - 1)))).getBlock()))
						&& ((Blocks.MAGMA_BLOCK.getDefaultState()
								.getBlock() == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) (z + 1)))).getBlock())
								&& ((Blocks.EMERALD_BLOCK.getDefaultState()
										.getBlock() == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) (z + 0)))).getBlock())
										&& (Blocks.AIR.getDefaultState()
												.getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z + 0))))
														.getBlock())))))
				&& (((((Blocks.LAPIS_BLOCK.getDefaultState()
						.getBlock() == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 0), (int) (z - 1)))).getBlock())
						&& (Blocks.LAPIS_BLOCK.getDefaultState()
								.getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 0), (int) (z - 1)))).getBlock()))
						&& ((Blocks.LAPIS_BLOCK.getDefaultState()
								.getBlock() == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 0), (int) (z + 0)))).getBlock())
								&& (Blocks.LAPIS_BLOCK.getDefaultState()
										.getBlock() == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 0), (int) (z - 1)))).getBlock())))
						&& (((Blocks.LAPIS_BLOCK.getDefaultState()
								.getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 0), (int) (z + 1)))).getBlock())
								&& (Blocks.LAPIS_BLOCK.getDefaultState()
										.getBlock() == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 0), (int) (z - 1)))).getBlock()))
								&& ((Blocks.LAPIS_BLOCK.getDefaultState()
										.getBlock() == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 0), (int) (z + 1)))).getBlock())
										&& ((Blocks.LAPIS_BLOCK.getDefaultState()
												.getBlock() == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 0), (int) (z + 0))))
														.getBlock())
												&& (GeneratorBlock.block.getDefaultState()
														.getBlock() == (world
																.getBlockState(new BlockPos((int) (x + 0), (int) (y + 0), (int) (z + 0))))
																		.getBlock())))))
						&& ((((Blocks.DIAMOND_BLOCK.getDefaultState()
								.getBlock() == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) (z - 1)))).getBlock())
								&& (Blocks.GLASS.getDefaultState()
										.getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z - 1)))).getBlock()))
								&& ((Blocks.GLASS.getDefaultState()
										.getBlock() == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) (z + 0)))).getBlock())
										&& (Blocks.DIAMOND_BLOCK.getDefaultState()
												.getBlock() == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) (z - 1))))
														.getBlock())))
								&& (((Blocks.GLASS.getDefaultState()
										.getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z + 1)))).getBlock())
										&& (Blocks.DIAMOND_BLOCK.getDefaultState()
												.getBlock() == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) (z - 1))))
														.getBlock()))
										&& ((Blocks.DIAMOND_BLOCK.getDefaultState()
												.getBlock() == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) (z + 1))))
														.getBlock())
												&& ((Blocks.GLASS.getDefaultState()
														.getBlock() == (world
																.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) (z + 0)))).getBlock())
														&& (Blocks.EMERALD_BLOCK.getDefaultState()
																.getBlock() == (world
																		.getBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z + 0))))
																				.getBlock())))))))) {
			if ((12000 == (new Object() {
				public double getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "Time")))) {
				world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), Blocks.ZOMBIE_HEAD.getDefaultState(), 3);
			} else {
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("Time", (1 + (new Object() {
							public double getValue(BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(new BlockPos((int) x, (int) y, (int) z), "Time"))));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				MagicaModVariables.MapVariables.get(world).GlobalMagic = (double) (1 + (MagicaModVariables.MapVariables.get(world).GlobalMagic));
				MagicaModVariables.MapVariables.get(world).syncData(world);
			}
		}
	}
}

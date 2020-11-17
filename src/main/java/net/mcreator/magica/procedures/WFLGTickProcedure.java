package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import net.mcreator.magica.block.DeadWildForestLightVineBlock;
import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class WFLGTickProcedure extends MagicaModElements.ModElement {
	public WFLGTickProcedure(MagicaModElements instance) {
		super(instance, 334);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure WFLGTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure WFLGTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure WFLGTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure WFLGTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((MagicaModVariables.MapVariables.get(world).WildForestDead)) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), DeadWildForestLightVineBlock.block.getDefaultState(), 3);
		}
	}
}

package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class ReturnerBlockBlockAddedProcedure extends MagicaModElements.ModElement {
	public ReturnerBlockBlockAddedProcedure(MagicaModElements instance) {
		super(instance, 327);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure ReturnerBlockBlockAdded!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure ReturnerBlockBlockAdded!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure ReturnerBlockBlockAdded!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure ReturnerBlockBlockAdded!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		MagicaModVariables.WorldVariables.get(world).PortallerLocX = (double) x;
		MagicaModVariables.WorldVariables.get(world).syncData(world);
		MagicaModVariables.WorldVariables.get(world).PortallerLocY = (double) (y + 1);
		MagicaModVariables.WorldVariables.get(world).syncData(world);
		MagicaModVariables.WorldVariables.get(world).PortallerLocZ = (double) z;
		MagicaModVariables.WorldVariables.get(world).syncData(world);
	}
}

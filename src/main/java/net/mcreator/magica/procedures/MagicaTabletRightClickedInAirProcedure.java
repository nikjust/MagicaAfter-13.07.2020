package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class MagicaTabletRightClickedInAirProcedure extends MagicaModElements.ModElement {
	public MagicaTabletRightClickedInAirProcedure(MagicaModElements instance) {
		super(instance, 8);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MagicaTabletRightClickedInAir!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (((false) == (MagicaModVariables.MapVariables.get(world).Overlay))) {
			MagicaModVariables.MapVariables.get(world).Overlay = (boolean) (true);
			MagicaModVariables.MapVariables.get(world).syncData(world);
		} else {
			MagicaModVariables.MapVariables.get(world).Overlay = (boolean) (false);
			MagicaModVariables.MapVariables.get(world).syncData(world);
		}
	}
}

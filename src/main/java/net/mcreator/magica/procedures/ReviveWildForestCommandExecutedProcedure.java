package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class ReviveWildForestCommandExecutedProcedure extends MagicaModElements.ModElement {
	public ReviveWildForestCommandExecutedProcedure(MagicaModElements instance) {
		super(instance, 250);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure ReviveWildForestCommandExecuted!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		MagicaModVariables.MapVariables.get(world).WildForestDead = (boolean) (false);
		MagicaModVariables.MapVariables.get(world).syncData(world);
	}
}

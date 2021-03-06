package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class MagicaTabletOnPlayerStoppedUsingProcedure extends MagicaModElements.ModElement {
	public MagicaTabletOnPlayerStoppedUsingProcedure(MagicaModElements instance) {
		super(instance, 167);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure MagicaTabletOnPlayerStoppedUsing!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		MagicaModVariables.MapVariables.get(world).Overlay = (boolean) (false);
		MagicaModVariables.MapVariables.get(world).syncData(world);
	}
}

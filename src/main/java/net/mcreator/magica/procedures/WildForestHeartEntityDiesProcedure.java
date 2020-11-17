package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class WildForestHeartEntityDiesProcedure extends MagicaModElements.ModElement {
	public WildForestHeartEntityDiesProcedure(MagicaModElements instance) {
		super(instance, 336);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure WildForestHeartEntityDies!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		MagicaModVariables.MapVariables.get(world).WildForestDead = (boolean) (true);
		MagicaModVariables.MapVariables.get(world).syncData(world);
	}
}

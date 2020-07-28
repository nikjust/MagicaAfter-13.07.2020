package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class MagicaTabletItemInHandTickProcedure extends MagicaModElements.ModElement {
	public MagicaTabletItemInHandTickProcedure(MagicaModElements instance) {
		super(instance, 6);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MagicaTabletItemInHandTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		MagicaModVariables.MapVariables.get(world).Overlay = (boolean) (true);
		MagicaModVariables.MapVariables.get(world).syncData(world);
	}
}

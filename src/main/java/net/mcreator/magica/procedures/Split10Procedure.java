package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class Split10Procedure extends MagicaModElements.ModElement {
	public Split10Procedure(MagicaModElements instance) {
		super(instance, 246);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure Split10!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure Split10!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		MagicaModVariables.MapVariables.get(world).GlobalMagic = (double) ((MagicaModVariables.MapVariables.get(world).GlobalMagic) - 10);
		MagicaModVariables.MapVariables.get(world).syncData(world);
		entity.getPersistentData().putDouble("RedMagica", (10 + (entity.getPersistentData().getDouble("RedMagica"))));
		entity.getPersistentData().putDouble("BlueMagica", (10 + (entity.getPersistentData().getDouble("BlueMagica"))));
	}
}

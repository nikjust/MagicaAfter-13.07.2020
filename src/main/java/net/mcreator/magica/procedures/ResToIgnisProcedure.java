package net.mcreator.magica.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class ResToIgnisProcedure extends MagicaModElements.ModElement {
	public ResToIgnisProcedure(MagicaModElements instance) {
		super(instance, 360);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ResToIgnis!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((0 < (entity.getPersistentData().getDouble("res")))) {
			entity.getPersistentData().putDouble("res", ((entity.getPersistentData().getDouble("res")) - 1));
			entity.getPersistentData().putDouble("ignis", ((entity.getPersistentData().getDouble("ignis")) + 10));
		}
	}
}

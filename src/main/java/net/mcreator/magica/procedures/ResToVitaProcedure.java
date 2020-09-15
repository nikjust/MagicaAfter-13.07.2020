package net.mcreator.magica.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class ResToVitaProcedure extends MagicaModElements.ModElement {
	public ResToVitaProcedure(MagicaModElements instance) {
		super(instance, 276);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ResToVita!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((0 < (entity.getPersistentData().getDouble("res")))) {
			entity.getPersistentData().putDouble("res", ((entity.getPersistentData().getDouble("res")) - 1));
			entity.getPersistentData().putDouble("vita", ((entity.getPersistentData().getDouble("vita")) + 10));
		}
	}
}

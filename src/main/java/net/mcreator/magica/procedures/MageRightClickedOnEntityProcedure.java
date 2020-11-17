package net.mcreator.magica.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class MageRightClickedOnEntityProcedure extends MagicaModElements.ModElement {
	public MageRightClickedOnEntityProcedure(MagicaModElements instance) {
		super(instance, 430);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MageRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure MageRightClickedOnEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		sourceentity.startRiding(entity);
		entity.setMotion(((new java.util.Random()).nextInt((int) 5 + 1)), 2, ((new java.util.Random()).nextInt((int) 5 + 1)));
	}
}

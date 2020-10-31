package net.mcreator.magica.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class ExoSkeletonLeggingsTickEventProcedure extends MagicaModElements.ModElement {
	public ExoSkeletonLeggingsTickEventProcedure(MagicaModElements instance) {
		super(instance, 266);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ExoSkeletonLeggingsTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setMotion(((entity.getMotion().getX()) * 2), 0, ((entity.getMotion().getZ()) * 2));
	}
}

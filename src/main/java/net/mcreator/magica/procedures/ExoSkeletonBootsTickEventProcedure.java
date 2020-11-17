package net.mcreator.magica.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class ExoSkeletonBootsTickEventProcedure extends MagicaModElements.ModElement {
	public ExoSkeletonBootsTickEventProcedure(MagicaModElements instance) {
		super(instance, 350);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ExoSkeletonBootsTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((0 < (entity.getMotion().getY()))) {
			entity.setMotion(0, (2 * (entity.getMotion().getY())), 0);
		} else if ((0 > (entity.getMotion().getY()))) {
			entity.setMotion(0, ((entity.getMotion().getY()) / 2), 0);
		}
	}
}

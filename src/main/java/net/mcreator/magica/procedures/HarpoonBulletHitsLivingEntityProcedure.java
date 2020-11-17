package net.mcreator.magica.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class HarpoonBulletHitsLivingEntityProcedure extends MagicaModElements.ModElement {
	public HarpoonBulletHitsLivingEntityProcedure(MagicaModElements instance) {
		super(instance, 424);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure HarpoonBulletHitsLivingEntity!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure HarpoonBulletHitsLivingEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		sourceentity.startRiding(entity);
	}
}

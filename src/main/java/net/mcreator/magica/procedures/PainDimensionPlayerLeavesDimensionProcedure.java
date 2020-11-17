package net.mcreator.magica.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class PainDimensionPlayerLeavesDimensionProcedure extends MagicaModElements.ModElement {
	public PainDimensionPlayerLeavesDimensionProcedure(MagicaModElements instance) {
		super(instance, 413);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure PainDimensionPlayerLeavesDimension!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).clearActivePotions();
	}
}

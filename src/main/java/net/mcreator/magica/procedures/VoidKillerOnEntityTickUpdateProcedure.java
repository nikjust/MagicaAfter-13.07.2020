package net.mcreator.magica.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class VoidKillerOnEntityTickUpdateProcedure extends MagicaModElements.ModElement {
	public VoidKillerOnEntityTickUpdateProcedure(MagicaModElements instance) {
		super(instance, 316);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure VoidKillerOnEntityTickUpdate!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) <= 500)
				&& (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) > 300))) {
			entity.getPersistentData().putDouble("Head", 1);
		} else if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) <= 300)
				&& (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) > 100))) {
			entity.getPersistentData().putDouble("Head", 2);
		} else if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) < 100)) {
			entity.getPersistentData().putDouble("Head", 4);
		} else {
			entity.getPersistentData().putDouble("Head", 0);
		}
	}
}

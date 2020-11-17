package net.mcreator.magica.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class HyperthermiaOnPotionActiveTickProcedure extends MagicaModElements.ModElement {
	public HyperthermiaOnPotionActiveTickProcedure(MagicaModElements instance) {
		super(instance, 275);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure HyperthermiaOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.IN_FIRE, (float) 0.5);
	}
}

package net.mcreator.magica.procedures;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class MNotMoveProcedure extends MagicaModElements.ModElement {
	public MNotMoveProcedure(MagicaModElements instance) {
		super(instance, 455);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MNotMove!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return ((entity instanceof TameableEntity) ? ((TameableEntity) entity).isTamed() : false);
	}
}

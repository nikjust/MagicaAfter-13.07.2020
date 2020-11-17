package net.mcreator.magica.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class CrystaliumItemInHandTickProcedure extends MagicaModElements.ModElement {
	public CrystaliumItemInHandTickProcedure(MagicaModElements instance) {
		super(instance, 377);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure CrystaliumItemInHandTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.GENERIC, (float) 0.1);
	}
}

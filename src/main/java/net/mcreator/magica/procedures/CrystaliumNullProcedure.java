package net.mcreator.magica.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class CrystaliumNullProcedure extends MagicaModElements.ModElement {
	public CrystaliumNullProcedure(MagicaModElements instance) {
		super(instance, 374);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure CrystaliumNull!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((1 == (entity.getPersistentData().getDouble("CrystaliumWake")))) {
			return (true);
		}
		return (false);
	}
}

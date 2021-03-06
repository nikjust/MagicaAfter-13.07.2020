package net.mcreator.magica.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class TestEvokerOnInitialEntitySpawnProcedure extends MagicaModElements.ModElement {
	public TestEvokerOnInitialEntitySpawnProcedure(MagicaModElements instance) {
		super(instance, 331);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure TestEvokerOnInitialEntitySpawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putString("ArmPose", "AbstractIllagerEntity.ArmPose.SPELLCASTING");
	}
}

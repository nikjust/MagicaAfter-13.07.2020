package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class SplitProcedure extends MagicaModElements.ModElement {
	public SplitProcedure(MagicaModElements instance) {
		super(instance, 124);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure Split!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure Split!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		MagicaModVariables.MapVariables.get(world).GlobalMagic = (double) ((MagicaModVariables.MapVariables.get(world).GlobalMagic) - 1);
		MagicaModVariables.MapVariables.get(world).syncData(world);
		entity.getPersistentData().putDouble("RedMagica", (1 + (entity.getPersistentData().getDouble("RedMagica"))));
		entity.getPersistentData().putDouble("BlueMagica", (1 + (entity.getPersistentData().getDouble("BlueMagica"))));
	}
}

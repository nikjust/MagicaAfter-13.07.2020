package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class MagicalAtrophyOnPotionActiveTickProcedure extends MagicaModElements.ModElement {
	public MagicalAtrophyOnPotionActiveTickProcedure(MagicaModElements instance) {
		super(instance, 217);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MagicalAtrophyOnPotionActiveTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MagicalAtrophyOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity.getPersistentData().getDouble("AtrophyTick")) == 20)) {
			MagicaModVariables.MapVariables.get(world).GlobalMagic = (double) ((MagicaModVariables.MapVariables.get(world).GlobalMagic) - 1);
			MagicaModVariables.MapVariables.get(world).syncData(world);
			entity.getPersistentData().putDouble("AtrophyTick", 0);
		} else {
			entity.getPersistentData().putDouble("AtrophyTick", ((entity.getPersistentData().getDouble("AtrophyTick")) + 1));
		}
	}
}

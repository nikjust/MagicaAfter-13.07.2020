package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.Collections;

@MagicaModElements.ModElement.Tag
public class HyperPortallerRightClickedInAirProcedure extends MagicaModElements.ModElement {
	public HyperPortallerRightClickedInAirProcedure(MagicaModElements instance) {
		super(instance, 234);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure HyperPortallerRightClickedInAir!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure HyperPortallerRightClickedInAir!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure HyperPortallerRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure HyperPortallerRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure HyperPortallerRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		entity.getPersistentData().putDouble("OtherX", x);
		entity.getPersistentData().putDouble("OtherY", y);
		entity.getPersistentData().putDouble("OtherZ", z);
		{
			Entity _ent = entity;
			_ent.setPositionAndUpdate((MagicaModVariables.WorldVariables.get(world).PortallerLocX),
					(MagicaModVariables.WorldVariables.get(world).PortallerLocY), (MagicaModVariables.WorldVariables.get(world).PortallerLocZ));
			if (_ent instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) _ent).connection.setPlayerLocation((MagicaModVariables.WorldVariables.get(world).PortallerLocX),
						(MagicaModVariables.WorldVariables.get(world).PortallerLocY), (MagicaModVariables.WorldVariables.get(world).PortallerLocZ),
						_ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
			}
		}
	}
}

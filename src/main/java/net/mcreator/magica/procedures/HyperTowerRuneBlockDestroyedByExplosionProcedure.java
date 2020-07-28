package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;

import net.mcreator.magica.entity.HyperTowerEntity;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.Comparator;

@MagicaModElements.ModElement.Tag
public class HyperTowerRuneBlockDestroyedByExplosionProcedure extends MagicaModElements.ModElement {
	public HyperTowerRuneBlockDestroyedByExplosionProcedure(MagicaModElements instance) {
		super(instance, 172);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure HyperTowerRuneBlockDestroyedByExplosion!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure HyperTowerRuneBlockDestroyedByExplosion!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure HyperTowerRuneBlockDestroyedByExplosion!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure HyperTowerRuneBlockDestroyedByExplosion!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (!(world
				.getEntitiesWithinAABB(HyperTowerEntity.CustomEntity.class,
						new AxisAlignedBB(x - 128 / 2, y - 128 / 2, z - 128 / 2, x + 128 / 2, y + 128 / 2, z + 128 / 2), null)
				.stream().sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)).world.isRemote)
			(world.getEntitiesWithinAABB(HyperTowerEntity.CustomEntity.class,
					new AxisAlignedBB(x - 128 / 2, y - 128 / 2, z - 128 / 2, x + 128 / 2, y + 128 / 2, z + 128 / 2), null).stream()
					.sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)).remove();
	}
}

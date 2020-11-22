package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.function.Function;
import java.util.Map;
import java.util.Comparator;

@MagicaModElements.ModElement.Tag
public class AntigravityBlockEntityWalksOnTheBlockProcedure extends MagicaModElements.ModElement {
	public AntigravityBlockEntityWalksOnTheBlockProcedure(MagicaModElements instance) {
		super(instance, 434);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure AntigravityBlockEntityWalksOnTheBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure AntigravityBlockEntityWalksOnTheBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure AntigravityBlockEntityWalksOnTheBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure AntigravityBlockEntityWalksOnTheBlock!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		((Entity) world
				.getEntitiesWithinAABB(PlayerEntity.class,
						new AxisAlignedBB(x - (1 / 2d), (y + 1) - (1 / 2d), z - (1 / 2d), x + (1 / 2d), (y + 1) + (1 / 2d), z + (1 / 2d)), null)
				.stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
					}
				}.compareDistOf(x, (y + 1),
						z))
				.findFirst().orElse(null)).setMotion(
						0,
						((((Entity) world.getEntitiesWithinAABB(PlayerEntity.class,
								new AxisAlignedBB(x - (1 / 2d), (y + 1) - (1 / 2d), z - (1 / 2d), x + (1 / 2d), (y + 1) + (1 / 2d), z + (1 / 2d)),
								null).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
									}
								}.compareDistOf(x, (y + 1), z)).findFirst().orElse(null)).getMotion().getY()) * 10),
						0);
	}
}
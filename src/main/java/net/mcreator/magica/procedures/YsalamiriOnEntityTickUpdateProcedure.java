package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.potion.MagicalAtrophyPotion;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.Comparator;

@MagicaModElements.ModElement.Tag
public class YsalamiriOnEntityTickUpdateProcedure extends MagicaModElements.ModElement {
	public YsalamiriOnEntityTickUpdateProcedure(MagicaModElements instance) {
		super(instance, 218);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure YsalamiriOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure YsalamiriOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure YsalamiriOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure YsalamiriOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure YsalamiriOnEntityTickUpdate!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity instanceof TameableEntity) ? ((TameableEntity) entity).isTamed() : false)) {
			if ((world
					.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(x - 4 / 2, y - 4 / 2, z - 4 / 2, x + 4 / 2, y + 4 / 2, z + 4 / 2),
							null)
					.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst()
					.orElse(null)) instanceof LivingEntity)
				((LivingEntity) (world
						.getEntitiesWithinAABB(PlayerEntity.class,
								new AxisAlignedBB(x - 4 / 2, y - 4 / 2, z - 4 / 2, x + 4 / 2, y + 4 / 2, z + 4 / 2), null)
						.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst().orElse(null)))
								.clearActivePotions();
			if ((world
					.getEntitiesWithinAABB(ServerPlayerEntity.class,
							new AxisAlignedBB(x - 4 / 2, y - 4 / 2, z - 4 / 2, x + 4 / 2, y + 4 / 2, z + 4 / 2), null)
					.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst()
					.orElse(null)) instanceof LivingEntity)
				((LivingEntity) (world
						.getEntitiesWithinAABB(ServerPlayerEntity.class,
								new AxisAlignedBB(x - 4 / 2, y - 4 / 2, z - 4 / 2, x + 4 / 2, y + 4 / 2, z + 4 / 2), null)
						.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst().orElse(null)))
								.clearActivePotions();
		} else {
			if ((world
					.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(x - 4 / 2, y - 4 / 2, z - 4 / 2, x + 4 / 2, y + 4 / 2, z + 4 / 2),
							null)
					.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst()
					.orElse(null)) instanceof LivingEntity)
				((LivingEntity) (world
						.getEntitiesWithinAABB(PlayerEntity.class,
								new AxisAlignedBB(x - 4 / 2, y - 4 / 2, z - 4 / 2, x + 4 / 2, y + 4 / 2, z + 4 / 2), null)
						.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst().orElse(null)))
								.addPotionEffect(new EffectInstance(MagicalAtrophyPotion.potion, (int) 60, (int) 1));
			if ((world
					.getEntitiesWithinAABB(ServerPlayerEntity.class,
							new AxisAlignedBB(x - 4 / 2, y - 4 / 2, z - 4 / 2, x + 4 / 2, y + 4 / 2, z + 4 / 2), null)
					.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst()
					.orElse(null)) instanceof LivingEntity)
				((LivingEntity) (world
						.getEntitiesWithinAABB(ServerPlayerEntity.class,
								new AxisAlignedBB(x - 4 / 2, y - 4 / 2, z - 4 / 2, x + 4 / 2, y + 4 / 2, z + 4 / 2), null)
						.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst().orElse(null)))
								.addPotionEffect(new EffectInstance(MagicalAtrophyPotion.potion, (int) 60, (int) 1));
		}
	}
}

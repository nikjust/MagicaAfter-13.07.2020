package net.mcreator.magica.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.item.MagicHammerItem;
import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class RangedBoofProcedure extends MagicaModElements.ModElement {
	public RangedBoofProcedure(MagicaModElements instance) {
		super(instance, 186);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure RangedBoof!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure RangedBoof!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure RangedBoof!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure RangedBoof!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure RangedBoof!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		for (int index0 = 0; index0 < (int) (72); index0++) {
			entity.rotationYaw = (float) ((MagicaModVariables.MapVariables.get(world).Gradus));
			entity.setRenderYawOffset(entity.rotationYaw);
			entity.prevRotationYaw = entity.rotationYaw;
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).prevRenderYawOffset = entity.rotationYaw;
				((LivingEntity) entity).rotationYawHead = entity.rotationYaw;
				((LivingEntity) entity).prevRotationYawHead = entity.rotationYaw;
			}
			entity.rotationPitch = (float) (0);
			if (world instanceof World && !world.getWorld().isRemote && entity instanceof LivingEntity) {
				ArrowEntity entityToSpawn = new ArrowEntity(world.getWorld(), (LivingEntity) entity);
				entityToSpawn.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, (float) 1, 0);
				entityToSpawn.setDamage((float) 5);
				entityToSpawn.setKnockbackStrength((int) 5);
				world.addEntity(entityToSpawn);
			}
			MagicaModVariables.MapVariables.get(world).Gradus = (double) ((MagicaModVariables.MapVariables.get(world).Gradus) + 5);
			MagicaModVariables.MapVariables.get(world).syncData(world);
		}
		if (world instanceof World && !world.getWorld().isRemote) {
			ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(MagicHammerItem.block, (int) (1)));
			entityToSpawn.setPickupDelay((int) 10);
			world.addEntity(entityToSpawn);
		}
	}
}

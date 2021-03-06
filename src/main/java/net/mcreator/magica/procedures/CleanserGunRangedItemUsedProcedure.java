package net.mcreator.magica.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.item.MagicaWandItem;
import net.mcreator.magica.MagicaModElements;

import java.util.Random;
import java.util.Map;

@MagicaModElements.ModElement.Tag
public class CleanserGunRangedItemUsedProcedure extends MagicaModElements.ModElement {
	public CleanserGunRangedItemUsedProcedure(MagicaModElements instance) {
		super(instance, 184);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure CleanserGunRangedItemUsed!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure CleanserGunRangedItemUsed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		for (int index0 = 0; index0 < (int) (10); index0++) {
			if (world instanceof World && !world.getWorld().isRemote && entity instanceof LivingEntity) {
				MagicaWandItem.shoot(world.getWorld(), (LivingEntity) entity, new Random(), (float) 1, (float) 5, (int) 5);
			}
		}
	}
}

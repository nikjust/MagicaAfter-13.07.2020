package net.mcreator.magica.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.item.RicoLightBulletItem;
import net.mcreator.magica.MagicaModElements;

import java.util.Random;
import java.util.Map;

@MagicaModElements.ModElement.Tag
public class RicoLightBulletBulletHitsLivingEntityProcedure extends MagicaModElements.ModElement {
	public RicoLightBulletBulletHitsLivingEntityProcedure(MagicaModElements instance) {
		super(instance, 264);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure RicoLightBulletBulletHitsLivingEntity!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure RicoLightBulletBulletHitsLivingEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof World && !world.getWorld().isRemote && entity instanceof LivingEntity) {
			RicoLightBulletItem.shoot(world.getWorld(), (LivingEntity) entity, new Random(), (float) 1, (float) 5, (int) 5);
		}
	}
}

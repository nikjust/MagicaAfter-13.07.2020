package net.mcreator.magica.procedures;

import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.item.RicoLightBulletItem;
import net.mcreator.magica.MagicaModElements;

import java.util.Random;
import java.util.Map;

@MagicaModElements.ModElement.Tag
public class RicoLightBulletBulletHitsLivingEntityProcedure extends MagicaModElements.ModElement {
	public RicoLightBulletBulletHitsLivingEntityProcedure(MagicaModElements instance) {
		super(instance, 155);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure RicoLightBulletBulletHitsLivingEntity!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure RicoLightBulletBulletHitsLivingEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (!world.isRemote && entity instanceof LivingEntity) {
			RicoLightBulletItem.shoot(world, (LivingEntity) entity, new Random(), (float) 1, (float) 5, (int) 5);
		}
	}
}

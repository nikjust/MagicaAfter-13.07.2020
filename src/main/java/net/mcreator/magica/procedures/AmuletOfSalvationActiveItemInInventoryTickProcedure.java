package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.Comparator;

@MagicaModElements.ModElement.Tag
public class AmuletOfSalvationActiveItemInInventoryTickProcedure extends MagicaModElements.ModElement {
	public AmuletOfSalvationActiveItemInInventoryTickProcedure(MagicaModElements instance) {
		super(instance, 63);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure AmuletOfSalvationActiveItemInInventoryTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure AmuletOfSalvationActiveItemInInventoryTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure AmuletOfSalvationActiveItemInInventoryTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure AmuletOfSalvationActiveItemInInventoryTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure AmuletOfSalvationActiveItemInInventoryTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((4 >= ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1))
				&& (0 < (MagicaModVariables.MapVariables.get(world).GlobalMagic)))) {
			if ((world
					.getEntitiesWithinAABB(MonsterEntity.class,
							new AxisAlignedBB(x - 20 / 2, y - 20 / 2, z - 20 / 2, x + 20 / 2, y + 20 / 2, z + 20 / 2), null)
					.stream().sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)) instanceof LivingEntity)
				((LivingEntity) (world
						.getEntitiesWithinAABB(MonsterEntity.class,
								new AxisAlignedBB(x - 20 / 2, y - 20 / 2, z - 20 / 2, x + 20 / 2, y + 20 / 2, z + 20 / 2), null)
						.stream().sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)))
								.addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 60, (int) 100));
			if ((world
					.getEntitiesWithinAABB(MonsterEntity.class,
							new AxisAlignedBB(x - 20 / 2, y - 20 / 2, z - 20 / 2, x + 20 / 2, y + 20 / 2, z + 20 / 2), null)
					.stream().sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)) instanceof LivingEntity)
				((LivingEntity) (world
						.getEntitiesWithinAABB(MonsterEntity.class,
								new AxisAlignedBB(x - 20 / 2, y - 20 / 2, z - 20 / 2, x + 20 / 2, y + 20 / 2, z + 20 / 2), null)
						.stream().sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)))
								.addPotionEffect(new EffectInstance(Effects.WEAKNESS, (int) 60, (int) 100));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 20, (int) 3, (false), (false)));
			if ((60 == (entity.getPersistentData().getDouble("SLVTIMER")))) {
				MagicaModVariables.MapVariables.get(world).GlobalMagic = (double) ((MagicaModVariables.MapVariables.get(world).GlobalMagic) - 1);
				MagicaModVariables.MapVariables.get(world).syncData(world);
				entity.getPersistentData().putDouble("SLVTIMER", 0);
			} else {
				entity.getPersistentData().putDouble("SLVTIMER", ((entity.getPersistentData().getDouble("SLVTIMER")) + 1));
			}
		}
	}
}

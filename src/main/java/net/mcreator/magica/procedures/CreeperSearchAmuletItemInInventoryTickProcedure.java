package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.LivingEntity;

import net.mcreator.magica.entity.MysteryCreeperEntity;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.Comparator;

@MagicaModElements.ModElement.Tag
public class CreeperSearchAmuletItemInInventoryTickProcedure extends MagicaModElements.ModElement {
	public CreeperSearchAmuletItemInInventoryTickProcedure(MagicaModElements instance) {
		super(instance, 60);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure CreeperSearchAmuletItemInInventoryTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure CreeperSearchAmuletItemInInventoryTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure CreeperSearchAmuletItemInInventoryTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure CreeperSearchAmuletItemInInventoryTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((world
				.getEntitiesWithinAABB(CreeperEntity.class, new AxisAlignedBB(x - 20 / 2, y - 20 / 2, z - 20 / 2, x + 20 / 2, y + 20 / 2, z + 20 / 2),
						null)
				.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst().orElse(null)) instanceof LivingEntity)
			((LivingEntity) (world
					.getEntitiesWithinAABB(CreeperEntity.class,
							new AxisAlignedBB(x - 20 / 2, y - 20 / 2, z - 20 / 2, x + 20 / 2, y + 20 / 2, z + 20 / 2), null)
					.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst().orElse(null)))
							.addPotionEffect(new EffectInstance(Effects.GLOWING, (int) 60, (int) 1));
		if ((world
				.getEntitiesWithinAABB(MysteryCreeperEntity.CustomEntity.class,
						new AxisAlignedBB(x - 20 / 2, y - 20 / 2, z - 20 / 2, x + 20 / 2, y + 20 / 2, z + 20 / 2), null)
				.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst().orElse(null)) instanceof LivingEntity)
			((LivingEntity) (world
					.getEntitiesWithinAABB(MysteryCreeperEntity.CustomEntity.class,
							new AxisAlignedBB(x - 20 / 2, y - 20 / 2, z - 20 / 2, x + 20 / 2, y + 20 / 2, z + 20 / 2), null)
					.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst().orElse(null)))
							.addPotionEffect(new EffectInstance(Effects.GLOWING, (int) 60, (int) 1));
	}
}

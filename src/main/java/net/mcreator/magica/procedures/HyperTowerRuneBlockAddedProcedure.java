package net.mcreator.magica.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;

import net.mcreator.magica.entity.HyperTowerEntity;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.Comparator;

@MagicaModElements.ModElement.Tag
public class HyperTowerRuneBlockAddedProcedure extends MagicaModElements.ModElement {
	public HyperTowerRuneBlockAddedProcedure(MagicaModElements instance) {
		super(instance, 171);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure HyperTowerRuneBlockAdded!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure HyperTowerRuneBlockAdded!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure HyperTowerRuneBlockAdded!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure HyperTowerRuneBlockAdded!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof World && !world.getWorld().isRemote) {
			Entity entityToSpawn = new HyperTowerEntity.CustomEntity(HyperTowerEntity.entity, world.getWorld());
			entityToSpawn.setLocationAndAngles(x, (y + 2), z, world.getRandom().nextFloat() * 360F, 0);
			if (entityToSpawn instanceof MobEntity)
				((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
						SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
			world.addEntity(entityToSpawn);
		}
		(world.getEntitiesWithinAABB(HyperTowerEntity.CustomEntity.class, new AxisAlignedBB(x - 10 / 2, y - 10 / 2, z - 10 / 2, x + 10 / 2, y + 10
				/ 2, z
						+ 10 / 2),
				null).stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst().orElse(null))
						.setCustomName(new StringTextComponent(((world
								.getEntitiesWithinAABB(PlayerEntity.class,
										new AxisAlignedBB(x - 10 / 2, y - 10 / 2, z - 10 / 2, x + 10 / 2, y + 10 / 2, z + 10 / 2), null)
								.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst().orElse(null))
										.getDisplayName().getString())));
		(world.getEntitiesWithinAABB(HyperTowerEntity.CustomEntity.class, new AxisAlignedBB(x - 10 / 2, y - 10 / 2, z - 10 / 2, x + 10 / 2, y + 10
				/ 2, z
						+ 10 / 2),
				null).stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst().orElse(null))
						.setCustomName(new StringTextComponent(((world
								.getEntitiesWithinAABB(ServerPlayerEntity.class,
										new AxisAlignedBB(x - 10 / 2, y - 10 / 2, z - 10 / 2, x + 10 / 2, y + 10 / 2, z + 10 / 2), null)
								.stream().sorted(Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(x, y, z))).findFirst().orElse(null))
										.getDisplayName().getString())));
	}
}

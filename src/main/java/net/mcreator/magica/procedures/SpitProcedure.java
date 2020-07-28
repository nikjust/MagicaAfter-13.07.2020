package net.mcreator.magica.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.Comparator;

@MagicaModElements.ModElement.Tag
public class SpitProcedure extends MagicaModElements.ModElement {
	public SpitProcedure(MagicaModElements instance) {
		super(instance, 88);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Spit!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Spit!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Spit!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Spit!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof World && !world.getWorld().isRemote) {
			Entity entityToSpawn = new DragonFireballEntity(EntityType.DRAGON_FIREBALL, world.getWorld());
			entityToSpawn.setLocationAndAngles(x, y, z,
					(float) ((world
							.getEntitiesWithinAABB(PlayerEntity.class,
									new AxisAlignedBB(x - 4 / 2, y - 4 / 2, z - 4 / 2, x + 4 / 2, y + 4 / 2, z + 4 / 2), null)
							.stream().sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)).rotationYaw),
					(float) ((world
							.getEntitiesWithinAABB(PlayerEntity.class,
									new AxisAlignedBB(x - 4 / 2, y - 4 / 2, z - 4 / 2, x + 4 / 2, y + 4 / 2, z + 4 / 2), null)
							.stream().sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)).rotationPitch));
			entityToSpawn
					.setMotion(((world
							.getEntitiesWithinAABB(
									PlayerEntity.class, new AxisAlignedBB(x - 4 / 2, y - 4 / 2, z - 4 / 2, x + 4 / 2, y + 4 / 2, z + 4 / 2), null)
							.stream().sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)).getMotion().getX()),
							0,
							((world.getEntitiesWithinAABB(PlayerEntity.class,
									new AxisAlignedBB(x - 4 / 2, y - 4 / 2, z - 4 / 2, x + 4 / 2, y + 4 / 2, z + 4 / 2), null).stream()
									.sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)).getMotion().getZ()));
			if (entityToSpawn instanceof MobEntity)
				((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
						SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
			world.addEntity(entityToSpawn);
		}
	}
}

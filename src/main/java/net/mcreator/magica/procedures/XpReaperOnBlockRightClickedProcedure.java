package net.mcreator.magica.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.EyeOfEnderEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;

import net.mcreator.magica.entity.MysteryCreeperEntity;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.Comparator;

@MagicaModElements.ModElement.Tag
public class XpReaperOnBlockRightClickedProcedure extends MagicaModElements.ModElement {
	public XpReaperOnBlockRightClickedProcedure(MagicaModElements instance) {
		super(instance, 117);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure XpReaperOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure XpReaperOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure XpReaperOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure XpReaperOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure XpReaperOnBlockRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((new ItemStack(Items.ENDER_EYE, (int) (1))
				.getItem() == ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem())) {
			if (!world.isRemote) {
				Entity entityToSpawn = new EyeOfEnderEntity(EntityType.EYE_OF_ENDER, world);
				entityToSpawn.setLocationAndAngles(x, y, z, (float) 0, (float) 0);
				entityToSpawn.setMotion(0, 2, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
			for (int index0 = 0; index0 < (int) (100); index0++) {
				if (!world.isRemote) {
					Entity entityToSpawn = new ExperienceOrbEntity(EntityType.EXPERIENCE_ORB, world);
					entityToSpawn.setLocationAndAngles(x, y, z, (float) 0, (float) 0);
					entityToSpawn.setMotion(0, 2, 0);
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
			}
			world.addParticle(ParticleTypes.EXPLOSION_EMITTER,
					((world.getEntitiesWithinAABB(EyeOfEnderEntity.class, new AxisAlignedBB(x - 10, y - 10, z - 10, x + 10, y + 10, z + 10), null)
							.stream().sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)).posX),
					((world.getEntitiesWithinAABB(MysteryCreeperEntity.CustomEntity.class,
							new AxisAlignedBB(x - 10, y - 10, z - 10, x + 10, y + 10, z + 10), null).stream()
							.sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)).posY),
					((world.getEntitiesWithinAABB(EyeOfEnderEntity.class, new AxisAlignedBB(x - 10, y - 10, z - 10, x + 10, y + 10, z + 10), null)
							.stream().sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)).getMotion().getZ()),
					0, 1, 0);
		}
	}
}

package net.mcreator.magica.procedures;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.Collections;

@MagicaModElements.ModElement.Tag
public class MarionetteOnEntityTickUpdateProcedure extends MagicaModElements.ModElement {
	public MarionetteOnEntityTickUpdateProcedure(MagicaModElements instance) {
		super(instance, 452);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MarionetteOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure MarionetteOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure MarionetteOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure MarionetteOnEntityTickUpdate!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if (((entity instanceof TameableEntity) ? ((TameableEntity) entity).isTamed() : false)) {
			entity.setMotion((((entity instanceof TameableEntity) ? ((TameableEntity) entity).getOwner() : null).getMotion().getX()),
					(((entity instanceof TameableEntity) ? ((TameableEntity) entity).getOwner() : null).getMotion().getY()),
					(((entity instanceof TameableEntity) ? ((TameableEntity) entity).getOwner() : null).getMotion().getZ()));
			entity.rotationYaw = (float) ((((entity instanceof TameableEntity) ? ((TameableEntity) entity).getOwner() : null).rotationYaw));
			entity.setRenderYawOffset(entity.rotationYaw);
			entity.prevRotationYaw = entity.rotationYaw;
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).prevRenderYawOffset = entity.rotationYaw;
				((LivingEntity) entity).rotationYawHead = entity.rotationYaw;
				((LivingEntity) entity).prevRotationYawHead = entity.rotationYaw;
			}
			entity.rotationPitch = (float) ((((entity instanceof TameableEntity) ? ((TameableEntity) entity).getOwner() : null).rotationPitch));
			if ((((entity instanceof TameableEntity) ? ((TameableEntity) entity).getOwner() : null).isSneaking())) {
				entity.setSneaking((true));
				entity.setMotion((((entity instanceof TameableEntity) ? ((TameableEntity) entity).getOwner() : null).getMotion().getX()), (-1),
						(((entity instanceof TameableEntity) ? ((TameableEntity) entity).getOwner() : null).getMotion().getZ()));
			} else {
				entity.setSneaking((false));
			}
			if ((((entity instanceof TameableEntity) ? ((TameableEntity) entity).getOwner() : null).isSprinting())) {
				entity.setSprinting((true));
			} else {
				entity.setSprinting((false));
			}
		}
		if (((entity instanceof LivingEntity) ? (entity.onGround) : false)) {
			{
				Entity _ent = entity;
				_ent.setPositionAndUpdate(x, (y + 0.1), z);
				if (_ent instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) _ent).connection.setPlayerLocation(x, (y + 0.1), z, _ent.rotationYaw, _ent.rotationPitch,
							Collections.emptySet());
				}
			}
		}
	}
}

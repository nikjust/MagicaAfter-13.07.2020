package net.mcreator.magica.procedures;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.Collections;

@MagicaModElements.ModElement.Tag
public class ReturnerBlockOnBlockRightClickedProcedure extends MagicaModElements.ModElement {
	public ReturnerBlockOnBlockRightClickedProcedure(MagicaModElements instance) {
		super(instance, 233);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ReturnerBlockOnBlockRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.isSneaking())) {
			{
				Entity _ent = entity;
				_ent.setPositionAndUpdate(0, 80, 0);
				if (_ent instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) _ent).connection.setPlayerLocation(0, 80, 0, _ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
				}
			}
		} else {
			{
				Entity _ent = entity;
				_ent.setPositionAndUpdate((entity.getPersistentData().getDouble("OtherX")), (entity.getPersistentData().getDouble("OtherY")),
						(entity.getPersistentData().getDouble("OtherZ")));
				if (_ent instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) _ent).connection.setPlayerLocation((entity.getPersistentData().getDouble("OtherX")),
							(entity.getPersistentData().getDouble("OtherY")), (entity.getPersistentData().getDouble("OtherZ")), _ent.rotationYaw,
							_ent.rotationPitch, Collections.emptySet());
				}
			}
		}
	}
}

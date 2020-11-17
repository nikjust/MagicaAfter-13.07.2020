package net.mcreator.magica.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class QuadrocopterRightClickedOnEntityProcedure extends MagicaModElements.ModElement {
	public QuadrocopterRightClickedOnEntityProcedure(MagicaModElements instance) {
		super(instance, 459);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure QuadrocopterRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure QuadrocopterRightClickedOnEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((!((entity instanceof TameableEntity) ? ((TameableEntity) entity).isTamed() : false))) {
			if ((entity instanceof TameableEntity) && (sourceentity instanceof PlayerEntity)) {
				((TameableEntity) entity).setTamed(true);
				((TameableEntity) entity).setTamedBy((PlayerEntity) sourceentity);
			}
			entity.setCustomName(new StringTextComponent(
					(((((entity instanceof TameableEntity) ? ((TameableEntity) entity).getOwner() : null).getDisplayName().getString())) + ""
							+ (" quadrocopter"))));
		}
	}
}

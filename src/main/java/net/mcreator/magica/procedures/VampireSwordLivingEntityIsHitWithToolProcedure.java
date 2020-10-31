package net.mcreator.magica.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class VampireSwordLivingEntityIsHitWithToolProcedure extends MagicaModElements.ModElement {
	public VampireSwordLivingEntityIsHitWithToolProcedure(MagicaModElements instance) {
		super(instance, 268);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure VampireSwordLivingEntityIsHitWithTool!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHealth() : -1) >= ((sourceentity instanceof LivingEntity)
				? ((LivingEntity) sourceentity).getMaxHealth()
				: -1))) {
			if (sourceentity instanceof LivingEntity)
				((LivingEntity) sourceentity)
						.setHealth((float) (2 + ((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHealth() : -1)));
		}
	}
}

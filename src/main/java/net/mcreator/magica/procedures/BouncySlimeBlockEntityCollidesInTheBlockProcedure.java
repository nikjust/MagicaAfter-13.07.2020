package net.mcreator.magica.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class BouncySlimeBlockEntityCollidesInTheBlockProcedure extends MagicaModElements.ModElement {
	public BouncySlimeBlockEntityCollidesInTheBlockProcedure(MagicaModElements instance) {
		super(instance, 262);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure BouncySlimeBlockEntityCollidesInTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((0 < (entity.getMotion().getY()))) {
			entity.setMotion((entity.getMotion().getX()), ((entity.getMotion().getY()) - ((entity.getMotion().getY()) - (entity.getMotion().getY()))),
					(entity.getMotion().getZ()));
		}
	}
}

package net.mcreator.magica.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class BouncySlimeBlockEntityWalksOnTheBlockProcedure extends MagicaModElements.ModElement {
	public BouncySlimeBlockEntityWalksOnTheBlockProcedure(MagicaModElements instance) {
		super(instance, 261);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BouncySlimeBlockEntityWalksOnTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setMotion(((entity.getMotion().getX()) * 10), (entity.getMotion().getY()), ((entity.getMotion().getZ()) * 10));
	}
}

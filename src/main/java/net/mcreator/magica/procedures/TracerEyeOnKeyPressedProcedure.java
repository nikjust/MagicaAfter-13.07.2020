package net.mcreator.magica.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class TracerEyeOnKeyPressedProcedure extends MagicaModElements.ModElement {
	public TracerEyeOnKeyPressedProcedure(MagicaModElements instance) {
		super(instance, 467);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure TracerEyeOnKeyPressed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			boolean _setval = (boolean) (!((entity.getCapability(MagicaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MagicaModVariables.PlayerVariables())).TracerEye));
			entity.getCapability(MagicaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.TracerEye = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}

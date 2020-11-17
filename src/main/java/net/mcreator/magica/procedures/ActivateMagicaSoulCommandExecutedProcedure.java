package net.mcreator.magica.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class ActivateMagicaSoulCommandExecutedProcedure extends MagicaModElements.ModElement {
	public ActivateMagicaSoulCommandExecutedProcedure(MagicaModElements instance) {
		super(instance, 383);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ActivateMagicaSoulCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			ItemStack _setval = new ItemStack(Blocks.STONE, (int) (1));
			entity.getCapability(MagicaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.soulUsed = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}

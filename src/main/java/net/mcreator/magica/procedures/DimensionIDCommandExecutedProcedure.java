package net.mcreator.magica.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class DimensionIDCommandExecutedProcedure extends MagicaModElements.ModElement {
	public DimensionIDCommandExecutedProcedure(MagicaModElements instance) {
		super(instance, 216);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure DimensionIDCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList()
						.sendMessage(new StringTextComponent((((("Current dimension id:") + "" + ("\u00A74"))) + "" + ((entity.dimension.getId())))));
		}
	}
}

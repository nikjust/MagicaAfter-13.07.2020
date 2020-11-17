package net.mcreator.magica.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.server.MinecraftServer;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

import java.io.File;

@MagicaModElements.ModElement.Tag
public class MagicaConfigPleaseCommandExecutedProcedure extends MagicaModElements.ModElement {
	public MagicaConfigPleaseCommandExecutedProcedure(MagicaModElements instance) {
		super(instance, 304);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		File folder = new File(System.getProperty("user.dir") + "/" + "magicaconfig");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File mgcconf = new File(((System.getProperty("user.dir")) + "" + ("/magicaconfig")) + "/" + "config.magica");
		{
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().sendMessage(new StringTextComponent(" _      ____  _____ _  ____  ____ "));
		}
		{
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().sendMessage(new StringTextComponent("/ \\__/|/  _ \\/  __// \\/   _\\/  _ \\"));
		}
		{
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().sendMessage(new StringTextComponent("| |\\/||| / \\|| |  _| ||  /  | / \\|"));
		}
		{
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().sendMessage(new StringTextComponent("| |  ||| |-||| |_//| ||  \\_ | |-||"));
		}
		{
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().sendMessage(new StringTextComponent("\\_/  \\|\\_/ \\|\\____\\\\_/\\____/\\_/ \\|"));
		}
		{
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().sendMessage(new StringTextComponent("                                  "));
		}
	}
}

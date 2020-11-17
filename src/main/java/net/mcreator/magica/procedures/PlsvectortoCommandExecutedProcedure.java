package net.mcreator.magica.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.HashMap;

@MagicaModElements.ModElement.Tag
public class PlsvectortoCommandExecutedProcedure extends MagicaModElements.ModElement {
	public PlsvectortoCommandExecutedProcedure(MagicaModElements instance) {
		super(instance, 416);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure PlsvectortoCommandExecuted!");
			return;
		}
		if (dependencies.get("cmdparams") == null) {
			if (!dependencies.containsKey("cmdparams"))
				System.err.println("Failed to load dependency cmdparams for procedure PlsvectortoCommandExecuted!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure PlsvectortoCommandExecuted!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure PlsvectortoCommandExecuted!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure PlsvectortoCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap cmdparams = (HashMap) dependencies.get("cmdparams");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		entity.setMotion((x - new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText()))), (y - new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("1");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText()))), (z - new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("2");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText()))));
	}
}

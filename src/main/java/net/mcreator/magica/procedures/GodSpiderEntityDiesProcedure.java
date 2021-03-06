package net.mcreator.magica.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class GodSpiderEntityDiesProcedure extends MagicaModElements.ModElement {
	public GodSpiderEntityDiesProcedure(MagicaModElements instance) {
		super(instance, 409);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure GodSpiderEntityDies!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure GodSpiderEntityDies!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure GodSpiderEntityDies!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure GodSpiderEntityDies!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		MagicaModVariables.MapVariables.get(world).YSpiderGod = (double) 0;
		MagicaModVariables.MapVariables.get(world).syncData(world);
		for (int index0 = 0; index0 < (int) (3); index0++) {
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager()
						.handleCommand(new CommandSource(ICommandSource.field_213139_a_,
								new Vec3d(x, (y + (MagicaModVariables.MapVariables.get(world).YSpiderGod)), z), Vec2f.ZERO, (ServerWorld) world, 4,
								"", new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
								"summon magica:entitybulletgod_spider_weapon ~ ~2 ~ {Motion:[0.5,0.5,0.0]}");
			}
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager()
						.handleCommand(new CommandSource(ICommandSource.field_213139_a_,
								new Vec3d(x, (y + (MagicaModVariables.MapVariables.get(world).YSpiderGod)), z), Vec2f.ZERO, (ServerWorld) world, 4,
								"", new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
								"summon magica:entitybulletgod_spider_weapon ~ ~2 ~ {Motion:[0.0,0.5,0.5]}");
			}
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.field_213139_a_,
								new Vec3d(x, (y + (MagicaModVariables.MapVariables.get(world).YSpiderGod)), z), Vec2f.ZERO, (ServerWorld) world, 4,
								"", new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"summon magica:entitybulletgod_spider_weapon ~ ~2 ~ {Motion:[0.0,0.5,-0.5]}");
			}
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.field_213139_a_,
								new Vec3d(x, (y + (MagicaModVariables.MapVariables.get(world).YSpiderGod)), z), Vec2f.ZERO, (ServerWorld) world, 4,
								"", new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"summon magica:entitybulletgod_spider_weapon ~ ~2 ~ {Motion:[-0.5,0.5,0.0]}");
			}
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.field_213139_a_,
								new Vec3d(x, (y + (MagicaModVariables.MapVariables.get(world).YSpiderGod)), z), Vec2f.ZERO, (ServerWorld) world, 4,
								"", new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"summon magica:entitybulletgod_spider_weapon ~ ~2 ~ {Motion:[-0.5,0.5,-0.5]}");
			}
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.field_213139_a_,
								new Vec3d(x, (y + (MagicaModVariables.MapVariables.get(world).YSpiderGod)), z), Vec2f.ZERO, (ServerWorld) world, 4,
								"", new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"summon magica:entitybulletgod_spider_weapon ~ ~2 ~ {Motion:[0.5,0.5,-0.5]}");
			}
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.field_213139_a_,
								new Vec3d(x, (y + (MagicaModVariables.MapVariables.get(world).YSpiderGod)), z), Vec2f.ZERO, (ServerWorld) world, 4,
								"", new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"summon magica:entitybulletgod_spider_weapon ~ ~2 ~ {Motion:[-0.5,0.5,0.5]}");
			}
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager()
						.handleCommand(new CommandSource(ICommandSource.field_213139_a_,
								new Vec3d(x, (y + (MagicaModVariables.MapVariables.get(world).YSpiderGod)), z), Vec2f.ZERO, (ServerWorld) world, 4,
								"", new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
								"summon magica:entitybulletgod_spider_weapon ~ ~2 ~ {Motion:[0.5,0.5,0.5]}");
			}
			MagicaModVariables.MapVariables.get(world).YSpiderGod = (double) (1 + (MagicaModVariables.MapVariables.get(world).YSpiderGod));
			MagicaModVariables.MapVariables.get(world).syncData(world);
		}
	}
}

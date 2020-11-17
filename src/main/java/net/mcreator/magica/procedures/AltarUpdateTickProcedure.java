package net.mcreator.magica.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.block.Blocks;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class AltarUpdateTickProcedure extends MagicaModElements.ModElement {
	public AltarUpdateTickProcedure(MagicaModElements instance) {
		super(instance, 353);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure AltarUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure AltarUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure AltarUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure AltarUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((Blocks.DIAMOND_BLOCK.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x + 0), (int) y, (int) (z - 2))))
				.getBlock())
				&& (Blocks.DIAMOND_BLOCK.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x - 0), (int) y, (int) (z + 2))))
						.getBlock()))
				&& ((Blocks.DIAMOND_BLOCK.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) (x - 2), (int) y, (int) (z - 0))))
						.getBlock())
						&& (Blocks.DIAMOND_BLOCK.getDefaultState()
								.getBlock() == (world.getBlockState(new BlockPos((int) (x + 2), (int) y, (int) (z - 0)))).getBlock())))) {
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.ENCHANT, x, (y + 0), z, (int) 100, 0, 1, 0, 0);
			}
			for (int index0 = 0; index0 < (int) (12); index0++) {
				if (world instanceof World && !world.getWorld().isRemote) {
					ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, (y + 1), z, new ItemStack(Blocks.END_PORTAL_FRAME, (int) (1)));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
			}
			world.setBlockState(new BlockPos((int) (x + 0), (int) y, (int) (z - 2)), Blocks.AIR.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) (x + 0), (int) y, (int) (z + 2)), Blocks.AIR.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) (x + 2), (int) y, (int) (z - 0)), Blocks.AIR.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) (x - 2), (int) y, (int) (z - 0)), Blocks.AIR.getDefaultState(), 3);
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.field_213139_a_, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"/summon minecraft:ender_dragon ~ ~1 ~ {NoAI:1b,Health:0,Attributes:[{Name:\"generic.maxHealth\",Base:1},{Name:\"generic.attackDamage\",Base:0}]}");
			}
		}
	}
}

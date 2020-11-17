package net.mcreator.magica.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.item.ExoSkeletonItem;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class ExoSkeletonBodyTickEventProcedure extends MagicaModElements.ModElement {
	public ExoSkeletonBodyTickEventProcedure(MagicaModElements instance) {
		super(instance, 349);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ExoSkeletonBodyTickEvent!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure ExoSkeletonBodyTickEvent!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure ExoSkeletonBodyTickEvent!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure ExoSkeletonBodyTickEvent!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure ExoSkeletonBodyTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((new ItemStack(ExoSkeletonItem.helmet, (int) (1))
				.getItem() == ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get((int) 3) : ItemStack.EMPTY)
						.getItem())
				&& (new ItemStack(ExoSkeletonItem.legs, (int) (1)).getItem() == ((entity instanceof PlayerEntity)
						? ((PlayerEntity) entity).inventory.armorInventory.get((int) 1)
						: ItemStack.EMPTY).getItem()))
				&& (new ItemStack(ExoSkeletonItem.boots, (int) (1)).getItem() == ((entity instanceof PlayerEntity)
						? ((PlayerEntity) entity).inventory.armorInventory.get((int) 0)
						: ItemStack.EMPTY).getItem()))) {
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.note_block.bass")),
						SoundCategory.NEUTRAL, (float) 1, (float) 2);
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.note_block.bass")),
						SoundCategory.NEUTRAL, (float) 1, (float) 2, false);
			}
		}
	}
}

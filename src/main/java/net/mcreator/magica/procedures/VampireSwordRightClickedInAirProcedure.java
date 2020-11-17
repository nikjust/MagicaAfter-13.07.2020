package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.client.Minecraft;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class VampireSwordRightClickedInAirProcedure extends MagicaModElements.ModElement {
	public VampireSwordRightClickedInAirProcedure(MagicaModElements instance) {
		super(instance, 398);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure VampireSwordRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure VampireSwordRightClickedInAir!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		IWorld world = (IWorld) dependencies.get("world");
		if (world.getWorld().isRemote) {
			Minecraft.getInstance().gameRenderer.displayItemActivation(/* @ItemStack */(itemstack));
		}
	}
}

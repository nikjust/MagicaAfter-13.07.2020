package net.mcreator.magica.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.item.BloodBladeItem;
import net.mcreator.magica.item.BloodBladeClosedItem;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class BloodBladeClosedRightClickedOnBlockProcedure extends MagicaModElements.ModElement {
	public BloodBladeClosedRightClickedOnBlockProcedure(MagicaModElements instance) {
		super(instance, 215);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure BloodBladeClosedRightClickedOnBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity) {
			ItemStack _stktoremove = new ItemStack(BloodBladeClosedItem.block, (int) (1));
			((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
		}
		if (entity instanceof PlayerEntity) {
			ItemStack _setstack = new ItemStack(BloodBladeItem.block, (int) (1));
			_setstack.setCount((int) 1);
			ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
		}
		entity.attackEntityFrom(DamageSource.GENERIC, (float) 2);
	}
}

package net.mcreator.magica.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.item.UngrowedCrystalItem;
import net.mcreator.magica.item.BloodyCrystalItem;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class UngrowedCrystalRightClickedInAirProcedure extends MagicaModElements.ModElement {
	public UngrowedCrystalRightClickedInAirProcedure(MagicaModElements instance) {
		super(instance, 73);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure UngrowedCrystalRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.DRYOUT, (float) 10);
		if (entity instanceof PlayerEntity) {
			ItemStack _stktoremove = new ItemStack(UngrowedCrystalItem.block, (int) (1));
			((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
		}
		if (entity instanceof PlayerEntity) {
			ItemStack _setstack = new ItemStack(BloodyCrystalItem.block, (int) (1));
			_setstack.setCount((int) 1);
			ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
		}
	}
}

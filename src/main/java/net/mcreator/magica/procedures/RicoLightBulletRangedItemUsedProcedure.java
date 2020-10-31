package net.mcreator.magica.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.item.BloodyCrystalItem;
import net.mcreator.magica.MagicaModElements;

import java.util.Random;
import java.util.Map;

@MagicaModElements.ModElement.Tag
public class RicoLightBulletRangedItemUsedProcedure extends MagicaModElements.ModElement {
	public RicoLightBulletRangedItemUsedProcedure(MagicaModElements instance) {
		super(instance, 156);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure RicoLightBulletRangedItemUsed!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure RicoLightBulletRangedItemUsed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		{
			ItemStack _ist = (itemstack);
			if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
				_ist.shrink(1);
				_ist.setDamage(0);
			}
		}
		if ((1 == (((itemstack)).getDamage()))) {
			((itemstack)).setDamage((int) 0);
			if (((entity instanceof PlayerEntity)
					? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(BloodyCrystalItem.block, (int) (1)))
					: false)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(BloodyCrystalItem.block, (int) (1));
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
				}
			} else {
				entity.attackEntityFrom(DamageSource.DRYOUT, (float) 1);
			}
		}
	}
}

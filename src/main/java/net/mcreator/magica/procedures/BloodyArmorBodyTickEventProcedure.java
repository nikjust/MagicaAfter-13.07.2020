package net.mcreator.magica.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.item.BloodyArmorItem;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class BloodyArmorBodyTickEventProcedure extends MagicaModElements.ModElement {
	public BloodyArmorBodyTickEventProcedure(MagicaModElements instance) {
		super(instance, 198);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure BloodyArmorBodyTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((new ItemStack(BloodyArmorItem.boots, (int) (1))
				.getItem() == ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get((int) 0) : ItemStack.EMPTY)
						.getItem())
				&& ((new ItemStack(BloodyArmorItem.legs, (int) (1)).getItem() == ((entity instanceof PlayerEntity)
						? ((PlayerEntity) entity).inventory.armorInventory.get((int) 1)
						: ItemStack.EMPTY).getItem())
						&& (new ItemStack(BloodyArmorItem.helmet, (int) (1)).getItem() == ((entity instanceof PlayerEntity)
								? ((PlayerEntity) entity).inventory.armorInventory.get((int) 3)
								: ItemStack.EMPTY).getItem())))) {
		}
	}
}


package net.mcreator.magica.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mcreator.magica.itemgroup.MagicaItemGroup;
import net.mcreator.magica.MagicaModElements;

@MagicaModElements.ModElement.Tag
public class MagicMeatItem extends MagicaModElements.ModElement {
	@ObjectHolder("magica:magic_meat")
	public static final Item block = null;
	public MagicMeatItem(MagicaModElements instance) {
		super(instance, 4);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(MagicaItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(10).saturation(1f).meat().build()));
			setRegistryName("magic_meat");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}
	}
}

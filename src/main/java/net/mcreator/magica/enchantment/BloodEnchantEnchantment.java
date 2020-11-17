
package net.mcreator.magica.enchantment;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.DamageSource;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantment;

import net.mcreator.magica.MagicaModElements;

@MagicaModElements.ModElement.Tag
public class BloodEnchantEnchantment extends MagicaModElements.ModElement {
	@ObjectHolder("magica:blood_enchant")
	public static final Enchantment enchantment = null;
	public BloodEnchantEnchantment(MagicaModElements instance) {
		super(instance, 229);
	}

	@Override
	public void initElements() {
		elements.enchantments.add(() -> new CustomEnchantment(EquipmentSlotType.MAINHAND).setRegistryName("blood_enchant"));
	}
	public static class CustomEnchantment extends Enchantment {
		public CustomEnchantment(EquipmentSlotType... slots) {
			super(Enchantment.Rarity.UNCOMMON, EnchantmentType.WEAPON, slots);
		}

		@Override
		public int getMinLevel() {
			return 1;
		}

		@Override
		public int getMaxLevel() {
			return 25;
		}

		@Override
		public int calcModifierDamage(int level, DamageSource source) {
			return level * 2;
		}

		@Override
		public boolean isTreasureEnchantment() {
			return false;
		}

		@Override
		public boolean isCurse() {
			return false;
		}

		@Override
		public boolean isAllowedOnBooks() {
			return true;
		}
	}
}

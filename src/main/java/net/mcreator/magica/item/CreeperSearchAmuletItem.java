
package net.mcreator.magica.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import net.mcreator.magica.procedures.CreeperSearchAmuletItemInInventoryTickProcedure;
import net.mcreator.magica.itemgroup.MagicaItemGroup;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.HashMap;

@MagicaModElements.ModElement.Tag
public class CreeperSearchAmuletItem extends MagicaModElements.ModElement {
	@ObjectHolder("magica:creeper_search_amulet")
	public static final Item block = null;
	public CreeperSearchAmuletItem(MagicaModElements instance) {
		super(instance, 26);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MagicaItemGroup.tab).maxStackSize(1).rarity(Rarity.COMMON));
			setRegistryName("creeper_search_amulet");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}

		@Override
		public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			double x = entity.posX;
			double y = entity.posY;
			double z = entity.posZ;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				CreeperSearchAmuletItemInInventoryTickProcedure.executeProcedure($_dependencies);
			}
		}
	}
}

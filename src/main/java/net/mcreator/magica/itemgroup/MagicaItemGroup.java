
package net.mcreator.magica.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.magica.item.MagicaIconItem;
import net.mcreator.magica.MagicaModElements;

@MagicaModElements.ModElement.Tag
public class MagicaItemGroup extends MagicaModElements.ModElement {
	public MagicaItemGroup(MagicaModElements instance) {
		super(instance, 103);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabmagica") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(MagicaIconItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}
	public static ItemGroup tab;
}

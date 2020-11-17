
package net.mcreator.magica.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.IWorldReader;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.magica.itemgroup.MagicaItemGroup;
import net.mcreator.magica.MagicaModElements;

import java.util.List;
import java.util.Collections;

@MagicaModElements.ModElement.Tag
public class DeadWildForestLightVineBlock extends MagicaModElements.ModElement {
	@ObjectHolder("magica:dead_wild_forest_light_vine")
	public static final Block block = null;
	public DeadWildForestLightVineBlock(MagicaModElements instance) {
		super(instance, 63);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(MagicaItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends FallingBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.CACTUS).sound(SoundType.PLANT).hardnessAndResistance(1f, 10f).lightValue(0).harvestLevel(1)
					.harvestTool(ToolType.SHOVEL).tickRandomly());
			setRegistryName("dead_wild_forest_light_vine");
		}

		@Override
		public int tickRate(IWorldReader world) {
			return 200;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}

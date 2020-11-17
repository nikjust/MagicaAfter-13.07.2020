
package net.mcreator.magica.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.magica.itemgroup.MagicaItemGroup;
import net.mcreator.magica.MagicaModElements;

import java.util.List;
import java.util.Collections;

@MagicaModElements.ModElement.Tag
public class RedCimexHoneycombBlock extends MagicaModElements.ModElement {
	@ObjectHolder("magica:red_cimex_honeycomb")
	public static final Block block = null;
	public RedCimexHoneycombBlock(MagicaModElements instance) {
		super(instance, 80);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(MagicaItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.CAKE).sound(SoundType.SLIME).hardnessAndResistance(1f, 50f).lightValue(0).harvestLevel(3)
					.harvestTool(ToolType.AXE).slipperiness(2f));
			setRegistryName("red_cimex_honeycomb");
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getPackedLightmapCoords(BlockState state, IEnviromentBlockReader worldIn, BlockPos pos) {
			return 15728880;
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


package net.mcreator.magica.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.magica.world.dimension.CarribandimDimension;
import net.mcreator.magica.itemgroup.MagicaItemGroup;
import net.mcreator.magica.item.CortosisIngotItem;
import net.mcreator.magica.MagicaModElements;

import java.util.Random;
import java.util.List;
import java.util.Collections;

@MagicaModElements.ModElement.Tag
public class CortosisOreBlock extends MagicaModElements.ModElement {
	@ObjectHolder("magica:cortosis_ore")
	public static final Block block = null;
	public CortosisOreBlock(MagicaModElements instance) {
		super(instance, 44);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(MagicaItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(12f, 15.157165665103982f).lightValue(0)
					.harvestLevel(8).harvestTool(ToolType.PICKAXE));
			setRegistryName("cortosis_ore");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(CortosisIngotItem.block, (int) (1)));
		}
	}
	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(new OreFeature(OreFeatureConfig::deserialize) {
				@Override
				public boolean place(IWorld world, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
					DimensionType dimensionType = world.getDimension().getType();
					boolean dimensionCriteria = false;
					if (dimensionType == DimensionType.OVERWORLD)
						dimensionCriteria = true;
					if (dimensionType == CarribandimDimension.type)
						dimensionCriteria = true;
					if (!dimensionCriteria)
						return false;
					return super.place(world, generator, rand, pos, config);
				}
			}, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("cortosis_ore", "cortosis_ore", blockAt -> {
				boolean blockCriteria = false;
				if (blockAt.getBlock() == Blocks.GRASS_BLOCK.getDefaultState().getBlock())
					blockCriteria = true;
				if (blockAt.getBlock() == Blocks.DIRT.getDefaultState().getBlock())
					blockCriteria = true;
				if (blockAt.getBlock() == Blocks.PODZOL.getDefaultState().getBlock())
					blockCriteria = true;
				if (blockAt.getBlock() == Blocks.MYCELIUM.getDefaultState().getBlock())
					blockCriteria = true;
				if (blockAt.getBlock() == Blocks.GRASS_PATH.getDefaultState().getBlock())
					blockCriteria = true;
				if (blockAt.getBlock() == Blocks.LIGHT_GRAY_TERRACOTTA.getDefaultState().getBlock())
					blockCriteria = true;
				return blockCriteria;
			}), block.getDefaultState(), 1), Placement.COUNT_RANGE, new CountRangeConfig(2, 1, 1, 255)));
		}
	}
}

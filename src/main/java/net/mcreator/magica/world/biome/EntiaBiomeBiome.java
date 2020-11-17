
package net.mcreator.magica.world.biome;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.feature.structure.PillagerOutpostConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.block.Blocks;

import net.mcreator.magica.entity.HyperEntEntity;
import net.mcreator.magica.entity.EntEntity;
import net.mcreator.magica.MagicaModElements;

@MagicaModElements.ModElement.Tag
public class EntiaBiomeBiome extends MagicaModElements.ModElement {
	@ObjectHolder("magica:entia_biome")
	public static final CustomBiome biome = null;
	public EntiaBiomeBiome(MagicaModElements instance) {
		super(instance, 232);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}
	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder().downfall(0.5f).depth(0.1f).scale(0.2f).temperature(0.5f).precipitation(Biome.RainType.RAIN)
					.category(Biome.Category.TAIGA).waterColor(-6684826).waterFogColor(-6684826)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(),
							Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState())));
			setRegistryName("entia_biome");
			DefaultBiomeFeatures.addCarvers(this);
			DefaultBiomeFeatures.addMonsterRooms(this);
			DefaultBiomeFeatures.addStructures(this);
			DefaultBiomeFeatures.addOres(this);
			DefaultBiomeFeatures.addLakes(this);
			this.addStructure(Feature.STRONGHOLD, IFeatureConfig.NO_FEATURE_CONFIG);
			this.addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
			this.addStructure(Feature.PILLAGER_OUTPOST, new PillagerOutpostConfig(0.004D));
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.DEFAULT_FLOWER,
					IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(4)));
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS,
					new GrassFeatureConfig(Blocks.GRASS.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(4)));
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
					Biome.createDecoratedFeature(Feature.RANDOM_SELECTOR,
							new MultipleRandomFeatureConfig(new Feature[]{Feature.FANCY_TREE}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG},
									new float[]{0.1F}, Feature.NORMAL_TREE, IFeatureConfig.NO_FEATURE_CONFIG),
							Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(3, 0.1F, 1)));
			this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntEntity.entity, 40, 4, 12));
			this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(HyperEntEntity.entity, 5, 1, 1));
			this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.EVOKER_FANGS, 20, 4, 4));
			this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.LIGHTNING_BOLT, 20, 4, 4));
			this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 20, 4, 4));
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getGrassColor(BlockPos pos) {
			return -16764109;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getFoliageColor(BlockPos pos) {
			return -16764109;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getSkyColorByTemp(float currentTemperature) {
			return -13395712;
		}
	}
}

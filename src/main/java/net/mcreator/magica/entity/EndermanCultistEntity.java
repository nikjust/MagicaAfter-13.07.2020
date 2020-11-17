
package net.mcreator.magica.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;

import net.mcreator.magica.item.EndermanCultistMagicItem;
import net.mcreator.magica.MagicaModElements;

import java.util.Random;

import com.mojang.blaze3d.platform.GlStateManager;

@MagicaModElements.ModElement.Tag
public class EndermanCultistEntity extends MagicaModElements.ModElement {
	public static EntityType entity = null;
	public EndermanCultistEntity(MagicaModElements instance) {
		super(instance, 143);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(1.1f, 2.5f)).build("enderman_cultist")
						.setRegistryName("enderman_cultist");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -13434829, -10092442, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("enderman_cultist_spawn_egg"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("the_end")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 20, 4, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::func_223315_a);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new ModelEnderManCultist(), 1f) {
				{
					this.addLayer(new GlowingLayer<>(this));
				}
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magica:textures/endermancultist.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity implements IRangedAttackMob {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 85;
			setNoAI(false);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, false));
			this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 0.8));
			this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 20, 10) {
				@Override
				public boolean shouldContinueExecuting() {
					return this.shouldExecute();
				}
			});
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7);
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) {
			EndermanCultistMagicItem.shoot(this, target);
		}

		public void livingTick() {
			super.livingTick();
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
			Random random = this.rand;
			Entity entity = this;
			if (true)
				for (int l = 0; l < 4; ++l) {
					double d0 = (x + random.nextFloat());
					double d1 = (y + random.nextFloat());
					double d2 = (z + random.nextFloat());
					int i1 = random.nextInt(2) * 2 - 1;
					double d3 = (random.nextFloat() - 0.5D) * 0.5D;
					double d4 = (random.nextFloat() - 0.5D) * 0.5D;
					double d5 = (random.nextFloat() - 0.5D) * 0.5D;
					world.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
				}
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("magica:textures/endermancultist.png");
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(T entityIn, float l1, float l2, float l3, float l4, float l5, float l6, float l7) {
			this.bindTexture(GLOW_TEXTURE);
			GlStateManager.enableBlend();
			GlStateManager.disableAlphaTest();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
			if (entityIn.isInvisible())
				GlStateManager.depthMask(false);
			else
				GlStateManager.depthMask(true);
			int i = 61680;
			int j = i % 65536;
			int k = i / 65536;
			com.mojang.blaze3d.platform.GLX.glMultiTexCoord2f(com.mojang.blaze3d.platform.GLX.GL_TEXTURE1, (float) j, (float) k);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			GameRenderer gamerenderer = Minecraft.getInstance().gameRenderer;
			gamerenderer.setupFogColor(true);
			((EntityModel<T>) this.getEntityModel()).render(entityIn, l1, l2, l4, l5, l6, l7);
			gamerenderer.setupFogColor(false);
			i = entityIn.getBrightnessForRender();
			j = i % 65536;
			k = i / 65536;
			com.mojang.blaze3d.platform.GLX.glMultiTexCoord2f(com.mojang.blaze3d.platform.GLX.GL_TEXTURE1, (float) j, (float) k);
			this.func_215334_a(entityIn);
			GlStateManager.depthMask(true);
			GlStateManager.disableBlend();
			GlStateManager.enableAlphaTest();
		}

		public boolean shouldCombineTextures() {
			return false;
		}
	}

	// Made with Blockbench 3.6.2
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class ModelEnderManCultist extends EntityModel {
		private final RendererModel ArmL;
		private final RendererModel ArmR;
		private final RendererModel LegL;
		private final RendererModel LegR;
		private final RendererModel Head;
		private final RendererModel bone3;
		private final RendererModel bone;
		private final RendererModel bone2;
		private final RendererModel Body;
		public ModelEnderManCultist() {
			textureWidth = 128;
			textureHeight = 128;
			ArmL = new RendererModel(this);
			ArmL.setRotationPoint(4.0F, -15.0F, 0.0F);
			ArmL.cubeList.add(new ModelBox(ArmL, 118, 2, -1.0F, -1.0F, -1.0F, 2, 26, 2, 0.0F, false));
			ArmR = new RendererModel(this);
			ArmR.setRotationPoint(-4.0F, -15.0F, 0.0F);
			ArmR.cubeList.add(new ModelBox(ArmR, 110, 2, -1.0F, -1.0F, -1.0F, 2, 26, 2, 0.0F, false));
			LegL = new RendererModel(this);
			LegL.setRotationPoint(1.0F, 5.0F, 0.0F);
			LegL.cubeList.add(new ModelBox(LegL, 85, 8, -1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F, false));
			LegR = new RendererModel(this);
			LegR.setRotationPoint(-1.0F, 5.0F, 0.0F);
			LegR.cubeList.add(new ModelBox(LegR, 111, 31, -1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F, false));
			Head = new RendererModel(this);
			Head.setRotationPoint(0.0F, -19.0F, 0.0F);
			Head.cubeList.add(new ModelBox(Head, 86, 25, -3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F, false));
			bone3 = new RendererModel(this);
			bone3.setRotationPoint(0.0F, 0.0F, -4.0F);
			Head.addChild(bone3);
			bone3.cubeList.add(new ModelBox(bone3, 74, 24, -1.9094F, 0.0F, 0.0423F, 4, 3, 1, 0.0F, false));
			bone = new RendererModel(this);
			bone.setRotationPoint(-2.0F, -2.5F, -3.5F);
			Head.addChild(bone);
			setRotationAngle(bone, 0.0F, 0.4363F, 0.0F);
			bone.cubeList.add(new ModelBox(bone, 74, 10, -1.9094F, -2.5F, -0.4577F, 4, 5, 1, 0.0F, false));
			bone2 = new RendererModel(this);
			bone2.setRotationPoint(1.4F, -2.5F, -3.5F);
			Head.addChild(bone2);
			setRotationAngle(bone2, 0.0F, -0.4363F, 0.0F);
			bone2.cubeList.add(new ModelBox(bone2, 74, 17, -2.0F, -2.5F, -0.5F, 4, 5, 1, 0.0F, false));
			Body = new RendererModel(this);
			Body.setRotationPoint(0.0F, -6.0F, 0.0F);
			Body.cubeList.add(new ModelBox(Body, 94, 2, -3.0F, -10.0F, -1.0F, 6, 20, 2, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			ArmL.render(f5);
			ArmR.render(f5);
			LegL.render(f5);
			LegR.render(f5);
			Head.render(f5);
			Body.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.LegR.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.ArmR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.LegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.ArmL.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}

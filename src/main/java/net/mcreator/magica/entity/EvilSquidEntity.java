
package net.mcreator.magica.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
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

import net.mcreator.magica.procedures.EvilSquidOnEntityTickUpdateProcedure;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.platform.GlStateManager;

@MagicaModElements.ModElement.Tag
public class EvilSquidEntity extends MagicaModElements.ModElement {
	public static EntityType entity = null;
	public EvilSquidEntity(MagicaModElements instance) {
		super(instance, 131);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("evil_squid")
						.setRegistryName("evil_squid");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -6750208, -10092544, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("evil_squid_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelsquid3(), 0.5f) {
				{
					this.addLayer(new GlowingLayer<>(this));
				}
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magica:textures/squid2.png");
				}
			};
		});
	}
	public static class CustomEntity extends SquidEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 10;
			setNoAI(false);
			this.moveController = new MovementController(this) {
				@Override
				public void tick() {
					if (CustomEntity.this.areEyesInFluid(FluidTags.WATER))
						CustomEntity.this.setMotion(CustomEntity.this.getMotion().add(0, 0.005, 0));
					if (this.action == MovementController.Action.MOVE_TO && !CustomEntity.this.getNavigator().noPath()) {
						double dx = this.posX - CustomEntity.this.posX;
						double dy = this.posY - CustomEntity.this.posY;
						double dz = this.posZ - CustomEntity.this.posZ;
						dy = dy / (double) MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
						CustomEntity.this.rotationYaw = this.limitAngle(CustomEntity.this.rotationYaw,
								(float) (MathHelper.atan2(dz, dx) * (double) (180 / (float) Math.PI)) - 90, 90);
						CustomEntity.this.renderYawOffset = CustomEntity.this.rotationYaw;
						CustomEntity.this.setAIMoveSpeed(MathHelper.lerp(0.125f, CustomEntity.this.getAIMoveSpeed(),
								(float) (this.speed * CustomEntity.this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue())));
						CustomEntity.this.setMotion(CustomEntity.this.getMotion().add(0, CustomEntity.this.getAIMoveSpeed() * dy * 0.1, 0));
					} else {
						CustomEntity.this.setAIMoveSpeed(0);
					}
				}
			};
			this.navigator = new SwimmerPathNavigator(this, this.world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
			this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1, 40));
			this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
			this.goalSelector.addGoal(6, new FollowMobGoal(this, (float) 1, 10, 5));
			this.goalSelector.addGoal(7, new TemptGoal(this, 1, Ingredient.fromItems(new ItemStack(Items.INK_SAC, (int) (1)).getItem()), false));
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
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void baseTick() {
			super.baseTick();
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EvilSquidOnEntityTickUpdateProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK);
			this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(3D);
		}

		@Override
		public boolean canBreatheUnderwater() {
			return true;
		}

		@Override
		public boolean isNotColliding(IWorldReader worldreader) {
			return worldreader.checkNoEntityCollision(this);
		}

		@Override
		public boolean isPushedByWater() {
			return false;
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("magica:textures/squid2_gl.png");
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

	// Made with Blockbench 3.6.6
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class Modelsquid3 extends EntityModel {
		private final RendererModel body;
		private final RendererModel tentacle1;
		private final RendererModel tentacle2;
		private final RendererModel tentacle3;
		private final RendererModel tentacle4;
		private final RendererModel tentacle5;
		private final RendererModel tentacle6;
		private final RendererModel tentacle7;
		private final RendererModel tentacle8;
		public Modelsquid3() {
			textureWidth = 64;
			textureHeight = 32;
			body = new RendererModel(this);
			body.setRotationPoint(0.0F, -1.0F, 0.0F);
			body.cubeList.add(new ModelBox(body, 0, 0, -6.0F, -8.0F, -6.0F, 12, 16, 12, 0.0F, false));
			tentacle1 = new RendererModel(this);
			tentacle1.setRotationPoint(5.0F, 7.0F, 0.0F);
			body.addChild(tentacle1);
			setRotationAngle(tentacle1, 0.0F, 1.5708F, 0.0F);
			tentacle1.cubeList.add(new ModelBox(tentacle1, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));
			tentacle2 = new RendererModel(this);
			tentacle2.setRotationPoint(3.5F, 7.0F, 3.5F);
			body.addChild(tentacle2);
			setRotationAngle(tentacle2, 0.0F, 0.7854F, 0.0F);
			tentacle2.cubeList.add(new ModelBox(tentacle2, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));
			tentacle3 = new RendererModel(this);
			tentacle3.setRotationPoint(0.0F, 7.0F, 5.0F);
			body.addChild(tentacle3);
			tentacle3.cubeList.add(new ModelBox(tentacle3, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));
			tentacle4 = new RendererModel(this);
			tentacle4.setRotationPoint(-3.5F, 7.0F, 3.5F);
			body.addChild(tentacle4);
			setRotationAngle(tentacle4, 0.0F, -0.7854F, 0.0F);
			tentacle4.cubeList.add(new ModelBox(tentacle4, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));
			tentacle5 = new RendererModel(this);
			tentacle5.setRotationPoint(-5.0F, 7.0F, 0.0F);
			body.addChild(tentacle5);
			setRotationAngle(tentacle5, 0.0F, -1.5708F, 0.0F);
			tentacle5.cubeList.add(new ModelBox(tentacle5, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));
			tentacle6 = new RendererModel(this);
			tentacle6.setRotationPoint(-3.5F, 7.0F, -3.5F);
			body.addChild(tentacle6);
			setRotationAngle(tentacle6, 0.0F, -2.3562F, 0.0F);
			tentacle6.cubeList.add(new ModelBox(tentacle6, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));
			tentacle7 = new RendererModel(this);
			tentacle7.setRotationPoint(0.0F, 7.0F, -5.0F);
			body.addChild(tentacle7);
			setRotationAngle(tentacle7, 0.0F, -3.1416F, 0.0F);
			tentacle7.cubeList.add(new ModelBox(tentacle7, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));
			tentacle8 = new RendererModel(this);
			tentacle8.setRotationPoint(3.5F, 7.0F, -3.5F);
			body.addChild(tentacle8);
			setRotationAngle(tentacle8, 0.0F, -3.927F, 0.0F);
			tentacle8.cubeList.add(new ModelBox(tentacle8, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			body.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.tentacle1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.tentacle8.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tentacle6.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tentacle7.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.body.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.body.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.tentacle4.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tentacle5.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.tentacle2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tentacle3.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}

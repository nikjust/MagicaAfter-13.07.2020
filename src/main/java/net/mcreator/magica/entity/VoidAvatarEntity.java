
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
import net.minecraft.world.ServerBossInfo;
import net.minecraft.world.BossInfo;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.block.BlockState;

import net.mcreator.magica.procedures.VoidAvatarEntityDiesProcedure;
import net.mcreator.magica.MagicaModElements;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;

import com.mojang.blaze3d.platform.GlStateManager;

@MagicaModElements.ModElement.Tag
public class VoidAvatarEntity extends MagicaModElements.ModElement {
	public static EntityType entity = null;
	public VoidAvatarEntity(MagicaModElements instance) {
		super(instance, 146);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(3f, 3f)).build("void_avatar")
						.setRegistryName("void_avatar");
		elements.entities.add(() -> entity);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new ModelVoidAvatar(), 3f) {
				{
					this.addLayer(new GlowingLayer<>(this));
				}
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magica:textures/voidavatar.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			enablePersistence();
			this.moveController = new FlyingMovementController(this);
			this.navigator = new FlyingPathNavigator(this, this.world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new Goal() {
				{
					this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
				}
				public boolean shouldExecute() {
					if (CustomEntity.this.getAttackTarget() != null && !CustomEntity.this.getMoveHelper().isUpdating()) {
						return true;
					} else {
						return false;
					}
				}

				@Override
				public boolean shouldContinueExecuting() {
					return CustomEntity.this.getMoveHelper().isUpdating() && CustomEntity.this.getAttackTarget() != null
							&& CustomEntity.this.getAttackTarget().isAlive();
				}

				@Override
				public void startExecuting() {
					LivingEntity livingentity = CustomEntity.this.getAttackTarget();
					Vec3d vec3d = livingentity.getEyePosition(1);
					CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
				}

				@Override
				public void tick() {
					LivingEntity livingentity = CustomEntity.this.getAttackTarget();
					if (CustomEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
						CustomEntity.this.attackEntityAsMob(livingentity);
					} else {
						double d0 = CustomEntity.this.getDistanceSq(livingentity);
						if (d0 < 16) {
							Vec3d vec3d = livingentity.getEyePosition(1);
							CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
						}
					}
				}
			});
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.8, 20) {
				@Override
				protected Vec3d getPosition() {
					Random random = CustomEntity.this.getRNG();
					double dir_x = CustomEntity.this.posX + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = CustomEntity.this.posY + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = CustomEntity.this.posZ + ((random.nextFloat() * 2 - 1) * 16);
					return new Vec3d(dir_x, dir_y, dir_z);
				}
			});
			this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, MysteryCreeperEntity.CustomEntity.class, false, false));
			this.targetSelector.addGoal(6, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, false));
			this.targetSelector.addGoal(7, new HurtByTargetGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		@Override
		public double getMountedYOffset() {
			return super.getMountedYOffset() + 3;
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
		public void fall(float l, float d) {
		}

		@Override
		public void onDeath(DamageSource source) {
			super.onDeath(source);
			Entity sourceentity = source.getTrueSource();
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
				VoidAvatarEntityDiesProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
			if (this.getAttribute(SharedMonsterAttributes.FLYING_SPEED) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
			this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.3);
		}

		@Override
		public boolean isNonBoss() {
			return false;
		}
		private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.NOTCHED_6);
		@Override
		public void addTrackingPlayer(ServerPlayerEntity player) {
			super.addTrackingPlayer(player);
			this.bossInfo.addPlayer(player);
		}

		@Override
		public void removeTrackingPlayer(ServerPlayerEntity player) {
			super.removeTrackingPlayer(player);
			this.bossInfo.removePlayer(player);
		}

		@Override
		public void updateAITasks() {
			super.updateAITasks();
			this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}

		public void livingTick() {
			super.livingTick();
			this.setNoGravity(true);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("magica:textures/voidavatar.png");
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

	// Made with Blockbench 3.6.5
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class ModelVoidAvatar extends EntityModel {
		private final RendererModel Layer0;
		private final RendererModel Layer1;
		private final RendererModel Layer2;
		public ModelVoidAvatar() {
			textureWidth = 64;
			textureHeight = 64;
			Layer0 = new RendererModel(this);
			Layer0.setRotationPoint(0.0F, 16.0F, -0.3333F);
			Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -20.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
			Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -6.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
			Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, 8.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
			Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, 8.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
			Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -20.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
			Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -20.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
			Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, 8.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
			Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -6.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
			Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -6.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
			Layer1 = new RendererModel(this);
			Layer1.setRotationPoint(0.0F, 2.0F, -0.3333F);
			Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -20.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
			Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -6.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
			Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, 8.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
			Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, 8.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
			Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -20.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
			Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -20.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
			Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, 8.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
			Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -6.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
			Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -6.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
			Layer2 = new RendererModel(this);
			Layer2.setRotationPoint(0.0F, -12.0F, -0.3333F);
			Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -20.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
			Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -6.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
			Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, 8.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
			Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, 8.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
			Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -20.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
			Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -20.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
			Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, 8.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
			Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -6.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
			Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -6.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			Layer0.render(f5);
			Layer1.render(f5);
			Layer2.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.Layer0.rotateAngleY = f2;
			this.Layer2.rotateAngleY = f2 / 20.f;
		}
	}
}

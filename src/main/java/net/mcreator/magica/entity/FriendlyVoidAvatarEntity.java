
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.magica.item.StrangeCubeItem;
import net.mcreator.magica.MagicaModElements;

import java.util.Random;

import com.mojang.blaze3d.platform.GlStateManager;

@MagicaModElements.ModElement.Tag
public class FriendlyVoidAvatarEntity extends MagicaModElements.ModElement {
	public static EntityType entity = null;
	public FriendlyVoidAvatarEntity(MagicaModElements instance) {
		super(instance, 213);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(3f, 3f)).build("friendly_void_avatar")
						.setRegistryName("friendly_void_avatar");
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
					return new ResourceLocation("magica:textures/voidavatarcar.png");
				}
			};
		});
	}
	public static class CustomEntity extends TameableEntity {
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
			this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 0.8, 20) {
				@Override
				protected Vec3d getPosition() {
					Random random = CustomEntity.this.getRNG();
					double dir_x = CustomEntity.this.posX + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = CustomEntity.this.posY + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = CustomEntity.this.posZ + ((random.nextFloat() * 2 - 1) * 16);
					return new Vec3d(dir_x, dir_y, dir_z);
				}
			});
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
			return super.getMountedYOffset() + 2;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(StrangeCubeItem.block, (int) (1)));
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
		public boolean processInteract(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			boolean retval = true;
			Item item = itemstack.getItem();
			if (itemstack.getItem() instanceof SpawnEggItem) {
				retval = super.processInteract(sourceentity, hand);
			} else if (this.world.isRemote) {
				retval = this.isTamed() && this.isOwner(sourceentity) || this.isBreedingItem(itemstack);
			} else {
				if (this.isTamed()) {
					if (this.isOwner(sourceentity)) {
						if (item.isFood() && this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
							this.consumeItemFromStack(sourceentity, itemstack);
							this.heal((float) item.getFood().getHealing());
							retval = true;
						} else if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
							this.consumeItemFromStack(sourceentity, itemstack);
							this.heal(4);
							retval = true;
						} else {
							retval = super.processInteract(sourceentity, hand);
						}
					}
				} else if (this.isBreedingItem(itemstack)) {
					this.consumeItemFromStack(sourceentity, itemstack);
					if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, sourceentity)) {
						this.setTamedBy(sourceentity);
						this.world.setEntityState(this, (byte) 7);
					} else {
						this.world.setEntityState(this, (byte) 6);
					}
					this.enablePersistence();
					retval = true;
				} else {
					retval = super.processInteract(sourceentity, hand);
					if (retval)
						this.enablePersistence();
				}
			}
			sourceentity.startRiding(this);
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
			Entity entity = this;
			return retval;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
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
		public AgeableEntity createChild(AgeableEntity ageable) {
			return (CustomEntity) entity.create(this.world);
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			if (stack == null)
				return false;
			if (new ItemStack(Blocks.AIR, (int) (1)).getItem() == stack.getItem())
				return true;
			return false;
		}

		@Override
		public void travel(Vec3d dir) {
			Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
			if (this.isBeingRidden()) {
				this.rotationYaw = entity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch = entity.rotationPitch * 0.5F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.jumpMovementFactor = this.getAIMoveSpeed() * 0.15F;
				this.renderYawOffset = entity.rotationYaw;
				this.rotationYawHead = entity.rotationYaw;
				this.stepHeight = 1.0F;
				if (entity instanceof LivingEntity) {
					this.setAIMoveSpeed((float) this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
					float forward = ((LivingEntity) entity).moveForward;
					float strafe = ((LivingEntity) entity).moveStrafing;
					super.travel(new Vec3d(strafe, 0, forward));
				}
				this.prevLimbSwingAmount = this.limbSwingAmount;
				double d1 = this.posX - this.prevPosX;
				double d0 = this.posZ - this.prevPosZ;
				float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
				if (f1 > 1.0F)
					f1 = 1.0F;
				this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
				this.limbSwing += this.limbSwingAmount;
				return;
			}
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.travel(dir);
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
		private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("magica:textures/voidavatarcar.png");
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

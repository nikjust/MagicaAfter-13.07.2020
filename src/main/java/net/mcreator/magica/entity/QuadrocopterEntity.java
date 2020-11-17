
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.BlockState;

import net.mcreator.magica.procedures.QuadrocopterRightClickedOnEntityProcedure;
import net.mcreator.magica.itemgroup.MagicaItemGroup;
import net.mcreator.magica.MagicaModElements;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

@MagicaModElements.ModElement.Tag
public class QuadrocopterEntity extends MagicaModElements.ModElement {
	public static EntityType entity = null;
	public QuadrocopterEntity(MagicaModElements instance) {
		super(instance, 459);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("quadrocopter")
						.setRegistryName("quadrocopter");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -12763843, -7303024, new Item.Properties().group(MagicaItemGroup.tab))
				.setRegistryName("quadrocopter_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelquadrocopter(), 0.5f) {
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magica:textures/transmutation_table.png");
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
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(2, new OwnerHurtTargetGoal(this));
			this.goalSelector.addGoal(3, new OwnerHurtByTargetGoal(this));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 0.8, 20) {
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
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("sourceentity", sourceentity);
				QuadrocopterRightClickedOnEntityProcedure.executeProcedure($_dependencies);
			}
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
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
			if (this.getAttribute(SharedMonsterAttributes.FLYING_SPEED) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
			this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.3);
		}

		@Override
		public AgeableEntity createChild(AgeableEntity ageable) {
			CustomEntity retval = (CustomEntity) entity.create(this.world);
			retval.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(retval)), SpawnReason.BREEDING,
					(ILivingEntityData) null, (CompoundNBT) null);
			return retval;
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			if (stack == null)
				return false;
			return false;
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

	// Made with Blockbench 3.6.6
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class Modelquadrocopter extends EntityModel {
		private final RendererModel all;
		private final RendererModel heli4;
		private final RendererModel heli3;
		private final RendererModel heli2;
		private final RendererModel heli1;
		private final RendererModel bone;
		public Modelquadrocopter() {
			textureWidth = 16;
			textureHeight = 16;
			all = new RendererModel(this);
			all.setRotationPoint(-4.5F, 22.2F, -4.5F);
			heli4 = new RendererModel(this);
			heli4.setRotationPoint(9.0F, 0.0F, 9.0F);
			all.addChild(heli4);
			heli4.cubeList.add(new ModelBox(heli4, 0, 0, -2.5F, -0.2F, -0.5F, 5, 1, 1, 0.0F, false));
			heli4.cubeList.add(new ModelBox(heli4, 0, 0, -0.5F, -0.2F, -2.5F, 1, 1, 5, 0.0F, false));
			heli4.cubeList.add(new ModelBox(heli4, 0, 0, -6.0F, 1.6F, -7.0F, 3, 2, 2, 0.0F, false));
			heli3 = new RendererModel(this);
			heli3.setRotationPoint(9.0F, 0.0F, 0.0F);
			all.addChild(heli3);
			heli3.cubeList.add(new ModelBox(heli3, 0, 0, -2.5F, -0.2F, -0.5F, 5, 1, 1, 0.0F, false));
			heli3.cubeList.add(new ModelBox(heli3, 0, 0, -0.5F, -0.2F, -2.5F, 1, 1, 5, 0.0F, false));
			heli2 = new RendererModel(this);
			heli2.setRotationPoint(0.0F, 0.0F, 0.0F);
			all.addChild(heli2);
			heli2.cubeList.add(new ModelBox(heli2, 0, 0, -2.5F, -0.2F, -0.5F, 5, 1, 1, 0.0F, false));
			heli2.cubeList.add(new ModelBox(heli2, 0, 0, -0.5F, -0.2F, -2.5F, 1, 1, 5, 0.0F, false));
			heli1 = new RendererModel(this);
			heli1.setRotationPoint(0.0F, 0.0F, 9.0F);
			all.addChild(heli1);
			heli1.cubeList.add(new ModelBox(heli1, 0, 0, -2.5F, -0.2F, -0.5F, 5, 1, 1, 0.0F, false));
			heli1.cubeList.add(new ModelBox(heli1, 0, 0, -0.5F, -0.2F, -2.5F, 1, 1, 5, 0.0F, false));
			bone = new RendererModel(this);
			bone.setRotationPoint(4.5F, 1.8F, 4.5F);
			all.addChild(bone);
			setRotationAngle(bone, 0.0F, -0.7854F, 0.0F);
			bone.cubeList.add(new ModelBox(bone, 0, 0, -7.0F, -1.0F, -1.0F, 14, 1, 2, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 0, -1.0F, -1.0F, -7.0F, 2, 1, 14, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 0, -1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			all.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.all.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.all.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.heli3.rotateAngleY = f2;
			this.heli2.rotateAngleY = f2;
			this.heli4.rotateAngleY = f2;
			this.heli1.rotateAngleY = f2;
		}
	}
}

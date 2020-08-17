
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
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.material.Material;

import net.mcreator.magica.procedures.YsalamiriOnEntityTickUpdateProcedure;
import net.mcreator.magica.item.PureJungleLeavesItem;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.HashMap;

@MagicaModElements.ModElement.Tag
public class YsalamiriEntity extends MagicaModElements.ModElement {
	public static EntityType entity = null;
	public YsalamiriEntity(MagicaModElements instance) {
		super(instance, 218);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(1f, 0.2f)).build("ysalamiri")
						.setRegistryName("ysalamiri");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -26317, -3355444, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("ysalamiri"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 20, 4, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos,
						random) -> (world.getBlockState(pos.down()).getMaterial() == Material.ORGANIC && world.getLightSubtracted(pos, 0) > 8));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new ModelYsalamiri(), 0.5f) {
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magica:textures/ysalamiri_texture.png");
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
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1,
					new TemptGoal(this, 1, Ingredient.fromItems(new ItemStack(PureJungleLeavesItem.block, (int) (1)).getItem()), false));
			this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(4, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(6, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.ARTHROPOD;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
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
			return retval;
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
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				YsalamiriOnEntityTickUpdateProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}

		@Override
		public AgeableEntity createChild(AgeableEntity ageable) {
			return (CustomEntity) entity.create(this.world);
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			if (stack == null)
				return false;
			if (new ItemStack(PureJungleLeavesItem.block, (int) (1)).getItem() == stack.getItem())
				return true;
			return false;
		}
	}

	// Made with Blockbench 3.6.5
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class ModelYsalamiri extends EntityModel {
		private final RendererModel head;
		private final RendererModel body;
		private final RendererModel LegL;
		private final RendererModel LegR;
		private final RendererModel ArmL;
		private final RendererModel ArmR;
		private final RendererModel Tail;
		private final RendererModel Tail1;
		private final RendererModel tail2;
		private final RendererModel Tail3;
		public ModelYsalamiri() {
			textureWidth = 32;
			textureHeight = 32;
			head = new RendererModel(this);
			head.setRotationPoint(0.0F, 24.0F, 0.0F);
			head.cubeList.add(new ModelBox(head, 20, 18, -1.0F, -3.0F, -6.0F, 2, 2, 3, 0.0F, false));
			body = new RendererModel(this);
			body.setRotationPoint(0.0F, 24.0F, 0.0F);
			body.cubeList.add(new ModelBox(body, 1, 24, -1.0F, -3.0F, 2.0F, 2, 2, 4, 0.0F, true));
			body.cubeList.add(new ModelBox(body, 13, 23, -2.0F, -3.0F, -3.0F, 4, 2, 5, 0.0F, false));
			LegL = new RendererModel(this);
			LegL.setRotationPoint(0.0F, 24.0F, 0.0F);
			LegL.cubeList.add(new ModelBox(LegL, 27, 24, 0.5F, -2.0F, 3.0F, 1, 2, 1, 0.0F, false));
			LegR = new RendererModel(this);
			LegR.setRotationPoint(0.0F, 24.0F, 0.0F);
			LegR.cubeList.add(new ModelBox(LegR, 10, 24, -1.5F, -2.0F, 3.0F, 1, 2, 1, 0.0F, false));
			ArmL = new RendererModel(this);
			ArmL.setRotationPoint(0.0F, 24.0F, 0.0F);
			ArmL.cubeList.add(new ModelBox(ArmL, 16, 24, 1.5F, -2.0F, -3.0F, 1, 2, 1, 0.0F, false));
			ArmR = new RendererModel(this);
			ArmR.setRotationPoint(0.0F, 24.0F, 0.0F);
			ArmR.cubeList.add(new ModelBox(ArmR, 2, 24, -2.5F, -2.0F, -3.0F, 1, 2, 1, 0.0F, false));
			Tail = new RendererModel(this);
			Tail.setRotationPoint(0.0F, 23.25F, 6.5F);
			Tail.cubeList.add(new ModelBox(Tail, 4, 4, -0.5F, -1.25F, -0.5F, 1, 1, 2, 0.0F, false));
			Tail1 = new RendererModel(this);
			Tail1.setRotationPoint(0.0F, 0.0F, 2.0F);
			Tail.addChild(Tail1);
			Tail1.cubeList.add(new ModelBox(Tail1, 10, 4, -0.5F, -1.25F, -0.5F, 1, 1, 2, 0.0F, false));
			tail2 = new RendererModel(this);
			tail2.setRotationPoint(0.0F, 0.0F, 2.0F);
			Tail1.addChild(tail2);
			tail2.cubeList.add(new ModelBox(tail2, 16, 4, -0.5F, -1.25F, -0.5F, 1, 1, 2, 0.0F, false));
			Tail3 = new RendererModel(this);
			Tail3.setRotationPoint(0.0F, -0.75F, 2.0F);
			tail2.addChild(Tail3);
			Tail3.cubeList.add(new ModelBox(Tail3, 22, 4, -0.5F, -0.5F, -0.5F, 1, 1, 2, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			head.render(f5);
			body.render(f5);
			LegL.render(f5);
			LegR.render(f5);
			ArmL.render(f5);
			ArmR.render(f5);
			Tail.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.LegR.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.tail2.rotateAngleY = f4 / (180F / (float) Math.PI);
			this.Tail.rotateAngleY = f4 / (180F / (float) Math.PI);
			this.ArmR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.LegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Tail1.rotateAngleY = f4 / (180F / (float) Math.PI);
			this.Tail3.rotateAngleY = f4 / (180F / (float) Math.PI);
			this.ArmL.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}


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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ILivingEntityData;
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
import net.minecraft.block.Blocks;

import net.mcreator.magica.procedures.VornskrRightClickedOnEntityProcedure;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.HashMap;

@MagicaModElements.ModElement.Tag
public class VornskrEntity extends MagicaModElements.ModElement {
	public static EntityType entity = null;
	public VornskrEntity(MagicaModElements instance) {
		super(instance, 119);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(1.5f, 1.1f)).build("vornskr")
						.setRegistryName("vornskr");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -13421773, -3407872, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("vornskr_spawn_egg"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 20, 4, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::func_223315_a);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new ModelVornskr(), 0.5f) {
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magica:textures/vornskr2.png");
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
			experienceValue = 50;
			setNoAI(false);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, YsalamiriEntity.CustomEntity.class, false, false));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, false));
			this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(6, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(8, new SwimGoal(this));
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
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			return super.attackEntityFrom(source, amount);
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
				VornskrRightClickedOnEntityProcedure.executeProcedure($_dependencies);
			}
			return retval;
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
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
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
			if (new ItemStack(Blocks.ANVIL, (int) (1)).getItem() == stack.getItem())
				return true;
			if (new ItemStack(Items.IRON_SWORD, (int) (1)).getItem() == stack.getItem())
				return true;
			return false;
		}
	}

	// Made with Blockbench 3.6.5
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class ModelVornskr extends EntityModel {
		private final RendererModel Head;
		private final RendererModel LegL;
		private final RendererModel LegR;
		private final RendererModel ArmL;
		private final RendererModel ArmR;
		private final RendererModel Body;
		private final RendererModel tail;
		private final RendererModel tail2;
		private final RendererModel tail3;
		private final RendererModel tail4;
		private final RendererModel Tail5;
		public ModelVornskr() {
			textureWidth = 64;
			textureHeight = 64;
			Head = new RendererModel(this);
			Head.setRotationPoint(0.0F, 6.25F, -10.35F);
			Head.cubeList.add(new ModelBox(Head, 46, 30, -2.0F, 0.75F, -8.65F, 4, 3, 4, 0.0F, false));
			Head.cubeList.add(new ModelBox(Head, 39, 6, -3.0F, -1.25F, -4.65F, 6, 5, 6, 0.0F, false));
			Head.cubeList.add(new ModelBox(Head, 47, 1, 3.0F, -2.25F, 0.65F, 1, 1, 3, 0.0F, false));
			Head.cubeList.add(new ModelBox(Head, 55, 1, -4.0F, -2.25F, 0.65F, 1, 1, 3, 0.0F, false));
			LegL = new RendererModel(this);
			LegL.setRotationPoint(4.0F, 13.5F, 10.0F);
			LegL.cubeList.add(new ModelBox(LegL, 22, 46, -1.0F, -0.5F, -1.0F, 2, 11, 2, 0.0F, false));
			LegR = new RendererModel(this);
			LegR.setRotationPoint(-4.0F, 13.5F, 10.0F);
			LegR.cubeList.add(new ModelBox(LegR, 22, 46, -1.0F, -0.5F, -1.0F, 2, 11, 2, 0.0F, false));
			ArmL = new RendererModel(this);
			ArmL.setRotationPoint(4.0F, 13.5F, -10.0F);
			ArmL.cubeList.add(new ModelBox(ArmL, 22, 46, -1.0F, -0.5F, -1.0F, 2, 11, 2, 0.0F, false));
			ArmR = new RendererModel(this);
			ArmR.setRotationPoint(-4.0F, 13.5F, -10.0F);
			ArmR.cubeList.add(new ModelBox(ArmR, 22, 46, -1.0F, -0.5F, -1.0F, 2, 11, 2, 0.0F, false));
			Body = new RendererModel(this);
			Body.setRotationPoint(0.0F, 9.5F, 0.0F);
			Body.cubeList.add(new ModelBox(Body, 0, 0, -5.0F, -3.5F, -11.0F, 10, 7, 22, 0.0F, false));
			tail = new RendererModel(this);
			tail.setRotationPoint(-0.25F, 8.25F, 11.5F);
			tail.cubeList.add(new ModelBox(tail, 4, 38, -1.75F, -1.25F, -0.5F, 3, 3, 4, 0.0F, false));
			tail2 = new RendererModel(this);
			tail2.setRotationPoint(0.0F, 0.0F, 4.0F);
			tail.addChild(tail2);
			tail2.cubeList.add(new ModelBox(tail2, 4, 38, -1.75F, -1.25F, -0.5F, 3, 3, 4, 0.0F, false));
			tail3 = new RendererModel(this);
			tail3.setRotationPoint(0.0F, 0.25F, 4.0F);
			tail2.addChild(tail3);
			tail3.cubeList.add(new ModelBox(tail3, 18, 39, -0.75F, -0.5F, -0.5F, 1, 2, 4, 0.0F, false));
			tail4 = new RendererModel(this);
			tail4.setRotationPoint(0.0F, -0.05F, 4.0F);
			tail3.addChild(tail4);
			tail4.cubeList.add(new ModelBox(tail4, 28, 40, -0.75F, -0.45F, -0.5F, 1, 1, 4, 0.0F, false));
			Tail5 = new RendererModel(this);
			Tail5.setRotationPoint(-0.25F, 0.05F, 4.5F);
			tail4.addChild(Tail5);
			Tail5.cubeList.add(new ModelBox(Tail5, 37, 40, -0.5F, -0.5F, -1.0F, 1, 1, 4, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			Head.render(f5);
			LegL.render(f5);
			LegR.render(f5);
			ArmL.render(f5);
			ArmR.render(f5);
			Body.render(f5);
			tail.render(f5);
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
			this.tail2.rotateAngleX = f3 / (180F / (float) Math.PI);
			this.tail3.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.tail4.rotateAngleY = f4 / (180F / (float) Math.PI);
			this.tail.rotateAngleY = f4 / (180F / (float) Math.PI);
			this.Tail5.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.ArmR.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.LegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.ArmL.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}

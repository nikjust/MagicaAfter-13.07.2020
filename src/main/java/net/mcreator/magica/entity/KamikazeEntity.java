
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.BlockState;

import net.mcreator.magica.procedures.KamikazePlayerCollidesWithThisEntityProcedure;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.HashMap;

@MagicaModElements.ModElement.Tag
public class KamikazeEntity extends MagicaModElements.ModElement {
	public static EntityType entity = null;
	public KamikazeEntity(MagicaModElements instance) {
		super(instance, 82);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.95f)).build("kamikaze")
						.setRegistryName("kamikaze");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -1, -1, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("kamikaze"));
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
			return new MobRenderer(renderManager, new Modelpufferfish(), 0.5f) {
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magica:textures/kamikaze.png");
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
			this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, false));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
			this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(7, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
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
		public void fall(float l, float d) {
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void onCollideWithPlayer(PlayerEntity sourceentity) {
			super.onCollideWithPlayer(sourceentity);
			Entity entity = this;
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				KamikazePlayerCollidesWithThisEntityProcedure.executeProcedure($_dependencies);
			}
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

	// Made with Blockbench 3.5.2
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class Modelpufferfish extends EntityModel {
		private final RendererModel body_large;
		private final RendererModel leftFin;
		private final RendererModel rightFin;
		private final RendererModel spines_top_front;
		private final RendererModel spines_top_mid;
		private final RendererModel spines_top_back;
		private final RendererModel spines_bottom_front;
		private final RendererModel spines_bottom_mid;
		private final RendererModel spines_bottom_back;
		private final RendererModel spines_left_front;
		private final RendererModel spines_left_mid;
		private final RendererModel spines_left_back;
		private final RendererModel spines_right_front;
		private final RendererModel spines_right_mid;
		private final RendererModel spines_right_back;
		public Modelpufferfish() {
			textureWidth = 32;
			textureHeight = 32;
			body_large = new RendererModel(this);
			body_large.setRotationPoint(0.0F, 24.0F, 0.0F);
			body_large.cubeList.add(new ModelBox(body_large, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));
			leftFin = new RendererModel(this);
			leftFin.setRotationPoint(4.0F, -7.0F, 3.0F);
			body_large.addChild(leftFin);
			leftFin.cubeList.add(new ModelBox(leftFin, 24, 3, 0.0F, 0.0F, -5.9904F, 2, 1, 2, 0.0F, false));
			rightFin = new RendererModel(this);
			rightFin.setRotationPoint(-4.0F, -7.0F, 1.0F);
			body_large.addChild(rightFin);
			rightFin.cubeList.add(new ModelBox(rightFin, 24, 0, -1.9968F, 0.0F, -3.992F, 2, 1, 2, 0.0F, false));
			spines_top_front = new RendererModel(this);
			spines_top_front.setRotationPoint(-4.0F, -8.0F, -4.0F);
			body_large.addChild(spines_top_front);
			setRotationAngle(spines_top_front, 0.7854F, 0.0F, 0.0F);
			spines_top_front.cubeList.add(new ModelBox(spines_top_front, 14, 16, 0.0F, -1.0F, 0.0F, 8, 1, 1, 0.0F, false));
			spines_top_mid = new RendererModel(this);
			spines_top_mid.setRotationPoint(0.0F, -8.0F, 0.0F);
			body_large.addChild(spines_top_mid);
			spines_top_mid.cubeList.add(new ModelBox(spines_top_mid, 14, 16, -4.0F, -1.0F, 0.0F, 8, 1, 1, 0.0F, false));
			spines_top_back = new RendererModel(this);
			spines_top_back.setRotationPoint(0.0F, -8.0F, 4.0F);
			body_large.addChild(spines_top_back);
			setRotationAngle(spines_top_back, -0.7854F, 0.0F, 0.0F);
			spines_top_back.cubeList.add(new ModelBox(spines_top_back, 14, 16, -4.0F, -1.0F, 0.0F, 8, 1, 1, 0.0F, false));
			spines_bottom_front = new RendererModel(this);
			spines_bottom_front.setRotationPoint(0.0F, 0.0F, -4.0F);
			body_large.addChild(spines_bottom_front);
			setRotationAngle(spines_bottom_front, -0.7854F, 0.0F, 0.0F);
			spines_bottom_front.cubeList.add(new ModelBox(spines_bottom_front, 14, 19, -4.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F, false));
			spines_bottom_mid = new RendererModel(this);
			spines_bottom_mid.setRotationPoint(0.0F, 1.0F, 0.0F);
			body_large.addChild(spines_bottom_mid);
			spines_bottom_mid.cubeList.add(new ModelBox(spines_bottom_mid, 14, 19, -4.0F, -1.0F, 0.0F, 8, 1, 1, 0.0F, false));
			spines_bottom_back = new RendererModel(this);
			spines_bottom_back.setRotationPoint(0.0F, 0.0F, 4.0F);
			body_large.addChild(spines_bottom_back);
			setRotationAngle(spines_bottom_back, 0.7854F, 0.0F, 0.0F);
			spines_bottom_back.cubeList.add(new ModelBox(spines_bottom_back, 14, 19, -4.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F, false));
			spines_left_front = new RendererModel(this);
			spines_left_front.setRotationPoint(4.0F, 0.0F, -4.0F);
			body_large.addChild(spines_left_front);
			setRotationAngle(spines_left_front, 0.0F, 0.7854F, 0.0F);
			spines_left_front.cubeList.add(new ModelBox(spines_left_front, 0, 16, 0.0F, -8.0F, 0.0F, 1, 8, 1, 0.0F, false));
			spines_left_mid = new RendererModel(this);
			spines_left_mid.setRotationPoint(4.0F, 0.0F, 0.0F);
			body_large.addChild(spines_left_mid);
			spines_left_mid.cubeList.add(new ModelBox(spines_left_mid, 4, 16, 0.0F, -8.0F, 0.0F, 1, 8, 1, 0.0F, true));
			spines_left_back = new RendererModel(this);
			spines_left_back.setRotationPoint(4.0F, 0.0F, 4.0F);
			body_large.addChild(spines_left_back);
			setRotationAngle(spines_left_back, 0.0F, -0.7854F, 0.0F);
			spines_left_back.cubeList.add(new ModelBox(spines_left_back, 8, 16, 0.0F, -8.0F, 0.0F, 1, 8, 1, 0.0F, true));
			spines_right_front = new RendererModel(this);
			spines_right_front.setRotationPoint(-4.0F, 0.0F, -4.0F);
			body_large.addChild(spines_right_front);
			setRotationAngle(spines_right_front, 0.0F, -0.7854F, 0.0F);
			spines_right_front.cubeList.add(new ModelBox(spines_right_front, 4, 16, -1.0F, -8.0F, 0.0F, 1, 8, 1, 0.0F, false));
			spines_right_mid = new RendererModel(this);
			spines_right_mid.setRotationPoint(-4.0F, 0.0F, 0.0F);
			body_large.addChild(spines_right_mid);
			spines_right_mid.cubeList.add(new ModelBox(spines_right_mid, 8, 16, -1.0F, -8.0F, 0.0F, 1, 8, 1, 0.0F, false));
			spines_right_back = new RendererModel(this);
			spines_right_back.setRotationPoint(-4.0F, 0.0F, 4.0F);
			body_large.addChild(spines_right_back);
			setRotationAngle(spines_right_back, 0.0F, 0.7854F, 0.0F);
			spines_right_back.cubeList.add(new ModelBox(spines_right_back, 8, 16, -1.0F, -8.0F, 0.0F, 1, 8, 1, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			body_large.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
		}
	}
}

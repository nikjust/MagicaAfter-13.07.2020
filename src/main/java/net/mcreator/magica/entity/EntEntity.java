
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
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
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

import net.mcreator.magica.MagicaModElements;

@MagicaModElements.ModElement.Tag
public class EntEntity extends MagicaModElements.ModElement {
	public static EntityType entity = null;
	public EntEntity(MagicaModElements instance) {
		super(instance, 105);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("ent")
						.setRegistryName("ent");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -16724992, -13421824, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("ent"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("magica:entia_biome")))
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
			return new MobRenderer(renderManager, new Modelent(), 0.5f) {
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magica:textures/ent.png");
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
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, VillagerEntity.class, false, false));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, false));
			this.goalSelector.addGoal(5, new BreakDoorGoal(this, e -> true));
			this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(8, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(9, new SwimGoal(this));
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
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10);
		}
	}

	// Made with Blockbench 3.5.2
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class Modelent extends EntityModel {
		private final RendererModel Head;
		private final RendererModel Head0;
		private final RendererModel Head1;
		private final RendererModel bone2;
		private final RendererModel bone3;
		private final RendererModel Rarm;
		private final RendererModel Larm;
		private final RendererModel Rleg;
		private final RendererModel Lleg;
		private final RendererModel Body;
		public Modelent() {
			textureWidth = 128;
			textureHeight = 128;
			Head = new RendererModel(this);
			Head.setRotationPoint(-0.0333F, 1.6667F, -0.0333F);
			Head.cubeList.add(new ModelBox(Head, 116, 0, -1.5667F, -5.6667F, -1.5667F, 3, 6, 3, 0.0F, false));
			Head0 = new RendererModel(this);
			Head0.setRotationPoint(-0.0667F, -6.6667F, -1.4667F);
			Head.addChild(Head0);
			setRotationAngle(Head0, 0.6981F, 0.0F, 0.0F);
			Head0.cubeList.add(new ModelBox(Head0, 116, 0, -1.5F, -3.0F, -1.5F, 3, 6, 3, 0.0F, false));
			Head1 = new RendererModel(this);
			Head1.setRotationPoint(0.0333F, 22.3333F, 0.0333F);
			Head.addChild(Head1);
			Head1.cubeList.add(new ModelBox(Head1, 0, 0, 3.0F, -37.0F, -12.0F, 3, 3, 3, 0.0F, false));
			Head1.cubeList.add(new ModelBox(Head1, 0, 0, -5.0F, -38.0F, -12.0F, 3, 3, 3, 0.0F, false));
			bone2 = new RendererModel(this);
			bone2.setRotationPoint(2.5F, -32.5F, -6.5F);
			Head1.addChild(bone2);
			setRotationAngle(bone2, 0.0F, -0.7854F, -1.1345F);
			bone2.cubeList.add(new ModelBox(bone2, 0, 68, -0.5F, -0.5F, -4.5F, 1, 1, 9, 0.0F, false));
			bone3 = new RendererModel(this);
			bone3.setRotationPoint(-2.5F, -32.5F, -6.5F);
			Head1.addChild(bone3);
			setRotationAngle(bone3, 0.0F, 0.7854F, 1.1345F);
			bone3.cubeList.add(new ModelBox(bone3, 0, 68, -0.5F, -0.5F, -4.5F, 1, 1, 9, 0.0F, false));
			Rarm = new RendererModel(this);
			Rarm.setRotationPoint(-6.0F, 4.5F, 0.0F);
			Rarm.cubeList.add(new ModelBox(Rarm, 112, 113, -2.0F, -2.5F, -2.0F, 4, 11, 4, 0.0F, false));
			Larm = new RendererModel(this);
			Larm.setRotationPoint(6.0F, 4.5F, 0.0F);
			Larm.cubeList.add(new ModelBox(Larm, 112, 113, -2.0F, -2.5F, -2.0F, 4, 11, 4, 0.0F, false));
			Rleg = new RendererModel(this);
			Rleg.setRotationPoint(-2.0F, 15.5F, 0.0F);
			Rleg.cubeList.add(new ModelBox(Rleg, 112, 113, -2.0F, -2.5F, -2.0F, 4, 11, 4, 0.0F, false));
			Lleg = new RendererModel(this);
			Lleg.setRotationPoint(2.0F, 15.5F, 0.0F);
			Lleg.cubeList.add(new ModelBox(Lleg, 112, 113, -2.0F, -2.5F, -2.0F, 4, 11, 4, 0.0F, false));
			Body = new RendererModel(this);
			Body.setRotationPoint(0.0F, 7.5F, 0.0F);
			Body.cubeList.add(new ModelBox(Body, 0, 113, -4.0F, -5.5F, -2.0F, 8, 11, 4, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			Head.render(f5);
			Rarm.render(f5);
			Larm.render(f5);
			Rleg.render(f5);
			Lleg.render(f5);
			Body.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.Larm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.Lleg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Rleg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.Rarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		}
	}
}

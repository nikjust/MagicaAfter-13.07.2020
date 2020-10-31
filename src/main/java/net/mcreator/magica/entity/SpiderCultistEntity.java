
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
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.magica.procedures.SpiderCultistEntityDiesProcedure;
import net.mcreator.magica.item.SpiderCultistWeaponItem;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;
import java.util.HashMap;

@MagicaModElements.ModElement.Tag
public class SpiderCultistEntity extends MagicaModElements.ModElement {
	public static EntityType entity = null;
	public SpiderCultistEntity(MagicaModElements instance) {
		super(instance, 188);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(1.0999999999999999f, 2f))
						.build("spider_cultist").setRegistryName("spider_cultist");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -16777114, -10066330, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("spider_cultist_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelspidercultist(), 0.5f) {
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magica:textures/spidercultist.png");
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
			experienceValue = 100;
			setNoAI(false);
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
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(4, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(6, new SwimGoal(this));
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
				SpiderCultistEntityDiesProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10);
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) {
			SpiderCultistWeaponItem.shoot(this, target);
		}
	}

	// Made with Blockbench 3.6.2
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class Modelspidercultist extends EntityModel {
		private final RendererModel armL;
		private final RendererModel ArmR;
		private final RendererModel ArmL1;
		private final RendererModel ArmR1;
		private final RendererModel ArmL2;
		private final RendererModel ArmR2;
		private final RendererModel Body;
		private final RendererModel Head;
		private final RendererModel LegR;
		private final RendererModel LegL;
		public Modelspidercultist() {
			textureWidth = 128;
			textureHeight = 128;
			armL = new RendererModel(this);
			armL.setRotationPoint(-5.0F, 5.0F, 0.0F);
			armL.cubeList.add(new ModelBox(armL, 8, 14, -1.0F, -1.0F, -1.0F, 2, 10, 2, 0.0F, false));
			ArmR = new RendererModel(this);
			ArmR.setRotationPoint(5.0F, 5.0F, 0.0F);
			ArmR.cubeList.add(new ModelBox(ArmR, 0, 14, -1.0F, -1.0F, -1.0F, 2, 10, 2, 0.0F, false));
			ArmL1 = new RendererModel(this);
			ArmL1.setRotationPoint(-2.25F, 5.0F, -3.0F);
			ArmL1.cubeList.add(new ModelBox(ArmL1, 57, 0, -3.75F, -1.0F, -1.0F, 5, 2, 2, 0.0F, false));
			ArmL1.cubeList.add(new ModelBox(ArmL1, 48, 15, -5.75F, -1.0F, -1.0F, 2, 10, 2, 0.0F, false));
			ArmR1 = new RendererModel(this);
			ArmR1.setRotationPoint(2.25F, 5.0F, -3.0F);
			ArmR1.cubeList.add(new ModelBox(ArmR1, 39, 15, 3.75F, -1.0F, -1.0F, 2, 10, 2, 0.0F, false));
			ArmR1.cubeList.add(new ModelBox(ArmR1, 58, 4, -1.25F, -1.0F, -1.0F, 5, 2, 2, 0.0F, false));
			ArmL2 = new RendererModel(this);
			ArmL2.setRotationPoint(-0.5F, 9.0F, -5.0F);
			ArmL2.cubeList.add(new ModelBox(ArmL2, 20, 26, -7.5F, -1.0F, -1.0F, 8, 2, 2, 0.0F, false));
			ArmL2.cubeList.add(new ModelBox(ArmL2, 0, 32, -9.5F, -1.0F, -1.0F, 2, 10, 2, 0.0F, false));
			ArmR2 = new RendererModel(this);
			ArmR2.setRotationPoint(1.5F, 9.0F, -5.0F);
			ArmR2.cubeList.add(new ModelBox(ArmR2, 8, 31, 6.5F, -1.0F, -1.0F, 2, 10, 2, 0.0F, false));
			ArmR2.cubeList.add(new ModelBox(ArmR2, 0, 27, -1.5F, -1.0F, -1.0F, 8, 2, 2, 0.0F, false));
			Body = new RendererModel(this);
			Body.setRotationPoint(0.0F, 9.0F, -1.5F);
			Body.cubeList.add(new ModelBox(Body, 33, 0, -4.0F, -5.0F, -0.5F, 8, 10, 4, 0.0F, false));
			Body.cubeList.add(new ModelBox(Body, 16, 32, -1.0F, -1.0F, -2.5F, 2, 2, 2, 0.0F, false));
			Head = new RendererModel(this);
			Head.setRotationPoint(-0.05F, 1.5F, 1.25F);
			Head.cubeList.add(new ModelBox(Head, 15, 15, -2.45F, -4.5F, -3.25F, 5, 7, 4, 0.0F, false));
			Head.cubeList.add(new ModelBox(Head, 34, 15, -0.55F, 0.0F, 0.75F, 1, 2, 1, 0.0F, false));
			LegR = new RendererModel(this);
			LegR.setRotationPoint(2.0F, 15.0F, 0.0F);
			LegR.cubeList.add(new ModelBox(LegR, 16, 0, -2.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F, false));
			LegL = new RendererModel(this);
			LegL.setRotationPoint(-2.0F, 15.0F, 0.0F);
			LegL.cubeList.add(new ModelBox(LegL, 0, 0, -2.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			armL.render(f5);
			ArmR.render(f5);
			ArmL1.render(f5);
			ArmR1.render(f5);
			ArmL2.render(f5);
			ArmR2.render(f5);
			Body.render(f5);
			Head.render(f5);
			LegR.render(f5);
			LegL.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.LegR.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.ArmR1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.ArmR2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.ArmL1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.ArmR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.LegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.ArmL2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.armL.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}

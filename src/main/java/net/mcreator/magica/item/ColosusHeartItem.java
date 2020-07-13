
package net.mcreator.magica.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ActionResult;
import net.minecraft.network.IPacket;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.block.Blocks;

import net.mcreator.magica.procedures.ColosusHeartBulletHitsPlayerProcedure;
import net.mcreator.magica.procedures.ColosusHeartBulletHitsBlockProcedure;
import net.mcreator.magica.MagicaModElements;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.platform.GlStateManager;

@MagicaModElements.ModElement.Tag
public class ColosusHeartItem extends MagicaModElements.ModElement {
	@ObjectHolder("magica:colosus_heart")
	public static final Item block = null;
	@ObjectHolder("magica:entitybulletcolosus_heart")
	public static final EntityType arrow = null;
	public ColosusHeartItem(MagicaModElements instance) {
		super(instance, 84);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemRanged());
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
				.size(0.5f, 0.5f)).build("entitybulletcolosus_heart").setRegistryName("entitybulletcolosus_heart"));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void init(FMLCommonSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ArrowCustomEntity.class, renderManager -> new CustomRender(renderManager));
	}
	public static class ItemRanged extends Item {
		public ItemRanged() {
			super(new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1));
			setRegistryName("colosus_heart");
		}

		@Override
		public UseAction getUseAction(ItemStack stack) {
			return UseAction.BOW;
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			entity.setActiveHand(hand);
			return new ActionResult(ActionResultType.SUCCESS, entity.getHeldItem(hand));
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 72000;
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}

		@Override
		public void onPlayerStoppedUsing(ItemStack itemstack, World world, LivingEntity entityLiving, int timeLeft) {
			if (!world.isRemote && entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;
				double x = entity.posX;
				double y = entity.posY;
				double z = entity.posZ;
				if (true) {
					ArrowCustomEntity entityarrow = shoot(world, entity, random, 1f, 5, 5);
					itemstack.damageItem(1, entity, e -> e.sendBreakAnimation(entity.getActiveHand()));
					entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
				}
			}
		}
	}

	@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
	public static class ArrowCustomEntity extends AbstractArrowEntity implements IRendersAsItem {
		public ArrowCustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			super(arrow, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, World world) {
			super(type, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, double x, double y, double z, World world) {
			super(type, x, y, z, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, LivingEntity entity, World world) {
			super(type, entity, world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack getItem() {
			return new ItemStack(Blocks.REDSTONE_BLOCK, (int) (1));
		}

		@Override
		protected ItemStack getArrowStack() {
			return null;
		}

		@Override
		public void onCollideWithPlayer(PlayerEntity entity) {
			super.onCollideWithPlayer(entity);
			Entity sourceentity = this.getShooter();
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
			World world = this.world;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("sourceentity", sourceentity);
				ColosusHeartBulletHitsPlayerProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void arrowHit(LivingEntity entity) {
			super.arrowHit(entity);
			entity.setArrowCountInEntity(entity.getArrowCountInEntity() - 1);
			Entity sourceentity = this.getShooter();
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
			World world = this.world;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("sourceentity", sourceentity);
				ColosusHeartBulletHitsPlayerProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public void tick() {
			super.tick();
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
			World world = this.world;
			Entity entity = this.getShooter();
			if (this.inGround) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					ColosusHeartBulletHitsBlockProcedure.executeProcedure($_dependencies);
				}
				this.remove();
			}
		}
	}

	public static class CustomRender extends EntityRenderer<ArrowCustomEntity> {
		private static final ResourceLocation texture = new ResourceLocation("magica:textures/collosusheart.png");
		public CustomRender(EntityRendererManager renderManager) {
			super(renderManager);
		}

		@Override
		public void doRender(ArrowCustomEntity bullet, double d, double d1, double d2, float f, float f1) {
			this.bindEntityTexture(bullet);
			GlStateManager.pushMatrix();
			GlStateManager.translatef((float) d, (float) d1, (float) d2);
			GlStateManager.rotatef(f, 0, 1, 0);
			GlStateManager.rotatef(90f - bullet.prevRotationPitch - (bullet.rotationPitch - bullet.prevRotationPitch) * f1, 1, 0, 0);
			EntityModel model = new ModelCollosusHeart();
			model.render(bullet, 0, 0, 0, 0, 0, 0.0625f);
			GlStateManager.popMatrix();
		}

		@Override
		protected ResourceLocation getEntityTexture(ArrowCustomEntity entity) {
			return texture;
		}
	}

	// Made with Blockbench 3.5.2
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class ModelCollosusHeart extends EntityModel {
		private final RendererModel Q;
		private final RendererModel f;
		private final RendererModel bone;
		public ModelCollosusHeart() {
			textureWidth = 32;
			textureHeight = 32;
			Q = new RendererModel(this);
			Q.setRotationPoint(-8.0F, 16.0F, 8.0F);
			Q.cubeList.add(new ModelBox(Q, 0, 2, -3.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F, false));
			f = new RendererModel(this);
			f.setRotationPoint(0.0F, 0.0F, 0.0F);
			Q.addChild(f);
			f.cubeList.add(new ModelBox(f, 0, 4, -3.0316F, -1.9065F, -1.0F, 2, 2, 1, 0.0F, false));
			f.cubeList.add(new ModelBox(f, 0, 2, -3.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F, false));
			bone = new RendererModel(this);
			bone.setRotationPoint(0.0F, 24.0F, 0.0F);
			bone.cubeList.add(new ModelBox(bone, 0, 6, -12.2367F, -11.1355F, 8.0F, 2, 4, 3, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 6, -11.2367F, -9.4355F, 8.0F, 2, 4, 3, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 4, -13.2367F, -11.1355F, 8.5F, 1, 3, 2, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 6, -10.0F, -11.0F, 8.0F, 3, 5, 3, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 6, -10.8F, -10.6F, 8.0F, 3, 5, 3, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 2, -11.0F, -14.0F, 10.0F, 1, 3, 1, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 1, -11.0F, -14.0F, 9.0F, 1, 3, 0, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 7, -9.0F, -11.0F, 10.0F, 3, 1, 0, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 7, -9.0F, -10.4F, 9.4F, 3, 0, 0, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 7, -9.0F, -9.8F, 10.0F, 3, 0, 0, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 3, -12.0F, -14.0F, 9.0F, 0, 3, 1, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			Q.render(f5);
			bone.render(f5);
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
	public static ArrowCustomEntity shoot(World world, LivingEntity entity, Random random, float power, double damage, int knockback) {
		ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, world);
		entityarrow.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setIsCritical(false);
		entityarrow.setDamage(damage);
		entityarrow.setKnockbackStrength(knockback);
		world.addEntity(entityarrow);
		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;
		world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")),
				SoundCategory.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static ArrowCustomEntity shoot(LivingEntity entity, LivingEntity target) {
		ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, entity.world);
		double d0 = target.posY + (double) target.getEyeHeight() - 1.1;
		double d1 = target.posX - entity.posX;
		double d3 = target.posZ - entity.posZ;
		entityarrow.shoot(d1, d0 - entityarrow.posY + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setDamage(5);
		entityarrow.setKnockbackStrength(5);
		entityarrow.setIsCritical(false);
		entity.world.addEntity(entityarrow);
		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;
		entity.world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")),
				SoundCategory.PLAYERS, 1, 1f / (new Random().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}


package net.mcreator.magica.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.wrapper.EntityHandsInvWrapper;
import net.minecraftforge.items.wrapper.EntityArmorInvWrapper;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction;
import net.minecraft.util.DamageSource;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.magica.gui.ZUGURUKGUIGui;
import net.mcreator.magica.MagicaModElements;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;

import io.netty.buffer.Unpooled;

@MagicaModElements.ModElement.Tag
public class ZugurukEntity extends MagicaModElements.ModElement {
	public static EntityType entity = null;
	public ZugurukEntity(MagicaModElements instance) {
		super(instance, 139);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("zuguruk")
						.setRegistryName("zuguruk");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -1, -1, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("zuguruk_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelzugurukos(), 0.5f) {
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magica:textures/zuguruk.png");
				}
			};
		});
	}
	public static class CustomEntity extends CreatureEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			enablePersistence();
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new SwimGoal(this));
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
		private final ItemStackHandler inventory = new ItemStackHandler(2) {
			@Override
			public int getSlotLimit(int slot) {
				return 64;
			}
		};
		private final CombinedInvWrapper combined = new CombinedInvWrapper(inventory, new EntityHandsInvWrapper(this),
				new EntityArmorInvWrapper(this));
		@Override
		public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
			if (this.isAlive() && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side == null)
				return LazyOptional.of(() -> combined).cast();
			return super.getCapability(capability, side);
		}

		@Override
		protected void dropInventory() {
			super.dropInventory();
			for (int i = 0; i < inventory.getSlots(); ++i) {
				ItemStack itemstack = inventory.getStackInSlot(i);
				if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
					this.entityDropItem(itemstack);
				}
			}
		}

		@Override
		public void writeAdditional(CompoundNBT compound) {
			super.writeAdditional(compound);
			compound.put("InventoryCustom", inventory.serializeNBT());
		}

		@Override
		public void readAdditional(CompoundNBT compound) {
			super.readAdditional(compound);
			INBT inventoryCustom = compound.get("InventoryCustom");
			if (inventoryCustom instanceof CompoundNBT)
				inventory.deserializeNBT((CompoundNBT) inventoryCustom);
		}

		@Override
		public boolean processInteract(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			boolean retval = true;
			if (sourceentity instanceof ServerPlayerEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) sourceentity, new INamedContainerProvider() {
					@Override
					public ITextComponent getDisplayName() {
						return new StringTextComponent("Zuguruk");
					}

					@Override
					public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
						PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
						packetBuffer.writeBlockPos(new BlockPos(sourceentity));
						packetBuffer.writeByte(0);
						packetBuffer.writeVarInt(CustomEntity.this.getEntityId());
						return new ZUGURUKGUIGui.GuiContainerMod(id, inventory, packetBuffer);
					}
				}, buf -> {
					buf.writeBlockPos(new BlockPos(sourceentity));
					buf.writeByte(0);
					buf.writeVarInt(this.getEntityId());
				});
			}
			super.processInteract(sourceentity, hand);
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
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}
	}

	// Made with Blockbench 3.6.2
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class Modelzugurukos extends EntityModel {
		private final RendererModel head;
		private final RendererModel armL;
		private final RendererModel armR;
		private final RendererModel LegL;
		private final RendererModel LegR;
		private final RendererModel Body;
		public Modelzugurukos() {
			textureWidth = 64;
			textureHeight = 64;
			head = new RendererModel(this);
			head.setRotationPoint(0.0F, 0.4F, -2.4F);
			head.cubeList.add(new ModelBox(head, 36, 4, -3.0F, -4.4F, -0.6F, 6, 6, 6, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 1, 59, -4.0F, -3.4F, -1.6F, 3, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 1, 59, 1.0F, -3.4F, -1.6F, 3, 1, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 19, 41, -2.0F, -0.4F, -0.6F, 1, 4, 1, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 22, 45, 1.0F, -0.4F, -0.6F, 1, 4, 1, 0.0F, false));
			armL = new RendererModel(this);
			armL.setRotationPoint(6.0F, 3.5F, 0.0F);
			armL.cubeList.add(new ModelBox(armL, 17, 16, -2.0F, -1.5F, -2.0F, 4, 11, 4, 0.0F, false));
			armR = new RendererModel(this);
			armR.setRotationPoint(-6.0F, 3.5F, 0.0F);
			armR.cubeList.add(new ModelBox(armR, 1, 16, -2.0F, -1.5F, -2.0F, 4, 11, 4, 0.0F, false));
			LegL = new RendererModel(this);
			LegL.setRotationPoint(2.0F, 14.5F, 0.0F);
			LegL.cubeList.add(new ModelBox(LegL, 17, 1, -2.0F, -1.5F, -2.0F, 4, 11, 4, 0.0F, false));
			LegR = new RendererModel(this);
			LegR.setRotationPoint(-2.0F, 14.5F, 0.0F);
			LegR.cubeList.add(new ModelBox(LegR, 1, 1, -2.0F, -1.5F, -2.0F, 4, 11, 4, 0.0F, false));
			Body = new RendererModel(this);
			Body.setRotationPoint(0.0F, 7.5F, 0.0F);
			Body.cubeList.add(new ModelBox(Body, 36, 40, -4.0F, -5.5F, -2.0F, 8, 11, 4, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			head.render(f5);
			armL.render(f5);
			armR.render(f5);
			LegL.render(f5);
			LegR.render(f5);
			Body.render(f5);
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
			this.LegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.armR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.armL.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}

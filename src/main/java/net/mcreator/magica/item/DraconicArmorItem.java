
package net.mcreator.magica.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.BipedModel;

import net.mcreator.magica.itemgroup.MagicaItemGroup;
import net.mcreator.magica.MagicaModElements;

@MagicaModElements.ModElement.Tag
public class DraconicArmorItem extends MagicaModElements.ModElement {
	@ObjectHolder("magica:draconic_armor_helmet")
	public static final Item helmet = null;
	@ObjectHolder("magica:draconic_armor_chestplate")
	public static final Item body = null;
	@ObjectHolder("magica:draconic_armor_leggings")
	public static final Item legs = null;
	@ObjectHolder("magica:draconic_armor_boots")
	public static final Item boots = null;
	public DraconicArmorItem(MagicaModElements instance) {
		super(instance, 48);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 50;
			}

			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{5, 5, 6, 5}[slot.getIndex()];
			}

			public int getEnchantability() {
				return 50;
			}

			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.fall"));
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}

			@OnlyIn(Dist.CLIENT)
			public String getName() {
				return "draconic_armor";
			}

			public float getToughness() {
				return 2f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(MagicaItemGroup.tab)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel();
				armorModel.bipedHead = new Modelhelmet().Helmet;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "magica:textures/helmet.png";
			}
		}.setRegistryName("draconic_armor_helmet"));
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.CHEST, new Item.Properties().group(MagicaItemGroup.tab)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel();
				armorModel.bipedBody = new Modelarmor().armor;
				armorModel.bipedLeftArm = new Modelarmor().armor;
				armorModel.bipedRightArm = new Modelarmor().armor;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "magica:textures/armor.png";
			}
		}.setRegistryName("draconic_armor_chestplate"));
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.LEGS, new Item.Properties().group(MagicaItemGroup.tab)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel();
				armorModel.bipedLeftLeg = new Modelcape().leggins;
				armorModel.bipedRightLeg = new Modelcape().leggins;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "magica:textures/cape.png";
			}
		}.setRegistryName("draconic_armor_leggings"));
	}
	// Made with Blockbench 3.6.2
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class Modelcape extends EntityModel {
		private final RendererModel leggins;
		public Modelcape() {
			textureWidth = 64;
			textureHeight = 64;
			leggins = new RendererModel(this);
			leggins.setRotationPoint(0.0F, 12.0F, 2.5F);
			setRotationAngle(leggins, 0.3054F, 0.0F, 0.0F);
			leggins.cubeList.add(new ModelBox(leggins, 0, 0, -4.0F, 0.0F, -0.5F, 8, 12, 1, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			leggins.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.leggins.rotateAngleX = f2 / 20.f;
		}
	}

	// Made with Blockbench 3.6.2
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class Modelarmor extends EntityModel {
		private final RendererModel armor;
		private final RendererModel armr;
		private final RendererModel arml;
		public Modelarmor() {
			textureWidth = 64;
			textureHeight = 64;
			armor = new RendererModel(this);
			armor.setRotationPoint(0.0F, 25.0F, 0.0F);
			armor.cubeList.add(new ModelBox(armor, 0, 0, -4.0F, -25.0F, 2.0F, 8, 12, 1, 0.0F, false));
			armor.cubeList.add(new ModelBox(armor, 0, 0, -4.0F, -25.0F, -3.0F, 8, 12, 1, 0.0F, false));
			armr = new RendererModel(this);
			armr.setRotationPoint(-4.0F, -30.0F, -3.0F);
			armor.addChild(armr);
			armr.cubeList.add(new ModelBox(armr, 0, 0, -5.0F, 5.0F, 0.0F, 1, 6, 6, 0.0F, false));
			armr.cubeList.add(new ModelBox(armr, 0, 0, -4.0F, 4.0F, 0.0F, 5, 1, 6, 0.0F, false));
			armr.cubeList.add(new ModelBox(armr, 0, 0, -4.0F, 5.0F, 5.0F, 4, 6, 1, 0.0F, false));
			armr.cubeList.add(new ModelBox(armr, 0, 0, -4.0F, 5.0F, 0.0F, 4, 6, 1, 0.0F, false));
			arml = new RendererModel(this);
			arml.setRotationPoint(-4.0F, -30.0F, -3.0F);
			armor.addChild(arml);
			arml.cubeList.add(new ModelBox(arml, 0, 0, 12.0F, 5.0F, 0.0F, 1, 6, 6, 0.0F, false));
			arml.cubeList.add(new ModelBox(arml, 0, 0, 8.0F, 4.0F, 0.0F, 5, 1, 6, 0.0F, false));
			arml.cubeList.add(new ModelBox(arml, 0, 0, 8.0F, 5.0F, 0.0F, 4, 6, 1, 0.0F, false));
			arml.cubeList.add(new ModelBox(arml, 0, 0, 8.0F, 5.0F, 5.0F, 4, 6, 1, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			armor.render(f5);
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

	// Made with Blockbench 3.6.2
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class Modelhelmet extends EntityModel {
		private final RendererModel Helmet;
		private final RendererModel bone;
		private final RendererModel bone4;
		private final RendererModel bone6;
		private final RendererModel bone2;
		private final RendererModel bone3;
		private final RendererModel bone5;
		public Modelhelmet() {
			textureWidth = 64;
			textureHeight = 64;
			Helmet = new RendererModel(this);
			Helmet.setRotationPoint(0.0F, 45.0F, 0.0F);
			Helmet.cubeList.add(new ModelBox(Helmet, 11, 42, -5.0F, -33.0F, -5.0F, 10, 1, 10, 0.0F, false));
			Helmet.cubeList.add(new ModelBox(Helmet, 36, 3, -5.0F, -32.0F, -5.0F, 10, 3, 1, 0.0F, false));
			Helmet.cubeList.add(new ModelBox(Helmet, 19, 25, 4.0F, -32.0F, -4.0F, 1, 8, 9, 0.0F, false));
			Helmet.cubeList.add(new ModelBox(Helmet, 19, 25, -5.0F, -32.0F, -4.0F, 1, 8, 9, 0.0F, false));
			Helmet.cubeList.add(new ModelBox(Helmet, 52, 7, 3.0F, -29.0F, -5.0F, 2, 2, 1, 0.0F, false));
			Helmet.cubeList.add(new ModelBox(Helmet, 52, 7, -5.0F, -29.0F, -5.0F, 2, 2, 1, 0.0F, false));
			Helmet.cubeList.add(new ModelBox(Helmet, 52, 7, -1.0F, -29.0F, -5.0F, 2, 2, 1, 0.0F, false));
			Helmet.cubeList.add(new ModelBox(Helmet, 45, 12, -4.0F, -34.0F, -1.0F, 2, 1, 2, 0.0F, false));
			Helmet.cubeList.add(new ModelBox(Helmet, 46, 13, 2.0F, -34.0F, -1.0F, 2, 1, 2, 0.0F, false));
			Helmet.cubeList.add(new ModelBox(Helmet, 0, 0, -4.0F, -32.0F, 4.0F, 8, 5, 1, 0.0F, false));
			Helmet.cubeList.add(new ModelBox(Helmet, 36, 3, -5.0F, -27.0F, -5.0F, 10, 3, 1, 0.0F, false));
			Helmet.cubeList.add(new ModelBox(Helmet, 2, 53, -5.0F, -27.0F, 4.0F, 10, 3, 1, 0.0F, false));
			bone = new RendererModel(this);
			bone.setRotationPoint(2.9F, -34.3F, 0.0F);
			Helmet.addChild(bone);
			setRotationAngle(bone, 0.0F, 0.0F, -0.3054F);
			bone.cubeList.add(new ModelBox(bone, 46, 13, -1.0F, -0.5F, -1.0F, 2, 1, 2, 0.0F, false));
			bone4 = new RendererModel(this);
			bone4.setRotationPoint(0.0146F, -1.0439F, 0.0F);
			bone.addChild(bone4);
			setRotationAngle(bone4, 0.0F, 0.0F, -0.3054F);
			bone4.cubeList.add(new ModelBox(bone4, 46, 13, -1.0F, -0.4561F, -1.0F, 2, 1, 2, 0.0F, false));
			bone6 = new RendererModel(this);
			bone6.setRotationPoint(0.3277F, 0.2294F, 0.0F);
			bone4.addChild(bone6);
			setRotationAngle(bone6, 0.0F, 0.0F, -0.3054F);
			bone6.cubeList.add(new ModelBox(bone6, 46, 13, -1.0F, -1.4561F, -1.0F, 2, 1, 2, 0.0F, false));
			bone2 = new RendererModel(this);
			bone2.setRotationPoint(-3.0F, -34.2F, 0.0F);
			Helmet.addChild(bone2);
			setRotationAngle(bone2, 0.0F, 0.0F, 0.3054F);
			bone2.cubeList.add(new ModelBox(bone2, 46, 13, -1.0F, -0.6F, -1.0F, 2, 1, 2, 0.0F, false));
			bone3 = new RendererModel(this);
			bone3.setRotationPoint(-0.0146F, -1.0439F, 0.0F);
			bone2.addChild(bone3);
			setRotationAngle(bone3, 0.0F, 0.0F, 0.3054F);
			bone3.cubeList.add(new ModelBox(bone3, 45, 13, -1.0F, -0.5561F, -1.0F, 2, 1, 2, 0.0F, false));
			bone5 = new RendererModel(this);
			bone5.setRotationPoint(-0.1638F, 0.1147F, 0.0F);
			bone3.addChild(bone5);
			setRotationAngle(bone5, 0.0F, 0.0F, 0.3054F);
			bone5.cubeList.add(new ModelBox(bone5, 45, 13, -1.0F, -1.5561F, -1.0F, 2, 1, 2, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			Helmet.render(f5);
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

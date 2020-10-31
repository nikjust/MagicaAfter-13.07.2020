
package net.mcreator.magica.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
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

import net.mcreator.magica.MagicaModElements;

@MagicaModElements.ModElement.Tag
public class ExoSkeletonLegsItem extends MagicaModElements.ModElement {
	@ObjectHolder("magica:exo_skeleton_legs_helmet")
	public static final Item helmet = null;
	@ObjectHolder("magica:exo_skeleton_legs_chestplate")
	public static final Item body = null;
	@ObjectHolder("magica:exo_skeleton_legs_leggings")
	public static final Item legs = null;
	@ObjectHolder("magica:exo_skeleton_legs_boots")
	public static final Item boots = null;
	public ExoSkeletonLegsItem(MagicaModElements instance) {
		super(instance, 320);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
			}

			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{2, 5, 6, 2}[slot.getIndex()];
			}

			public int getEnchantability() {
				return 9;
			}

			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}

			@OnlyIn(Dist.CLIENT)
			public String getName() {
				return "exo_skeleton_legs";
			}

			public float getToughness() {
				return 0f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel();
				armorModel.bipedLeftLeg = new Modelfff().leftLeg2;
				armorModel.bipedRightLeg = new Modelfff().rightLeg2;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "magica:textures/fff.png";
			}
		}.setRegistryName("exo_skeleton_legs_leggings"));
	}
	// Made with Blockbench 3.6.6
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class Modelfff extends EntityModel {
		private final RendererModel leftLeg2;
		private final RendererModel rightLeg2;
		public Modelfff() {
			textureWidth = 64;
			textureHeight = 64;
			leftLeg2 = new RendererModel(this);
			leftLeg2.setRotationPoint(2.0F, 12.0F, 0.0F);
			leftLeg2.cubeList.add(new ModelBox(leftLeg2, 24, 24, -2.0F, 6.0F, -3.0F, 5, 1, 6, 0.0F, false));
			leftLeg2.cubeList.add(new ModelBox(leftLeg2, 0, 0, -1.0F, 7.0F, 2.0F, 2, 6, 1, 0.0F, false));
			leftLeg2.cubeList.add(new ModelBox(leftLeg2, 0, 0, -1.0F, 13.0F, 1.0F, 2, 1, 1, 0.0F, false));
			leftLeg2.cubeList.add(new ModelBox(leftLeg2, 0, 0, -1.0F, 14.0F, 0.0F, 2, 1, 1, 0.0F, false));
			leftLeg2.cubeList.add(new ModelBox(leftLeg2, 0, 0, -1.0F, 14.0F, -4.0F, 2, 1, 1, 0.0F, false));
			leftLeg2.cubeList.add(new ModelBox(leftLeg2, 0, 0, -1.0F, 15.0F, -3.0F, 2, 1, 3, 0.0F, false));
			rightLeg2 = new RendererModel(this);
			rightLeg2.setRotationPoint(-2.0F, 12.0F, 0.0F);
			rightLeg2.cubeList.add(new ModelBox(rightLeg2, 24, 24, -3.0F, 6.0F, -3.0F, 5, 1, 6, 0.0F, false));
			rightLeg2.cubeList.add(new ModelBox(rightLeg2, 0, 0, -1.0F, 7.0F, 2.0F, 2, 6, 1, 0.0F, false));
			rightLeg2.cubeList.add(new ModelBox(rightLeg2, 0, 0, -1.0F, 13.0F, 1.0F, 2, 1, 1, 0.0F, false));
			rightLeg2.cubeList.add(new ModelBox(rightLeg2, 0, 0, -1.0F, 14.0F, 0.0F, 2, 1, 1, 0.0F, false));
			rightLeg2.cubeList.add(new ModelBox(rightLeg2, 0, 0, -1.0F, 14.0F, -4.0F, 2, 1, 1, 0.0F, false));
			rightLeg2.cubeList.add(new ModelBox(rightLeg2, 0, 0, -1.0F, 15.0F, -3.0F, 2, 1, 3, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			leftLeg2.render(f5);
			rightLeg2.render(f5);
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

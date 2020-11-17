package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.client.Minecraft;

import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class UnsignedAmuletItemInInventoryTickProcedure extends MagicaModElements.ModElement {
	public UnsignedAmuletItemInInventoryTickProcedure(MagicaModElements instance) {
		super(instance, 395);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure UnsignedAmuletItemInInventoryTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure UnsignedAmuletItemInInventoryTick!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!((itemstack).getOrCreateTag().getBoolean("Setted")))) {
			(itemstack).getOrCreateTag().putBoolean("Setted", (true));
			if (((new java.util.Random()).nextBoolean())) {
				((itemstack)).setDisplayName(
						new StringTextComponent((((new TranslationTextComponent("amulet.rarity.common").getUnformattedComponentText())) + ""
								+ (((itemstack).getDisplayName().getString())))));
				(itemstack).getOrCreateTag().putDouble("Rarity", 0);
			} else if (((new java.util.Random()).nextBoolean())) {
				((itemstack))
						.setDisplayName(new StringTextComponent((((new TranslationTextComponent("amulet.rarity.rare").getUnformattedComponentText()))
								+ "" + (((itemstack).getDisplayName().getString())))));
				(itemstack).getOrCreateTag().putDouble("Rarity", 1);
			} else if (((new java.util.Random()).nextBoolean())) {
				(itemstack).getOrCreateTag().putDouble("Rarity", 2);
				((itemstack)).setDisplayName(
						new StringTextComponent((((new TranslationTextComponent("amulet.rarity.superrare").getUnformattedComponentText())) + ""
								+ (((itemstack).getDisplayName().getString())))));
			} else if (((new java.util.Random()).nextBoolean())) {
				(itemstack).getOrCreateTag().putDouble("Rarity", 3);
				((itemstack))
						.setDisplayName(new StringTextComponent((((new TranslationTextComponent("amulet.rarity.ideal").getUnformattedComponentText()))
								+ "" + (((itemstack).getDisplayName().getString())))));
				if (world.getWorld().isRemote) {
					Minecraft.getInstance().gameRenderer.displayItemActivation(/* @ItemStack */(itemstack));
				}
			} else if (((new java.util.Random()).nextBoolean())) {
				(itemstack).getOrCreateTag().putDouble("Rarity", 4);
				((itemstack)).setDisplayName(
						new StringTextComponent((((new TranslationTextComponent("amulet.rarity.legendary").getUnformattedComponentText())) + ""
								+ (((itemstack).getDisplayName().getString())))));
				if (world.getWorld().isRemote) {
					Minecraft.getInstance().gameRenderer.displayItemActivation(/* @ItemStack */(itemstack));
				}
			}
		}
	}
}

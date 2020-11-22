package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class FruitOfMagicToolInInventoryTickProcedure extends MagicaModElements.ModElement {
	public FruitOfMagicToolInInventoryTickProcedure(MagicaModElements instance) {
		super(instance, 471);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure FruitOfMagicToolInInventoryTick!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure FruitOfMagicToolInInventoryTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure FruitOfMagicToolInInventoryTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		IWorld world = (IWorld) dependencies.get("world");
		if (((MagicaModVariables.MapVariables.get(world).GlobalMagic) >= ((itemstack).getOrCreateTag().getDouble("iternum")))) {
			MagicaModVariables.MapVariables.get(world).GlobalMagic = (double) ((MagicaModVariables.MapVariables.get(world).GlobalMagic)
					- ((itemstack).getOrCreateTag().getDouble("iternum")));
			MagicaModVariables.MapVariables.get(world).syncData(world);
			(itemstack).getOrCreateTag().putDouble("iternum", (((itemstack).getOrCreateTag().getDouble("iternum")) + 1));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 30, (int) 3, (false), (false)));
		} else {
			((itemstack)).shrink((int) 1);
		}
	}
}

package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class AvantaCigarCanUseRangedItemProcedure extends MagicaModElements.ModElement {
	public AvantaCigarCanUseRangedItemProcedure(MagicaModElements instance) {
		super(instance, 384);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure AvantaCigarCanUseRangedItem!");
			return false;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure AvantaCigarCanUseRangedItem!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((new ItemStack(Items.FLINT_AND_STEEL, (int) (1))
				.getItem() == ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem())) {
			return (true);
		}
		{
			ItemStack _setval = new ItemStack(Items.FLINT_AND_STEEL, (int) (1));
			entity.getCapability(MagicaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.ItemStackBuffer = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (world.getWorld().isRemote) {
			Minecraft.getInstance().gameRenderer
					.displayItemActivation(/* @ItemStack */((entity.getCapability(MagicaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MagicaModVariables.PlayerVariables())).ItemStackBuffer));
		}
		return (false);
	}
}

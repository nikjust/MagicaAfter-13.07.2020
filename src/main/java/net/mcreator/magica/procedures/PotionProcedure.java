package net.mcreator.magica.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.Map;

@MagicaModElements.ModElement.Tag
public class PotionProcedure extends MagicaModElements.ModElement {
	public PotionProcedure(MagicaModElements instance) {
		super(instance, 333);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure Potion!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure Potion!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((new ItemStack(Items.POTION, (int) (1)).getItem() == (itemstack).getItem())) {
			if ((ItemTags.getCollection().getOrCreate(new ResourceLocation(("LightWeightPotion").toLowerCase(java.util.Locale.ENGLISH)))
					.contains((itemstack).getItem()))) {
				{
					double _setval = (double) (((entity.getCapability(MagicaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MagicaModVariables.PlayerVariables())).Intoxication) + 3);
					entity.getCapability(MagicaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.Intoxication = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if ((ItemTags.getCollection().getOrCreate(new ResourceLocation(("StrengthWeightPotion").toLowerCase(java.util.Locale.ENGLISH)))
					.contains((itemstack).getItem()))) {
				{
					double _setval = (double) (((entity.getCapability(MagicaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MagicaModVariables.PlayerVariables())).Intoxication) + 5);
					entity.getCapability(MagicaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.Intoxication = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else if ((ItemTags.getCollection().getOrCreate(new ResourceLocation(("DangerPotion").toLowerCase(java.util.Locale.ENGLISH)))
					.contains((itemstack).getItem()))) {
				{
					double _setval = (double) (((entity.getCapability(MagicaModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MagicaModVariables.PlayerVariables())).Intoxication) + 7);
					entity.getCapability(MagicaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.Intoxication = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}

	@SubscribeEvent
	public void onUseItemStart(LivingEntityUseItemEvent.Start event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			int i = (int) entity.posX;
			int j = (int) entity.posY;
			int k = (int) entity.posZ;
			double duration = event.getDuration();
			ItemStack itemstack = event.getItem();
			World world = entity.world;
			java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("itemstack", itemstack);
			dependencies.put("duration", duration);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}

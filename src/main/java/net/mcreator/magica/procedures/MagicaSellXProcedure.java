package net.mcreator.magica.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.magica.item.MagicQuartzItem;
import net.mcreator.magica.item.ItemsForSellItem;
import net.mcreator.magica.item.CookedMagicMeatItem;
import net.mcreator.magica.block.MagicQuartzBlockBlock;
import net.mcreator.magica.block.DirtyMagicQuartzOreBlock;
import net.mcreator.magica.MagicaModElements;

import java.util.function.Supplier;
import java.util.Map;

@MagicaModElements.ModElement.Tag
public class MagicaSellXProcedure extends MagicaModElements.ModElement {
	public MagicaSellXProcedure(MagicaModElements instance) {
		super(instance, 323);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MagicaSellX!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity) {
			Container _current = ((PlayerEntity) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = new ItemStack(ItemsForSellItem.block, (int) (1));
					_setstack.setCount((int) 1);
					((Slot) ((Map) invobj).get((int) (1))).putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
		if ((new ItemStack(DirtyMagicQuartzOreBlock.block, (int) (1)).getItem() == (new Object() {
			public ItemStack getItemStack(int sltid) {
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) _ent).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							return ((Slot) ((Map) invobj).get(sltid)).getStack();
						}
					}
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack((int) (0))).getItem())) {
			entity.getPersistentData().putDouble("MagicaSell", 6);
		} else if ((new ItemStack(MagicQuartzItem.block, (int) (1)).getItem() == (new Object() {
			public ItemStack getItemStack(int sltid) {
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) _ent).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							return ((Slot) ((Map) invobj).get(sltid)).getStack();
						}
					}
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack((int) (0))).getItem())) {
			entity.getPersistentData().putDouble("MagicaSell", 12);
		} else if ((new ItemStack(MagicQuartzBlockBlock.block, (int) (1)).getItem() == (new Object() {
			public ItemStack getItemStack(int sltid) {
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) _ent).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							return ((Slot) ((Map) invobj).get(sltid)).getStack();
						}
					}
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack((int) (0))).getItem())) {
			entity.getPersistentData().putDouble("MagicaSell", 108);
		} else if ((new ItemStack(CookedMagicMeatItem.block, (int) (1)).getItem() == (new Object() {
			public ItemStack getItemStack(int sltid) {
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) _ent).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							return ((Slot) ((Map) invobj).get(sltid)).getStack();
						}
					}
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack((int) (0))).getItem())) {
			entity.getPersistentData().putDouble("MagicaSell", 60);
		} else if ((new ItemStack(Blocks.AIR, (int) (1)).getItem() == (new Object() {
			public ItemStack getItemStack(int sltid) {
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) _ent).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							return ((Slot) ((Map) invobj).get(sltid)).getStack();
						}
					}
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack((int) (0))).getItem())) {
			entity.getPersistentData().putDouble("MagicaSell", 0);
		} else {
			entity.getPersistentData().putDouble("MagicaSell", (Math.random() * 10));
		}
	}
}

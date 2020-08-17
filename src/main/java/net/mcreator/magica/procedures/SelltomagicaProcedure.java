package net.mcreator.magica.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.magica.item.MagicQuartzItem;
import net.mcreator.magica.item.CookedMagicMeatItem;
import net.mcreator.magica.block.MagicQuartzBlockBlock;
import net.mcreator.magica.block.DirtyMagicQuartzOreBlock;
import net.mcreator.magica.MagicaModVariables;
import net.mcreator.magica.MagicaModElements;

import java.util.function.Supplier;
import java.util.Map;

@MagicaModElements.ModElement.Tag
public class SelltomagicaProcedure extends MagicaModElements.ModElement {
	public SelltomagicaProcedure(MagicaModElements instance) {
		super(instance, 229);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure Selltomagica!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Selltomagica!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
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
			{
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) _ent).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							((Slot) ((Map) invobj).get((int) (0))).decrStackSize((int) (1));
							_current.detectAndSendChanges();
						}
					}
				}
			}
			MagicaModVariables.MapVariables.get(world).GlobalMagic = (double) ((MagicaModVariables.MapVariables.get(world).GlobalMagic) + 6);
			MagicaModVariables.MapVariables.get(world).syncData(world);
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
			{
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) _ent).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							((Slot) ((Map) invobj).get((int) (0))).decrStackSize((int) (1));
							_current.detectAndSendChanges();
						}
					}
				}
			}
			MagicaModVariables.MapVariables.get(world).GlobalMagic = (double) ((MagicaModVariables.MapVariables.get(world).GlobalMagic) + 12);
			MagicaModVariables.MapVariables.get(world).syncData(world);
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
			{
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) _ent).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							((Slot) ((Map) invobj).get((int) (0))).decrStackSize((int) (1));
							_current.detectAndSendChanges();
						}
					}
				}
			}
			MagicaModVariables.MapVariables.get(world).GlobalMagic = (double) ((MagicaModVariables.MapVariables.get(world).GlobalMagic) + 108);
			MagicaModVariables.MapVariables.get(world).syncData(world);
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
			{
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) _ent).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							((Slot) ((Map) invobj).get((int) (0))).decrStackSize((int) (1));
							_current.detectAndSendChanges();
						}
					}
				}
			}
			MagicaModVariables.MapVariables.get(world).GlobalMagic = (double) ((MagicaModVariables.MapVariables.get(world).GlobalMagic) + 60);
			MagicaModVariables.MapVariables.get(world).syncData(world);
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
			{
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) _ent).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							((Slot) ((Map) invobj).get((int) (0))).decrStackSize((int) (1));
							_current.detectAndSendChanges();
						}
					}
				}
			}
			entity.getPersistentData().putDouble("MagicaSell", (Math.random() * 10));
			MagicaModVariables.MapVariables.get(world).GlobalMagic = (double) ((MagicaModVariables.MapVariables.get(world).GlobalMagic)
					+ (entity.getPersistentData().getDouble("MagicaSell")));
			MagicaModVariables.MapVariables.get(world).syncData(world);
		}
	}
}

package net.mcreator.magica.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.TextFieldWidget;

import net.mcreator.magica.MagicaModElements;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

@MagicaModElements.ModElement.Tag
public class RenameProcedure extends MagicaModElements.ModElement {
	public RenameProcedure(MagicaModElements instance) {
		super(instance, 271);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure Rename!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				System.err.println("Failed to load dependency guistate for procedure Rename!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		((new Object() {
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
		}.getItemStack((int) (0)))).setDisplayName(new StringTextComponent(("\u00A7".replace("&", (new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:name");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())))));
	}
}

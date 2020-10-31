
package net.mcreator.magica.painting;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.entity.item.PaintingType;

import net.mcreator.magica.MagicaModElements;

@MagicaModElements.ModElement.Tag
public class TerrariaPainting extends MagicaModElements.ModElement {
	public TerrariaPainting(MagicaModElements instance) {
		super(instance, 303);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerPaintingType(RegistryEvent.Register<PaintingType> event) {
		event.getRegistry().register(new PaintingType(22, 18).setRegistryName("terraria"));
	}
}

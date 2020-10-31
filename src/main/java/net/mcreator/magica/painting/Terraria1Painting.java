
package net.mcreator.magica.painting;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.entity.item.PaintingType;

import net.mcreator.magica.MagicaModElements;

@MagicaModElements.ModElement.Tag
public class Terraria1Painting extends MagicaModElements.ModElement {
	public Terraria1Painting(MagicaModElements instance) {
		super(instance, 304);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerPaintingType(RegistryEvent.Register<PaintingType> event) {
		event.getRegistry().register(new PaintingType(512, 512).setRegistryName("terraria_1"));
	}
}

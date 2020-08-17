package net.mcreator.magica;

import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.IWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.function.Supplier;

public class MagicaModVariables {
	public MagicaModVariables(MagicaModElements elements) {
		elements.addNetworkMessage(WorldSavedDataSyncMessage.class, WorldSavedDataSyncMessage::buffer, WorldSavedDataSyncMessage::new,
				WorldSavedDataSyncMessage::handler);
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote) {
			WorldSavedData mapdata = MapVariables.get(event.getPlayer().world);
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (mapdata != null)
				MagicaMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(0, mapdata));
			if (worlddata != null)
				MagicaMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(1, worlddata));
		}
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote) {
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (worlddata != null)
				MagicaMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(1, worlddata));
		}
	}
	public static class WorldVariables extends WorldSavedData {
		public static final String DATA_NAME = "magica_worldvars";
		public double PortallerLocX = 0;
		public double PortallerLocY = 0;
		public double PortallerLocZ = 0;
		public WorldVariables() {
			super(DATA_NAME);
		}

		public WorldVariables(String s) {
			super(s);
		}

		@Override
		public void read(CompoundNBT nbt) {
			PortallerLocX = nbt.getDouble("PortallerLocX");
			PortallerLocY = nbt.getDouble("PortallerLocY");
			PortallerLocZ = nbt.getDouble("PortallerLocZ");
		}

		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			nbt.putDouble("PortallerLocX", PortallerLocX);
			nbt.putDouble("PortallerLocY", PortallerLocY);
			nbt.putDouble("PortallerLocZ", PortallerLocZ);
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (!world.getWorld().isRemote)
				MagicaMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(world.getWorld().dimension::getType),
						new WorldSavedDataSyncMessage(1, this));
		}
		static WorldVariables clientSide = new WorldVariables();
		public static WorldVariables get(IWorld world) {
			if (world.getWorld() instanceof ServerWorld) {
				return ((ServerWorld) world.getWorld()).getSavedData().getOrCreate(WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends WorldSavedData {
		public static final String DATA_NAME = "magica_mapvars";
		public double GlobalMagic = 0;
		public boolean Overlay = false;
		public double Gradus = 0.0;
		public double jumbo = 5.0;
		public MapVariables() {
			super(DATA_NAME);
		}

		public MapVariables(String s) {
			super(s);
		}

		@Override
		public void read(CompoundNBT nbt) {
			GlobalMagic = nbt.getDouble("GlobalMagic");
			Overlay = nbt.getBoolean("Overlay");
			Gradus = nbt.getDouble("Gradus");
			jumbo = nbt.getDouble("jumbo");
		}

		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			nbt.putDouble("GlobalMagic", GlobalMagic);
			nbt.putBoolean("Overlay", Overlay);
			nbt.putDouble("Gradus", Gradus);
			nbt.putDouble("jumbo", jumbo);
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (!world.getWorld().isRemote)
				MagicaMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new WorldSavedDataSyncMessage(0, this));
		}
		static MapVariables clientSide = new MapVariables();
		public static MapVariables get(IWorld world) {
			if (world.getWorld() instanceof ServerWorld) {
				return world.getWorld().getServer().getWorld(DimensionType.OVERWORLD).getSavedData().getOrCreate(MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class WorldSavedDataSyncMessage {
		public int type;
		public WorldSavedData data;
		public WorldSavedDataSyncMessage(PacketBuffer buffer) {
			this.type = buffer.readInt();
			this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
			this.data.read(buffer.readCompoundTag());
		}

		public WorldSavedDataSyncMessage(int type, WorldSavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(WorldSavedDataSyncMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.type);
			buffer.writeCompoundTag(message.data.write(new CompoundNBT()));
		}

		public static void handler(WorldSavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}
}

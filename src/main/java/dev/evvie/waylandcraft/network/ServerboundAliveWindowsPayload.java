package dev.evvie.waylandcraft.network;

import dev.evvie.waylandcraft.WaylandCraftCommon;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ServerboundAliveWindowsPayload(long[] handles) implements CustomPacketPayload {

	public static final ResourceLocation ALIVE_WINDOWS_PAYLOAD_ID = ResourceLocation.fromNamespaceAndPath(WaylandCraftCommon.MOD_ID, "alive_windows");

	public static final CustomPacketPayload.Type<ServerboundAliveWindowsPayload> TYPE = new CustomPacketPayload.Type<ServerboundAliveWindowsPayload>(ALIVE_WINDOWS_PAYLOAD_ID);

	public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundAliveWindowsPayload> CODEC = new StreamCodec<>() {
		public ServerboundAliveWindowsPayload decode(RegistryFriendlyByteBuf buf) {
			long[] handles = new long[buf.readVarInt()];
			for (int i = 0; i < handles.length; i++) handles[i] = buf.readLong();
			return new ServerboundAliveWindowsPayload(handles);
		}
		public void encode(RegistryFriendlyByteBuf buf, ServerboundAliveWindowsPayload payload) {
			buf.writeVarInt(payload.handles.length);
			for (long handle : payload.handles) buf.writeLong(handle);
		}
	};

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}

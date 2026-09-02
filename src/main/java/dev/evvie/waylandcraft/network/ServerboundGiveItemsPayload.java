package dev.evvie.waylandcraft.network;

import dev.evvie.waylandcraft.WaylandCraftCommon;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ServerboundGiveItemsPayload(long[] handles, boolean missingOnly) implements CustomPacketPayload {

	public static final ResourceLocation GIVE_ITEMS_PAYLOAD_ID = ResourceLocation.fromNamespaceAndPath(WaylandCraftCommon.MOD_ID, "give_items");

	public static final CustomPacketPayload.Type<ServerboundGiveItemsPayload> TYPE = new CustomPacketPayload.Type<ServerboundGiveItemsPayload>(GIVE_ITEMS_PAYLOAD_ID);

	public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundGiveItemsPayload> CODEC = new StreamCodec<>() {
		public ServerboundGiveItemsPayload decode(RegistryFriendlyByteBuf buf) {
			long[] handles = new long[buf.readVarInt()];
			for (int i = 0; i < handles.length; i++) handles[i] = buf.readLong();
			return new ServerboundGiveItemsPayload(handles, buf.readBoolean());
		}
		public void encode(RegistryFriendlyByteBuf buf, ServerboundGiveItemsPayload payload) {
			buf.writeVarInt(payload.handles.length);
			for (long handle : payload.handles) buf.writeLong(handle);
			buf.writeBoolean(payload.missingOnly);
		}
	};

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}

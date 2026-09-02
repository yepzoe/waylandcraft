package dev.evvie.waylandcraft.item;

import dev.evvie.waylandcraft.WaylandCraftCommon;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WindowItem extends Item {
	public static Item WINDOW;
	public static DataComponentType<WindowHandle> WINDOW_HANDLE;
	public static final ResourceLocation BROKEN_WINDOW_MODEL =
			ResourceLocation.fromNamespaceAndPath(WaylandCraftCommon.MOD_ID, "item/broken_window");

	public static void register() {
		WINDOW = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(WaylandCraftCommon.MOD_ID, "window"), new WindowItem());
		WINDOW_HANDLE = Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE,
				ResourceLocation.fromNamespaceAndPath(WaylandCraftCommon.MOD_ID, "window_handle"),
				DataComponentType.<WindowHandle>builder().persistent(WindowHandle.CODEC).build());
	}

	public WindowItem() {
		super(new Properties());
	}

	@Override
	public Component getName(ItemStack stack) {
		WindowItemInteractionProvider provider = WaylandCraftCommon.instance.windowItemInteractionProvider;
		return provider == null ? super.getName(stack) : provider.getName(stack);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		WindowItemInteractionProvider provider = WaylandCraftCommon.instance.windowItemInteractionProvider;
		if (provider != null && !provider.isValid(stack)) return InteractionResultHolder.pass(stack);
		player.startUsingItem(hand);
		return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
	}

	@Override
	public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int tick) {
		if (level.isClientSide) {
			WindowItemInteractionProvider provider = WaylandCraftCommon.instance.windowItemInteractionProvider;
			if (provider != null) provider.useTick(entity, stack);
		}
	}

}

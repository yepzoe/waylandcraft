package dev.evvie.waylandcraft.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.evvie.waylandcraft.WaylandCraft;
import dev.evvie.waylandcraft.WaylandCraftCommon;
import dev.evvie.waylandcraft.gui.WaylandCraftSettingsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {

	protected TitleScreenMixin() {
		super(null);
	}

	@Inject(method = "init", at = @At("TAIL"))
	public void addWaylandCraftButton(CallbackInfo info) {
		SpriteIconButton settingsButton = SpriteIconButton
				.builder(Component.literal("waylandcraft"), (ignored) -> {Minecraft.getInstance().setScreen(new WaylandCraftSettingsScreen(WaylandCraft.instance));}, true)
				.sprite(ResourceLocation.fromNamespaceAndPath(WaylandCraftCommon.MOD_ID, "logo"), 16, 16)
				.width(20)
				.build();
		settingsButton.setPosition(width / 2 - 124, height / 4 + 48 - 36);
		this.addRenderableWidget(settingsButton);
	}

}

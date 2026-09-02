package dev.evvie.waylandcraft.gui;

import dev.evvie.waylandcraft.WaylandCraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class WaylandCraftSettingsScreen extends Screen {
	private final WaylandCraft waylandCraft;

	public WaylandCraftSettingsScreen(WaylandCraft waylandCraft) {
		super(Component.literal("WaylandCraft"));
		this.waylandCraft = waylandCraft;
	}

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
		renderBackground(graphics, mouseX, mouseY, partialTick);
		graphics.drawCenteredString(font, title, width / 2, 40, 0xFFFFFFFF);
		super.render(graphics, mouseX, mouseY, partialTick);
	}
}

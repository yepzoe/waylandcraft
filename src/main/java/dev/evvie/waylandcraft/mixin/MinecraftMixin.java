package dev.evvie.waylandcraft.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.evvie.waylandcraft.WaylandCraft;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.Platform;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Inject(
		method = "<init>",
		at = @At(
			value = "INVOKE",
			target = "Lcom/mojang/blaze3d/systems/RenderSystem;initBackendSystem()Lnet/minecraft/util/TimeSource$NanoTimeSource;"
		)
	)
	private void selectWaylandPlatform(CallbackInfo info) {
		if (Platform.get() == Platform.LINUX && System.getenv("WAYLAND_DISPLAY") != null) {
			GLFW.glfwInitHint(GLFW.GLFW_PLATFORM, GLFW.GLFW_PLATFORM_WAYLAND);
		}
	}

	@Inject(method = "runTick", at = @At(value = "INVOKE_STRING", target = "Lcom/mojang/blaze3d/platform/Window;setErrorSection(Ljava/lang/String;)V", args = "ldc=Render"))
	public void updateRunTick(boolean doTick, CallbackInfo info) {
		WaylandCraft.instance.update();
	}

}

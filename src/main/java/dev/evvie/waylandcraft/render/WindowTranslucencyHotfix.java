package dev.evvie.waylandcraft.render;

/**
 * The GPU render-pass workaround used by newer Minecraft versions is not
 * needed by the 1.21.1 OpenGL renderer.
 */
public final class WindowTranslucencyHotfix {
	private WindowTranslucencyHotfix() {
	}

	public static void render() {
	}
}

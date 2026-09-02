package dev.evvie.waylandcraft.compat;

import net.fabricmc.loader.api.FabricLoader;

public class IrisCompat {

	public static boolean isShaderActive() {
		if (!FabricLoader.getInstance().isModLoaded("iris")) {
			return false;
		}

		try {
			Class<?> irisApi = Class.forName("net.irisshaders.iris.api.v0.IrisApi");
			Object instance = irisApi.getMethod("getInstance").invoke(null);
			return (Boolean) irisApi.getMethod("isShaderPackInUse").invoke(instance);
		} catch (ReflectiveOperationException | LinkageError exception) {
			return false;
		}
	}

}

package ray4rc.rayclient;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ray4rc.rayclient.modules.Mod;
import ray4rc.rayclient.modules.ModuleManager;
import ray4rc.rayclient.ui.screens.clickgui.ClickGUI;

public class RayClientClient implements ClientModInitializer {

	public static final RayClientClient INSTANCE = new RayClientClient();

	public static final String MOD_ID = "ray-client";

	private MinecraftClient mc = MinecraftClient.getInstance();

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		LOGGER.info(


				"ray was here\n" +
				"superduperdopewebsite.vercel.app"



		);
	}

	public void onKeyPress(int key, int action) {
		if (action == GLFW.GLFW_PRESS && !(mc.currentScreen instanceof Screen)) {
			for (Mod module: ModuleManager.INSTANCE.getModules()) {
				if (key == module.getKey()) module.toggle();
			}

			if (key == GLFW.GLFW_KEY_RIGHT_SHIFT) mc.setScreen(ClickGUI.INSTANCE);
		}
	}

	public void onTick() {
		if (mc.player != null) {
			for (Mod module: ModuleManager.INSTANCE.getEnabledModules()) {
				module.onTick();
			}
		}
	}

	public void onPreTick() {
		if (mc.player != null) {
			for (Mod module: ModuleManager.INSTANCE.getEnabledModules()) {
				module.onPreTick();
			}
		}
	}

	public void onRender() {
		if (mc.player != null) {
			for (Mod module: ModuleManager.INSTANCE.getEnabledModules()) {
				module.onRender();
			}
		}
	}

	public void onPostTick() {
		if (mc.player != null) {
			for (Mod module: ModuleManager.INSTANCE.getEnabledModules()) {
				module.onPostTick();
			}
		}
	}
}
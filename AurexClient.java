package dev.aurex;

import dev.aurex.config.AurexConfig;
import dev.aurex.feature.ZoomFeature;
import dev.aurex.hud.HudRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class AurexClient implements ClientModInitializer {

    public static final String MOD_ID = "aurexclient";

    private static KeyBinding zoomKey;
    private static ZoomFeature zoomFeature;
    private static HudRenderer hudRenderer;

    @Override
    public void onInitializeClient() {
        AurexConfig.load();

        zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.aurexclient.zoom",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                "category.aurexclient"
        ));

        zoomFeature = new ZoomFeature();
        hudRenderer = new HudRenderer();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (zoomKey.wasPressed()) {
                zoomFeature.toggle();
            }
        });

        HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
            hudRenderer.render(matrices, tickDelta);
        });
    }
}

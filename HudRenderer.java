package dev.aurex.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class HudRenderer {

    public void render(MatrixStack matrices, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.options.hudHidden) return;

        TextRenderer tr = client.textRenderer;
        String watermark = "Aurex Client 0.1.0";

        matrices.push();
        RenderSystem.enableBlend();

        tr.drawWithShadow(matrices, Text.of(watermark), 4, 4, 0xFFFFFF);

        RenderSystem.disableBlend();
        matrices.pop();
    }
}

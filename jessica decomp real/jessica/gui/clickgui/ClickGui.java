/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package jessica.gui.clickgui;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import jessica.Category;
import jessica.gui.clickgui.panel.Panel;
import jessica.module.ClickGuiModule;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public final class ClickGui
extends GuiScreen {
    private Category categ;
    private final List<Panel> panels = Lists.newArrayList();

    public void init() {
        Category[] categories = Category.values();
        int i = categories.length - 1;
        while (i >= 0) {
            this.panels.add(new Panel(categories[i], 5 + 87 * i, 15));
            --i;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int i = 0;
        while (i < this.panels.size()) {
            this.panels.get(i).onDraw(mouseX, mouseY);
            ++i;
        }
        if (!this.mc.gameSettings.ofFastRender && !this.mc.entityRenderer.isShaderActive()) {
            this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/antialias.json"));
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        int i = 0;
        while (i < this.panels.size()) {
            this.panels.get(i).onMouseClick(mouseX, mouseY, mouseButton);
            ++i;
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        int i = 0;
        while (i < this.panels.size()) {
            this.panels.get(i).onMouseRelease(mouseX, mouseY, state);
            ++i;
        }
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        int i = 0;
        while (i < this.panels.size()) {
            this.panels.get(i).onKeyPress(typedChar, keyCode);
            ++i;
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void onGuiClosed() {
        if (this.mc.entityRenderer.isShaderActive()) {
            this.mc.entityRenderer.stopUseShader();
        }
        int i = 0;
        while (i < this.panels.size()) {
            this.panels.get(i).onGuiClose();
            ++i;
        }
        super.onGuiClosed();
    }

    public static Color getColor() {
        return new Color((int)ClickGuiModule.red.getDoubleValue(), (int)ClickGuiModule.green.getDoubleValue(), (int)ClickGuiModule.blue.getDoubleValue());
    }

    public static Color rainbowEffect(float f, float fade) {
        float hue = ((float)System.nanoTime() + f) / 1.0E10f % 1.0f;
        long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, 1.0f, 1.0f)), 16);
        Color c = new Color((int)color);
        return new Color((float)c.getRed() / 255.0f * fade, (float)c.getGreen() / 255.0f * fade, (float)c.getBlue() / 255.0f * fade, (float)c.getAlpha() / 255.0f);
    }
}


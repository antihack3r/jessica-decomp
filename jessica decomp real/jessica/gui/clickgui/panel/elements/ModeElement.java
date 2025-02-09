/*
 * Decompiled with CFR 0.152.
 */
package jessica.gui.clickgui.panel.elements;

import jessica.Wrapper;
import jessica.gui.clickgui.ClickGui;
import jessica.gui.clickgui.panel.Panel;
import jessica.gui.clickgui.panel.elements.Element;
import jessica.module.ClickGuiModule;
import jessica.value.ValueMode;
import net.minecraft.client.gui.Gui;

public final class ModeElement
extends Element {
    private final ValueMode value;

    public ModeElement(ValueMode value, Panel panel, int x, int y, int offsetY, int width, int height) {
        super(panel, x, y, offsetY, width, height);
        this.value = value;
    }

    @Override
    public void moved(int posX, int posY) {
        super.moved(posX, posY);
    }

    @Override
    public void onDraw(int mouseX, int mouseY) {
        Panel parent = this.getPanel();
        int x = parent.getX() + this.getX();
        int y = parent.getY() + this.getY();
        if (!ClickGuiModule.rgb.getValue().booleanValue()) {
            Gui.drawRect(x + 3, y + 1, x + this.getWidth() - 2, y + this.getHeight(), ClickGui.getColor().getRGB());
        } else {
            Gui.drawRect(x + 3, y + 1, x + this.getWidth() - 2, y + this.getHeight(), ClickGui.rainbowEffect(8.0f, 1.0f).getRGB());
        }
        boolean hovered = this.isMouseOver(mouseX, mouseY);
        if (hovered) {
            Gui.drawRect(x + 3, y + 1, x + this.getWidth() - 2, y + this.getHeight(), 0x20000000);
        }
        String s = String.format("%s: %s", this.value.getName(), this.value.getValue());
        Wrapper.mc().fontRendererObj.drawStringWithShadow(s, (float)(x + this.getWidth() / 2) - (float)(Wrapper.mc().fontRendererObj.getStringWidth(s) / 2), (float)y + (float)this.getHeight() / 2.0f - 4.0f, -1);
    }

    @Override
    public void onMouseClick(int mouseX, int mouseY, int mouseButton) {
        if (this.isMouseOver(mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.value.increment();
            } else if (mouseButton == 1) {
                this.value.decrement();
            }
        }
    }
}


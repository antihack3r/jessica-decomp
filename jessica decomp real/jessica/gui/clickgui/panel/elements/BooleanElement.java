/*
 * Decompiled with CFR 0.152.
 */
package jessica.gui.clickgui.panel.elements;

import jessica.Wrapper;
import jessica.gui.clickgui.ClickGui;
import jessica.gui.clickgui.panel.Panel;
import jessica.gui.clickgui.panel.elements.Element;
import jessica.module.ClickGuiModule;
import jessica.value.ValueBool;
import net.minecraft.client.gui.Gui;

public final class BooleanElement
extends Element {
    private final ValueBool value;

    public BooleanElement(ValueBool value, Panel panel, int x, int y, int offsetY, int width, int height) {
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
        Gui.drawRect(x + 2, y, x + this.getWidth(), y + this.getHeight(), -1879048192);
        Gui.drawRect(x + 3, y + 2, x + 11, y + 10, -1);
        if (this.value.getValue().booleanValue()) {
            if (!ClickGuiModule.rgb.getValue().booleanValue()) {
                Gui.drawRect(x + 4, y + 3, x + 10, y + 9, ClickGui.getColor().getRGB());
            } else {
                Gui.drawRect(x + 4, y + 3, x + 10, y + 9, ClickGui.rainbowEffect(8.0f, 1.0f).getRGB());
            }
        }
        Wrapper.mc().fontRendererObj.drawString(this.value.getName(), (int)((float)x + 14.0f), (int)((float)y + (float)this.getHeight() / 2.0f - 4.0f), -1);
    }

    @Override
    public void onMouseClick(int mouseX, int mouseY, int mouseButton) {
        if (this.isMouseOver(mouseX, mouseY)) {
            this.value.setValue(this.value.getValue() == false);
            Wrapper.getFiles().saveValues();
        }
    }
}


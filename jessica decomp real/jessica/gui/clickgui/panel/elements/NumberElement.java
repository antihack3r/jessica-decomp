/*
 * Decompiled with CFR 0.152.
 */
package jessica.gui.clickgui.panel.elements;

import java.math.BigDecimal;
import java.math.RoundingMode;
import jessica.Wrapper;
import jessica.gui.clickgui.ClickGui;
import jessica.gui.clickgui.panel.Panel;
import jessica.gui.clickgui.panel.elements.Element;
import jessica.module.ClickGuiModule;
import jessica.value.ValueNumber;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.math.MathHelper;

public final class NumberElement
extends Element {
    private final ValueNumber value;
    private boolean dragging = false;
    private double selected;

    public NumberElement(ValueNumber value, Panel panel, int x, int y, int offsetY, int width, int height) {
        super(panel, x, y, offsetY, width, height);
        this.value = value;
        this.selected = value.getValue() / value.getMax();
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
        boolean hovered = this.isMouseOver(mouseX, mouseY);
        int height = this.getHeight();
        int width = this.getWidth();
        ValueNumber value = this.value;
        double min = value.getMin();
        double max = value.getMax();
        double inc = value.getDoubleValue();
        if (this.dragging) {
            double diff = this.value.getMax() - this.value.getMin();
            double percentBar = MathHelper.clamp(((float)mouseX - (float)x) / (float)width, 0.0f, 1.0f);
            double val = this.value.getMin() + percentBar * diff;
            this.value.setValue(val);
            this.selected = percentBar;
            Wrapper.getFiles().saveValues();
        } else if (value.getValue() < min) {
            value.setValue(min);
        }
        double valueValue = this.round(value.getValue(), inc);
        String valueName = String.valueOf(valueValue);
        double renderPerc = (double)(width - 3) / (max - min);
        double barWidth = renderPerc * valueValue - renderPerc * min;
        Gui.drawRect(x + 2, y, x + this.getWidth(), y + this.getHeight(), -1879048192);
        Gui.drawRect((double)(x + 3), (double)(y + height - 2), (double)(x + width - 3), (double)(y + 3), -1436524448);
        if (!ClickGuiModule.rgb.getValue().booleanValue()) {
            Gui.drawRect((double)(x + 3), (double)(y + height - 2), (double)x + Math.max(barWidth, 4.0), (double)(y + 3), ClickGui.getColor().getRGB());
        } else {
            Gui.drawRect((double)(x + 3), (double)(y + height - 2), (double)x + Math.max(barWidth, 4.0), (double)(y + 3), ClickGui.rainbowEffect(8.0f, 1.0f).getRGB());
        }
        Wrapper.mc().fontRendererObj.drawStringWithShadow(valueName, x + width - Wrapper.mc().fontRendererObj.getStringWidth(valueName) - 3, (float)y + (float)height / 2.0f - 4.0f, -1);
        Wrapper.mc().fontRendererObj.drawStringWithShadow(value.getName(), (float)x + 6.0f, (float)y + (float)height / 2.0f - 4.0f, -1);
    }

    @Override
    public void onMouseClick(int mouseX, int mouseY, int mouseButton) {
        if (this.isMouseOver(mouseX, mouseY)) {
            this.dragging = true;
        }
    }

    @Override
    public void onMouseRelease(int mouseX, int mouseY, int mouseButton) {
        this.dragging = false;
    }

    @Override
    public void onGuiClose() {
        this.dragging = false;
    }

    private double round(double num, double increment) {
        double v = (double)Math.round(num / increment) * increment;
        BigDecimal bd = new BigDecimal(v);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}


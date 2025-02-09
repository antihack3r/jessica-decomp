/*
 * Decompiled with CFR 0.152.
 */
package jessica.gui.clickgui.panel.elements;

import java.util.ArrayList;
import java.util.List;
import jessica.Module;
import jessica.Wrapper;
import jessica.gui.clickgui.ClickGui;
import jessica.gui.clickgui.panel.Panel;
import jessica.gui.clickgui.panel.elements.BooleanElement;
import jessica.gui.clickgui.panel.elements.Element;
import jessica.gui.clickgui.panel.elements.ModeElement;
import jessica.gui.clickgui.panel.elements.NumberElement;
import jessica.module.ClickGuiModule;
import jessica.utils.RenderHelper;
import jessica.value.Value;
import jessica.value.ValueBool;
import jessica.value.ValueMode;
import jessica.value.ValueNumber;
import net.minecraft.client.gui.Gui;

public final class ModuleElement
extends Element {
    public final List<Element> components = new ArrayList<Element>();
    private final Module module;
    private final List<Element> elementValues = new ArrayList<Element>();
    private int opacity = 120;
    private int elementHeight;
    private double myHeight;
    public boolean extended;
    private Panel.AnimateEnumFormat state = Panel.AnimateEnumFormat.STATIC;

    public ModuleElement(Module module, Panel panel, int x, int y, int offsetY, int width, int height) {
        super(panel, x, y, offsetY, width, height);
        this.module = module;
        this.extended = false;
        int y2 = height;
        int yNumber = 13;
        if (!module.getValues().isEmpty()) {
            for (Value v : module.getValues()) {
                if (v instanceof ValueMode) {
                    this.elementValues.add(new ModeElement((ValueMode)v, this.getPanel(), x, y, y2, width, height));
                } else if (v instanceof ValueBool) {
                    this.elementValues.add(new BooleanElement((ValueBool)v, this.getPanel(), x, y, y2, width, height));
                } else if (v instanceof ValueNumber) {
                    this.elementValues.add(new NumberElement((ValueNumber)v, this.getPanel(), x, y, yNumber, width, height));
                }
                y2 += height;
                yNumber += 5;
            }
            this.calcHeight();
        }
    }

    @Override
    public void moved(int posX, int posY) {
        super.moved(posX, posY);
        this.elementValues.forEach(component -> component.moved((int)this.getFinishedX(), (int)this.getFinishedY()));
    }

    @Override
    public double getOffset() {
        return this.myHeight;
    }

    private void drawMyElement(int mouseX, int mouseY) {
        int elementY = 12;
        List<Element> elementValues = this.elementValues;
        int elementButtonSize = elementValues.size();
        int i = 0;
        while (i < elementButtonSize) {
            Element element = elementValues.get(i);
            if (!element.isHidden()) {
                element.setY(this.getY() + elementY);
                element.onDraw(mouseX, mouseY);
                elementY += 12;
            }
            ++i;
        }
    }

    private int calcHeight() {
        int height = 0;
        List<Element> elementValues = this.elementValues;
        int elementValuesSize = elementValues.size();
        int i = 0;
        while (i < elementValuesSize) {
            Element component = elementValues.get(i);
            if (!component.isHidden()) {
                height = (int)((double)height + ((double)component.getHeight() + component.getOffset()));
            }
            ++i;
        }
        return height;
    }

    @Override
    public void onDraw(int mouseX, int mouseY) {
        Panel parent = this.getPanel();
        int x = parent.getX() + this.getX();
        int y = parent.getY() + this.getY();
        int height = this.getHeight();
        int width = this.getWidth();
        boolean hovered = this.isMouseOver(mouseX, mouseY);
        this.handleScissorBox();
        this.elementHeight = this.calcHeight();
        int opacity = this.opacity;
        Gui.drawRect((double)x, (double)y, (double)(x + width), (double)(y + height) + this.getOffset(), -14474461);
        Gui.drawRect((double)x, (double)y, (double)(x + width), (double)y + 0.7, -15527149);
        if (hovered) {
            Gui.drawRect((double)x, (double)y, (double)(x + width), (double)(y + this.getHeight()), -1152167085);
        }
        int color = this.module.isToggled() ? ClickGui.getColor().getRGB() : -7;
        Wrapper.mc().fontRendererObj.drawString(this.module.getName(), (int)((float)x + 4.0f), (int)((float)y + (float)height / 2.0f - 4.0f), color);
        if (!this.elementValues.isEmpty() && this.myHeight <= 0.0) {
            Wrapper.mc().fontRendererObj.drawString(">", x + this.getWidth() - 4 - Wrapper.mc().fontRendererObj.getStringWidth(">"), y + this.getHeight() / 2 - 3 - Wrapper.mc().fontRendererObj.getStringWidth(">") / 2, -1);
        } else if (!this.elementValues.isEmpty() && this.myHeight > 0.0) {
            Wrapper.mc().fontRendererObj.drawString("<", x + this.getWidth() - 4 - Wrapper.mc().fontRendererObj.getStringWidth("<"), y + this.getHeight() / 2 - 3 - Wrapper.mc().fontRendererObj.getStringWidth("<") / 2, -1);
        }
        if (this.myHeight > 0.0) {
            if (parent.state != Panel.AnimateEnumFormat.RETRACTING) {
                if (!ClickGuiModule.rgb.getValue().booleanValue()) {
                    Gui.drawRect(x + 1, y + 12, x + 2, (int)((double)y + Math.min(this.myHeight, parent.myHeight) + (double)height), ClickGui.getColor().getRGB());
                } else {
                    Gui.drawRect(x + 1, y + 12, x + 2, (int)((double)y + Math.min(this.myHeight, parent.myHeight) + (double)height), ClickGui.rainbowEffect(8.0f, 1.0f).getRGB());
                }
            }
            this.drawMyElement(mouseX, mouseY);
        }
    }

    @Override
    public void onMouseClick(int mouseX, int mouseY, int mouseButton) {
        if (this.myHeight > 0.0) {
            List<Element> elementButton = this.elementValues;
            int elementButtonSize = elementButton.size();
            int i = 0;
            while (i < elementButtonSize) {
                elementButton.get(i).onMouseClick(mouseX, mouseY, mouseButton);
                ++i;
            }
        }
        if (this.isMouseOver(mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.module.toggle();
            } else if (mouseButton == 1 && !this.elementValues.isEmpty()) {
                this.extended = true;
                if (this.myHeight > 0.0 && (this.state == Panel.AnimateEnumFormat.EXPANDING || this.state == Panel.AnimateEnumFormat.STATIC)) {
                    this.state = Panel.AnimateEnumFormat.RETRACTING;
                } else if (this.myHeight < (double)this.elementHeight && (this.state == Panel.AnimateEnumFormat.EXPANDING || this.state == Panel.AnimateEnumFormat.STATIC)) {
                    this.state = Panel.AnimateEnumFormat.EXPANDING;
                }
            }
        }
    }

    @Override
    public void onMouseRelease(int mouseX, int mouseY, int mouseButton) {
        if (this.myHeight > 0.0) {
            List<Element> elementButton = this.elementValues;
            int elementButtonSize = elementButton.size();
            int i = 0;
            while (i < elementButtonSize) {
                elementButton.get(i).onMouseRelease(mouseX, mouseY, mouseButton);
                ++i;
            }
        }
    }

    @Override
    public void onKeyPress(int typedChar, int keyCode) {
        if (this.myHeight > 0.0) {
            List<Element> elementButton = this.elementValues;
            int elementButtonSize = elementButton.size();
            int i = 0;
            while (i < elementButtonSize) {
                elementButton.get(i).onKeyPress(typedChar, keyCode);
                ++i;
            }
        }
    }

    @Override
    public void onGuiClose() {
        if (this.myHeight > 0.0) {
            List<Element> elementButton = this.elementValues;
            int elementButtonSize = elementButton.size();
            int i = 0;
            while (i < elementButtonSize) {
                elementButton.get(i).onGuiClose();
                ++i;
            }
        }
    }

    private void handleScissorBox() {
        int elementHeight = this.elementHeight;
        switch (this.state) {
            case EXPANDING: {
                if (this.myHeight < (double)elementHeight) {
                    this.myHeight = RenderHelper.animate(elementHeight, this.myHeight, 0.34);
                } else if (this.myHeight >= (double)elementHeight) {
                    this.state = Panel.AnimateEnumFormat.STATIC;
                }
                this.myHeight = this.clamp(this.myHeight, elementHeight);
                break;
            }
            case RETRACTING: {
                if (this.myHeight > 0.0) {
                    this.myHeight = RenderHelper.animate(0.0, this.myHeight, 0.34);
                } else if (this.myHeight <= 0.0) {
                    this.state = Panel.AnimateEnumFormat.STATIC;
                }
                this.myHeight = this.clamp(this.myHeight, elementHeight);
                break;
            }
            case STATIC: {
                if (this.myHeight > 0.0 && this.myHeight != (double)elementHeight) {
                    this.myHeight = RenderHelper.animate(elementHeight, this.myHeight, 0.34);
                }
                this.myHeight = this.clamp(this.myHeight, elementHeight);
            }
        }
    }

    private double clamp(double a, double max) {
        if (a < 0.0) {
            return 0.0;
        }
        if (a > max) {
            return max;
        }
        return a;
    }
}


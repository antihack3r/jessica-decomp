/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package jessica.gui.clickgui.panel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.gui.clickgui.ClickGui;
import jessica.gui.clickgui.panel.elements.Element;
import jessica.gui.clickgui.panel.elements.ModuleElement;
import jessica.module.ClickGuiModule;
import jessica.utils.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public final class Panel {
    public static final int HEADER_SIZE = 20;
    public static final int HEADER_OFFSET = 2;
    private final Category category;
    private final List<Element> elements = new ArrayList<Element>();
    private final int width;
    public double myHeight;
    public boolean extended;
    private int x;
    private int lastX;
    private int y;
    private int lastY;
    private int height;
    public AnimateEnumFormat state = AnimateEnumFormat.STATIC;
    private boolean dragging;

    public Panel(Category category, int x, int y) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = 85;
        this.dragging = false;
        int elementY = 22;
        for (Module mod : Wrapper.getModules().values()) {
            if (mod.getCategory().equals((Object)this.category)) {
                ModuleElement element = new ModuleElement(mod, this, 0, 0, elementY, this.width, 12);
                this.elements.add(element);
                elementY += 32;
            }
            this.height = elementY - 20;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private void updateComponentHeight() {
        int componentY = 16;
        List<Element> componentList = this.elements;
        int componentListSize = componentList.size();
        int i = 0;
        while (i < componentListSize) {
            Element component = componentList.get(i);
            component.setY(componentY);
            componentY = (int)((double)componentY + ((double)component.getHeight() + component.getOffset()));
            ++i;
        }
        this.height = componentY - 16;
    }

    public final void onDraw(int mouseX, int mouseY) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int x = this.x;
        int y = this.y;
        int width = this.width;
        if (this.dragging) {
            this.x = this.lastX + mouseX;
            this.y = this.lastY + mouseY;
        }
        this.updateComponentHeight();
        this.handleScissorBox();
        double myHeight = this.myHeight;
        int backgroundColor = ClickGui.getColor().getRGB();
        if (!ClickGuiModule.rgb.getValue().booleanValue()) {
            Gui.drawRect(x, y, x + width, y + 16, new Color((int)ClickGuiModule.red.getDoubleValue(), (int)ClickGuiModule.green.getDoubleValue(), (int)ClickGuiModule.blue.getDoubleValue()).getRGB());
        } else {
            Gui.drawRect(x, y, x + width, y + 16, ClickGui.rainbowEffect(8.0f, 1.0f).getRGB());
        }
        Wrapper.mc().fontRendererObj.drawStringWithShadow(this.category.toString(), x + width / 2 - Wrapper.mc().fontRendererObj.getStringWidth(this.category.toString()) / 2, (int)((float)y + 10.0f - 5.0f), -1);
        if (this.extended) {
            GL11.glPushMatrix();
            GL11.glEnable((int)3089);
            List<Element> elements = this.elements;
            int i = 0;
            while (i < elements.size()) {
                RenderHelper.prepareScissorBox(x, y + 16, x + width, (float)((double)(y + 16) + myHeight));
                elements.get(i).onDraw(mouseX, mouseY);
                ++i;
            }
            GL11.glDisable((int)3089);
            GL11.glPopMatrix();
        }
    }

    public void onMouseClick(int mouseX, int mouseY, int mouseButton) {
        int x = this.x;
        int y = this.y;
        int width = this.width;
        double myHeight = this.myHeight;
        if (mouseX >= x - 2 && mouseX <= x + width + 2 && mouseY >= y && mouseY <= y + 16) {
            if (mouseButton == 0 && !this.dragging) {
                this.lastX = x - mouseX;
                this.lastY = y - mouseY;
                this.dragging = true;
            }
            if (mouseButton == 1) {
                this.extended = true;
                if (myHeight > 0.0 && (this.state == AnimateEnumFormat.EXPANDING || this.state == AnimateEnumFormat.STATIC)) {
                    this.state = AnimateEnumFormat.RETRACTING;
                } else if (myHeight < (double)(this.height + 2) && (this.state == AnimateEnumFormat.EXPANDING || this.state == AnimateEnumFormat.STATIC)) {
                    this.state = AnimateEnumFormat.EXPANDING;
                }
            }
        }
        List<Element> elements = this.elements;
        int elementsSize = elements.size();
        int i = 0;
        while (i < elementsSize) {
            Element component = elements.get(i);
            int componentY = component.getY();
            if ((double)componentY < myHeight + 16.0) {
                component.onMouseClick(mouseX, mouseY, mouseButton);
            }
            ++i;
        }
    }

    public final void onMouseRelease(int mouseX, int mouseY, int state) {
        if (state == 0) {
            this.dragging = false;
        }
        if (this.myHeight > 0.0) {
            List<Element> elements = this.elements;
            int elementsSize = elements.size();
            int i = 0;
            while (i < elementsSize) {
                elements.get(i).onMouseRelease(mouseX, mouseY, state);
                ++i;
            }
        }
    }

    public final void onKeyPress(char typedChar, int keyCode) {
        if (this.myHeight > 0.0) {
            List<Element> elements = this.elements;
            int elementsSize = elements.size();
            int i = 0;
            while (i < elementsSize) {
                elements.get(i).onKeyPress(typedChar, keyCode);
                ++i;
            }
        }
    }

    public final void onGuiClose() {
        if (this.myHeight > 0.0) {
            List<Element> elements = this.elements;
            int i = 0;
            while (i < elements.size()) {
                elements.get(i).onGuiClose();
                ++i;
            }
        }
    }

    private void handleScissorBox() {
        int height = this.height;
        switch (this.state) {
            case EXPANDING: {
                if (this.myHeight < (double)(height + 2)) {
                    this.myHeight = RenderHelper.animate(height + 2, this.myHeight, 0.5);
                    break;
                }
                if (!(this.myHeight >= (double)(height + 2))) break;
                this.state = AnimateEnumFormat.STATIC;
                break;
            }
            case RETRACTING: {
                if (this.myHeight > 0.0) {
                    this.myHeight = RenderHelper.animate(0.0, this.myHeight, 0.5);
                    break;
                }
                if (!(this.myHeight <= 0.0)) break;
                this.state = AnimateEnumFormat.STATIC;
                break;
            }
            case STATIC: {
                if (this.myHeight > 0.0 && this.myHeight != (double)(height + 2)) {
                    this.myHeight = RenderHelper.animate(height + 2, this.myHeight, 0.5);
                }
                this.myHeight = this.clamp(this.myHeight, height + 2);
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

    public static enum AnimateEnumFormat {
        RETRACTING,
        EXPANDING,
        STATIC;

    }
}


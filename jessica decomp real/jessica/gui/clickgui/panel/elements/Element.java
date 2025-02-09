/*
 * Decompiled with CFR 0.152.
 */
package jessica.gui.clickgui.panel.elements;

import jessica.gui.clickgui.panel.Panel;
import net.minecraft.client.Minecraft;

public abstract class Element {
    protected static final Minecraft MC = Minecraft.getMinecraft();
    private final Panel panel;
    private int x;
    private int y;
    private float finishedX;
    private float finishedY;
    private float offsetX;
    private float offsetY;
    private final int width;
    protected final int height;

    public Element(Panel panel, int x, int y, int offsetY, int width, int height) {
        this.panel = panel;
        this.x = x;
        this.y = y;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.finishedX = (float)x + this.offsetX;
        this.finishedY = y + offsetY;
    }

    public Panel getPanel() {
        return this.panel;
    }

    public void moved(int posX, int posY) {
        this.setX(posX);
        this.setY(posY);
        this.setFinishedX((float)this.getX() + this.getOffsetX());
        this.setFinishedY((float)this.getY() + this.getOffsetY());
    }

    public void onDraw(int mouseX, int mouseY) {
    }

    public void onMouseClick(int mouseX, int mouseY, int mouseButton) {
    }

    public void onMouseRelease(int mouseX, int mouseY, int state) {
    }

    public void onKeyPress(int typedChar, int keyCode) {
    }

    public void onGuiClose() {
    }

    public final boolean isMouseOver(int mouseX, int mouseY) {
        int x = this.panel.getX() + this.x;
        int y = this.panel.getY() + this.y;
        return mouseX >= x && mouseX < x + this.width && mouseY >= y && mouseY < y + this.height;
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public double getOffset() {
        return 0.0;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getWidth() {
        return this.width;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public float getFinishedX() {
        return this.finishedX;
    }

    public void setFinishedX(float finishedX) {
        this.finishedX = finishedX;
    }

    public float getFinishedY() {
        return this.finishedY;
    }

    public void setFinishedY(float finishedY) {
        this.finishedY = finishedY;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isHidden() {
        return false;
    }
}


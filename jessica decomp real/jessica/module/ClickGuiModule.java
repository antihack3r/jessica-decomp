/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.gui.clickgui.ClickGui;
import jessica.value.ValueBool;
import jessica.value.ValueNumber;

public class ClickGuiModule
extends Module {
    public static ValueNumber red = new ValueNumber("Red", 255.0, 0.0, 255.0);
    public static ValueNumber green = new ValueNumber("Green", 165.0, 0.0, 255.0);
    public static ValueNumber blue = new ValueNumber("Blue", 1.0, 0.0, 255.0);
    public static ValueBool rgb = new ValueBool("Rainbow", false);
    private ClickGui clickGUI;

    public ClickGuiModule() {
        super("ClickGui", 54, Category.ColorSettings);
        this.addValue(red);
        this.addValue(green);
        this.addValue(blue);
        this.addValue(rgb);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (this.clickGUI == null) {
            this.clickGUI = new ClickGui();
            this.clickGUI.init();
        }
        Wrapper.mc().displayGuiScreen(this.clickGUI);
        this.toggle();
    }
}


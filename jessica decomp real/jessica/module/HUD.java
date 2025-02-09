/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.value.ValueBool;
import jessica.value.ValueNumber;

public class HUD
extends Module {
    public static ValueNumber red = new ValueNumber("Red", 255.0, 0.0, 255.0);
    public static ValueNumber green = new ValueNumber("Green", 26.0, 0.0, 255.0);
    public static ValueNumber blue = new ValueNumber("Blue", 42.0, 0.0, 255.0);
    public static ValueBool rgb = new ValueBool("Rainbow", false);
    public static ValueBool logo = new ValueBool("Logo", true);
    public static ValueBool totaltime = new ValueBool("Total Playtime", true);

    public HUD() {
        super("HUD", 0, Category.ColorSettings);
        this.addValue(red);
        this.addValue(green);
        this.addValue(blue);
        this.addValue(rgb);
        this.addValue(logo);
        this.addValue(totaltime);
    }
}


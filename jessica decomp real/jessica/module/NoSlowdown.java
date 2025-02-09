/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.value.ValueNumber;

public class NoSlowdown
extends Module {
    public static ValueNumber value = new ValueNumber("NoSlow (%)", 40.0, 20.0, 100.0);

    public NoSlowdown() {
        super("NoSlowdown", 0, Category.Player);
        this.addValue(value);
    }
}


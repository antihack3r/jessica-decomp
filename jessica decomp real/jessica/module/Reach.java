/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.value.ValueNumber;

public class Reach
extends Module {
    public static ValueNumber reach = new ValueNumber("Reach", 4.6, 3.0, 5.5);

    public Reach() {
        super("Reach", 0, Category.Combat);
        this.addValue(reach);
    }
}


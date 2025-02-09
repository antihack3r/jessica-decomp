/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;

public class FastPlace
extends Module {
    public FastPlace() {
        super("FastPlace", 0, Category.Player);
    }

    @Override
    public void onUpdate() {
        Wrapper.mc().rightClickDelayTimer = 0;
    }

    @Override
    public void onDisable() {
        Wrapper.mc().rightClickDelayTimer = 4;
    }
}


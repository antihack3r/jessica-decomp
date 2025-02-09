/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;

public class Sprint
extends Module {
    public Sprint() {
        super("Sprint", 0, Category.Player);
    }

    @Override
    public void onUpdate() {
        Wrapper.player().setSprinting(true);
        super.onUpdate();
    }
}


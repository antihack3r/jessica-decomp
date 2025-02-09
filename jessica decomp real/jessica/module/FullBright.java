/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;

public class FullBright
extends Module {
    public FullBright() {
        super("FullBright", 0, Category.Render);
    }

    @Override
    public void onUpdate() {
        Wrapper.mc().gameSettings.gammaSetting = 100.0f;
    }

    @Override
    public void onDisable() {
        Wrapper.mc().gameSettings.gammaSetting = 0.0f;
    }
}


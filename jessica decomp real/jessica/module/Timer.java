/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.value.ValueNumber;

public class Timer
extends Module {
    ValueNumber timerhack = new ValueNumber("Timer", 1.0, 0.0, 5.0);

    public Timer() {
        super("Timer", 0, Category.Exploits);
        this.addValue(this.timerhack);
    }

    @Override
    public void onUpdate() {
        net.minecraft.util.Timer.elapsedTicks = (int)this.timerhack.getDoubleValue();
    }
}


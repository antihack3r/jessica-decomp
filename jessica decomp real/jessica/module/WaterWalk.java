/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;

public class WaterWalk
extends Module {
    public WaterWalk() {
        super("WaterWalk", 0, Category.Player);
    }

    @Override
    public void onUpdate() {
        if (!Wrapper.mc().gameSettings.keyBindSneak.isKeyDown() && Wrapper.player().isInWater() && !Wrapper.player().isCollidedHorizontally) {
            Wrapper.player().motionY = 0.09;
        }
    }
}


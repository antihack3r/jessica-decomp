/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;

public class Flight
extends Module {
    public Flight() {
        super("Flight", 0, Category.Player);
    }

    @Override
    public void onUpdate() {
        Wrapper.player().capabilities.isFlying = false;
        Wrapper.player().inWater = false;
        Wrapper.player().motionX = 0.0;
        Wrapper.player().motionY = 0.0;
        Wrapper.player().motionZ = 0.0;
        Wrapper.player().landMovementFactor = 1.0f;
        Wrapper.player().jumpMovementFactor = 1.0f;
        if (Wrapper.mc().gameSettings.keyBindJump.pressed) {
            Wrapper.player().motionY += 1.0;
        }
        if (Wrapper.mc().gameSettings.keyBindSneak.pressed) {
            Wrapper.player().motionY -= 1.0;
        }
    }
}


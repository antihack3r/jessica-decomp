/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.utils.EntityUtils;
import jessica.utils.Timer2;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumHand;

public class Glide
extends Module {
    public Timer2 ground = new Timer2();
    private static float speed = -1.0f;
    public static int tick = 0;
    public static boolean helpBool = true;
    public static int times = 0;

    public Glide() {
        super("Glide", 0, Category.Player);
    }

    @Override
    public void onDisable() {
        tick = 0;
        helpBool = true;
    }

    @Override
    public void onEnable() {
        if (!Wrapper.getModule("AAC").isToggled() && Wrapper.player() != null) {
            EntityUtils.damagePlayer();
            EntityPlayerSP player = Wrapper.player();
            player.motionX *= 0.2;
            EntityPlayerSP player2 = Wrapper.player();
            player2.motionZ *= 0.2;
            Wrapper.player().swingArm(EnumHand.MAIN_HAND);
        }
    }

    @Override
    public void onUpdate() {
        if (!Wrapper.getModule("AAC").isToggled()) {
            if (!Wrapper.player().capabilities.isFlying && Wrapper.player().fallDistance > 0.0f && !Wrapper.player().isSneaking()) {
                Wrapper.player().motionY = 0.0;
            }
            if (Wrapper.mc().gameSettings.keyBindSneak.pressed) {
                Wrapper.player().motionY = -0.11;
            }
            if (Wrapper.mc().gameSettings.keyBindJump.pressed) {
                Wrapper.player().motionY = 0.11;
            }
            if (this.ground.check(50.0f)) {
                Wrapper.player().onGround = false;
                this.ground.reset();
            }
        } else {
            if (Wrapper.player().onGround) {
                times = 0;
            }
            if (Wrapper.player().fallDistance > 0.0f && times <= 1) {
                if (tick > 0 && helpBool) {
                    Wrapper.player().motionY = 0.0;
                    tick = 0;
                } else {
                    ++tick;
                }
                if ((double)Wrapper.player().fallDistance >= 0.1) {
                    helpBool = false;
                }
                if ((double)Wrapper.player().fallDistance >= 0.4) {
                    helpBool = true;
                    Wrapper.player().fallDistance = 0.0f;
                }
            }
        }
    }
}


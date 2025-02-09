/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class JumpEffect3
extends Module {
    public JumpEffect3() {
        super("JumpEffect 8", 0, Category.Effects);
    }

    @Override
    public void onUpdate() {
        Wrapper.player().addPotionEffect(new PotionEffect(Potion.getPotionById(8), 9999999, 7));
    }

    @Override
    public void onDisable() {
        Wrapper.player().removePotionEffect(Potion.getPotionById(8));
    }
}


/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class MiningFatigueEffect
extends Module {
    public MiningFatigueEffect() {
        super("MiningFatigue", 0, Category.Effects);
    }

    @Override
    public void onEnable() {
        Wrapper.player().addPotionEffect(new PotionEffect(Potion.getPotionById(4), 9999999, 1));
    }

    @Override
    public void onDisable() {
        Wrapper.player().removePotionEffect(Potion.getPotionById(4));
    }
}


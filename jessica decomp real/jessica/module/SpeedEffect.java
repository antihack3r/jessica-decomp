/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class SpeedEffect
extends Module {
    public SpeedEffect() {
        super("SpeedEffect", 0, Category.Player);
    }

    @Override
    public void onEnable() {
        Wrapper.player().addPotionEffect(new PotionEffect(Potion.getPotionById(1), 9999999, 1));
    }

    @Override
    public void onUpdate() {
        if (!(Wrapper.getModule("AAC").isToggled() || Wrapper.getModule("Spartan").isToggled() || Wrapper.getModule("AAC").isToggled())) {
            Wrapper.player().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.14000000447034835);
        } else if (Wrapper.player().fallDistance > 0.0f) {
            Wrapper.player().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3900000044703484);
            EntityPlayerSP player = Wrapper.player();
            player.motionY -= 0.05;
        } else {
            Wrapper.player().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1f);
        }
    }

    @Override
    public void onDisable() {
        Wrapper.player().removePotionEffect(Potion.getPotionById(1));
        Wrapper.player().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1f);
    }
}


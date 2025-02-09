/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.utils.FriendManager;
import jessica.utils.Timer2;
import jessica.value.ValueBool;
import jessica.value.ValueNumber;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;

public class TriggerBot
extends Module {
    ValueNumber speed = new ValueNumber("Speed 1.8", 6.9, 0.0, 20.0);
    ValueBool players = new ValueBool("Players", true);
    ValueBool mobs = new ValueBool("Mobs", false);
    ValueBool invisibles = new ValueBool("Invisibles", false);
    Timer2 cooldown = new Timer2();

    public TriggerBot() {
        super("TriggerBot", 0, Category.Combat);
        this.addValue(this.speed);
        this.addValue(this.players);
        this.addValue(this.mobs);
        this.addValue(this.invisibles);
    }

    @Override
    public void onUpdate() {
        EntityLivingBase elvb;
        if (Wrapper.mc().player != null && (Wrapper.player().getCooldownPeriod() > 1.0f ? Wrapper.mc().player.getCooledAttackStrength(0.0f) == 1.0f : this.cooldown.check((float)(1000.0 / this.speed.getDoubleValue()))) && Wrapper.mc().pointedEntity instanceof EntityLivingBase && (elvb = (EntityLivingBase)Wrapper.mc().pointedEntity).getHealth() > 0.0f && !(elvb instanceof EntityArmorStand) && this.isValid(elvb)) {
            Wrapper.mc().clickMouse();
            Wrapper.player().swingArm(EnumHand.MAIN_HAND);
            if (Wrapper.player().getCooldownPeriod() > 1.0f) {
                Wrapper.player().resetCooldown();
            } else {
                this.cooldown.reset();
            }
        }
    }

    public boolean isValid(EntityLivingBase elb) {
        if (elb == null) {
            return false;
        }
        if (FriendManager.isFriend(elb.getName())) {
            return false;
        }
        if (elb == Wrapper.player()) {
            return false;
        }
        if (!elb.isEntityAlive()) {
            return false;
        }
        if (elb.getHealth() <= 0.0f) {
            return false;
        }
        if (elb instanceof EntityAnimal || elb instanceof EntityMob) {
            return this.mobs.getValue();
        }
        if (elb instanceof EntityPlayer) {
            return this.players.getValue();
        }
        if (elb.isInvisible()) {
            return this.invisibles.getValue();
        }
        if (elb instanceof EntityVillager) {
            return this.mobs.getValue();
        }
        return true;
    }
}


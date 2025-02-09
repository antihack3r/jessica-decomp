/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import java.util.ArrayList;
import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.utils.FriendManager;
import jessica.utils.Timer2;
import jessica.value.ValueBool;
import jessica.value.ValueNumber;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;

public class KillauraPlus
extends Module {
    public static ArrayList<Entity> PlayerAuraAAC = new ArrayList();
    public static ValueNumber range = new ValueNumber("Range", 4.0, 0.0, 6.0);
    ValueNumber speed = new ValueNumber("Speed 1.8", 6.9, 0.0, 20.0);
    ValueBool aim = new ValueBool("Aim", false);
    ValueBool crits = new ValueBool("FallingCrits", true);
    ValueBool antibot = new ValueBool("AntiBot", false);
    Timer2 cooldown = new Timer2();

    public KillauraPlus() {
        super("Killaura+", 0, Category.Combat);
        this.addValue(range);
        this.addValue(this.speed);
        this.addValue(this.aim);
        this.addValue(this.crits);
        this.addValue(this.antibot);
    }

    public void rotate(Entity entity) {
        float[] arrayOfFloat = KillauraPlus.getRotationsNeeded(entity);
        if (arrayOfFloat != null) {
            Wrapper.player().rotationYaw = arrayOfFloat[0];
            Wrapper.player().rotationPitch = arrayOfFloat[1] - 4.0f;
            Wrapper.player().renderYawOffset = arrayOfFloat[0];
            Wrapper.player().rotationYawHead = arrayOfFloat[0];
        }
    }

    public static float[] getRotationsNeeded(Entity paramEntity) {
        double d3;
        if (paramEntity == null) {
            return null;
        }
        double d1 = paramEntity.posX - Wrapper.player().posX;
        double d2 = paramEntity.posZ - Wrapper.player().posZ;
        if (paramEntity instanceof EntityLivingBase) {
            EntityLivingBase localEntityLivingBase = (EntityLivingBase)paramEntity;
            d3 = localEntityLivingBase.posY + (double)localEntityLivingBase.getEyeHeight() - (Wrapper.player().posY + (double)Wrapper.player().getEyeHeight() + 1.0);
        } else {
            d3 = (paramEntity.boundingBox.minY + paramEntity.boundingBox.maxY) / 2.0 - (Wrapper.player().posY + (double)Wrapper.player().getEyeHeight() + 1.0);
        }
        double d4 = MathHelper.sqrt(d1 * d1 + d2 * d2);
        float f1 = (float)(Math.atan2(d2, d1) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(Math.atan2(d3, d4) * 180.0 / Math.PI));
        return new float[]{Wrapper.player().rotationYaw + MathHelper.wrapDegrees(f1 - Wrapper.player().rotationYaw), Wrapper.player().rotationPitch + MathHelper.wrapDegrees(f2 - Wrapper.player().rotationPitch)};
    }

    @Override
    public void onUpdate() {
        for (Object o : Wrapper.world().loadedEntityList) {
            EntityPlayer e;
            if (!(o instanceof EntityPlayer) || (e = (EntityPlayer)o) == null || Wrapper.world() == null || !(Wrapper.player().getCooldownPeriod() > 1.0f ? Wrapper.mc().player.getCooledAttackStrength(0.0f) == 1.0f : this.cooldown.check((float)(1000.0 / this.speed.getDoubleValue()))) || !((double)Wrapper.player().getDistanceToEntity(e) <= range.getDoubleValue()) || !this.isValid(e)) continue;
            if (this.aim.getValue().booleanValue()) {
                this.rotate(e);
            }
            Wrapper.mc().playerController.attackEntity(Wrapper.player(), e);
            Wrapper.player().swingArm(EnumHand.MAIN_HAND);
            Wrapper.player().setSprinting(true);
            if (Wrapper.player().getCooldownPeriod() > 1.0f) {
                Wrapper.player().resetCooldown();
                continue;
            }
            this.cooldown.reset();
        }
    }

    @Override
    public void onEnable() {
        PlayerAuraAAC.clear();
        for (Object o : Wrapper.world().loadedEntityList) {
            EntityPlayer e;
            if (!(o instanceof EntityPlayer) || (e = (EntityPlayer)o) instanceof EntityPlayerSP || e.isDead || e.isInvisible()) continue;
            Wrapper.getFriends();
            if (FriendManager.isFriend(e.getName())) continue;
            PlayerAuraAAC.add(e);
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        PlayerAuraAAC.clear();
        super.onDisable();
    }

    public boolean isValid(EntityLivingBase elb) {
        if (elb == null) {
            return false;
        }
        if (this.antibot.getValue().booleanValue() && !PlayerAuraAAC.contains(elb)) {
            return false;
        }
        if (this.crits.getValue().booleanValue() && !((double)Wrapper.player().fallDistance > 0.0)) {
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
            return false;
        }
        if (elb instanceof EntityPlayer) {
            return true;
        }
        if (elb.isInvisible()) {
            return false;
        }
        return !(elb instanceof EntityVillager);
    }
}


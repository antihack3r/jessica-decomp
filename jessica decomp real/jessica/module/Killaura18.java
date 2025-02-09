/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.modules.settings.KillauraSettings;
import jessica.utils.EntityUtils;
import jessica.utils.FriendManager;
import jessica.utils.RenderUtils;
import jessica.utils.RotationUtils;
import jessica.utils.Timer2;
import jessica.value.ValueBool;
import jessica.value.ValueNumber;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;

public class Killaura18
extends Module {
    private ValueBool players = new ValueBool("Players", true);
    private ValueBool mobs = new ValueBool("Mobs", false);
    private ValueBool team = new ValueBool("Team", false);
    private ValueNumber FOV = new ValueNumber("FOV", 180.0, 15.0, 180.0);
    private ValueNumber yaw = new ValueNumber("yaw", 180.0, 15.0, 180.0);
    private ValueNumber pitch = new ValueNumber("pitch", 180.0, 0.0, 180.0);
    private ValueNumber killauraSpeed = new ValueNumber("Killaura Speed", 4.9, 0.9, 49.9);
    private ValueNumber killauraRange = new ValueNumber("Range", 4.9, 0.9, 9.9);
    public static boolean active = false;
    public int delay;
    public static ArrayList<Entity> PlayerAuraAAC = new ArrayList();
    public int delayPacket = 0;
    public static Entity entity = null;
    public static boolean blockHit = false;
    public static boolean timerTrue = false;
    private final Timer2 timer = new Timer2();
    private final Timer2 timer2 = new Timer2();
    public static boolean hit = false;
    public static Entity hitEntity = null;

    public Killaura18() {
        super("Killaura", 0, Category.Combat);
        this.addValue(this.players);
        this.addValue(this.mobs);
        this.addValue(this.team);
        this.addValue(this.killauraRange);
        this.addValue(this.killauraSpeed);
        this.addValue(this.FOV);
        this.addValue(this.yaw);
        this.addValue(this.pitch);
    }

    @Override
    public void onDisable() {
        hit = false;
        active = false;
        PlayerAuraAAC.clear();
        timerTrue = false;
        super.onDisable();
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
    public void onUpdate() {
        if (Wrapper.getModule("AAC").isToggled() || Wrapper.getModule("Spartan").isToggled() || Wrapper.getModule("HardCombat").isToggled()) {
            this.KillauraAAC1();
            this.KillauraAAC2();
        } else {
            this.Killaura1();
        }
        super.onUpdate();
    }

    public void Killaura1() {
        try {
            EntityLivingBase e = EntityUtils.getClosestEntityLiving();
            if (e == null) {
                timerTrue = false;
                return;
            }
            timerTrue = true;
            this.faceEntity2(e, (float)this.yaw.getDoubleValue(), (float)this.pitch.getDoubleValue());
            active = true;
            entity = e;
            float[] yp = EntityUtils.facePacketEntity(e, (float)this.yaw.getDoubleValue(), (float)this.pitch.getDoubleValue());
            RotationUtils.set(yp[0], yp[1] + 4.0f);
            if (this.timer.check((float)(1000.0 / this.killauraSpeed.getDoubleValue())) && e != null && Wrapper.mc().objectMouseOver.entityHit == e) {
                Wrapper.mc().playerController.attackEntity(Wrapper.player(), e);
                Wrapper.player().swingArm(EnumHand.MAIN_HAND);
                this.timer.reset();
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /*
     * Unable to fully structure code
     */
    public void Killaura5() {
        try {
            for (E o : Wrapper.world().loadedEntityList) {
                block6: {
                    if (!this.players.getValue().booleanValue() || !(o instanceof EntityPlayer)) break block6;
                    e = (EntityPlayer)o;
                    if (e instanceof EntityPlayerSP || e.getHealth() == 0.0f || !((double)RenderUtils.getDistanceFromMouse(e) <= this.FOV.getDoubleValue()) || !((double)Wrapper.player().getDistanceToEntity(e) <= this.killauraRange.getDoubleValue()) || e.isDead) ** GOTO lbl-1000
                    Wrapper.getFriends();
                    if (!FriendManager.isFriend(e.getName()) && Wrapper.player().canEntityBeSeen(e) && !(this.team.getValue() != false ? e.getTeam().isSameTeam(Wrapper.player().getTeam()) != false : e.isDead != false)) {
                        this.faceEntity2(e, (float)this.yaw.getDoubleValue(), (float)this.pitch.getDoubleValue());
                        Killaura18.active = true;
                    } else lbl-1000:
                    // 2 sources

                    {
                        Killaura18.active = false;
                    }
                }
                if (!this.mobs.getValue().booleanValue() || !(o instanceof EntityLivingBase)) continue;
                e2 = (EntityLivingBase)o;
                if (!(e2 instanceof EntityPlayer) && e2.getHealth() != 0.0f && (double)Wrapper.player().getDistanceToEntity(e2) <= this.killauraRange.getDoubleValue() && !e2.isDead && Wrapper.player().canEntityBeSeen(e2)) {
                    this.faceEntity2(e2, (float)this.yaw.getDoubleValue(), (float)this.pitch.getDoubleValue());
                    Killaura18.active = true;
                    continue;
                }
                Killaura18.active = false;
            }
        }
        catch (Exception var1_3) {
            // empty catch block
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void KillauraAAC1() {
        try {
            EntityLivingBase e2;
            Object o;
            Random rand = new Random();
            if (!this.timer.check((float)(1000.0 / (this.killauraSpeed.getDoubleValue() + 0.01 * (double)rand.nextInt(100))))) return;
            Iterator iterator = Wrapper.world().loadedEntityList.iterator();
            do {
                EntityPlayer e;
                if (!iterator.hasNext()) {
                    return;
                }
                o = iterator.next();
                if (!this.players.getValue().booleanValue() || !(o instanceof EntityPlayer) || (e = (EntityPlayer)o) instanceof EntityPlayerSP || !((double)Wrapper.player().getDistanceToEntity(e) <= this.killauraRange.getDoubleValue()) || e.isDead || !PlayerAuraAAC.contains(e) || e.isInvisible()) continue;
                Wrapper.getFriends();
                if (FriendManager.isFriend(e.getName()) || !Wrapper.player().canEntityBeSeen(e) || !((double)RenderUtils.getDistanceFromMouse(e) <= this.FOV.getDoubleValue()) || (this.team.getValue() != false ? e.getTeam().isSameTeam(Wrapper.player().getTeam()) : e.isDead || Wrapper.mc().objectMouseOver.entityHit == null)) continue;
                Wrapper.mc().clickMouse();
                this.timer.reset();
                return;
            } while (!this.mobs.getValue().booleanValue() || !(o instanceof EntityLivingBase) || (e2 = (EntityLivingBase)o) instanceof EntityPlayer || !((double)Wrapper.player().getDistanceToEntity(e2) <= this.killauraRange.getDoubleValue()) || e2.isDead || !Wrapper.player().canEntityBeSeen(e2) || !((double)RenderUtils.getDistanceFromMouse(e2) <= this.FOV.getDoubleValue()) || Wrapper.mc().objectMouseOver.entityHit == null);
            Wrapper.mc().clickMouse();
            this.timer.reset();
            return;
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void KillauraAAC5Packet() {
        try {
            EntityPlayer cEP = null;
            EntityLivingBase cELB = null;
            int i = 0;
            for (Entity o : Wrapper.world().loadedEntityList) {
                float[] yp;
                if (this.players.getValue().booleanValue() && o != null && o instanceof EntityPlayer && !(o instanceof EntityPlayerSP) && !Wrapper.player().isDead && (double)Wrapper.player().getDistanceToEntity(o) <= this.killauraRange.getDoubleValue() + 3.5 && PlayerAuraAAC.contains(o) && !o.isInvisible()) {
                    Wrapper.getFriends();
                    if (!FriendManager.isFriend(((EntityPlayer)o).getName()) && !(!KillauraSettings.throughWalls ? !Wrapper.player().canEntityBeSeen(o) : o.isDead) && (double)RenderUtils.getDistanceFromMouse(o) <= this.FOV.getDoubleValue() && !(this.team.getValue() != false ? ((EntityPlayer)o).getTeam().isSameTeam(Wrapper.player().getTeam()) : o.isDead || cEP != null && Wrapper.player().getDistanceToEntity(o) >= Wrapper.player().getDistanceToEntity(cEP))) {
                        cEP = (EntityPlayer)o;
                    }
                }
                if (this.mobs.getValue().booleanValue() && o != null && o instanceof EntityLivingBase && !(o instanceof EntityPlayer) && (cELB == null || Wrapper.player().getDistanceToEntity(o) < Wrapper.player().getDistanceToEntity(cELB)) && (double)Wrapper.player().getDistanceToEntity(o) <= this.killauraRange.getDoubleValue() + 3.5 && !o.isDead && (double)RenderUtils.getDistanceFromMouse(o) <= this.FOV.getDoubleValue() && !(KillauraSettings.throughWalls ? o.isDead : !Wrapper.player().canEntityBeSeen(o))) {
                    cELB = (EntityLivingBase)o;
                }
                if (i != Wrapper.world().loadedEntityList.size() - 1) {
                    ++i;
                    continue;
                }
                if (cEP == null && cELB == null) {
                    active = false;
                    timerTrue = false;
                    RotationUtils.yaw = Wrapper.player().rotationYaw;
                    RotationUtils.pitch = Wrapper.player().rotationPitch;
                }
                if (this.players.getValue().booleanValue() && cEP != null && !(this.mobs.getValue() != false ? cELB != null && Wrapper.player().getDistanceToEntity(cEP) > Wrapper.player().getDistanceToEntity(cELB) : this.players.getValue() == false)) {
                    entity = cEP;
                    timerTrue = true;
                    active = true;
                    yp = EntityUtils.facePacketEntity(entity, (float)this.yaw.getDoubleValue(), (float)this.pitch.getDoubleValue());
                    RotationUtils.set(yp[0], yp[1] + 8.0f);
                    if (this.timer2.check((float)(1000.0 / (this.killauraSpeed.getDoubleValue() + 0.01 * (double)new Random().nextInt(100)))) && hitEntity != null && (double)Wrapper.player().getDistanceToEntity(hitEntity) <= this.killauraRange.getDoubleValue()) {
                        Wrapper.mc().playerController.attackEntity(Wrapper.player(), hitEntity);
                        Wrapper.player().swingArm(EnumHand.MAIN_HAND);
                        hit = false;
                        hitEntity = null;
                        this.timer2.reset();
                        break;
                    }
                }
                if (!this.mobs.getValue().booleanValue() || cELB == null || (this.players.getValue() != false ? cEP != null && Wrapper.player().getDistanceToEntity(cELB) > Wrapper.player().getDistanceToEntity(cEP) : this.mobs.getValue() == false)) continue;
                entity = cELB;
                timerTrue = true;
                active = true;
                yp = EntityUtils.facePacketEntity(entity, (float)this.yaw.getDoubleValue(), (float)this.pitch.getDoubleValue());
                RotationUtils.set(yp[0], yp[1]);
                if (!this.timer2.check((float)(1000.0 / (this.killauraSpeed.getDoubleValue() + 0.01 * (double)new Random().nextInt(100)))) || hitEntity == null || !((double)Wrapper.player().getDistanceToEntity(hitEntity) <= this.killauraRange.getDoubleValue())) continue;
                Wrapper.mc().playerController.attackEntity(Wrapper.player(), hitEntity);
                Wrapper.player().swingArm(EnumHand.MAIN_HAND);
                hit = false;
                hitEntity = null;
                this.timer2.reset();
                break;
            }
            if (entity == null) {
                timerTrue = false;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    /*
     * Unable to fully structure code
     */
    public void KillauraAAC2() {
        block9: {
            if (!Killaura18.timerTrue) {
                Wrapper.player().rotationYaw2 = Wrapper.player().rotationYaw;
                Wrapper.player().rotationPitch2 = Wrapper.player().rotationPitch;
            }
            ent = null;
            try {
                if (!this.timer2.check((float)new Random().nextInt(20) / 100.0f)) break block9;
                for (E o : Wrapper.world().loadedEntityList) {
                    if (!this.players.getValue().booleanValue() || !(o instanceof EntityPlayer) || (e = (EntityPlayer)o) instanceof EntityPlayerSP || !((double)Wrapper.player().getDistanceToEntity(e) <= this.killauraRange.getDoubleValue()) || e.isDead || !Killaura18.PlayerAuraAAC.contains(e) || e.isInvisible()) ** GOTO lbl-1000
                    Wrapper.getFriends();
                    if (!FriendManager.isFriend(e.getName()) && Wrapper.player().canEntityBeSeen(e) && (double)RenderUtils.getDistanceFromMouse(e) <= this.FOV.getDoubleValue() && !e.isInvisible() && !(this.team.getValue() == false ? e.isDead != false : e.getTeam().isSameTeam(Wrapper.player().getTeam()) != false)) {
                        ent = e;
                        if (Wrapper.mc().objectMouseOver.entityHit != ent) {
                            Killaura18.timerTrue = true;
                            this.faceEntity2(e, (float)this.yaw.getDoubleValue() + (float)new Random().nextInt(1000) / 100.0f, (float)this.pitch.getDoubleValue() + (float)new Random().nextInt(1000) / 100.0f);
                        }
                        this.timer2.reset();
                    } else lbl-1000:
                    // 2 sources

                    {
                        if (!this.mobs.getValue().booleanValue() || !(o instanceof EntityLivingBase) || (e2 = (EntityLivingBase)o) instanceof EntityPlayer || !((double)Wrapper.player().getDistanceToEntity(e2) <= this.killauraRange.getDoubleValue()) || e2.isDead || !Wrapper.player().canEntityBeSeen(e2) || !((double)RenderUtils.getDistanceFromMouse(e2) <= this.FOV.getDoubleValue()) || (this.team.getValue() == false ? e2.isDead != false : e2.getTeam().isSameTeam(Wrapper.player().getTeam()) != false)) continue;
                        ent = e2;
                        if (Wrapper.mc().objectMouseOver.entityHit != ent) {
                            Killaura18.timerTrue = true;
                            this.faceEntity2(e2, (float)this.yaw.getDoubleValue() + (float)new Random().nextInt(1000) / 100.0f, (float)this.pitch.getDoubleValue() + (float)new Random().nextInt(1000) / 100.0f);
                        }
                        this.timer2.reset();
                    }
                    break;
                }
            }
            catch (Exception var2_4) {
                // empty catch block
            }
        }
        if (ent == null) {
            Killaura18.timerTrue = false;
        }
    }

    public void faceEntity2(Entity par1Entity, float par2, float par3) {
        double var7;
        Random rand = new Random();
        double var4 = par1Entity.posX - Wrapper.player().posX;
        double var5 = par1Entity.posZ - Wrapper.player().posZ;
        if (par1Entity instanceof EntityLivingBase) {
            EntityLivingBase var6 = (EntityLivingBase)par1Entity;
            var7 = var6.posY + ((double)var6.getEyeHeight() - (Wrapper.getModule("AAC").isToggled() || Wrapper.getModule("HardCombat").isToggled() || Wrapper.getModule("Spartan").isToggled() ? 0.1 + 0.01 * (double)rand.nextInt(40) : 0.6)) - (Wrapper.player().posY + (double)Wrapper.player().getEyeHeight());
        } else {
            var7 = (par1Entity.boundingBox.minY + par1Entity.boundingBox.maxY) / 2.0 - (Wrapper.player().posY + (double)Wrapper.player().getEyeHeight());
        }
        double var8 = MathHelper.sqrt(var4 * var4 + var5 * var5);
        float var9 = (float)(Math.atan2(var5, var4) * 180.0 / Math.PI) - 90.0f;
        float var10 = (float)(-(Math.atan2(var7, var8) * 180.0 / Math.PI));
        Wrapper.player().rotationPitch2 = EntityLiving.updateRotation(Wrapper.player().rotationPitch2, var10, par3);
        Wrapper.player().rotationYaw2 = EntityLiving.updateRotation(Wrapper.player().rotationYaw2, var9, par2);
    }
}


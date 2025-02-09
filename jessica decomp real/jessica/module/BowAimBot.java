/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import java.util.ArrayList;
import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.modules.settings.BowAimBotSettings;
import jessica.utils.EntityUtils;
import jessica.utils.FriendManager;
import jessica.utils.RotationUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class BowAimBot
extends Module {
    public static ArrayList<Entity> BowAimAAC;
    public static double FOV;
    Entity target;
    private float velocity = 1.0f;
    private static float serverYaw;
    private static float serverPitch;

    static {
        FOV = 180.0;
        BowAimAAC = new ArrayList();
    }

    public BowAimBot() {
        super("BowAimBot", 0, Category.Combat);
    }

    @Override
    public void onDisable() {
        BowAimAAC.clear();
    }

    @Override
    public void onEnable() {
        BowAimAAC.clear();
        for (Object o : Wrapper.world().loadedEntityList) {
            EntityPlayer e;
            if (!(o instanceof EntityPlayer) || (e = (EntityPlayer)o) instanceof EntityPlayerSP || e.isDead || e.isInvisible()) continue;
            Wrapper.getFriends();
            if (FriendManager.isFriend(e.getName())) continue;
            BowAimAAC.add(e);
        }
        super.onEnable();
    }

    @Override
    public void onUpdate() {
        ItemStack item = Wrapper.player().getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
        Item itembow = item.getItem();
        if (itembow == Items.BOW && Wrapper.mc().gameSettings.keyBindUseItem.pressed) {
            this.BowAimbot2();
        }
    }

    public void BowAimbot2() {
        super.onUpdate();
        this.target = BowAimBot.getClosestEntityLiving();
        if (this.target == null || !Wrapper.player().canEntityBeSeen(this.target)) {
            return;
        }
        this.velocity = (float)(72000 - Wrapper.player().getItemInUseCount()) / 20.0f;
        this.velocity = (this.velocity * this.velocity + this.velocity * 2.0f) / 3.0f;
        if (this.velocity > 1.0f) {
            this.velocity = 1.0f;
        }
        if (BowAimBotSettings.predict) {
            double d = EntityUtils.getEyesPos().distanceTo(this.target.boundingBox.getCenter());
            double posX = this.target.posX + (this.target.posX - this.target.prevPosX) * d - Wrapper.player().posX;
            double posY = this.target.posY + (this.target.posY - this.target.prevPosY) * d + (double)this.target.height * 0.5 - Wrapper.player().posY - (double)Wrapper.player().getEyeHeight();
            double posZ = this.target.posZ + (this.target.posZ - this.target.prevPosZ) * d - Wrapper.player().posZ;
            Wrapper.player().rotationYaw = (float)Math.toDegrees(Math.atan2(posZ, posX)) - 90.0f;
            double hDistance = Math.sqrt(posX * posX + posZ * posZ);
            double hDistanceSq = hDistance * hDistance;
            float g = 0.006f;
            float velocitySq = this.velocity * this.velocity;
            float velocityPow4 = velocitySq * velocitySq;
            float neededPitch = (float)(-Math.toDegrees(Math.atan(((double)velocitySq - Math.sqrt((double)velocityPow4 - (double)0.006f * ((double)0.006f * hDistanceSq + 2.0 * posY * (double)velocitySq))) / ((double)0.006f * hDistance))));
            if (Float.isNaN(neededPitch)) {
                this.faceEntityClient(this.target);
            } else {
                Wrapper.player().rotationPitch = neededPitch;
            }
        } else {
            this.faceEntity(this.target, BowAimBotSettings.yaw, BowAimBotSettings.pitch);
            float[] yp = EntityUtils.facePacketEntity(this.target, BowAimBotSettings.yaw, BowAimBotSettings.pitch);
            RotationUtils.set(yp[0], yp[1] + 4.0f);
        }
    }

    private void faceEntity(Entity par1Entity, float par2, float par3) {
        double diffY;
        double diffX = par1Entity.posX - Wrapper.mc().player.posX;
        double diffZ = par1Entity.posZ - Wrapper.mc().player.posZ;
        if (par1Entity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)par1Entity;
            diffY = entityLivingBase.posY + (double)entityLivingBase.getEyeHeight() - (Wrapper.mc().player.posY + (double)Wrapper.mc().player.getEyeHeight());
        } else {
            diffY = (par1Entity.boundingBox.minY + par1Entity.boundingBox.maxY) / 2.0 - (Wrapper.mc().player.posY + (double)Wrapper.mc().player.getEyeHeight());
        }
        double dist = MathHelper.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float)(-(Math.atan2(diffY, dist) * 180.0 / Math.PI));
        Wrapper.player().rotationPitch = EntityLiving.updateRotation(Wrapper.player().rotationPitch, pitch, par3);
        Wrapper.player().rotationYaw = EntityLiving.updateRotation(Wrapper.player().rotationYaw, yaw, par2);
    }

    private static EntityLivingBase getClosestEntityLiving() {
        EntityLivingBase closestEntity = null;
        for (Entity o : Wrapper.world().loadedEntityList) {
            EntityLivingBase e2;
            if (BowAimBotSettings.players && o instanceof EntityPlayer) {
                EntityLivingBase e = (EntityLivingBase)o;
                if (Wrapper.getModule("AAC").isToggled()) {
                    if (!(e instanceof EntityPlayerSP) && (closestEntity == null || Wrapper.player().getDistanceToEntity(e) < Wrapper.player().getDistanceToEntity(closestEntity) && BowAimAAC.contains(e) && Wrapper.player().canEntityBeSeen(e))) {
                        closestEntity = e;
                    }
                } else if (!(e instanceof EntityPlayerSP) && (closestEntity == null || Wrapper.player().getDistanceToEntity(e) < Wrapper.player().getDistanceToEntity(closestEntity) && Wrapper.player().canEntityBeSeen(e))) {
                    closestEntity = e;
                }
            }
            if (!BowAimBotSettings.mobs || !(o instanceof EntityLivingBase) || (e2 = (EntityLivingBase)o) instanceof EntityPlayer || e2 instanceof EntityPlayerSP || closestEntity != null && (!(Wrapper.player().getDistanceToEntity(e2) < Wrapper.player().getDistanceToEntity(closestEntity)) || !Wrapper.player().canEntityBeSeen(e2))) continue;
            closestEntity = e2;
        }
        return closestEntity;
    }

    private float limitAngleChange(float current, float intended, float maxChange) {
        float change = MathHelper.wrapDegrees(intended - current);
        change = MathHelper.clamp(change, -maxChange, maxChange);
        return MathHelper.wrapDegrees(current + change);
    }

    private float[] getNeededRotations(Vec3d vec) {
        Vec3d eyesPos = EntityUtils.getEyesPos();
        double diffX = vec.xCoord - eyesPos.xCoord;
        double diffY = vec.yCoord - eyesPos.yCoord;
        double diffZ = vec.zCoord - eyesPos.zCoord;
        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        return new float[]{MathHelper.wrapDegrees(yaw), MathHelper.wrapDegrees(pitch)};
    }

    private boolean faceVectorClient(Vec3d vec) {
        float[] rotations = this.getNeededRotations(vec);
        float oldYaw = Wrapper.player().prevRotationYaw;
        float oldPitch = Wrapper.player().prevRotationPitch;
        Wrapper.player().rotationYaw = this.limitAngleChange(oldYaw, rotations[0], 30.0f);
        Wrapper.player().rotationPitch = rotations[1];
        return Math.abs(oldYaw - rotations[0]) + Math.abs(oldPitch - rotations[1]) < 1.0f;
    }

    private Vec3d getServerLookVec() {
        float f = MathHelper.cos(-serverYaw * ((float)Math.PI / 180) - (float)Math.PI);
        float f2 = MathHelper.sin(-serverYaw * ((float)Math.PI / 180) - (float)Math.PI);
        float f3 = -MathHelper.cos(-serverPitch * ((float)Math.PI / 180));
        float f4 = MathHelper.sin(-serverPitch * ((float)Math.PI / 180));
        return new Vec3d(f2 * f3, f4, f * f3);
    }

    private boolean faceEntityClient(Entity entity) {
        Vec3d eyesPos = EntityUtils.getEyesPos();
        Vec3d lookVec = this.getServerLookVec();
        AxisAlignedBB bb = entity.boundingBox;
        return this.faceVectorClient(bb.getCenter()) || bb.calculateIntercept(eyesPos, eyesPos.add(lookVec.scale(6.0))) != null;
    }
}


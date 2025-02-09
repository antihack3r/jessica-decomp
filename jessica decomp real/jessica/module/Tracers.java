/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package jessica.module;

import java.util.ArrayList;
import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.utils.FriendManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class Tracers
extends Module {
    private final ArrayList<EntityPlayer> players = new ArrayList();

    public Tracers() {
        super("Tracers", 0, Category.Render);
    }

    @Override
    public void onRender(double partialTicks) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glPushMatrix();
        for (Entity entities : Wrapper.world().loadedEntityList) {
            if (entities == Wrapper.player() || entities == null || !(entities instanceof EntityPlayer)) continue;
            Tracers.drawTracerLine(entities);
        }
        GL11.glPopMatrix();
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    public static void drawTracerLine(Entity entities) {
        double posX = entities.prevPosX + (entities.posX - entities.prevPosX) - Wrapper.mc().getRenderManager().renderPosX;
        double posY = entities.prevPosY + (entities.posY - entities.prevPosY) - Wrapper.mc().getRenderManager().renderPosY;
        double posZ = entities.prevPosZ + (entities.posZ - entities.prevPosZ) - Wrapper.mc().getRenderManager().renderPosZ;
        Vec3d eyes = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-((float)Math.toRadians(Wrapper.player().rotationPitch))).rotateYaw(-((float)Math.toRadians(Wrapper.player().rotationYaw)));
        GL11.glColor4f((float)2.0f, (float)0.0f, (float)0.0f, (float)0.5f);
        GL11.glBegin((int)1);
        Wrapper.getFriends();
        if (FriendManager.isFriend(entities.getName())) {
            GL11.glColor4f((float)0.0f, (float)0.0f, (float)1.0f, (float)0.5f);
        } else {
            GL11.glColor4f((float)2.0f, (float)0.0f, (float)0.0f, (float)0.5f);
        }
        GL11.glVertex3d((double)eyes.xCoord, (double)((double)Wrapper.player().getEyeHeight() + eyes.yCoord), (double)eyes.zCoord);
        GL11.glVertex3d((double)posX, (double)posY, (double)posZ);
        GL11.glEnd();
    }
}


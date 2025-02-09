/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.utils.FriendManager;
import jessica.utils.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class PlayerESP
extends Module {
    private int playerBox;

    public PlayerESP() {
        super("PlayerESP", 0, Category.Render);
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
        GL11.glTranslated((double)(-Wrapper.mc().getRenderManager().renderPosX), (double)(-Wrapper.mc().getRenderManager().renderPosY), (double)(-Wrapper.mc().getRenderManager().renderPosZ));
        this.playerBox = GL11.glGenLists((int)1);
        GL11.glNewList((int)this.playerBox, (int)4864);
        AxisAlignedBB bb = new AxisAlignedBB(-0.5, 0.0, -0.5, 0.5, 1.0, 0.5);
        RenderUtils.drawOutlinedBox(bb);
        GL11.glEndList();
        for (Entity entities : Wrapper.world().loadedEntityList) {
            if (entities == Wrapper.player() || entities == null || !(entities instanceof EntityPlayer)) continue;
            this.renderBoxes(entities);
        }
        GL11.glPopMatrix();
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    private void renderBoxes(Entity entities) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)(entities.prevPosX + (entities.posX - entities.prevPosX) * 1.0), (double)(entities.prevPosY + (entities.posY - entities.prevPosY) * 1.0), (double)(entities.prevPosZ + (entities.posZ - entities.prevPosZ) * 1.0));
        GL11.glScaled((double)((double)entities.width + 0.1), (double)((double)entities.height + 0.1), (double)((double)entities.width + 0.1));
        Wrapper.getFriends();
        if (FriendManager.isFriend(entities.getName())) {
            GL11.glColor4f((float)0.0f, (float)0.0f, (float)1.0f, (float)0.5f);
        } else {
            GL11.glColor4f((float)2.0f, (float)0.0f, (float)0.0f, (float)0.5f);
        }
        GL11.glCallList((int)this.playerBox);
        GL11.glPopMatrix();
    }
}


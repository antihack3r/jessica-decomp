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
import jessica.utils.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class ESP2D
extends Module {
    public ESP2D() {
        super("PlayerESP2D", 0, Category.Render);
    }

    @Override
    public void onRender(double partilTicks) {
        for (Object i : Wrapper.world().loadedEntityList) {
            Entity player = (Entity)i;
            if (player == Wrapper.player() || player == null || !(player instanceof EntityPlayer)) continue;
            float posX = (float)((double)((float)player.lastTickPosX) + (player.posX - player.lastTickPosX) * (double)Wrapper.mc().timer.field_194147_b);
            float posY = (float)((double)((float)player.lastTickPosY) + (player.posY - player.lastTickPosY) * (double)Wrapper.mc().timer.field_194147_b);
            float posZ = (float)((double)((float)player.lastTickPosZ) + (player.posZ - player.lastTickPosZ) * (double)Wrapper.mc().timer.field_194147_b);
            this.draw2D(player, (double)posX - Wrapper.mc().getRenderManager().renderPosX, (double)posY - Wrapper.mc().getRenderManager().renderPosY, (double)posZ - Wrapper.mc().getRenderManager().renderPosZ);
        }
    }

    public void draw2D(Entity e, double posX, double posY, double posZ) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(posX, posY, posZ);
        GL11.glNormal3f((float)0.0f, (float)0.0f, (float)0.0f);
        GlStateManager.rotate(-Wrapper.mc().getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.scale(-0.1, -0.1, 0.1);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GlStateManager.depthMask(true);
        Wrapper.getFriends();
        RenderHelper.drawLine2D(-5.0, -20.0, 5.0, -20.0, 2.0f, FriendManager.isFriend(e.getName()) ? 210 : 0xD20000);
        RenderHelper.drawLine2D(5.0, -20.0, 5.0, -0.0, 2.0f, FriendManager.isFriend(e.getName()) ? 210 : 0xD20000);
        RenderHelper.drawLine2D(-5.0, -20.0, -5.0, -0.0, 2.0f, FriendManager.isFriend(e.getName()) ? 210 : 0xD20000);
        RenderHelper.drawLine2D(-5.0, -0.0, 5.0, -0.0, 2.0f, FriendManager.isFriend(e.getName()) ? 210 : 0xD20000);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)2896);
        GlStateManager.popMatrix();
    }
}


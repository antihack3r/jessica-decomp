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
import jessica.utils.RenderHelper;
import jessica.value.ValueBool;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class HealthLine
extends Module {
    private ValueBool players = new ValueBool("Players", true);
    private ValueBool mobs = new ValueBool("Mobs", false);

    public HealthLine() {
        super("HealthLine", 0, Category.Render);
        this.addValue(this.players);
        this.addValue(this.mobs);
    }

    @Override
    public void onRender(double partilTicks) {
        for (Object o : Wrapper.world().loadedEntityList) {
            EntityLivingBase e2;
            float posZ;
            float posY;
            float posX;
            EntityPlayer player;
            if (this.players.getValue().booleanValue() && o instanceof EntityPlayer && (player = (EntityPlayer)o) != Wrapper.player() && player != null) {
                posX = (float)((double)((float)player.lastTickPosX) + (player.posX - player.lastTickPosX) * (double)Wrapper.mc().timer.field_194147_b);
                posY = (float)((double)((float)player.lastTickPosY) + (player.posY - player.lastTickPosY) * (double)Wrapper.mc().timer.field_194147_b);
                posZ = (float)((double)((float)player.lastTickPosZ) + (player.posZ - player.lastTickPosZ) * (double)Wrapper.mc().timer.field_194147_b);
                this.drawHealthLine(player, (double)posX - Wrapper.mc().getRenderManager().renderPosX, (double)posY - Wrapper.mc().getRenderManager().renderPosY, (double)posZ - Wrapper.mc().getRenderManager().renderPosZ);
            }
            if (!this.mobs.getValue().booleanValue() || !(o instanceof EntityLivingBase) || (e2 = (EntityLivingBase)o) instanceof EntityPlayer || e2 instanceof EntityPlayerSP || !(e2 instanceof EntityMob) && !(e2 instanceof EntityAnimal)) continue;
            posX = (float)((double)((float)e2.lastTickPosX) + (e2.posX - e2.lastTickPosX) * (double)Wrapper.mc().timer.field_194147_b);
            posY = (float)((double)((float)e2.lastTickPosY) + (e2.posY - e2.lastTickPosY) * (double)Wrapper.mc().timer.field_194147_b);
            posZ = (float)((double)((float)e2.lastTickPosZ) + (e2.posZ - e2.lastTickPosZ) * (double)Wrapper.mc().timer.field_194147_b);
            this.drawHealthLine(e2, (double)posX - Wrapper.mc().getRenderManager().renderPosX, (double)posY - Wrapper.mc().getRenderManager().renderPosY, (double)posZ - Wrapper.mc().getRenderManager().renderPosZ);
        }
    }

    public void drawHealthLine(Entity e, double posX, double posY, double posZ) {
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
        float hp1 = ((EntityLivingBase)e).getMaxHealth() / 100.0f;
        float hp100 = ((EntityLivingBase)e).getHealth() / hp1;
        float normalhp = hp100 / 5.0f;
        int color = 0;
        if ((double)hp100 >= 75.0) {
            color = RenderHelper.setColor(65280);
        } else if ((double)hp100 > 50.0 && (double)hp100 < 75.0) {
            color = RenderHelper.setColor(0xFFFF00);
        } else if ((double)hp100 > 25.0 && (double)hp100 <= 50.0) {
            color = RenderHelper.setColor(16753920);
        } else if ((double)hp100 <= 25.0) {
            color = RenderHelper.setColor(0xFF0000);
        }
        RenderHelper.setColor(color);
        RenderHelper.drawRect(-8.0, hp1 - (normalhp + hp1), -5.0, -0.0);
        RenderHelper.drawLine2D(-5.0, -20.0, -5.0, -0.0, 1.0f, color);
        RenderHelper.drawLine2D(-8.0, -20.0, -8.0, -0.0, 1.0f, color);
        RenderHelper.drawLine2D(-8.0, -0.0, -5.0, -0.0, 1.0f, color);
        RenderHelper.drawLine2D(-8.0, -20.0, -5.0, -20.0, 1.0f, color);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)2896);
        GlStateManager.popMatrix();
    }
}


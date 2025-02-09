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
import jessica.utils.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class ItemESP
extends Module {
    private int itemBox;

    public ItemESP() {
        super("ItemESP", 0, Category.Render);
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
        this.itemBox = GL11.glGenLists((int)1);
        GL11.glNewList((int)this.itemBox, (int)4864);
        GL11.glEndList();
        for (Entity e : Wrapper.world().loadedEntityList) {
            if (!(e instanceof EntityItem)) continue;
            EntityItem item = (EntityItem)e;
            this.renderBoxes(item);
        }
        GL11.glPopMatrix();
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    private void renderBoxes(EntityItem item) {
        GL11.glPushMatrix();
        GL11.glColor4d((double)0.0, (double)0.0, (double)1.0, (double)0.5);
        AxisAlignedBB bb = new AxisAlignedBB(item.boundingBox.minX - 0.05 - item.posX + (item.posX - Wrapper.mc().getRenderManager().renderPosX), item.boundingBox.minY - item.posY + (item.posY - Wrapper.mc().getRenderManager().renderPosY), item.boundingBox.minZ - 0.05 - item.posZ + (item.posZ - Wrapper.mc().getRenderManager().renderPosZ), item.boundingBox.maxX + 0.05 - item.posX + (item.posX - Wrapper.mc().getRenderManager().renderPosX), item.boundingBox.maxY + 0.1 - item.posY + (item.posY - Wrapper.mc().getRenderManager().renderPosY), item.boundingBox.maxZ + 0.05 - item.posZ + (item.posZ - Wrapper.mc().getRenderManager().renderPosZ));
        RenderUtils.drawOutlinedBox(bb);
        GL11.glCallList((int)this.itemBox);
        GL11.glPopMatrix();
    }
}


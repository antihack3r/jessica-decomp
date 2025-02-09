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
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class ChestESP
extends Module {
    private int chestBox;

    public ChestESP() {
        super("ChestESP", 0, Category.Render);
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
        this.chestBox = GL11.glGenLists((int)1);
        GL11.glNewList((int)this.chestBox, (int)4864);
        GL11.glEndList();
        for (Object o : Wrapper.world().loadedTileEntityList) {
            if (!(o instanceof TileEntityChest)) continue;
            TileEntityChest chest = (TileEntityChest)o;
            this.renderBoxes(chest);
        }
        GL11.glPopMatrix();
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    private void renderBoxes(TileEntityChest chest) {
        GL11.glPushMatrix();
        double x = (double)chest.getPos().getX() - Wrapper.mc().getRenderManager().renderPosX;
        double y = (double)chest.getPos().getY() - Wrapper.mc().getRenderManager().renderPosY;
        double z = (double)chest.getPos().getZ() - Wrapper.mc().getRenderManager().renderPosZ;
        GL11.glColor4f((float)2.0f, (float)2.0f, (float)0.0f, (float)0.5f);
        AxisAlignedBB bb = new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0);
        RenderUtils.drawOutlinedBox(bb);
        GL11.glCallList((int)this.chestBox);
        GL11.glPopMatrix();
    }
}


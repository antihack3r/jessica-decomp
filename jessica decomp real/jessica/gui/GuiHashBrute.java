/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package jessica.gui;

import java.io.IOException;
import jessica.gui.MD5_Brute;
import jessica.gui.MineLand_Hash_Brute;
import jessica.gui.ProstoCraft_Hash_Brute;
import jessica.gui.YottaCraft_Hash_Brute;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public class GuiHashBrute
extends GuiScreen {
    private GuiScreen parentScreen;

    public GuiHashBrute(GuiScreen guiscreen) {
        this.parentScreen = guiscreen;
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    @Override
    protected void actionPerformed(GuiButton guibutton) {
        if (!guibutton.enabled) {
            return;
        }
        if (guibutton.id == 0) {
            this.mc.displayGuiScreen(this.parentScreen);
        } else if (guibutton.id == 1) {
            MineLand_Hash_Brute ps = new MineLand_Hash_Brute();
            ps.setVisible(true);
        } else if (guibutton.id == 2) {
            ProstoCraft_Hash_Brute ps2 = new ProstoCraft_Hash_Brute();
            ps2.setVisible(true);
        } else if (guibutton.id == 3) {
            YottaCraft_Hash_Brute ps3 = new YottaCraft_Hash_Brute();
            ps3.setVisible(true);
        } else if (guibutton.id == 4) {
            MD5_Brute ps4 = new MD5_Brute();
            ps4.setVisible(true);
        }
    }

    @Override
    protected void mouseClicked(int i, int j, int k) throws IOException {
        super.mouseClicked(i, j, k);
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents((boolean)true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, "Cancel"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96 + 12, "MineLand Hash Brute"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 72 + 12, "ProstoCraft Hash Brute"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 48 + 12, "YottaCraft Hash Brute"));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + 12, "MD5 Hash Brute"));
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        super.drawScreen(i, j, f);
    }
}


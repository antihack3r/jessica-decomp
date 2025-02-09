/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.GL11
 */
package jessica.gui;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import jessica.Module;
import jessica.Wrapper;
import jessica.gui.font.UnicodeFontRenderer;
import jessica.module.HUD;
import jessica.utils.Binds;
import jessica.utils.Timer2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class GuiIngameHook
extends GuiIngame {
    private ResourceLocation jsLogo;
    private FontRenderer fontRenderer;
    private FontRenderer fontRenderer2;
    public static int responseTime;
    private Timer2 counter = new Timer2();
    public static int tick;

    static {
        tick = 0;
        responseTime = 0;
    }

    public GuiIngameHook(Minecraft mcIn) {
        super(mcIn);
        this.jsLogo = new ResourceLocation("jessica/logo5.png");
        this.fontRenderer = new UnicodeFontRenderer(new Font("Trebuchet MS", 1, 19));
        this.fontRenderer2 = new UnicodeFontRenderer(new Font("Trebuchet MS", 1, 21));
    }

    @Override
    public void renderGameOverlay(float partialTicks) {
        super.renderGameOverlay(partialTicks);
        int count = 0;
        ArrayList<Module> enable = new ArrayList<Module>();
        for (Module m : Wrapper.getModules().values()) {
            if (!m.isToggled()) continue;
            enable.add(m);
        }
        Comparator<Module> cp = new Comparator<Module>(){

            @Override
            public int compare(Module b1, Module b2) {
                return Integer.compare(GuiIngameHook.this.fontRenderer.getStringWidth(b1.getName()), GuiIngameHook.this.fontRenderer.getStringWidth(b2.getName()));
            }
        };
        enable.sort(cp.reversed());
        for (Module i : enable) {
            int j = this.fontRenderer.FONT_HEIGHT;
            int k = this.fontRenderer.getStringWidth(i.getName());
            int l = new ScaledResolution(Wrapper.mc()).getScaledWidth() - 2 - k;
            int i2 = 2 + j * count;
            try {
                if (!Wrapper.mc().gameSettings.showDebugInfo) {
                    if (HUD.rgb.getValue().booleanValue()) {
                        this.fontRenderer.drawString(i.getName(), l, i2, GuiIngameHook.rgb(228));
                    } else {
                        this.fontRenderer.drawString(i.getName(), l, i2, new Color((int)HUD.red.getDoubleValue(), (int)HUD.green.getDoubleValue(), (int)HUD.blue.getDoubleValue()).getRGB());
                    }
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            ++count;
        }
        int lasttime = tick++;
        if (this.counter.get() > 1000L) {
            this.counter.reset();
            Wrapper.getFiles().saveValues();
        }
        if (HUD.totaltime.getValue().booleanValue()) {
            if (lasttime != tick) {
                DecimalFormat decimalFormatHour = new DecimalFormat("0");
                DecimalFormat decimalFormat = new DecimalFormat("00");
                Display.setTitle((String)(String.valueOf(Wrapper.getClientName()) + " " + Wrapper.getVesrion() + " Time: " + String.valueOf(String.valueOf(decimalFormatHour.format(tick / 3600)) + ":" + decimalFormat.format(tick % 3600 / 60) + ":" + decimalFormat.format(tick % 60))));
            }
        } else {
            Display.setTitle((String)(String.valueOf(String.valueOf(Wrapper.getClientName())) + " " + Wrapper.getVesrion()));
        }
        try {
            if (!Wrapper.mc().gameSettings.showDebugInfo) {
                if (!HUD.logo.getValue().booleanValue()) {
                    this.fontRenderer.drawString(String.valueOf(Wrapper.getClientName()) + " " + Wrapper.getVesrion(), 2, 2, 11546150);
                } else {
                    this.fontRenderer2.drawString("Crash In You", 2, 2, new Color(0, 200, 255).getRGB());
                }
                if (!Wrapper.mc().gameSettings.keyBindPlayerList.isKeyDown() && HUD.logo.getValue().booleanValue()) {
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)50.0f, (float)50.0f, (float)0.0f);
                    this.drawGradientRect(-48, -48, -48, -48, -16777216, Integer.MIN_VALUE);
                    GL11.glPopMatrix();
                    OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    Minecraft.getMinecraft().getTextureManager().bindTexture(this.jsLogo);
                    Gui.drawScaledCustomSizeModalRect(-18, 0, 0.0f, 0.0f, 2, 2, 100, 100, 2.0f, 2.0f);
                }
            }
        }
        catch (Exception decimalFormatHour) {
            // empty catch block
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)50.0f, (float)50.0f, (float)0.0f);
        this.drawGradientRect(-48, -48, -48, -48, -16777216, Integer.MIN_VALUE);
        GL11.glPopMatrix();
        new ScaledResolution(Wrapper.mc());
        Wrapper.mc().entityRenderer.setupOverlayRendering();
        GlStateManager.enableBlend();
        Wrapper.getBinds();
        Binds.makeBinds();
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.jsLogo);
        boolean x = true;
        int y = 2;
        boolean w = false;
        boolean h = false;
        float fw = 0.0f;
        float fh = 0.0f;
        float u = 0.0f;
        float v = 0.0f;
        Gui.drawModalRectWithCustomSizedTexture(1, 2, 0.0f, 0.0f, 0, 0, 0.0f, 0.0f);
    }

    private static int rgb(int n) {
        return Color.getHSBColor((float)(Math.ceil((double)(System.currentTimeMillis() + (long)n) / 20.0) % 360.0 / 360.0), 0.8f, 0.7f).getRGB();
    }
}


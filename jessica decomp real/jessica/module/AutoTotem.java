/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.event.EventRender2D;
import jessica.utils.RenderHelper;
import jessica.utils.Timer2;
import jessica.value.ValueBool;
import jessica.value.ValueNumber;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class AutoTotem
extends Module {
    private Timer2 counter = new Timer2();
    private ValueNumber delay = new ValueNumber("Delay", 100.0, 0.0, 2000.0);
    private ValueBool display = new ValueBool("Display", true);
    private ItemStack totem = new ItemStack(Items.field_190929_cY);

    public AutoTotem() {
        super("AutoTotem", 0, Category.Combat);
        this.addValue(this.delay);
        this.addValue(this.display);
    }

    @Override
    public void onUpdate() {
        if (Wrapper.mc().player.getHeldItemOffhand().getItem() == Items.field_190929_cY) {
            this.counter.reset();
        }
        if (Wrapper.mc().player.getHeldItemOffhand().getItem() != Items.field_190929_cY && this.getSlotOfTotem() != -1 && (Wrapper.mc().currentScreen instanceof GuiInventory || Wrapper.mc().currentScreen == null) && (double)this.counter.get() >= this.delay.getValue()) {
            Wrapper.mc().playerController.windowClick(0, this.getSlotOfTotem(), 1, ClickType.PICKUP, Wrapper.player());
            Wrapper.mc().playerController.windowClick(0, 45, 1, ClickType.PICKUP, Wrapper.player());
        }
    }

    @Override
    public void onRender2D(EventRender2D e) {
        if (!this.display.getValue().booleanValue()) {
            return;
        }
        if (!Wrapper.mc().playerController.shouldDrawHUD()) {
            return;
        }
        ScaledResolution scale = new ScaledResolution(Wrapper.mc());
        int i = scale.getScaledWidth() / 2;
        int j = scale.getScaledHeight();
        int i1 = i - 90 + 80 + 2;
        int j1 = j - 16 - 35;
        String str = "";
        if (this.countTotems() >= 5) {
            str = "" + (Object)((Object)TextFormatting.GREEN);
        } else if (this.countTotems() < 5 && this.countTotems() > 2) {
            str = "" + (Object)((Object)TextFormatting.YELLOW);
        } else if (this.countTotems() <= 2) {
            str = "" + (Object)((Object)TextFormatting.RED);
        }
        if (this.countTotems() > 0) {
            RenderHelper.drawItem(this.totem, i1, j1);
            String str2 = String.valueOf(str) + (Object)((Object)TextFormatting.BOLD) + this.countTotems();
            Wrapper.mc().fontRendererObj.drawStringWithShadow(str2, i1 + 19 - 2 - Wrapper.mc().fontRendererObj.getStringWidth(str2), j1 + 6 + 3, 0xFFFFFF);
        }
    }

    private int getSlotOfTotem() {
        int i = 0;
        while (i < 45) {
            ItemStack item = Wrapper.player().inventoryContainer.getSlot(i).getStack();
            if (item.getItem() == Items.field_190929_cY) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    private int countTotems() {
        int i = 0;
        int j = 0;
        while (j < Wrapper.player().inventory.getSizeInventory()) {
            ItemStack item = Wrapper.player().inventory.getStackInSlot(j);
            if (!(item.getItem() instanceof ItemAir) && item.getItem() == Items.field_190929_cY) {
                ++i;
            }
            ++j;
        }
        return i;
    }
}


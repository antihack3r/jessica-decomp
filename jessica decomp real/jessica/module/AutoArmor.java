/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class AutoArmor
extends Module {
    private final Item blockAir = Item.getItemFromBlock(Blocks.AIR);

    public AutoArmor() {
        super("AutoArmor", 0, Category.Combat);
    }

    @Override
    public void onUpdate() {
        if (Wrapper.mc().currentScreen instanceof GuiContainer && !(Wrapper.mc().currentScreen instanceof InventoryEffectRenderer)) {
            return;
        }
        int[] bestArmorSlots = new int[4];
        int[] bestArmorValues = new int[4];
        int armorType = 0;
        while (armorType < 4) {
            ItemStack oldArmor = Wrapper.mc().player.inventory.armorItemInSlot(armorType);
            if (oldArmor != null && oldArmor.getItem() instanceof ItemArmor) {
                bestArmorValues[armorType] = ((ItemArmor)oldArmor.getItem()).damageReduceAmount;
            }
            bestArmorSlots[armorType] = -1;
            ++armorType;
        }
        int slot = 0;
        while (slot < 36) {
            int armorType2;
            ItemArmor armor;
            int armorValue;
            ItemStack stack = Wrapper.mc().player.inventory.getStackInSlot(slot);
            if (stack != null && stack.getItem() instanceof ItemArmor && (armorValue = armor.damageReduceAmount) > bestArmorValues[armorType2 = this.getArmorType(armor = (ItemArmor)stack.getItem())]) {
                bestArmorSlots[armorType2] = slot;
                bestArmorValues[armorType2] = armorValue;
            }
            ++slot;
        }
        armorType = 0;
        while (armorType < 4) {
            ItemStack oldArmor2;
            int slot2 = bestArmorSlots[armorType];
            if (!(slot2 == -1 || (oldArmor2 = Wrapper.mc().player.inventory.armorItemInSlot(armorType)) != null && this.isEmptySlot(oldArmor2) && Wrapper.mc().player.inventory.getFirstEmptyStack() == -1)) {
                if (slot2 < 9) {
                    slot2 += 36;
                }
                Wrapper.mc().playerController.windowClick(0, 8 - armorType, 0, ClickType.QUICK_MOVE, Wrapper.mc().player);
                Wrapper.mc().playerController.windowClick(0, slot2, 0, ClickType.QUICK_MOVE, Wrapper.mc().player);
                break;
            }
            ++armorType;
        }
    }

    public int getArmorType(ItemArmor armor) {
        return armor.armorType.ordinal() - 2;
    }

    public boolean isEmptySlot(ItemStack slot) {
        return slot.getItem() == this.blockAir;
    }
}


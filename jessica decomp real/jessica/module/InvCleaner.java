/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.value.ValueBool;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class InvCleaner
extends Module {
    private ValueBool invopen = new ValueBool("InvOpen", true);

    public InvCleaner() {
        super("InvCleaner", 0, Category.Player);
        this.addValue(this.invopen);
    }

    @Override
    public void onUpdate() {
        if (this.getSlotTrashItem() != -1) {
            Wrapper.mc().playerController.windowClick(0, this.getSlotTrashItem(), 1, ClickType.THROW, Wrapper.player());
        }
    }

    private int getBestSwordOfSlot() {
        float bestSwordDamage = -1.0f;
        int bestSwordSlot = -1;
        int slot = 45;
        while (slot > 0) {
            ItemStack item = Wrapper.player().inventoryContainer.getSlot(slot).getStack();
            if (item != null && item.getItem() instanceof ItemSword) {
                ItemSword sword = (ItemSword)item.getItem();
                float damage = sword.getDamageVsEntity();
                if ((damage += (float)EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, item)) > bestSwordDamage) {
                    bestSwordDamage = damage;
                    bestSwordSlot = slot;
                }
            }
            slot = (short)(slot - 1);
        }
        return bestSwordSlot;
    }

    private int getSlotTrashItem() {
        if (this.invopen.getValue().booleanValue() && !(Wrapper.mc().currentScreen instanceof GuiContainer)) {
            return -1;
        }
        int slot = 0;
        while (slot < 45) {
            ItemStack item = Wrapper.player().inventoryContainer.getSlot(slot).getStack();
            int bestSword = this.getBestSwordOfSlot();
            if (item == null) {
                return -1;
            }
            if (item.getItem() instanceof ItemSword && bestSword != -1 && bestSword != slot) {
                return slot;
            }
            if (item.getItem() == Items.STICK) {
                return slot;
            }
            if (item.getItem() == Items.BONE) {
                return slot;
            }
            if (item.getItem() == Items.FLINT) {
                return slot;
            }
            if (item.getItem() == Items.STRING) {
                return slot;
            }
            if (item.getItem() == Items.FEATHER) {
                return slot;
            }
            if (item.getItem() instanceof ItemSeeds) {
                return slot;
            }
            if (item.getItem() == Items.ROTTEN_FLESH) {
                return slot;
            }
            if (item.getItem() == Items.POISONOUS_POTATO) {
                return slot;
            }
            if (item.getItem() == Items.RABBIT_FOOT) {
                return slot;
            }
            if (item.getItem() == Items.CLAY_BALL) {
                return slot;
            }
            if (item.getItem() == Items.FLINT_AND_STEEL) {
                return slot;
            }
            if (item.getItem() == Items.CHEST_MINECART) {
                return slot;
            }
            if (item.getItem() == Items.SPIDER_EYE) {
                return slot;
            }
            if (item.getItem() == Items.WHEAT) {
                return slot;
            }
            if (item.getItem() == Items.BOWL) {
                return slot;
            }
            if (item.getItem() == Items.GLASS_BOTTLE) {
                return slot;
            }
            if (item.getItem() == Items.SUGAR) {
                return slot;
            }
            if (item.getItem() == Items.RABBIT_HIDE) {
                return slot;
            }
            if (item.getItem() == Items.GUNPOWDER) {
                return slot;
            }
            if (item.getItem() == Items.PAPER) {
                return slot;
            }
            if (item.getItem() == Items.FLOWER_POT) {
                return slot;
            }
            if (item.getItem() == Items.REEDS) {
                return slot;
            }
            if (item.getItem() == ItemBlock.getByNameOrId("YELLOW_FLOWER")) {
                return slot;
            }
            if (item.getItem() == ItemBlock.getByNameOrId("RED_FLOWER")) {
                return slot;
            }
            if (item.getItem() == ItemBlock.getByNameOrId("SAND")) {
                return slot;
            }
            if (item.getItem() == ItemBlock.getByNameOrId("GRAVEL")) {
                return slot;
            }
            if (item.getItem() == ItemBlock.getByNameOrId("CACTUS")) {
                return slot;
            }
            slot = (short)(slot + 1);
        }
        return -1;
    }
}


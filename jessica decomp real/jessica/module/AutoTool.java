/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.event.DamageBlockEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.BlockPos;

public class AutoTool
extends Module {
    public AutoTool() {
        super("AutoTool", 0, Category.Player);
    }

    @Override
    public void onDamage(DamageBlockEvent event) {
        this.getTool(event.getPos(), false, true, false);
    }

    public void getTool(BlockPos pos, boolean useSwords, boolean useHands, boolean repairMode) {
        boolean useFallback;
        EntityPlayerSP player = Wrapper.player();
        if (player.capabilities.isCreativeMode) {
            return;
        }
        IBlockState state = AutoTool.getState(pos);
        ItemStack heldItem = player.getHeldItemMainhand();
        float bestSpeed = this.getDestroySpeed(heldItem, state);
        int bestSlot = -1;
        int fallbackSlot = -1;
        InventoryPlayer inventory = player.inventory;
        int slot = 0;
        while (slot < 9) {
            if (slot != inventory.currentItem) {
                float speed;
                ItemStack stack = inventory.getStackInSlot(slot);
                if (fallbackSlot == -1 && !this.isDamageable(stack)) {
                    fallbackSlot = slot;
                }
                if ((speed = this.getDestroySpeed(stack, state)) > bestSpeed && (useSwords || !(stack.getItem() instanceof ItemSword)) && !this.isTooDamaged(stack, repairMode)) {
                    bestSpeed = speed;
                    bestSlot = slot;
                }
            }
            ++slot;
        }
        boolean bl = useFallback = this.isDamageable(heldItem) && (this.isTooDamaged(heldItem, repairMode) || useHands && this.getDestroySpeed(heldItem, state) <= 1.0f);
        if (bestSlot != -1) {
            inventory.currentItem = bestSlot;
            return;
        }
        if (!useFallback) {
            return;
        }
        if (fallbackSlot != -1) {
            inventory.currentItem = fallbackSlot;
            return;
        }
        if (this.isTooDamaged(heldItem, repairMode)) {
            inventory.currentItem = inventory.currentItem == 8 ? 0 : ++inventory.currentItem;
        }
    }

    private float getDestroySpeed(ItemStack stack, IBlockState state) {
        int efficiency;
        float speed = AutoTool.getDestroySpeed2(stack, state);
        if (speed > 1.0f && (efficiency = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, stack)) > 0 && !AutoTool.isNullOrEmpty(stack)) {
            speed += (float)(efficiency * efficiency + 1);
        }
        return speed;
    }

    private static IBlockState getState(BlockPos pos) {
        return Wrapper.world().getBlockState(pos);
    }

    private boolean isDamageable(ItemStack stack) {
        return !AutoTool.isNullOrEmpty(stack) && stack.getItem().isDamageable();
    }

    private boolean isTooDamaged(ItemStack stack, boolean repairMode) {
        return repairMode && stack.getMaxDamage() - stack.getItemDamage() <= 4;
    }

    private static float getDestroySpeed2(ItemStack stack, IBlockState state) {
        return AutoTool.isNullOrEmpty(stack) ? 1.0f : stack.getStrVsBlock(state);
    }

    private static boolean isNullOrEmpty(ItemStack stack) {
        return stack == null || stack.func_190926_b();
    }
}


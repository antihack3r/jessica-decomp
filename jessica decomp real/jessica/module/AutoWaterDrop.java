/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class AutoWaterDrop
extends Module {
    ItemStack waterbucket = new ItemStack(Items.WATER_BUCKET);
    public static boolean canPlaceWater;

    public AutoWaterDrop() {
        super("AutoWaterDrop", 0, Category.Player);
    }

    @Override
    public void onUpdate() {
        int xPos = (int)Wrapper.player().posX;
        int yPos = (int)Wrapper.player().posY - 4;
        int zPos = (int)Wrapper.player().posZ;
        BlockPos blockPos = new BlockPos(xPos, yPos, zPos);
        Block block = Wrapper.world().getBlockState(blockPos).getBlock();
        if (block != null && block != Blocks.AIR && Wrapper.player().fallDistance > 4.0f) {
            if (this.isSlotOfWater()) {
                canPlaceWater = true;
            }
            if (canPlaceWater) {
                this.rotate();
                this.placeWater();
            }
        }
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    public boolean isSlotOfWater() {
        block5: {
            EntityPlayerSP entityPlayerSP;
            int i;
            block4: {
                if (Wrapper.player().getHeldItemMainhand().getItem() == Items.WATER_BUCKET) {
                    return true;
                }
                i = 0;
                if (!true) break block4;
                entityPlayerSP = Wrapper.player();
                if (i >= entityPlayerSP.inventory.getHotbarSize()) break block5;
            }
            do {
                ItemStack item;
                if ((item = Wrapper.player().inventory.getStackInSlot(i)).getItem() == Items.WATER_BUCKET) {
                    Wrapper.player().inventory.currentItem = i;
                    return true;
                }
                ++i;
                entityPlayerSP = Wrapper.player();
            } while (i < entityPlayerSP.inventory.getHotbarSize());
        }
        return false;
    }

    public void rotate() {
        Wrapper.player().rotationPitch = 90.0f;
    }

    public void placeWater() {
        ItemStack item = Wrapper.player().getHeldItem(EnumHand.MAIN_HAND);
        if (item.getItem() == Items.WATER_BUCKET) {
            Wrapper.sendPacket(new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        }
        canPlaceWater = false;
    }
}


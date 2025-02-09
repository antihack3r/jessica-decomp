/*
 * Decompiled with CFR 0.152.
 */
package jessica.utils;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class BlockData {
    public BlockPos position;
    public EnumFacing face;

    public BlockData(BlockPos position, EnumFacing face) {
        this.position = position;
        this.face = face;
    }
}


/*
 * Decompiled with CFR 0.152.
 */
package jessica.event;

import java.util.EventListener;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class DamageBlockEvent
implements EventListener {
    public BlockPos pos;
    public EnumFacing facing;

    public DamageBlockEvent(BlockPos pos, EnumFacing facing) {
        this.pos = pos;
        this.facing = facing;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public EnumFacing getFacing() {
        return this.facing;
    }
}


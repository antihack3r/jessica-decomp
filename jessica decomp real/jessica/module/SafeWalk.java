/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.value.ValueBool;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class SafeWalk
extends Module {
    public static ValueBool eagle = new ValueBool("Eagle", false);

    public SafeWalk() {
        super("SafeWalk", 0, Category.Player);
        this.addValue(eagle);
    }

    @Override
    public void onUpdate() {
        if (eagle.getValue().booleanValue()) {
            BlockPos blockPos = new BlockPos(Wrapper.player().posX, Wrapper.player().posY - 1.0, Wrapper.player().posZ);
            if (Wrapper.player().fallDistance <= 4.0f) {
                Wrapper.mc().gameSettings.keyBindSneak.pressed = Wrapper.world().getBlockState(blockPos).getBlock() == Blocks.AIR;
            }
        }
    }

    @Override
    public void onDisable() {
        if (eagle.getValue().booleanValue()) {
            Wrapper.mc().gameSettings.keyBindSneak.pressed = false;
        }
    }
}


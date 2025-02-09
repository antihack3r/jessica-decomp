/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import java.util.ArrayList;
import java.util.List;
import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import net.minecraft.network.Packet;

public class Blink
extends Module {
    public static List<Packet> packets = new ArrayList<Packet>();

    public Blink() {
        super("Blink", 0, Category.Player);
    }

    @Override
    public void onDisable() {
        for (Packet packet : packets) {
            Wrapper.sendPacketBypass(packet);
        }
        packets.clear();
    }
}


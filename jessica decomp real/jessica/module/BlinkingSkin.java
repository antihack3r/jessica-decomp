/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import java.util.HashMap;
import java.util.Map;
import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.value.ValueNumber;
import net.minecraft.entity.player.EnumPlayerModelParts;

public class BlinkingSkin
extends Module {
    private ValueNumber blinkingvalue = new ValueNumber("Delay", 20.0, 0.0, 100.0);
    private Map<EnumPlayerModelParts, Integer> intervals = new HashMap<EnumPlayerModelParts, Integer>();

    public BlinkingSkin() {
        super("BlinkingSkin", 0, Category.Exploits);
        this.addValue(this.blinkingvalue);
    }

    @Override
    public void onEnable() {
        EnumPlayerModelParts[] enumPlayerModelPartsArray = EnumPlayerModelParts.values();
        int n = enumPlayerModelPartsArray.length;
        int n2 = 0;
        while (n2 < n) {
            EnumPlayerModelParts overlay = enumPlayerModelPartsArray[n2];
            this.intervals.put(overlay, 0);
            ++n2;
        }
    }

    @Override
    public void onUpdate() {
        for (Map.Entry<EnumPlayerModelParts, Integer> entry : this.intervals.entrySet()) {
            int counter = this.intervals.get((Object)entry.getKey());
            if (++counter >= this.getBlinkIntervalProperty(entry.getKey())) {
                this.intervals.put(entry.getKey(), 0);
                Wrapper.mc().gameSettings.switchModelPartEnabled(entry.getKey());
                continue;
            }
            this.intervals.put(entry.getKey(), counter);
        }
    }

    public int getBlinkIntervalProperty(EnumPlayerModelParts overlay) {
        return (int)this.blinkingvalue.getDoubleValue();
    }
}


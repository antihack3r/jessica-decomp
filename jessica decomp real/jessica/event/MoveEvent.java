/*
 * Decompiled with CFR 0.152.
 */
package jessica.event;

import com.darkmagician6.eventapi.events.Event;

public class MoveEvent
implements Event {
    public double x;
    public double y;
    public double z;

    public MoveEvent(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}


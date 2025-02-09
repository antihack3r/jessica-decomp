/*
 * Decompiled with CFR 0.152.
 */
package jessica.event;

import com.darkmagician6.eventapi.events.Event;

public class EventRender2D
implements Event {
    private float width;
    private float height;

    public EventRender2D(float width, float height) {
        this.width = width;
        this.height = height;
    }
}


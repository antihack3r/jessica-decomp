/*
 * Decompiled with CFR 0.152.
 */
package jessica.utils;

public class Timer2 {
    private long previousTime = -1L;

    public boolean check(float milliseconds) {
        return (float)(this.getCurrentMS() - this.previousTime) >= milliseconds;
    }

    public void reset() {
        this.previousTime = this.getCurrentMS();
    }

    public short convert(float perSecond) {
        return (short)(1000.0f / perSecond);
    }

    public long get() {
        return this.getCurrentMS() - this.previousTime;
    }

    protected long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.Sys
 */
package org.newdawn.slick;

import java.util.ArrayList;
import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.util.Log;

public class Animation
implements Renderable {
    private ArrayList frames = new ArrayList();
    private int currentFrame = -1;
    private long nextChange = 0L;
    private boolean stopped = false;
    private long timeLeft;
    private float speed = 1.0f;
    private int stopAt = -2;
    private long lastUpdate;
    private boolean firstUpdate = true;
    private boolean autoUpdate = true;
    private int direction = 1;
    private boolean pingPong;
    private boolean loop = true;
    private SpriteSheet spriteSheet = null;

    public Animation() {
        this(true);
    }

    public Animation(Image[] frames, int duration) {
        this(frames, duration, true);
    }

    public Animation(Image[] frames, int[] durations) {
        this(frames, durations, true);
    }

    public Animation(boolean autoUpdate) {
        this.currentFrame = 0;
        this.autoUpdate = autoUpdate;
    }

    public Animation(Image[] frames, int duration, boolean autoUpdate) {
        int i = 0;
        while (i < frames.length) {
            this.addFrame(frames[i], duration);
            ++i;
        }
        this.currentFrame = 0;
        this.autoUpdate = autoUpdate;
    }

    public Animation(Image[] frames, int[] durations, boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
        if (frames.length != durations.length) {
            throw new RuntimeException("There must be one duration per frame");
        }
        int i = 0;
        while (i < frames.length) {
            this.addFrame(frames[i], durations[i]);
            ++i;
        }
        this.currentFrame = 0;
    }

    public Animation(SpriteSheet frames, int duration) {
        this(frames, 0, 0, frames.getHorizontalCount() - 1, frames.getVerticalCount() - 1, true, duration, true);
    }

    public Animation(SpriteSheet frames, int x1, int y1, int x2, int y2, boolean horizontalScan, int duration, boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
        if (!horizontalScan) {
            int x = x1;
            while (x <= x2) {
                int y = y1;
                while (y <= y2) {
                    this.addFrame(frames.getSprite(x, y), duration);
                    ++y;
                }
                ++x;
            }
        } else {
            int y = y1;
            while (y <= y2) {
                int x = x1;
                while (x <= x2) {
                    this.addFrame(frames.getSprite(x, y), duration);
                    ++x;
                }
                ++y;
            }
        }
    }

    public Animation(SpriteSheet ss, int[] frames, int[] duration) {
        this.spriteSheet = ss;
        int x = -1;
        int y = -1;
        int i = 0;
        while (i < frames.length / 2) {
            x = frames[i * 2];
            y = frames[i * 2 + 1];
            this.addFrame(duration[i], x, y);
            ++i;
        }
    }

    public void addFrame(int duration, int x, int y) {
        if (duration == 0) {
            Log.error("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }
        if (this.frames.isEmpty()) {
            this.nextChange = (int)((float)duration / this.speed);
        }
        this.frames.add(new Frame(duration, x, y));
        this.currentFrame = 0;
    }

    public void setAutoUpdate(boolean auto) {
        this.autoUpdate = auto;
    }

    public void setPingPong(boolean pingPong) {
        this.pingPong = pingPong;
    }

    public boolean isStopped() {
        return this.stopped;
    }

    public void setSpeed(float spd) {
        if (spd > 0.0f) {
            this.nextChange = (long)((float)this.nextChange * this.speed / spd);
            this.speed = spd;
        }
    }

    public float getSpeed() {
        return this.speed;
    }

    public void stop() {
        if (this.frames.size() == 0) {
            return;
        }
        this.timeLeft = this.nextChange;
        this.stopped = true;
    }

    public void start() {
        if (!this.stopped) {
            return;
        }
        if (this.frames.size() == 0) {
            return;
        }
        this.stopped = false;
        this.nextChange = this.timeLeft;
    }

    public void restart() {
        if (this.frames.size() == 0) {
            return;
        }
        this.stopped = false;
        this.currentFrame = 0;
        this.nextChange = (int)((float)((Frame)this.frames.get((int)0)).duration / this.speed);
        this.firstUpdate = true;
        this.lastUpdate = 0L;
    }

    public void addFrame(Image frame, int duration) {
        if (duration == 0) {
            Log.error("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }
        if (this.frames.isEmpty()) {
            this.nextChange = (int)((float)duration / this.speed);
        }
        this.frames.add(new Frame(frame, duration));
        this.currentFrame = 0;
    }

    public void draw() {
        this.draw(0.0f, 0.0f);
    }

    @Override
    public void draw(float x, float y) {
        this.draw(x, y, this.getWidth(), this.getHeight());
    }

    public void draw(float x, float y, Color filter) {
        this.draw(x, y, this.getWidth(), this.getHeight(), filter);
    }

    public void draw(float x, float y, float width, float height) {
        this.draw(x, y, width, height, Color.white);
    }

    public void draw(float x, float y, float width, float height, Color col) {
        if (this.frames.size() == 0) {
            return;
        }
        if (this.autoUpdate) {
            long now = this.getTime();
            long delta = now - this.lastUpdate;
            if (this.firstUpdate) {
                delta = 0L;
                this.firstUpdate = false;
            }
            this.lastUpdate = now;
            this.nextFrame(delta);
        }
        Frame frame = (Frame)this.frames.get(this.currentFrame);
        frame.image.draw(x, y, width, height, col);
    }

    public void renderInUse(int x, int y) {
        if (this.frames.size() == 0) {
            return;
        }
        if (this.autoUpdate) {
            long now = this.getTime();
            long delta = now - this.lastUpdate;
            if (this.firstUpdate) {
                delta = 0L;
                this.firstUpdate = false;
            }
            this.lastUpdate = now;
            this.nextFrame(delta);
        }
        Frame frame = (Frame)this.frames.get(this.currentFrame);
        this.spriteSheet.renderInUse(x, y, frame.x, frame.y);
    }

    public int getWidth() {
        return ((Frame)this.frames.get((int)this.currentFrame)).image.getWidth();
    }

    public int getHeight() {
        return ((Frame)this.frames.get((int)this.currentFrame)).image.getHeight();
    }

    public void drawFlash(float x, float y, float width, float height) {
        this.drawFlash(x, y, width, height, Color.white);
    }

    public void drawFlash(float x, float y, float width, float height, Color col) {
        if (this.frames.size() == 0) {
            return;
        }
        if (this.autoUpdate) {
            long now = this.getTime();
            long delta = now - this.lastUpdate;
            if (this.firstUpdate) {
                delta = 0L;
                this.firstUpdate = false;
            }
            this.lastUpdate = now;
            this.nextFrame(delta);
        }
        Frame frame = (Frame)this.frames.get(this.currentFrame);
        frame.image.drawFlash(x, y, width, height, col);
    }

    public void updateNoDraw() {
        if (this.autoUpdate) {
            long now = this.getTime();
            long delta = now - this.lastUpdate;
            if (this.firstUpdate) {
                delta = 0L;
                this.firstUpdate = false;
            }
            this.lastUpdate = now;
            this.nextFrame(delta);
        }
    }

    public void update(long delta) {
        this.nextFrame(delta);
    }

    public int getFrame() {
        return this.currentFrame;
    }

    public void setCurrentFrame(int index) {
        this.currentFrame = index;
    }

    public Image getImage(int index) {
        Frame frame = (Frame)this.frames.get(index);
        return frame.image;
    }

    public int getFrameCount() {
        return this.frames.size();
    }

    public Image getCurrentFrame() {
        Frame frame = (Frame)this.frames.get(this.currentFrame);
        return frame.image;
    }

    private void nextFrame(long delta) {
        if (this.stopped) {
            return;
        }
        if (this.frames.size() == 0) {
            return;
        }
        this.nextChange -= delta;
        while (this.nextChange < 0L && !this.stopped) {
            if (this.currentFrame == this.stopAt) {
                this.stopped = true;
                break;
            }
            if (this.currentFrame == this.frames.size() - 1 && !this.loop && !this.pingPong) {
                this.stopped = true;
                break;
            }
            this.currentFrame = (this.currentFrame + this.direction) % this.frames.size();
            if (this.pingPong) {
                if (this.currentFrame <= 0) {
                    this.currentFrame = 0;
                    this.direction = 1;
                    if (!this.loop) {
                        this.stopped = true;
                        break;
                    }
                } else if (this.currentFrame >= this.frames.size() - 1) {
                    this.currentFrame = this.frames.size() - 1;
                    this.direction = -1;
                }
            }
            int realDuration = (int)((float)((Frame)this.frames.get((int)this.currentFrame)).duration / this.speed);
            this.nextChange += (long)realDuration;
        }
    }

    public void setLooping(boolean loop) {
        this.loop = loop;
    }

    private long getTime() {
        return Sys.getTime() * 1000L / Sys.getTimerResolution();
    }

    public void stopAt(int frameIndex) {
        this.stopAt = frameIndex;
    }

    public int getDuration(int index) {
        return ((Frame)this.frames.get((int)index)).duration;
    }

    public void setDuration(int index, int duration) {
        ((Frame)this.frames.get((int)index)).duration = duration;
    }

    public int[] getDurations() {
        int[] durations = new int[this.frames.size()];
        int i = 0;
        while (i < this.frames.size()) {
            durations[i] = this.getDuration(i);
            ++i;
        }
        return durations;
    }

    public String toString() {
        String res = "[Animation (" + this.frames.size() + ") ";
        int i = 0;
        while (i < this.frames.size()) {
            Frame frame = (Frame)this.frames.get(i);
            res = String.valueOf(res) + frame.duration + ",";
            ++i;
        }
        res = String.valueOf(res) + "]";
        return res;
    }

    public Animation copy() {
        Animation copy = new Animation();
        copy.spriteSheet = this.spriteSheet;
        copy.frames = this.frames;
        copy.autoUpdate = this.autoUpdate;
        copy.direction = this.direction;
        copy.loop = this.loop;
        copy.pingPong = this.pingPong;
        copy.speed = this.speed;
        return copy;
    }

    private class Frame {
        public Image image;
        public int duration;
        public int x = -1;
        public int y = -1;

        public Frame(Image image, int duration) {
            this.image = image;
            this.duration = duration;
        }

        public Frame(int duration, int x, int y) {
            this.image = Animation.this.spriteSheet.getSubImage(x, y);
            this.duration = duration;
            this.x = x;
            this.y = y;
        }
    }
}


/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.scoreboard;

import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.stats.StatBase;

public class ScoreCriteriaStat
extends ScoreCriteria {
    private final StatBase stat;

    public ScoreCriteriaStat(StatBase statIn) {
        super(statIn.statId);
        this.stat = statIn;
    }
}


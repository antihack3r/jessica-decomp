/*
 * Decompiled with CFR 0.152.
 */
package jessica.value;

import jessica.value.Value;

public class ValueNumber
extends Value {
    private String name;
    private double value;
    private double min;
    private double max;
    private int round;

    public ValueNumber(String name, double value, double min, double max) {
        super(name, value);
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
        this.round = 1;
        arrayVal.add(this);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return this.value;
    }

    public double getDoubleValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMin() {
        return this.min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return this.max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double setValueMax(double v) {
        this.max = v;
        return this.max;
    }
}


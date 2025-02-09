/*
 * Decompiled with CFR 0.152.
 */
package jessica.value;

import java.util.ArrayList;

public class Value<T> {
    public static ArrayList<Value> arrayVal = new ArrayList();
    public T value;
    public T defaultValue;
    private String name;

    public Value(String name) {
        this.name = name;
    }

    public Value(String name, T value) {
        this.name = name;
        this.value = value;
        this.defaultValue = value;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


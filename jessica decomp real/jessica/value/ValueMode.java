/*
 * Decompiled with CFR 0.152.
 */
package jessica.value;

import jessica.value.Value;

public class ValueMode
extends Value {
    private String name;
    private String[] values;
    private String value;

    public ValueMode(String name, String value, String[] values) {
        super(name, value);
        this.name = name;
        this.value = value;
        this.values = values;
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

    public String[] getValues() {
        return this.values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void increment() {
        String currentMode = this.getValue();
        String[] stringArray = this.getValues();
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String mode = stringArray[n2];
            if (mode.equalsIgnoreCase(currentMode)) {
                int ordinal = this.getOrdinal(mode, this.getValues());
                String newValue = ordinal == this.getValues().length - 1 ? this.getValues()[0] : this.getValues()[ordinal + 1];
                this.setValue(newValue);
                return;
            }
            ++n2;
        }
    }

    public void decrement() {
        String currentMode = this.getValue();
        String[] stringArray = this.getValues();
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String mode = stringArray[n2];
            if (mode.equalsIgnoreCase(currentMode)) {
                int ordinal = this.getOrdinal(mode, this.getValues());
                String newValue = ordinal == 0 ? this.getValues()[this.getValues().length - 1] : this.getValues()[ordinal - 1];
                this.setValue(newValue);
                return;
            }
            ++n2;
        }
    }

    private int getOrdinal(String value, String[] array) {
        int i = 0;
        while (i <= array.length - 1) {
            String indexString = array[i];
            if (indexString.equalsIgnoreCase(value)) {
                return i;
            }
            ++i;
        }
        return 0;
    }
}


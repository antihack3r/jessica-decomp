/*
 * Decompiled with CFR 0.152.
 */
package jessica;

import java.util.ArrayList;
import java.util.List;
import jessica.Category;
import jessica.Wrapper;
import jessica.event.DamageBlockEvent;
import jessica.event.EventPacket;
import jessica.event.EventRender2D;
import jessica.event.MoveEvent;
import jessica.value.Value;

public class Module {
    private List<Value> values = new ArrayList<Value>();
    public double hoverOpacity = 0.0;
    private String name;
    protected boolean toggled;
    private Category category;
    private int keyBind;

    public Module(String m, int keyBind, Category c) {
        this.name = m;
        this.category = c;
        this.toggled = false;
        this.keyBind = keyBind;
    }

    public void toggle() {
        boolean bl = this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
        try {
            Wrapper.getFiles().saveModules();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onUpdate() {
    }

    public void onGetPacket(EventPacket packet) {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        try {
            Wrapper.getFiles().saveModules();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public List<Value> getValues() {
        return this.values;
    }

    public void addValue(Value value) {
        this.values.add(value);
    }

    public int getKeyBind() {
        return this.keyBind;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getAlias() {
        return this.getName().toLowerCase().replace(" ", "");
    }

    public void onMotion(MoveEvent e) {
    }

    public void onDamage(DamageBlockEvent e) {
    }

    public void onRender2D(EventRender2D e) {
    }

    public void onRender(double partialTicks) {
    }
}


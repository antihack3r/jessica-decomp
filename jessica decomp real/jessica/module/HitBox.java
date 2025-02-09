/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.value.ValueNumber;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;

public class HitBox
extends Module {
    private ValueNumber hitboxSize = new ValueNumber("Expand", 0.1, 0.0, 1.2);

    public HitBox() {
        super("HitBox", 0, Category.Combat);
        this.addValue(this.hitboxSize);
    }

    @Override
    public void onUpdate() {
        float n = (float)this.hitboxSize.getDoubleValue() * 10.0f;
        float result = Math.round(n);
        float result2 = result / 10.0f;
        for (Entity entities : Wrapper.world().loadedEntityList) {
            if (entities instanceof EntityPlayerSP || entities instanceof EntityArmorStand || !(entities instanceof EntityPlayer) && !(entities instanceof EntityLivingBase)) continue;
            entities.setCollisionBorderSize(result2);
        }
    }

    @Override
    public void onDisable() {
        for (Entity entities : Wrapper.world().loadedEntityList) {
            entities.setCollisionBorderSize(0.0f);
        }
    }
}


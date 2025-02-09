/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 */
package net.minecraft.advancements.critereon;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import javax.annotation.Nullable;
import net.minecraft.advancements.critereon.DistancePredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.advancements.critereon.NBTPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class EntityPredicate {
    public static final EntityPredicate field_192483_a = new EntityPredicate(null, DistancePredicate.field_193423_a, LocationPredicate.field_193455_a, MobEffectsPredicate.field_193473_a, NBTPredicate.field_193479_a);
    private final ResourceLocation field_192484_b;
    private final DistancePredicate field_192485_c;
    private final LocationPredicate field_193435_d;
    private final MobEffectsPredicate field_193436_e;
    private final NBTPredicate field_193437_f;

    public EntityPredicate(@Nullable ResourceLocation p_i47541_1_, DistancePredicate p_i47541_2_, LocationPredicate p_i47541_3_, MobEffectsPredicate p_i47541_4_, NBTPredicate p_i47541_5_) {
        this.field_192484_b = p_i47541_1_;
        this.field_192485_c = p_i47541_2_;
        this.field_193435_d = p_i47541_3_;
        this.field_193436_e = p_i47541_4_;
        this.field_193437_f = p_i47541_5_;
    }

    public boolean func_192482_a(EntityPlayerMP p_192482_1_, @Nullable Entity p_192482_2_) {
        if (this == field_192483_a) {
            return true;
        }
        if (p_192482_2_ == null) {
            return false;
        }
        if (this.field_192484_b != null && !EntityList.isStringEntityName(p_192482_2_, this.field_192484_b)) {
            return false;
        }
        if (!this.field_192485_c.func_193422_a(p_192482_1_.posX, p_192482_1_.posY, p_192482_1_.posZ, p_192482_2_.posX, p_192482_2_.posY, p_192482_2_.posZ)) {
            return false;
        }
        if (!this.field_193435_d.func_193452_a(p_192482_1_.getServerWorld(), p_192482_2_.posX, p_192482_2_.posY, p_192482_2_.posZ)) {
            return false;
        }
        if (!this.field_193436_e.func_193469_a(p_192482_2_)) {
            return false;
        }
        return this.field_193437_f.func_193475_a(p_192482_2_);
    }

    public static EntityPredicate func_192481_a(@Nullable JsonElement p_192481_0_) {
        if (p_192481_0_ != null && !p_192481_0_.isJsonNull()) {
            JsonObject jsonobject = JsonUtils.getJsonObject(p_192481_0_, "entity");
            ResourceLocation resourcelocation = null;
            if (jsonobject.has("type") && !EntityList.isStringValidEntityName(resourcelocation = new ResourceLocation(JsonUtils.getString(jsonobject, "type")))) {
                throw new JsonSyntaxException("Unknown entity type '" + resourcelocation + "', valid types are: " + EntityList.func_192840_b());
            }
            DistancePredicate distancepredicate = DistancePredicate.func_193421_a(jsonobject.get("distance"));
            LocationPredicate locationpredicate = LocationPredicate.func_193454_a(jsonobject.get("location"));
            MobEffectsPredicate mobeffectspredicate = MobEffectsPredicate.func_193471_a(jsonobject.get("effects"));
            NBTPredicate nbtpredicate = NBTPredicate.func_193476_a(jsonobject.get("nbt"));
            return new EntityPredicate(resourcelocation, distancepredicate, locationpredicate, mobeffectspredicate, nbtpredicate);
        }
        return field_192483_a;
    }
}


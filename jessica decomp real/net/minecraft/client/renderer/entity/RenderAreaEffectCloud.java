/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package net.minecraft.client.renderer.entity;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.util.ResourceLocation;

public class RenderAreaEffectCloud
extends Render<EntityAreaEffectCloud> {
    public RenderAreaEffectCloud(RenderManager manager) {
        super(manager);
    }

    @Override
    @Nullable
    protected ResourceLocation getEntityTexture(EntityAreaEffectCloud entity) {
        return null;
    }
}


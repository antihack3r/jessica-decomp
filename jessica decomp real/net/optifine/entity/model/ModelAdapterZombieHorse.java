/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderAbstractHorse;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterHorse;

public class ModelAdapterZombieHorse
extends ModelAdapterHorse {
    public ModelAdapterZombieHorse() {
        super(EntitySkeletonHorse.class, "skeleton_horse", 0.75f);
    }

    @Override
    public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        RenderAbstractHorse renderabstracthorse = new RenderAbstractHorse(rendermanager);
        renderabstracthorse.mainModel = modelBase;
        renderabstracthorse.shadowSize = shadowSize;
        return renderabstracthorse;
    }
}


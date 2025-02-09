/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.module.Flight;
import net.minecraft.client.entity.EntityOtherPlayerMP;

public class Freecam
extends Module {
    private EntityOtherPlayerMP player;

    public Freecam() {
        super("Freecam", 0, Category.Player);
    }

    @Override
    public void onEnable() {
        if (Wrapper.player() != null) {
            this.player = new EntityOtherPlayerMP(Wrapper.world(), Wrapper.player().getGameProfile());
            this.player.copyLocationAndAnglesFrom(Wrapper.player());
            this.player.setPosition(Wrapper.player().posX, Wrapper.player().posY, Wrapper.player().posZ);
            this.player.rotationYawHead = Wrapper.player().rotationYawHead;
            this.player.renderYawOffset = Wrapper.player().renderYawOffset;
            this.player.rotationYaw = Wrapper.player().rotationYaw;
            this.player.rotationPitch = Wrapper.player().rotationPitch;
            this.player.inventory.copyInventory(Wrapper.player().inventory);
            Wrapper.world().addEntityToWorld(this.player.getEntityId(), this.player);
            Wrapper.mc().entityRenderer.drawBlockOutline = false;
        }
    }

    @Override
    public void onUpdate() {
        Wrapper.player().noClip = true;
        Wrapper.player().onGround = false;
        Flight flight = new Flight();
        if (!flight.isToggled()) {
            flight.onUpdate();
        }
    }

    @Override
    public void onDisable() {
        Wrapper.player().noClip = false;
        Wrapper.player().motionX = 0.0;
        Wrapper.player().motionY = 0.0;
        Wrapper.player().motionZ = 0.0;
        Wrapper.player().landMovementFactor = 0.0f;
        Wrapper.player().jumpMovementFactor = 0.0f;
        Wrapper.mc().entityRenderer.drawBlockOutline = true;
        Wrapper.player().setPositionAndRotation(this.player.posX, this.player.posY, this.player.posZ, this.player.rotationYaw, this.player.rotationPitch);
        Wrapper.world().removeEntityFromWorld(this.player.getEntityId());
    }
}


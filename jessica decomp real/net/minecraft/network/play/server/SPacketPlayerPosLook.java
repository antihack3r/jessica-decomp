/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketPlayerPosLook
implements Packet<INetHandlerPlayClient> {
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private Set<EnumFlags> flags;
    private int teleportId;

    public SPacketPlayerPosLook() {
    }

    public SPacketPlayerPosLook(double xIn, double yIn, double zIn, float yawIn, float pitchIn, Set<EnumFlags> flagsIn, int teleportIdIn) {
        this.x = xIn;
        this.y = yIn;
        this.z = zIn;
        this.yaw = yawIn;
        this.pitch = pitchIn;
        this.flags = flagsIn;
        this.teleportId = teleportIdIn;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.yaw = buf.readFloat();
        this.pitch = buf.readFloat();
        this.flags = EnumFlags.unpack(buf.readUnsignedByte());
        this.teleportId = buf.readVarIntFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeFloat(this.yaw);
        buf.writeFloat(this.pitch);
        buf.writeByte(EnumFlags.pack(this.flags));
        buf.writeVarIntToBuffer(this.teleportId);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handlePlayerPosLook(this);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public int getTeleportId() {
        return this.teleportId;
    }

    public Set<EnumFlags> getFlags() {
        return this.flags;
    }

    public static enum EnumFlags {
        X(0),
        Y(1),
        Z(2),
        Y_ROT(3),
        X_ROT(4);

        private final int bit;

        private EnumFlags(int p_i46690_3_) {
            this.bit = p_i46690_3_;
        }

        private int getMask() {
            return 1 << this.bit;
        }

        private boolean isSet(int p_187043_1_) {
            return (p_187043_1_ & this.getMask()) == this.getMask();
        }

        public static Set<EnumFlags> unpack(int flags) {
            EnumSet<EnumFlags> set = EnumSet.noneOf(EnumFlags.class);
            EnumFlags[] enumFlagsArray = EnumFlags.values();
            int n = enumFlagsArray.length;
            int n2 = 0;
            while (n2 < n) {
                EnumFlags spacketplayerposlook$enumflags = enumFlagsArray[n2];
                if (spacketplayerposlook$enumflags.isSet(flags)) {
                    set.add(spacketplayerposlook$enumflags);
                }
                ++n2;
            }
            return set;
        }

        public static int pack(Set<EnumFlags> flags) {
            int i = 0;
            for (EnumFlags spacketplayerposlook$enumflags : flags) {
                i |= spacketplayerposlook$enumflags.getMask();
            }
            return i;
        }
    }
}


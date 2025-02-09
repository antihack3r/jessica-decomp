/*
 * Decompiled with CFR 0.152.
 */
package com.jcraft.jsch.jce;

import com.jcraft.jsch.HASH;
import java.security.MessageDigest;

public class MD5
implements HASH {
    MessageDigest md;

    public int getBlockSize() {
        return 16;
    }

    public void init() throws Exception {
        try {
            this.md = MessageDigest.getInstance("MD5");
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    public void update(byte[] foo, int start, int len) throws Exception {
        this.md.update(foo, start, len);
    }

    public byte[] digest() throws Exception {
        return this.md.digest();
    }
}


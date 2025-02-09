/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ArrayListMultimap
 *  com.google.common.collect.Multimap
 *  org.lwjgl.input.Keyboard
 */
package jessica.utils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import jessica.Wrapper;
import org.lwjgl.input.Keyboard;

public class Binds {
    private static boolean[] keyStates = new boolean[256];
    public static Multimap<Integer, String> binds = ArrayListMultimap.create();

    public static void makeBinds() {
        for (Integer key : binds.keySet()) {
            if (!Binds.checkKey(key)) continue;
            for (String s : binds.get((Object)key)) {
                Wrapper.player().sendChatMessage(s);
                try {
                    Thread.sleep(15L);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int getKey(String key) {
        return Keyboard.getKeyIndex((String)key);
    }

    private static boolean checkKey(int i) {
        if (Wrapper.mc().currentScreen != null) {
            return false;
        }
        return Keyboard.isKeyDown((int)i) != keyStates[i] ? (Binds.keyStates[i] = !keyStates[i]) : false;
    }

    public static void addBind(String key, String msg) {
        binds.put((Object)Binds.getKey(key.toUpperCase()), (Object)msg);
        Wrapper.msg("&aMessage \"" + msg + "\" binded on key " + key.toUpperCase() + ".", true);
    }

    public static void addBindSave(String key, String msg) {
        binds.put((Object)Binds.getKey(key.toUpperCase()), (Object)msg);
    }

    public static void delBind(String key) {
        if (!binds.containsKey((Object)Binds.getKey(key.toUpperCase()))) {
            Wrapper.msg("&cThis key not contains messages.", true);
        } else {
            binds.removeAll((Object)Binds.getKey(key.toUpperCase()));
            Wrapper.msg("&aDeleted messages from key " + key.toUpperCase() + ".", true);
        }
    }
}


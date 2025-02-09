/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  org.lwjgl.input.Keyboard
 */
package jessica.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import jessica.Module;
import jessica.Wrapper;
import jessica.gui.GuiIngameHook;
import jessica.utils.Binds;
import jessica.utils.FriendManager;
import jessica.utils.JsonUtils;
import jessica.value.Value;
import jessica.value.ValueBool;
import jessica.value.ValueNumber;
import org.lwjgl.input.Keyboard;

public class FileManager {
    public File dir;
    public File modules;
    public File binds;
    public File friends;
    public File values;

    public FileManager() {
        this.dir = new File(Wrapper.mc().mcDataDir, "Jessica");
        this.modules = new File(this.dir, "Modules.json");
        this.binds = new File(this.dir, "Binds.json");
        this.friends = new File(this.dir, "Friends.json");
        this.values = new File(this.dir, "Values.json");
        if (!this.dir.exists()) {
            this.dir.mkdir();
        }
    }

    public void init() throws Exception {
        this.loadModules();
        this.saveModules();
        this.loadBinds();
        this.saveBinds();
        this.loadFriends();
        this.saveFriends();
        this.loadValues();
        this.saveValues();
    }

    public void saveModules() {
        try {
            JsonObject json = new JsonObject();
            for (Module mod : Wrapper.getModules().values()) {
                JsonObject jsonMod = new JsonObject();
                jsonMod.addProperty("Toggled", Boolean.valueOf(mod.isToggled()));
                json.add(mod.getName(), (JsonElement)jsonMod);
            }
            PrintWriter save = new PrintWriter(new FileWriter(this.modules));
            save.println(JsonUtils.prettyGson.toJson((JsonElement)json));
            save.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void loadModules() {
        try {
            if (!this.modules.exists()) {
                this.modules.createNewFile();
            }
            BufferedReader load = new BufferedReader(new FileReader(this.modules));
            JsonObject json = (JsonObject)JsonUtils.jsonParser.parse((Reader)load);
            load.close();
            for (Map.Entry entry : json.entrySet()) {
                Module module = Wrapper.getModule((String)entry.getKey());
                if (module == null) continue;
                JsonObject jsonModule = (JsonObject)entry.getValue();
                boolean enabled = jsonModule.get("Toggled").getAsBoolean();
                module.setToggled(enabled);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void saveBinds() {
        try {
            JsonObject json = new JsonObject();
            Wrapper.getBinds();
            for (Integer key : Binds.binds.keySet()) {
                JsonObject jsonMod = new JsonObject();
                Wrapper.getBinds();
                for (String s : Binds.binds.get((Object)key)) {
                    jsonMod.addProperty(String.valueOf(new Random().nextInt(99999999)), s);
                    json.add(key.toString(), (JsonElement)jsonMod);
                }
            }
            PrintWriter save = new PrintWriter(new FileWriter(this.binds));
            save.println(JsonUtils.prettyGson.toJson((JsonElement)json));
            save.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadBinds() {
        try {
            if (!this.binds.exists()) {
                this.binds.createNewFile();
            }
            BufferedReader load = new BufferedReader(new FileReader(this.binds));
            JsonObject json = (JsonObject)JsonUtils.jsonParser.parse((Reader)load);
            load.close();
            for (Map.Entry entry : json.entrySet()) {
                JsonObject jsonModule = (JsonObject)entry.getValue();
                Set entrySet = jsonModule.entrySet();
                for (Map.Entry s : entrySet) {
                    JsonElement jsonModule2 = (JsonElement)s.getValue();
                    String message = jsonModule2.getAsString();
                    Wrapper.getBinds();
                    Binds.addBindSave(Keyboard.getKeyName((int)Integer.parseInt((String)entry.getKey())), message);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFriends() {
        try {
            BufferedReader load;
            ArrayList friends;
            if (!this.friends.exists()) {
                this.friends.createNewFile();
            }
            if ((friends = (ArrayList)JsonUtils.gson.fromJson((Reader)(load = new BufferedReader(new FileReader(this.friends))), ArrayList.class)) != null) {
                FriendManager.setFriends(friends);
            }
            load.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void saveFriends() {
        try {
            PrintWriter save = new PrintWriter(new FileWriter(this.friends));
            save.println(JsonUtils.prettyGson.toJson(FriendManager.getFriends()));
            save.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void write(File file, String text) {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            } else if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            fw.write(String.valueOf(String.valueOf(text)) + System.getProperty("line.separator"));
            fw.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveValues() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(this.values));
            writer.print("TotalTime:" + GuiIngameHook.tick + "\n");
            for (Module m : Wrapper.getModules().values()) {
                for (Value v : m.getValues()) {
                    writer.println(String.valueOf(m.getName()) + ":" + v.getName() + ":" + v.getValue());
                    writer.flush();
                }
            }
            writer.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void loadValues() {
        try {
            if (!this.values.exists()) {
                try {
                    this.values.createNewFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            BufferedReader load = new BufferedReader(new FileReader(this.values));
            String str = "";
            try {
                block14: {
                    block13: {
                        if (!true) break block13;
                        str = load.readLine();
                        if (str == null) return;
                        if (str.startsWith("#")) break block14;
                    }
                    do {
                        String name = str.split(":")[0];
                        String value = str.split(":")[1];
                        if (name.equalsIgnoreCase("TotalTime")) {
                            GuiIngameHook.tick = Integer.parseInt(value);
                        }
                        for (Module m : Wrapper.getModules().values()) {
                            if (!m.getName().equalsIgnoreCase(name)) continue;
                            for (Value v : m.getValues()) {
                                if (!v.getName().equalsIgnoreCase(value)) continue;
                                if (v instanceof ValueBool) {
                                    ValueBool vB = (ValueBool)v;
                                    vB.setValue(Boolean.parseBoolean(str.split(":")[2]));
                                }
                                if (!(v instanceof ValueNumber)) continue;
                                ValueNumber vD = (ValueNumber)v;
                                vD.setValue(Double.parseDouble(str.split(":")[2]));
                            }
                        }
                        str = load.readLine();
                        if (str == null) return;
                    } while (!str.startsWith("#"));
                }
                return;
            }
            catch (IOException iOException) {
                return;
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            // empty catch block
        }
    }

    public static String getClientDir() {
        String dir = Wrapper.mc().mcDataDir + "//Jessica//";
        File filedir = new File(dir);
        if (!filedir.exists()) {
            filedir.mkdirs();
        }
        return dir;
    }
}


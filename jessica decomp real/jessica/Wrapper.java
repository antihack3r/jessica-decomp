/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.Display
 */
package jessica;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;
import jessica.ChatCommands;
import jessica.Module;
import jessica.event.DamageBlockEvent;
import jessica.event.EventPacket;
import jessica.event.EventRender2D;
import jessica.event.MoveEvent;
import jessica.module.AAC;
import jessica.module.AirJump;
import jessica.module.AntiKnockback;
import jessica.module.AutoArmor;
import jessica.module.AutoBlock;
import jessica.module.AutoTool;
import jessica.module.AutoTotem;
import jessica.module.AutoWaterDrop;
import jessica.module.BedESP;
import jessica.module.BedExploit;
import jessica.module.Blink;
import jessica.module.BlinkingSkin;
import jessica.module.BowAimBot;
import jessica.module.BunnyHop;
import jessica.module.CameraNoClip;
import jessica.module.ChatCalculator;
import jessica.module.ChestESP;
import jessica.module.ChestStealer;
import jessica.module.ClickGuiModule;
import jessica.module.ESP2D;
import jessica.module.FakeCreative;
import jessica.module.FastPlace;
import jessica.module.Flight;
import jessica.module.Freecam;
import jessica.module.FullBright;
import jessica.module.Glide;
import jessica.module.HUD;
import jessica.module.HardCombat;
import jessica.module.HasteEffect;
import jessica.module.HealthLine;
import jessica.module.HitBox;
import jessica.module.InvCleaner;
import jessica.module.InventoryWalk;
import jessica.module.ItemESP;
import jessica.module.JumpEffect;
import jessica.module.Killaura18;
import jessica.module.KillauraPlus;
import jessica.module.Levitation;
import jessica.module.LongJump;
import jessica.module.NCP;
import jessica.module.NoSlowdown;
import jessica.module.PlayerESP;
import jessica.module.PlayerLine;
import jessica.module.Reach;
import jessica.module.SafeWalk;
import jessica.module.ScaffoldAAC;
import jessica.module.Spartan;
import jessica.module.Sprint;
import jessica.module.Timer;
import jessica.module.Tracers;
import jessica.module.TriggerBot;
import jessica.module.WaterWalk;
import jessica.utils.Binds;
import jessica.utils.FileManager;
import jessica.utils.FriendManager;
import jessica.utils.HackPack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.network.Packet;
import net.minecraft.util.text.TextComponentTranslation;
import org.lwjgl.opengl.Display;

public class Wrapper {
    private static Binds binds;
    private static HackPack hackpack;
    private static FileManager filemanager;
    private static ChatCommands chathandler;
    private static FriendManager friendmanager;
    public static Wrapper instance;
    private static TreeMap<String, Module> modules;
    private static String version;
    private static String clientname;

    static {
        version = "1.12.2";
        clientname = "Jessica";
    }

    public void setupClient() {
        binds = new Binds();
        hackpack = new HackPack();
        filemanager = new FileManager();
        chathandler = new ChatCommands();
        modules = new TreeMap();
        friendmanager = new FriendManager();
        Wrapper.addMod(new NCP());
        Wrapper.addMod(new AAC());
        Wrapper.addMod(new Spartan());
        Wrapper.addMod(new HardCombat());
        Wrapper.addMod(new Killaura18());
        Wrapper.addMod(new Reach());
        Wrapper.addMod(new AutoBlock());
        Wrapper.addMod(new AntiKnockback());
        Wrapper.addMod(new ChestStealer());
        Wrapper.addMod(new ScaffoldAAC());
        Wrapper.addMod(new Sprint());
        Wrapper.addMod(new BunnyHop());
        Wrapper.addMod(new Glide());
        Wrapper.addMod(new NoSlowdown());
        Wrapper.addMod(new Blink());
        Wrapper.addMod(new AirJump());
        Wrapper.addMod(new LongJump());
        Wrapper.addMod(new JumpEffect());
        Wrapper.addMod(new HasteEffect());
        Wrapper.addMod(new FakeCreative());
        Wrapper.addMod(new BedExploit());
        Wrapper.addMod(new PlayerESP());
        Wrapper.addMod(new Levitation());
        Wrapper.addMod(new Flight());
        Wrapper.addMod(new AutoArmor());
        Wrapper.addMod(new Tracers());
        Wrapper.addMod(new FullBright());
        Wrapper.addMod(new ChestESP());
        Wrapper.addMod(new FastPlace());
        Wrapper.addMod(new InventoryWalk());
        Wrapper.addMod(new SafeWalk());
        Wrapper.addMod(new BlinkingSkin());
        Wrapper.addMod(new ItemESP());
        Wrapper.addMod(new Freecam());
        Wrapper.addMod(new BowAimBot());
        Wrapper.addMod(new BedESP());
        Wrapper.addMod(new AutoTotem());
        Wrapper.addMod(new AutoTool());
        Wrapper.addMod(new Timer());
        Wrapper.addMod(new HitBox());
        Wrapper.addMod(new TriggerBot());
        Wrapper.addMod(new PlayerLine());
        Wrapper.addMod(new WaterWalk());
        Wrapper.addMod(new ChatCalculator());
        Wrapper.addMod(new ESP2D());
        Wrapper.addMod(new HealthLine());
        Wrapper.addMod(new ClickGuiModule());
        Wrapper.addMod(new HUD());
        Wrapper.addMod(new InvCleaner());
        Wrapper.addMod(new KillauraPlus());
        Wrapper.addMod(new AutoWaterDrop());
        Wrapper.addMod(new CameraNoClip());
        try {
            filemanager.init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Display.setTitle((String)(String.valueOf(String.valueOf(Wrapper.getClientName())) + " " + Wrapper.getVesrion()));
    }

    public static Binds getBinds() {
        return binds;
    }

    public static Minecraft mc() {
        return Minecraft.getMinecraft();
    }

    public static WorldClient world() {
        return Wrapper.mc().world;
    }

    public static GameSettings getSettings() {
        return Wrapper.getSettings();
    }

    public static String getVesrion() {
        return version;
    }

    public static String currentDate() {
        return new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date());
    }

    public static HackPack getHackPack() {
        return hackpack;
    }

    public static FileManager getFiles() {
        return filemanager;
    }

    public static String getClientName() {
        return clientname;
    }

    public static EntityPlayerSP player() {
        return Wrapper.mc().player;
    }

    public static void sendPacket(Packet p) {
        Wrapper.player().connection.sendPacket(p);
    }

    public static void sendPacketBypass(Packet p) {
        Wrapper.player().connection.sendPacket(p);
    }

    public static FriendManager getFriends() {
        return friendmanager;
    }

    public static ChatCommands getChatHandler() {
        return chathandler;
    }

    public static void msg(String s, boolean prefix) {
        s = String.valueOf(prefix ? "&f&l[&4&l" + Wrapper.getClientName() + "&f&l] &r" : "") + s;
        Wrapper.player().addChatMessage(new TextComponentTranslation(s.replace("&", "\u00a7"), new Object[0]));
    }

    private static void addMod(Module m) {
        if (modules.get(m.getAlias()) == null) {
            modules.put(m.getAlias(), m);
        }
    }

    public static Module getModule(String alias) {
        return Wrapper.getModules().get(alias.toLowerCase().replace(" ", ""));
    }

    public static TreeMap<String, Module> getModules() {
        return modules;
    }

    public static void keyPress(int key) {
        for (Module m : Wrapper.getModules().values()) {
            if (m.getKeyBind() != key) continue;
            m.toggle();
        }
    }

    public static void onUpdate() {
        for (Module m : Wrapper.getModules().values()) {
            if (!m.isToggled()) continue;
            m.onUpdate();
        }
    }

    public static void onRender(float partialTicks) {
        for (Module m : Wrapper.getModules().values()) {
            if (!m.isToggled()) continue;
            m.onRender(partialTicks);
        }
    }

    public static void onRender2D(EventRender2D e) {
        for (Module m : Wrapper.getModules().values()) {
            if (!m.isToggled()) continue;
            m.onRender2D(e);
        }
    }

    public static void onGetPacket(EventPacket packet) {
        for (Module m : Wrapper.getModules().values()) {
            if (!m.isToggled()) continue;
            m.onGetPacket(packet);
        }
    }

    public static void onMotion(MoveEvent e) {
        for (Module m : Wrapper.getModules().values()) {
            if (!m.isToggled()) continue;
            m.onMotion(e);
        }
    }

    public static void onDamage(DamageBlockEvent e) {
        for (Module m : Wrapper.getModules().values()) {
            if (!m.isToggled()) continue;
            m.onDamage(e);
        }
    }
}


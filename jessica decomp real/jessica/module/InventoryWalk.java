/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package jessica.module;

import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.gui.clickgui.ClickGui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class InventoryWalk
extends Module {
    public InventoryWalk() {
        super("InvWalk", 0, Category.Player);
    }

    @Override
    public void onUpdate() {
        KeyBinding[] moveKeys = new KeyBinding[]{Wrapper.mc().gameSettings.keyBindBack, Wrapper.mc().gameSettings.keyBindForward, Wrapper.mc().gameSettings.keyBindJump, Wrapper.mc().gameSettings.keyBindLeft, Wrapper.mc().gameSettings.keyBindRight, Wrapper.mc().gameSettings.keyBindSprint};
        if (Wrapper.mc().currentScreen instanceof GuiContainer && !(Wrapper.mc().currentScreen instanceof GuiIngameMenu) && !(Wrapper.mc().currentScreen instanceof GuiChat) || Wrapper.mc().currentScreen instanceof ClickGui) {
            KeyBinding[] keyBindingArray = moveKeys;
            int n = moveKeys.length;
            int n2 = 0;
            while (n2 < n) {
                KeyBinding key = keyBindingArray[n2];
                key.pressed = Keyboard.isKeyDown((int)key.getKeyCode());
                ++n2;
            }
        }
    }
}


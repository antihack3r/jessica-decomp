/*
 * Decompiled with CFR 0.152.
 */
package jessica.module;

import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jessica.Category;
import jessica.Module;
import jessica.Wrapper;
import jessica.event.EventPacket;
import jessica.value.ValueNumber;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketChat;

public class ChatCalculator
extends Module {
    ValueNumber delay = new ValueNumber("Delay (ms)", 1337.0, 0.0, 5000.0);

    public ChatCalculator() {
        super("ChatCalculator", 0, Category.Exploits);
        this.addValue(this.delay);
    }

    @Override
    public void onGetPacket(EventPacket packet) {
        if (packet.getPacket() instanceof SPacketChat) {
            SPacketChat m = (SPacketChat)packet.getPacket();
            final String message = m.getChatComponent().getUnformattedText();
            String skobka = "(\\()";
            String operands = "(\\-|\\+|\\*|/)";
            int skobkacount = 0;
            int operandcount = 0;
            Pattern pt = Pattern.compile("(\\()");
            Matcher mt = pt.matcher(message);
            Pattern po = Pattern.compile("(\\-|\\+|\\*|/)");
            Matcher mo = po.matcher(message);
            while (mt.find()) {
                skobkacount += mt.groupCount();
            }
            while (mo.find()) {
                operandcount += mo.groupCount();
            }
            if (message.startsWith("  \u0420\u0435\u0448\u0438\u0442\u0435 \u043f\u0440\u0438\u043c\u0435\u0440:")) {
                new Thread(){

                    @Override
                    public void run() {
                        try {
                            Integer result;
                            Thread.sleep((long)ChatCalculator.this.delay.getDoubleValue());
                            Integer num1 = 0;
                            Integer num2 = 0;
                            String sub1 = message.substring(0, 20);
                            String sub2 = message.substring(20, message.length());
                            Pattern p = Pattern.compile("\\d+");
                            Matcher m = p.matcher(sub1);
                            Matcher m2 = p.matcher(sub2);
                            while (m.find()) {
                                num1 = num1 + Integer.parseInt(m.group());
                            }
                            while (m2.find()) {
                                num2 = num2 + Integer.parseInt(m2.group());
                            }
                            char getsymbol = message.charAt(19);
                            char getsymbol2 = message.charAt(20);
                            char getsymbol3 = message.charAt(21);
                            if (getsymbol == '-') {
                                result = num1 - num2;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol == '+') {
                                result = num1 + num2;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol == '/') {
                                result = num1 / num2;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol == '*') {
                                result = num1 * num2;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol2 == '-') {
                                result = num1 - num2;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol2 == '+') {
                                result = num1 + num2;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol2 == '/') {
                                result = num1 / num2;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol2 == '*') {
                                result = num1 * num2;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol3 == '-') {
                                result = num1 - num2;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol3 == '+') {
                                result = num1 + num2;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol3 == '/') {
                                result = num1 / num2;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol3 == '*') {
                                result = num1 * num2;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            System.out.println("null3");
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
            if (message.startsWith("(i) \u0420\u0435\u0448\u0438\u0442\u0435 \u043f\u0440\u0438\u043c\u0435\u0440")) {
                new Thread(){

                    @Override
                    public void run() {
                        try {
                            Thread.sleep((long)ChatCalculator.this.delay.getDoubleValue());
                            Pattern p = Pattern.compile("(\\d+)");
                            Matcher m2 = p.matcher(message);
                            Integer sum = 0;
                            while (m2.find()) {
                                sum = sum + Integer.parseInt(m2.group());
                            }
                            sum = sum - 18;
                            Wrapper.sendPacket(new CPacketChatMessage(sum.toString()));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
            if (message.startsWith("[CegouCraft] \u0420\u0435\u0448\u0438\u0442\u0435")) {
                new Thread(){

                    @Override
                    public void run() {
                        try {
                            Thread.sleep((long)ChatCalculator.this.delay.getDoubleValue());
                            Pattern p3 = Pattern.compile("(\\d+)");
                            Matcher m3 = p3.matcher(message);
                            Integer sum = 0;
                            while (m3.find()) {
                                sum = sum + Integer.parseInt(m3.group());
                            }
                            Wrapper.sendPacket(new CPacketChatMessage(sum.toString()));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
            if (message.startsWith("   \u0420\u0435\u0448\u0438\u0442\u0435 \u043f\u0440\u0438\u043c\u0435\u0440:") && skobkacount == 0 && operandcount == 4) {
                new Thread(){

                    @Override
                    public void run() {
                        try {
                            Integer number;
                            int i;
                            Thread.sleep((long)ChatCalculator.this.delay.getDoubleValue());
                            String sub5 = message.substring(18);
                            CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
                            StringTokenizer sTok = new StringTokenizer(sub5, "(\\-|\\+|\\*|/)", true);
                            while (sTok.hasMoreTokens()) {
                                list.add(sTok.nextToken());
                            }
                            for (String ss : list) {
                                if (!ss.equals("*") && !ss.equals("/")) continue;
                                i = 0;
                                while (i < list.size()) {
                                    if (((String)list.get(i)).equals("*")) {
                                        number = Integer.parseInt((String)list.get(i - 1)) * Integer.parseInt((String)list.get(i + 1));
                                        list.set(i, number.toString());
                                        list.remove(i + 1);
                                        list.remove(i - 1);
                                    } else if (((String)list.get(i)).equals("/")) {
                                        number = Integer.parseInt((String)list.get(i - 1)) / Integer.parseInt((String)list.get(i + 1));
                                        list.set(i, number.toString());
                                        list.remove(i + 1);
                                        list.remove(i - 1);
                                    }
                                    ++i;
                                }
                            }
                            for (String ss : list) {
                                if (!ss.equals("+") && !ss.equals("-")) continue;
                                i = 0;
                                while (i < list.size()) {
                                    if (((String)list.get(i)).equals("+")) {
                                        number = Integer.parseInt((String)list.get(i - 1)) + Integer.parseInt((String)list.get(i + 1));
                                        list.set(i, number.toString());
                                        list.remove(i + 1);
                                        list.remove(i - 1);
                                    } else if (((String)list.get(i)).equals("-")) {
                                        number = Integer.parseInt((String)list.get(i - 1)) - Integer.parseInt((String)list.get(i + 1));
                                        list.set(i, number.toString());
                                        list.remove(i + 1);
                                        list.remove(i - 1);
                                    }
                                    ++i;
                                }
                            }
                            for (String l : list) {
                                Wrapper.sendPacket(new CPacketChatMessage(l));
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
            if (message.startsWith("   \u0420\u0435\u0448\u0438\u0442\u0435 \u043f\u0440\u0438\u043c\u0435\u0440:") && skobkacount == 2) {
                new Thread(){

                    @Override
                    public void run() {
                        try {
                            Thread.sleep((long)ChatCalculator.this.delay.getDoubleValue());
                            int n = message.charAt(28) == '(' ? 29 : 27;
                            if (message.charAt(27) == '(') {
                                n = 28;
                            }
                            String substr = message.substring(n, message.length());
                            char getsymbol = message.charAt(20);
                            char getsymbol2 = message.charAt(21);
                            char getsymbol5 = message.charAt(24);
                            char getsymbol6 = message.charAt(25);
                            char getsymbol7 = message.charAt(26);
                            char getsymbol9 = substr.charAt(1);
                            char getsymbol10 = substr.charAt(2);
                            char getsymbol11 = substr.charAt(3);
                            Integer num1 = 0;
                            Integer num2 = 0;
                            Integer num3 = 0;
                            Integer num4 = 0;
                            String sub1 = message.substring(19, 21);
                            String sub2 = message.substring(21, 24);
                            String sub3 = substr.substring(0, 2);
                            String sub4 = substr.substring(2, substr.length());
                            Pattern p = Pattern.compile("\\d+");
                            Matcher m123 = p.matcher(sub1);
                            Matcher m2 = p.matcher(sub2);
                            Matcher m3 = p.matcher(sub3);
                            Matcher m4 = p.matcher(sub4);
                            while (m123.find()) {
                                num1 = num1 + Integer.parseInt(m123.group());
                            }
                            while (m2.find()) {
                                num2 = num2 + Integer.parseInt(m2.group());
                            }
                            while (m3.find()) {
                                num3 = num3 + Integer.parseInt(m3.group());
                            }
                            while (m4.find()) {
                                num4 = num4 + Integer.parseInt(m4.group());
                            }
                            int first = 0;
                            int second = 0;
                            Integer result = 0;
                            if (getsymbol == '-') {
                                first = num1 - num2;
                            }
                            if (getsymbol == '+') {
                                first = num1 + num2;
                            }
                            if (getsymbol == '/') {
                                first = num1 / num2;
                            }
                            if (getsymbol == '*') {
                                first = num1 * num2;
                            }
                            if (getsymbol2 == '-') {
                                first = num1 - num2;
                            }
                            if (getsymbol2 == '+') {
                                first = num1 + num2;
                            }
                            if (getsymbol2 == '/') {
                                first = num1 / num2;
                            }
                            if (getsymbol2 == '*') {
                                first = num1 * num2;
                            }
                            if (getsymbol9 == '-') {
                                second = num3 - num4;
                            }
                            if (getsymbol9 == '+') {
                                second = num3 + num4;
                            }
                            if (getsymbol9 == '/') {
                                second = num1 / num2;
                            }
                            if (getsymbol9 == '*') {
                                second = num3 * num4;
                            }
                            if (getsymbol10 == '-') {
                                second = num3 - num4;
                            }
                            if (getsymbol10 == '+') {
                                second = num3 + num4;
                            }
                            if (getsymbol10 == '/') {
                                second = num3 / num4;
                            }
                            if (getsymbol10 == '*') {
                                second = num3 * num4;
                            }
                            if (getsymbol11 == '-') {
                                second = num3 - num4;
                            }
                            if (getsymbol11 == '+') {
                                second = num3 + num4;
                            }
                            if (getsymbol11 == '/') {
                                second = num3 / num4;
                            }
                            if (getsymbol11 == '*') {
                                second = num3 * num4;
                            }
                            if (getsymbol5 == '-') {
                                result = first - second;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol5 == '+') {
                                result = first + second;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol5 == '/') {
                                result = first / second;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol5 == '*') {
                                result = first * second;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol6 == '-') {
                                result = first - second;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol6 == '+') {
                                result = first + second;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol6 == '/') {
                                result = first / second;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol6 == '*') {
                                result = first * second;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol7 == '-') {
                                result = first - second;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol7 == '+') {
                                result = first + second;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol7 == '/') {
                                result = first / second;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                            if (getsymbol7 == '*') {
                                result = first * second;
                                Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
            if (message.startsWith("   \u0420\u0435\u0448\u0438\u0442\u0435 \u043f\u0440\u0438\u043c\u0435\u0440:") && message.indexOf("(") != 18 && skobkacount == 1) {
                new Thread(){

                    @Override
                    public void run() {
                        try {
                            Thread.sleep((long)ChatCalculator.this.delay.getDoubleValue());
                            int n = message.charAt(23) == '(' ? 24 : 23;
                            String substr = message.substring(n, message.length());
                            char getsymbol = message.charAt(20);
                            char getsymbol2 = message.charAt(21);
                            char getsymbol3 = substr.charAt(1);
                            char getsymbol4 = substr.charAt(2);
                            char getsymbol5 = substr.charAt(3);
                            Integer num1 = 0;
                            Integer num2 = 0;
                            Integer num3 = 0;
                            String sub1 = message.substring(18, 20);
                            String sub2 = substr.substring(0, 2);
                            String sub3 = substr.substring(2, substr.length());
                            Pattern p = Pattern.compile("\\d+");
                            Matcher m11 = p.matcher(sub1);
                            Matcher m2 = p.matcher(sub2);
                            Matcher m3 = p.matcher(sub3);
                            while (m11.find()) {
                                num1 = num1 + Integer.parseInt(m11.group());
                            }
                            while (m2.find()) {
                                num2 = num2 + Integer.parseInt(m2.group());
                            }
                            while (m3.find()) {
                                num3 = num3 + Integer.parseInt(m3.group());
                            }
                            System.out.println("\u041f\u0435\u0440\u0432\u043e\u0435 \u0447\u0438\u0441\u043b\u043e" + num1);
                            System.out.println("\u0412\u0442\u043e\u0440\u043e\u0435 \u0447\u0438\u0441\u043b\u043e" + num2);
                            System.out.println("\u0422\u0440\u0435\u0442\u044c\u0435 \u0447\u0438\u0441\u043b\u043e" + num3);
                            int first = 0;
                            Integer result = 0;
                            if (getsymbol3 == '-') {
                                first = num2 - num3;
                            }
                            if (getsymbol3 == '+') {
                                first = num2 + num3;
                            }
                            if (getsymbol3 == '/') {
                                first = num2 / num3;
                            }
                            if (getsymbol3 == '*') {
                                first = num2 * num3;
                            }
                            if (getsymbol4 == '-') {
                                first = num2 - num3;
                            }
                            if (getsymbol4 == '+') {
                                first = num2 + num3;
                            }
                            if (getsymbol4 == '/') {
                                first = num2 / num3;
                            }
                            if (getsymbol4 == '*') {
                                first = num2 * num3;
                            }
                            if (getsymbol5 == '-') {
                                first = num2 - num3;
                            }
                            if (getsymbol5 == '+') {
                                first = num2 + num3;
                            }
                            if (getsymbol5 == '/') {
                                first = num2 / num3;
                            }
                            if (getsymbol5 == '*') {
                                first = num2 * num3;
                            }
                            if (getsymbol == '-') {
                                result = num1 - first;
                            }
                            if (getsymbol == '+') {
                                result = num1 + first;
                            }
                            if (getsymbol == '/') {
                                result = num1 / first;
                            }
                            if (getsymbol == '*') {
                                result = num1 * first;
                            }
                            if (getsymbol2 == '-') {
                                result = num1 - first;
                            }
                            if (getsymbol2 == '+') {
                                result = num1 + first;
                            }
                            if (getsymbol2 == '/') {
                                result = num1 / first;
                            }
                            if (getsymbol2 == '*') {
                                result = num1 * first;
                            }
                            System.out.println(result);
                            Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
            if (message.startsWith("   \u0420\u0435\u0448\u0438\u0442\u0435 \u043f\u0440\u0438\u043c\u0435\u0440: (") && operandcount == 2) {
                new Thread(){

                    @Override
                    public void run() {
                        try {
                            Thread.sleep((long)ChatCalculator.this.delay.getDoubleValue());
                            Integer num1 = 0;
                            Integer num2 = 0;
                            Integer num3 = 0;
                            String sub1 = message.substring(19, 21);
                            String sub2 = message.substring(21, 24);
                            String sub3 = message.substring(25, message.length());
                            Pattern p = Pattern.compile("\\d+");
                            Matcher m11 = p.matcher(sub1);
                            Matcher m2 = p.matcher(sub2);
                            Matcher m3 = p.matcher(sub3);
                            while (m11.find()) {
                                num1 = num1 + Integer.parseInt(m11.group());
                            }
                            while (m2.find()) {
                                num2 = num2 + Integer.parseInt(m2.group());
                            }
                            while (m3.find()) {
                                num3 = num3 + Integer.parseInt(m3.group());
                            }
                            System.out.println("\u041f\u0435\u0440\u0432\u043e\u0435 \u0447\u0438\u0441\u043b\u043e" + num1);
                            System.out.println("\u0412\u0442\u043e\u0440\u043e\u0435 \u0447\u0438\u0441\u043b\u043e" + num2);
                            System.out.println("\u0422\u0440\u0435\u0442\u044c\u0435 \u0447\u0438\u0441\u043b\u043e" + num3);
                            char getsymbol = message.charAt(20);
                            char getsymbol2 = message.charAt(21);
                            char getsymbol5 = message.charAt(24);
                            char getsymbol6 = message.charAt(25);
                            char getsymbol7 = message.charAt(26);
                            int first = 0;
                            Integer result = 0;
                            if (getsymbol == '-' || getsymbol2 == '-') {
                                first = num1 - num2;
                            }
                            if (getsymbol == '+' || getsymbol2 == '+') {
                                first = num1 + num2;
                            }
                            if (getsymbol == '/' || getsymbol2 == '/') {
                                first = num1 / num2;
                            }
                            if (getsymbol == '*' || getsymbol2 == '*') {
                                first = num1 * num2;
                            }
                            if (getsymbol5 == '-' || getsymbol6 == '-' || getsymbol7 == '-') {
                                result = first - num3;
                            }
                            if (getsymbol5 == '+' || getsymbol6 == '+' || getsymbol7 == '+') {
                                result = first + num3;
                            }
                            if (getsymbol5 == '/' || getsymbol6 == '/' || getsymbol7 == '/') {
                                result = first / num3;
                            }
                            if (getsymbol5 == '*' || getsymbol6 == '*' || getsymbol7 == '*') {
                                result = first * num3;
                            }
                            System.out.println(result);
                            Wrapper.sendPacket(new CPacketChatMessage(result.toString()));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }
    }
}


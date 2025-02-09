/*
 * Decompiled with CFR 0.152.
 */
package jessica.gui;

import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GuiPortScanner
extends JFrame {
    private JButton a;
    private JLabel b;
    private JLabel c;
    private JLabel d;
    private JLabel e;
    private JTextField f;
    private JTextField g;
    private JTextField h;
    private TextArea i;
    private TextField j;

    public GuiPortScanner() {
        this.a();
    }

    private void a() {
        this.f = new JTextField();
        this.g = new JTextField();
        this.b = new JLabel();
        this.h = new JTextField();
        this.c = new JLabel();
        this.d = new JLabel();
        this.e = new JLabel();
        this.a = new JButton();
        this.i = new TextArea();
        this.j = new TextField();
        this.b.setText(":");
        this.c.setText("-");
        this.d.setText("IP");
        this.e.setText("Port");
        this.a.setText("Start");
        this.a.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                GuiPortScanner.this.a(evt);
            }
        });
        this.j.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                GuiPortScanner.this.b(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.f, -2, 140, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.b).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.g, -2, 65, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.c).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.h, -2, 65, -2)).addGroup(layout.createSequentialGroup().addGap(73, 73, 73).addComponent(this.d).addGap(140, 140, 140).addComponent(this.e))).addGap(0, 86, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.i, -1, -1, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addComponent(this.a, -2, 122, -2).addGap(99, 99, 99).addComponent(this.j, -1, -1, Short.MAX_VALUE))))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(17, 17, 17).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.d).addComponent(this.e)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.f, -2, -1, -2).addComponent(this.g, -2, -1, -2).addComponent(this.b).addComponent(this.h, -2, -1, -2).addComponent(this.c)).addGap(18, 18, 18).addComponent(this.a)).addComponent(this.j, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.i, -1, 182, Short.MAX_VALUE).addContainerGap()));
        this.pack();
    }

    private void a(ActionEvent actionEvent) {
        this.i.setText("");
        int i = Integer.parseInt(this.g.getText());
        while (i <= Integer.parseInt(this.h.getText())) {
            if (this.a(this.f.getText(), i, 150)) {
                this.i.append("\u041e\u0442\u043a\u0440\u044b\u0442\u044b\u0439 \u043f\u043e\u0440\u0442: " + i + "\n");
            }
            this.j.setText("\u041f\u043e\u0440\u0442: " + i);
            ++i;
        }
    }

    public boolean a(String s, int n, int n2) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(s, n), n2);
            socket.close();
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    private void b(ActionEvent actionEvent) {
    }

    public static void main(String[] array) {
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
            int length = installedLookAndFeels.length;
            int i = 0;
            while (i < length) {
                UIManager.LookAndFeelInfo lookAndFeelInfo = installedLookAndFeels[i];
                if ("Nimbus".equals(lookAndFeelInfo.getName())) {
                    UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                    break;
                }
                ++i;
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiPortScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(GuiPortScanner.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(GuiPortScanner.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(GuiPortScanner.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                new GuiPortScanner().setVisible(true);
            }
        });
    }
}


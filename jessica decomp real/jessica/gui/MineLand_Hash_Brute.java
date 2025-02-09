/*
 * Decompiled with CFR 0.152.
 */
package jessica.gui;

import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;

public class MineLand_Hash_Brute
extends JFrame {
    private static boolean isStop = false;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private TextArea textArea1;

    public MineLand_Hash_Brute() {
        this.initComponents();
    }

    private void initComponents() {
        this.jTextField1 = new JTextField();
        this.jLabel1 = new JLabel();
        this.jTextField2 = new JTextField();
        this.jLabel2 = new JLabel();
        this.jTextField3 = new JTextField();
        this.jLabel3 = new JLabel();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.textArea1 = new TextArea();
        this.jLabel4 = new JLabel();
        this.setTitle("MineLand Hash Brute");
        this.jTextField1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MineLand_Hash_Brute.this.jTextField1ActionPerformed(evt);
            }
        });
        this.jLabel1.setText("Hash");
        this.jTextField2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MineLand_Hash_Brute.this.jTextField2ActionPerformed(evt);
            }
        });
        this.jLabel2.setText("Salt");
        this.jTextField3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MineLand_Hash_Brute.this.jTextField3ActionPerformed(evt);
            }
        });
        this.jLabel3.setText("Path to dictionary");
        this.jButton1.setText("...");
        this.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MineLand_Hash_Brute.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton2.setText("Stop");
        this.jButton2.setToolTipText("");
        this.jButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MineLand_Hash_Brute.this.jButton2ActionPerformed(evt);
            }
        });
        this.jButton3.setText("Start");
        this.jButton3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MineLand_Hash_Brute.this.jButton3ActionPerformed(evt);
            }
        });
        this.jLabel4.setText("Log");
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jTextField1).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addComponent(this.jLabel2).addComponent(this.jTextField2, -2, 197, -2).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jTextField3, -2, 301, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 45, -2)).addGroup(layout.createSequentialGroup().addComponent(this.jButton3, -2, 163, -2).addGap(18, 18, 18).addComponent(this.jButton2, -2, 163, -2)).addComponent(this.jLabel3)).addGap(65, 65, 65).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.textArea1, -2, 282, -2)))).addGap(0, 8, Short.MAX_VALUE))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(16, 16, 16).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.jLabel4)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField3, -2, -1, -2).addComponent(this.jButton1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jButton3, -1, 92, Short.MAX_VALUE).addComponent(this.jButton2, -1, -1, Short.MAX_VALUE)).addGap(16, 16, 16)).addGroup(layout.createSequentialGroup().addComponent(this.textArea1, -1, -1, Short.MAX_VALUE).addContainerGap()))));
        this.pack();
    }

    private void jTextField1ActionPerformed(ActionEvent evt) {
    }

    private void jTextField2ActionPerformed(ActionEvent evt) {
    }

    private void jTextField3ActionPerformed(ActionEvent evt) {
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == 0) {
            File selectedFile = jfc.getSelectedFile();
            this.jTextField3.setText(selectedFile.getAbsolutePath());
        }
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        isStop = true;
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        isStop = false;
        this.textArea1.append("Process started!\n");
        new Thread(){

            @Override
            public void run() {
                BufferedReader br = null;
                FileReader fr = null;
                try {
                    String sCurrentLine;
                    fr = new FileReader(MineLand_Hash_Brute.this.jTextField3.getText());
                    br = new BufferedReader(fr);
                    while ((sCurrentLine = br.readLine()) != null && !isStop) {
                        if (!MineLand_Hash_Brute.hashPass(sCurrentLine, MineLand_Hash_Brute.this.jTextField2.getText()).equals(MineLand_Hash_Brute.this.jTextField1.getText())) continue;
                        MineLand_Hash_Brute.this.textArea1.append("Password found: " + sCurrentLine + "\n");
                        break;
                    }
                    MineLand_Hash_Brute.this.textArea1.append("The process is completed!\n");
                    isStop = false;
                }
                catch (IOException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static String hashPass(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        byte[] byArray = bytes;
        int n = bytes.length;
        int n2 = 0;
        while (n2 < n) {
            byte b = byArray[n2];
            sb.append(Integer.toString((b & 0xFF) + 256, 32).substring(1));
            ++n2;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
            int length = installedLookAndFeels.length;
            int i = 0;
            while (i < length) {
                UIManager.LookAndFeelInfo info = installedLookAndFeels[i];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                ++i;
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MineLand_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(MineLand_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(MineLand_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(MineLand_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                new MineLand_Hash_Brute().setVisible(true);
            }
        });
    }
}


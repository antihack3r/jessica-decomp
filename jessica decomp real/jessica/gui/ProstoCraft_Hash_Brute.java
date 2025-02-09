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
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
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

public class ProstoCraft_Hash_Brute
extends JFrame {
    static int i = 1;
    private static final char[] LOOKUP = "0123456789abcdef".toCharArray();
    private JButton jButton1;
    private JButton jButton3;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JTextField jTextField1;
    private JTextField jTextField3;
    private TextArea textArea1;

    public ProstoCraft_Hash_Brute() {
        this.initComponents();
    }

    private void initComponents() {
        this.jTextField1 = new JTextField();
        this.jLabel1 = new JLabel();
        this.jTextField3 = new JTextField();
        this.jLabel3 = new JLabel();
        this.jButton1 = new JButton();
        this.jButton3 = new JButton();
        this.textArea1 = new TextArea();
        this.jLabel4 = new JLabel();
        this.setTitle("ProstoCraft Hash Brute");
        this.jTextField1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                ProstoCraft_Hash_Brute.this.jTextField1ActionPerformed(evt);
            }
        });
        this.jLabel1.setText("Hash");
        this.jTextField3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                ProstoCraft_Hash_Brute.this.jTextField3ActionPerformed(evt);
            }
        });
        this.jLabel3.setText("Path to dictionary");
        this.jButton1.setText("...");
        this.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                ProstoCraft_Hash_Brute.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton3.setText("Start");
        this.jButton3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                ProstoCraft_Hash_Brute.this.jButton3ActionPerformed(evt);
            }
        });
        this.jLabel4.setText("Log");
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jTextField1).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jTextField3, -2, 301, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 45, -2)).addComponent(this.jButton3, -2, 163, -2).addComponent(this.jLabel3)).addGap(65, 65, 65).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.textArea1, -2, 282, -2)))).addGap(0, 8, Short.MAX_VALUE))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(28, 28, 28).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField1, -2, -1, -2).addGap(40, 40, 40).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.jLabel4)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField3, -2, -1, -2).addComponent(this.jButton1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton3, -1, 92, Short.MAX_VALUE).addGap(16, 16, 16)).addGroup(layout.createSequentialGroup().addComponent(this.textArea1, -1, -1, Short.MAX_VALUE).addContainerGap()))));
        this.pack();
    }

    public static byte[] getHash(String pass, byte[] salt, int iter, int len, String alg) throws Exception {
        PBEKeySpec ks = new PBEKeySpec(pass.toCharArray(), salt, iter, len);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(alg);
        return skf.generateSecret(ks).getEncoded();
    }

    public static String getHash(String pass, String salt, int iter, int len, String alg) throws Exception {
        byte[] hash = ProstoCraft_Hash_Brute.getHash(pass, ProstoCraft_Hash_Brute.stringToBytes(salt), iter, len, alg);
        return new String(ProstoCraft_Hash_Brute.bytesToString(hash));
    }

    public static char[] bytesToString(byte[] b) {
        char[] res = new char[b.length * 2];
        int i = 0;
        while (i < b.length) {
            res[i * 2] = LOOKUP[b[i] >>> 4 & 0xF];
            res[i * 2 + 1] = LOOKUP[b[i] & 0xF];
            ++i;
        }
        return res;
    }

    public static byte[] stringToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        int i = 0;
        while (i < len) {
            data[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
            i += 2;
        }
        return data;
    }

    private void jTextField1ActionPerformed(ActionEvent evt) {
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

    private void jButton3ActionPerformed(ActionEvent evt) {
        this.textArea1.append("Prepare!\n");
        new Thread(){

            @Override
            public void run() {
                BufferedReader br = null;
                FileReader fr = null;
                try {
                    try {
                        String sCurrentLine;
                        fr = new FileReader(ProstoCraft_Hash_Brute.this.jTextField3.getText());
                        br = new BufferedReader(fr);
                        HashSet<String> passwords = new HashSet<String>();
                        while ((sCurrentLine = br.readLine()) != null) {
                            passwords.add(sCurrentLine);
                        }
                        ProstoCraft_Hash_Brute.this.textArea1.append("Process started!\n");
                        passwords.parallelStream().forEach(p -> {
                            try {
                                String hash = ProstoCraft_Hash_Brute.getHash(String.valueOf(p) + "V6IBX8JXtMa_6UF08Wf5", ProstoCraft_Hash_Brute.this.jTextField1.getText().split(":")[1], 50000, 160, "PBKDF2WithHmacSHA256");
                                System.out.println(i);
                                ++i;
                                if (hash.equals(ProstoCraft_Hash_Brute.this.jTextField1.getText().split(":")[0])) {
                                    ProstoCraft_Hash_Brute.this.textArea1.append("Password found: " + p + "\n");
                                }
                            }
                            catch (Exception ex) {
                                Logger.getLogger(ProstoCraft_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        i = 1;
                        ProstoCraft_Hash_Brute.this.textArea1.append("The process is completed!\n");
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        try {
                            if (br != null) {
                                br.close();
                            }
                            if (fr != null) {
                                fr.close();
                            }
                        }
                        catch (IOException ex3) {
                            ex3.printStackTrace();
                        }
                    }
                    catch (Exception ex2) {
                        Logger.getLogger(ProstoCraft_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex2);
                        try {
                            if (br != null) {
                                br.close();
                            }
                            if (fr != null) {
                                fr.close();
                            }
                        }
                        catch (IOException ex3) {
                            ex3.printStackTrace();
                        }
                    }
                }
                finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                        if (fr != null) {
                            fr.close();
                        }
                    }
                    catch (IOException ex3) {
                        ex3.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        try {
            UIManager.LookAndFeelInfo[] lookAndFeelInfoArray = UIManager.getInstalledLookAndFeels();
            int n = lookAndFeelInfoArray.length;
            int n2 = 0;
            while (n2 < n) {
                UIManager.LookAndFeelInfo info = lookAndFeelInfoArray[n2];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                ++n2;
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(ProstoCraft_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(ProstoCraft_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(ProstoCraft_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(ProstoCraft_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                new ProstoCraft_Hash_Brute().setVisible(true);
            }
        });
    }
}


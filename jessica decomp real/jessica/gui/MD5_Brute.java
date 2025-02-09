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

public class MD5_Brute
extends JFrame {
    static int i = 1;
    private static final char[] LOOKUP;
    private static boolean isStop;
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

    static {
        isStop = false;
        LOOKUP = "0123456789abcdef".toCharArray();
    }

    public MD5_Brute() {
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
        this.setTitle("MD5 Hash Brute");
        this.jTextField1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MD5_Brute.this.jTextField1ActionPerformed(evt);
            }
        });
        this.jLabel1.setText("Hash");
        this.jTextField2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MD5_Brute.this.jTextField3ActionPerformed(evt);
            }
        });
        this.jLabel3.setText("Path to dictionary");
        this.jButton1.setText("...");
        this.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MD5_Brute.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton2.setText("Stop");
        this.jButton2.setToolTipText("");
        this.jButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MD5_Brute.this.jButton2ActionPerformed(evt);
            }
        });
        this.jButton3.setText("Start");
        this.jButton3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MD5_Brute.this.jButton3ActionPerformed(evt);
            }
        });
        this.jLabel4.setText("Log");
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jTextField1).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jTextField3, -2, 301, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 45, -2)).addComponent(this.jButton3, -2, 163, -2).addComponent(this.jLabel3)).addGap(65, 65, 65).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.textArea1, -2, 282, -2)))).addGap(0, 8, Short.MAX_VALUE))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(28, 28, 28).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField1, -2, -1, -2).addGap(40, 40, 40).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.jLabel4)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField3, -2, -1, -2).addComponent(this.jButton1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton3, -1, 92, Short.MAX_VALUE).addGap(16, 16, 16)).addGroup(layout.createSequentialGroup().addComponent(this.textArea1, -1, -1, Short.MAX_VALUE).addContainerGap()))));
        this.pack();
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
                    try {
                        String sCurrentLine;
                        fr = new FileReader(MD5_Brute.this.jTextField3.getText());
                        br = new BufferedReader(fr);
                        while ((sCurrentLine = br.readLine()) != null && !isStop) {
                            try {
                                if (MD5_Brute.hashPass(sCurrentLine).equals(MD5_Brute.this.jTextField1.getText())) {
                                    MD5_Brute.this.textArea1.append("Password found: " + sCurrentLine + "\n");
                                    break;
                                }
                                if (!MD5_Brute.hashPass(MD5_Brute.hashPassx2(sCurrentLine)).equals(MD5_Brute.this.jTextField1.getText())) continue;
                                MD5_Brute.this.textArea1.append("Password found: " + sCurrentLine + "\n");
                                break;
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        MD5_Brute.this.textArea1.append("The process is completed!\n");
                        isStop = false;
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
                        catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (br != null) {
                                br.close();
                            }
                            if (fr != null) {
                                fr.close();
                            }
                        }
                        catch (IOException ex2) {
                            ex2.printStackTrace();
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
                    catch (IOException ex2) {
                        ex2.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static String hashPass(String pass) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < bytes.length) {
                sb.append(String.format("%02X", bytes[i]));
                ++i;
            }
            return sb.toString().toLowerCase();
        }
        catch (NoSuchAlgorithmException e) {
            return pass;
        }
    }

    public static String hashPassx2(String pass) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < bytes.length) {
                sb.append(String.format("%02X", bytes[i]));
                ++i;
            }
            return sb.toString().toLowerCase();
        }
        catch (NoSuchAlgorithmException e) {
            return pass;
        }
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
            Logger.getLogger(MD5_Brute.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(MD5_Brute.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(MD5_Brute.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(MD5_Brute.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                new MD5_Brute().setVisible(true);
            }
        });
    }
}


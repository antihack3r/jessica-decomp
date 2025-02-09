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
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
import javax.xml.bind.DatatypeConverter;

public class YottaCraft_Hash_Brute
extends JFrame {
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    public static final int HASH_SECTIONS = 5;
    public static final int HASH_ALGORITHM_INDEX = 0;
    public static final int ITERATION_INDEX = 1;
    public static final int HASH_SIZE_INDEX = 2;
    public static final int SALT_INDEX = 3;
    public static final int PBKDF2_INDEX = 4;
    private static boolean isStop = false;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JTextField jTextField1;
    private JTextField jTextField3;
    private TextArea textArea1;

    public YottaCraft_Hash_Brute() {
        this.initComponents();
    }

    private void initComponents() {
        this.jTextField1 = new JTextField();
        this.jLabel1 = new JLabel();
        this.jTextField3 = new JTextField();
        this.jLabel3 = new JLabel();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.textArea1 = new TextArea();
        this.jLabel4 = new JLabel();
        this.setTitle("YottaCraft Hash Brute");
        this.jTextField1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                YottaCraft_Hash_Brute.this.jTextField1ActionPerformed(evt);
            }
        });
        this.jLabel1.setText("Hash");
        this.jTextField3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                YottaCraft_Hash_Brute.this.jTextField3ActionPerformed(evt);
            }
        });
        this.jLabel3.setText("Path to dictionary");
        this.jButton1.setText("...");
        this.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                YottaCraft_Hash_Brute.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton2.setText("Stop");
        this.jButton2.setToolTipText("");
        this.jButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                YottaCraft_Hash_Brute.this.jButton2ActionPerformed(evt);
            }
        });
        this.jButton3.setText("Start");
        this.jButton3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                YottaCraft_Hash_Brute.this.jButton3ActionPerformed(evt);
            }
        });
        this.jLabel4.setText("Log");
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jTextField1).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jTextField3, -2, 301, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 45, -2)).addGroup(layout.createSequentialGroup().addComponent(this.jButton3, -2, 163, -2).addGap(18, 18, 18).addComponent(this.jButton2, -2, 163, -2)).addComponent(this.jLabel3)).addGap(65, 65, 65).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.textArea1, -2, 282, -2)))).addGap(0, 8, Short.MAX_VALUE))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(28, 28, 28).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField1, -2, -1, -2).addGap(40, 40, 40).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.jLabel4)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField3, -2, -1, -2).addComponent(this.jButton1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jButton3, -1, 92, Short.MAX_VALUE).addComponent(this.jButton2, -1, -1, Short.MAX_VALUE)).addGap(16, 16, 16)).addGroup(layout.createSequentialGroup().addComponent(this.textArea1, -1, -1, Short.MAX_VALUE).addContainerGap()))));
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
                        fr = new FileReader(YottaCraft_Hash_Brute.this.jTextField3.getText());
                        br = new BufferedReader(fr);
                        while ((sCurrentLine = br.readLine()) != null && !isStop) {
                            if (!YottaCraft_Hash_Brute.verifyPassword(sCurrentLine.toCharArray(), YottaCraft_Hash_Brute.this.jTextField1.getText())) continue;
                            YottaCraft_Hash_Brute.this.textArea1.append("Password found: " + sCurrentLine + "\n");
                            break;
                        }
                        YottaCraft_Hash_Brute.this.textArea1.append("The process is completed!\n");
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

    public static boolean verifyPassword(char[] password, String correctHash) throws CannotPerformOperationException, InvalidHashException {
        String[] params = correctHash.split(":");
        if (params.length != 5) {
            throw new InvalidHashException("Fields are missing from the password hash.");
        }
        if (!params[0].equals("sha1")) {
            throw new CannotPerformOperationException("Unsupported hash type.");
        }
        int iterations = 0;
        try {
            iterations = Integer.parseInt(params[1]);
        }
        catch (NumberFormatException ex) {
            throw new InvalidHashException("Could not parse the iteration count as an integer.", ex);
        }
        if (iterations < 1) {
            throw new InvalidHashException("Invalid number of iterations. Must be >= 1.");
        }
        byte[] salt = null;
        try {
            salt = YottaCraft_Hash_Brute.fromBase64(params[3]);
        }
        catch (IllegalArgumentException ex2) {
            throw new InvalidHashException("Base64 decoding of salt failed.", ex2);
        }
        byte[] hash = null;
        try {
            hash = YottaCraft_Hash_Brute.fromBase64(params[4]);
        }
        catch (IllegalArgumentException ex3) {
            throw new InvalidHashException("Base64 decoding of pbkdf2 output failed.", ex3);
        }
        int storedHashSize = 0;
        try {
            storedHashSize = Integer.parseInt(params[2]);
        }
        catch (NumberFormatException ex4) {
            throw new InvalidHashException("Could not parse the hash size as an integer.", ex4);
        }
        if (storedHashSize != hash.length) {
            throw new InvalidHashException("Hash length doesn't match stored hash length.");
        }
        byte[] testHash = YottaCraft_Hash_Brute.pbkdf2(password, salt, iterations, hash.length);
        return YottaCraft_Hash_Brute.slowEquals(hash, testHash);
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        int i = 0;
        while (i < a.length && i < b.length) {
            diff |= a[i] ^ b[i];
            ++i;
        }
        return diff == 0;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) throws CannotPerformOperationException {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
            return skf.generateSecret(spec).getEncoded();
        }
        catch (NoSuchAlgorithmException ex) {
            throw new CannotPerformOperationException("Hash algorithm not supported.", ex);
        }
        catch (InvalidKeySpecException ex2) {
            throw new CannotPerformOperationException("Invalid key spec.", ex2);
        }
    }

    private static byte[] fromBase64(String hex) throws IllegalArgumentException {
        return DatatypeConverter.parseBase64Binary(hex);
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
            Logger.getLogger(YottaCraft_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(YottaCraft_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(YottaCraft_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(YottaCraft_Hash_Brute.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                new YottaCraft_Hash_Brute().setVisible(true);
            }
        });
    }

    public static class CannotPerformOperationException
    extends RuntimeException {
        public CannotPerformOperationException(String message) {
            super(message);
        }

        public CannotPerformOperationException(String message, Throwable source) {
            super(message, source);
        }
    }

    public static class InvalidHashException
    extends RuntimeException {
        public InvalidHashException(String message) {
            super(message);
        }

        public InvalidHashException(String message, Throwable source) {
            super(message, source);
        }
    }
}


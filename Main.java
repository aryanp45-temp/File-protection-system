import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

class Main extends JFrame implements ActionListener {
    // static JLabel choosedJLabel= new JLabel(new ImageIcon("check.png"));
    static chooseLabel ch1 = new chooseLabel();
    static chooseLabel ch2 = new chooseLabel();
    static KeyTextField k1 = new KeyTextField();
    static KeyTextField k2 = new KeyTextField();
    ProgressDialog jp = new ProgressDialog(this);

    Main() {
        setTitle("File Protection System");
        setSize(600, 600);
        setLocation(610, 190);
        setResizable(false);
        // Designing Encryption Panel
        JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP);
        JPanel enPanel = new JPanel();
        enPanel.setLayout(null);

        JButton enButton = new JButton("Encrypt File");
        enButton.setBounds(150, 250, 130, 35);
        enButton.addActionListener(this);

        JButton chooseFileButton = new JButton("Choose File");
        chooseFileButton.setBounds(200, 100, 130, 30);
        chooseFileButton.setBorder(new RoundedBorder(20));
        chooseFileButton.addActionListener(this);

        // choosedJLabel.setBounds(330, 100, 30, 30);
        // choosedJLabel.setVisible(false);

        enPanel.add(new FileLabel());
        enPanel.add(new KeyLabel());
        enPanel.add(ch1);
        enPanel.add(chooseFileButton);
        enPanel.add(k1);
        enPanel.add(enButton);

        // Designing Decryption Panel
        JPanel dePanel = new JPanel();
        dePanel.setLayout(null);

        JButton deButton = new JButton("Decrypt File");
        deButton.setBounds(150, 250, 130, 35);
        deButton.addActionListener(this);

        JButton chooseFileButtonD = new JButton("Choose File");
        chooseFileButtonD.setBounds(200, 100, 120, 30);
        chooseFileButtonD.addActionListener(this);

        dePanel.add(new FileLabel());
        dePanel.add(chooseFileButtonD);
        dePanel.add(ch2);
        dePanel.add(new KeyLabel());
        dePanel.add(k2);
        dePanel.add(deButton);

        // Designing Backup Panel
        JPanel bePanel = new JPanel();
        bePanel.setLayout(null);

        JButton chooseFileButtonB = new JButton("Choose File");
        chooseFileButtonB.setBounds(200, 100, 120, 30);
        chooseFileButtonB.addActionListener(this);

        JButton baButton = new JButton("Backup File");
        baButton.setBounds(150, 250, 130, 35);
        baButton.addActionListener(this);

        bePanel.add(new FileLabel());
        bePanel.add(chooseFileButtonB);
        bePanel.add(baButton);

        // Designing Restore Panel
        JPanel rePanel = new JPanel();
        rePanel.setLayout(null);

        JButton chooseFileButtonR = new JButton("Choose File to Restore");
        chooseFileButtonR.setBounds(200, 100, 200, 30);
        chooseFileButtonR.addActionListener(this);

        JLabel folder = new JLabel("Folder: ");
        folder.setBounds(100, 200, 100, 20);

        JButton chooseFolder = new JButton("Choose Folder");
        chooseFolder.setBounds(200, 200, 140, 30);
        chooseFolder.addActionListener(this);

        JButton reButton = new JButton("Restore File");
        reButton.setBounds(150, 250, 130, 35);
        reButton.addActionListener(this);

        rePanel.add(new FileLabel());
        rePanel.add(chooseFileButtonR);
        rePanel.add(folder);
        rePanel.add(chooseFolder);
        rePanel.add(reButton);

        pane.addTab("Encrypt", new ImageIcon("Lock.png"), enPanel);
        pane.addTab("Decrypt", new ImageIcon("Unlock.png"), dePanel);
        pane.addTab("Backup", new ImageIcon("Backup-Restore.png"), bePanel);
        pane.addTab("Restore", new ImageIcon("Backup-Restore.png"), rePanel);
        add(pane);
    }

    public static String filePath = "";
    public static String filePathRe = "";
    public static String folderPath = "";
    public static String filedir = "";

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "Choose File") {
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int r = j.showOpenDialog(null);
            System.out.println("inside choose........");
            if (r == JFileChooser.APPROVE_OPTION) {
                filePath = j.getSelectedFile().getAbsolutePath();
                System.out.println("got filepath......." + filePath);
                File f = new File(filePath);
                filedir = f.getParent();
                // System.out.println(filedir);
                Main.ch1.setVisible(true);
                Main.ch2.setVisible(true);

            } else {
                System.out.println("the user cancelled the operation");
            }
        } else if (e.getActionCommand() == "Encrypt File") {

            String type = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
            System.out.println(type);
            String s1 = Main.k1.getText();
            long st_time = new Date().getTime();

            if (type.equals("jpg") || type.equals("png") || type.equals("mp3") || type.equals("pdf")
                    || type.equals("mp4") || type.equals("webm") || type.equals("doc") || type.equals("xls")) {

                AESExample.EnImage(Integer.parseInt(s1), filePath, this);

            } else {

                String keyString = String.format("%-16s", s1).replace(' ', 'a');

                String filedata = "";
                try {
                    BufferedInputStream fin = new BufferedInputStream(new FileInputStream(filePath));
                    int total = fin.available();

                    jp.jprog.setMinimum(0);
                    jp.jprog.setMaximum(total);
                    jp.setTitle("Uploading...");
                    jp.jprog.setStringPainted(true);
                    jp.setVisible(true);

                    int i = 1;
                    int x = fin.read();
                    while (x != -1) {

                        filedata = filedata + (char) x;
                        x = fin.read();

                        // progressbar
                        jp.jprog.paintImmediately(0, 0, 1000, 100);
                        jp.jprog.setValue(i);
                        i++;
                    }
                    fin.close();
                    jp.setVisible(false);
                    // JOptionPane.showMessageDialog(this, "Done copying");
                    // System.out.println("Filedata : " + filedata);

                    String encrypString = AESExample.encrypt(filedata, keyString);
                    // System.out.println(encrypString);

                    String Temp = filePath;
                    filePath = filePath + ".fileEnc";
                    // System.out.println(filePath);
                    BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(filePath));
                    fout.write(encrypString.getBytes());
                    fout.close();
                    File deleteFile = new File(Temp);
                    deleteFile.delete();

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

            Main.ch1.setVisible(false);
            Main.k1.setText("");
            long end_time = new Date().getTime();
            System.out.println("File Encrypted successfully in " + (end_time - st_time) + " ms");
            JOptionPane.showMessageDialog(this,
                    "File Encrypted in " + (end_time - st_time) / 1000 + "sec\n Key Used: " + s1, "Message Box",
                    JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getActionCommand() == "Decrypt File") {
            // Logic to Decrypt File
            String s1 = Main.k2.getText();
            String keyString = String.format("%-16s", s1).replace(' ', 'a');
            String type = filePath.substring(filePath.length() - 12, filePath.length());
            System.out.println(type);
            String filedata = "";
            long st_time = new Date().getTime();

            if (type.equals(".jpg.fileEnc") || type.equals(".png.fileEnc") || type.equals(".mp3.fileEnc")
                    || type.equals(".pdf.fileEnc") || type.equals(".mp4.fileEnc") || type.equals("webm.fileEnc")
                    || type.equals(".doc.fileEnc") || type.equals(".xls.fileEnc")) {

                AESExample.DeImage(Integer.parseInt(s1), filePath, this);

            } else {

                try {

                    BufferedInputStream fin = new BufferedInputStream(new FileInputStream(filePath));
                    int total = fin.available();

                    jp.jprog.setMinimum(0);
                    jp.jprog.setMaximum(total);
                    jp.setTitle("Uploading...");
                    jp.jprog.setStringPainted(true);
                    jp.setVisible(true);

                    int i = 1;
                    int x = fin.read();
                    while (x != -1) {
                        filedata = filedata + (char) x;
                        x = fin.read();

                        // progressbar
                        jp.jprog.paintImmediately(0, 0, 1000, 100);
                        jp.jprog.setValue(i);
                        i++;
                    }
                    fin.close();
                    jp.setVisible(false);

                    String decryptString = AESExample.decrypt(filedata, keyString);
                    // System.out.println(decryptString);

                    String Temp = filePath;
                    filePath = filePath.substring(0, filePath.length() - 8);
                    // System.out.println(filePath);
                    BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(filePath));
                    fout.write(decryptString.getBytes());
                    fout.close();

                    File deleteFile = new File(Temp);
                    deleteFile.delete();

                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }

            Main.ch2.setVisible(false);
            Main.k2.setText("");
            long end_time = new Date().getTime();
            System.out.println("File Decrypted successfully in " + (end_time - st_time) + " ms");
            JOptionPane.showMessageDialog(null,
                    "File Decrypted in " + (end_time - st_time) / 1000 + "sec\n Key Used: " + s1, "Message Box",
                    JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getActionCommand() == "Backup File") {

            System.out.println("Inside backup file ");
            BackupRestore.BackupFile(filePath, this);

        } else if (e.getActionCommand() == "Choose File to Restore") {

            JFileChooser j = new JFileChooser("/home/aryan/Desktop/sem5/Microproject/AJP/Source Code/Backup");
            int r = j.showOpenDialog(null);
            System.out.println("inside choose........");
            if (r == JFileChooser.APPROVE_OPTION) {
                filePathRe = j.getSelectedFile().getAbsolutePath();
                System.out.println("got filepath......." + filePathRe);
            }
        } else if (e.getActionCommand() == "Choose Folder") {

            JFileChooser chooseFolderR = new JFileChooser();
            chooseFolderR.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = chooseFolderR.showOpenDialog(null);
            System.out.println("inside choose........");
            if (option == JFileChooser.APPROVE_OPTION) {
                folderPath = chooseFolderR.getSelectedFile().getAbsolutePath();
                System.out.println("got folderpath......." + folderPath);
            }
        }else if (e.getActionCommand() == "Restore File"){

            System.out.println("Inside Restore file ");

            BackupRestore.RestoreFile(filePathRe, folderPath, this);
        }
    }



    public static void main(String args[]) {
        Main m = new Main();
        m.setDefaultCloseOperation(3);

        m.setVisible(true);

    }

}

class FileLabel extends JLabel {
    FileLabel() {
        super("File: ");
        setBounds(100, 100, 100, 20);
    }
}

class KeyLabel extends JLabel {
    KeyLabel() {
        super("Key: ");
        setBounds(100, 200, 100, 20);
    }
}

class KeyTextField extends JTextField {
    KeyTextField() {
        super(20);
        setBounds(200, 200, 120, 30);
    }
}

class chooseLabel extends JLabel {
    chooseLabel() {
        super(new ImageIcon("check.png"));
        setBounds(330, 100, 30, 30);
        setVisible(false);

    }
}

class ProgressDialog extends JDialog {
    public JProgressBar jprog = new JProgressBar();

    public ProgressDialog(JFrame owner) {
        super(owner, "Progress", false);
        setSize(300, 70);
        setLocation(900, 550);
        add(jprog, BorderLayout.SOUTH);

    }
}

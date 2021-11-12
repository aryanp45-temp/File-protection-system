import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

class Main extends JFrame {
    // static JLabel choosedJLabel= new JLabel(new ImageIcon("check.png"));
    static chooseLabel ch1 = new chooseLabel();
    static chooseLabel ch2 = new chooseLabel();
    static KeyTextField k1 = new KeyTextField();
    static KeyTextField k2 = new KeyTextField();

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
        enButton.addActionListener(new EventsOnMain());

        // choosedJLabel.setBounds(330, 100, 30, 30);
        // choosedJLabel.setVisible(false);

        enPanel.add(new FileLabel());
        enPanel.add(new KeyLabel());
        enPanel.add(ch1);
        enPanel.add(new ChooseFileButton());
        enPanel.add(k1);
        enPanel.add(enButton);

        // Designing Decryption Panel
        JPanel dePanel = new JPanel();
        dePanel.setLayout(null);

        JButton deButton = new JButton("Decrypt File");
        deButton.setBounds(150, 250, 130, 35);
        deButton.addActionListener(new EventsOnMain());

        dePanel.add(new FileLabel());
        dePanel.add(new ChooseFileButton());
        dePanel.add(ch2);
        dePanel.add(new KeyLabel());
        dePanel.add(k2);
        dePanel.add(deButton);

        // Designing Backup Panel
        JPanel bePanel = new JPanel();
        bePanel.setLayout(null);

        JPanel rePanel = new JPanel();
        rePanel.setLayout(null);

        pane.addTab("Encrypt", new ImageIcon("Lock.png"), enPanel);
        pane.addTab("Decrypt", new ImageIcon("Unlock.png"), dePanel);
        pane.addTab("Backup", new ImageIcon("Backup-Restore.png"), bePanel);
        pane.addTab("Restore", new ImageIcon("Backup-Restore.png"), rePanel);
        add(pane);
    }

    public static void main(String args[]) {
        Main m = new Main();
        m.setDefaultCloseOperation(3);

        m.setVisible(true);

    }

}

class EventsOnMain implements ActionListener {
    public static String filePath = "";
    public static String filedir = "";
    long st_time = new Date().getTime();

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "Choose File") {
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int r = j.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                filePath = j.getSelectedFile().getAbsolutePath();
                File f = new File(filePath);
                filedir = f.getParent();
                // System.out.println(filedir);
                Main.ch1.setVisible(true);
                Main.ch2.setVisible(true);

            } else {
                System.out.println("the user cancelled the operation");
            }
        } else if (e.getActionCommand() == "Encrypt File") {

            String type = filePath.substring(filePath.length() - 3, filePath.length());
            System.out.println(type);
            String s1 = Main.k1.getText();

            if (type.equals("jpg") || type.equals("png") || type.equals("mp3") || type.equals("pdf") || type.equals("mp4")) {

                AESExample.EnImage(Integer.parseInt(s1), filePath);

            } else {

                String keyString = String.format("%-16s", s1).replace(' ', 'a');

                String filedata = "";
                try {
                    BufferedInputStream fin = new BufferedInputStream(new FileInputStream(filePath));
                    int x = fin.read();

                    while (x != -1) {

                        filedata = filedata + (char) x;
                        x = fin.read();
                    }
                    fin.close();
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
            JOptionPane.showMessageDialog(null, "File Encrypted\n Key Used: " + s1, "Message Box",
                    JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getActionCommand() == "Decrypt File") {
            // Logic to Decrypt File
            String s1 = Main.k2.getText();
            String keyString = String.format("%-16s", s1).replace(' ', 'a');
            String type = filePath.substring(filePath.length() - 11, filePath.length());
            System.out.println(type);
            String filedata = "";
            long st_time = new Date().getTime();

            if (type.equals("jpg.fileEnc" )|| type.equals("png.fileEnc") || type.equals("mp3.fileEnc") || type.equals("pdf.fileEnc") || type.equals("mp4.fileEnc")) {

                AESExample.DeImage(Integer.parseInt(s1), filePath);

            } else {

                try {

                    BufferedInputStream fin = new BufferedInputStream(new FileInputStream(filePath));

                    int x = fin.read();
                    while (x != -1) {
                        filedata = filedata + (char) x;
                        x = fin.read();
                    }
                    fin.close();

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
            JOptionPane.showMessageDialog(null, "File Decrypted\n Key Used: " + s1, "Message Box",
                    JOptionPane.INFORMATION_MESSAGE);

        }
    }
}

class ChooseFileButton extends JButton {
    ChooseFileButton() {
        super("Choose File");
        setBounds(200, 100, 120, 30);
        addActionListener(new EventsOnMain());

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
import java.awt.event.*;
import java.awt.*;
import java.util.Date;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
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
    static chooseLabel ch3 = new chooseLabel();
    static KeyTextField k1 = new KeyTextField();
    static KeyTextField k2 = new KeyTextField();
    ProgressDialog jp = new ProgressDialog(this);
    JLabel warningLabelE = new JLabel("Please Select file to Encrypt !!!");
    JLabel warningLabelD = new JLabel("Please Select file to Decrypt !!!");
    JLabel warningLabelD1 = new JLabel(
            "<html>Selected File is not Encrypted !!!<br> Please Select encrypted File.</html>");
    JLabel warningLabelB = new JLabel("Please Select File to Backup !!");
    JLabel warningLabelR1 = new JLabel("Please Select File to Restore !!");
    JLabel warningLabelR2 = new JLabel("Please Select Folder to save Restored File !!");

    Main() {
        setTitle("File Protection System");
        setSize(600, 600);
        setLocation(610, 190);
        setResizable(false);
        // Designing Encryption Panel
        JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP);
        OpPanel enPanel = new OpPanel();
        // enPanel.setLayout(null);

        JButton enButton = new JButton("Encrypt File");
        enButton.setBounds(150, 210, 130, 35);
        enButton.addActionListener(this);

        JLabel clickeden = new JLabel("Click Encrypt");
        clickeden.setBounds(420, 70, 100, 20);

        JLabel clickedimg = new JLabel(new ImageIcon("encrypt32.png"));
        clickedimg.setBounds(445, 25, 32, 32);

        warningLabelE.setBounds(250, 135, 250, 20);
        warningLabelE.setForeground(Color.RED);
        warningLabelE.setVisible(false);

        JButton chooseFileButton = new JButton("Choose File");
        chooseFileButton.setBounds(250, 100, 130, 30);
        chooseFileButton.setToolTipText("Select File to Encrypt");
        // chooseFileButton.setBorder(new RoundedBorder(20));
        chooseFileButton.addActionListener(this);

        // instruction for Encryption
        JEditorPane insArea = new JEditorPane();
        insArea.setContentType("text/html");
        insArea.setBounds(70, 270, 460, 220);
        insArea.setBackground(Color.gray);
        insArea.setForeground(Color.WHITE);
        insArea.setText(
                "<span style=\"color:white\"><center><h2>Instructions</h2></center><br><ul><li><b>Choose File of any Standard Extension.</b></li><li><b>Key</b><ol><li>It is for Advanced Security</li><li>Maximum Length is 16 Bytes</li><li>If forgot can't Decrypt File</li></ol></li><li><b>Original  File will be replaced with encrypted file.</b></li></ul></span>");
        insArea.setEditable(false);

        enPanel.add(new FileLabel());
        enPanel.add(new KeyLabel());
        enPanel.add(warningLabelE);
        enPanel.add(ch1);
        enPanel.add(chooseFileButton);
        enPanel.add(k1);
        enPanel.add(enButton);
        enPanel.add(insArea);
        enPanel.add(clickeden);
        enPanel.add(clickedimg);

        // Designing Decryption Panel
        OpPanel dePanel = new OpPanel();

        JLabel clickedde = new JLabel("Click Decrypt");
        clickedde.setBounds(420, 70, 100, 20);

        JLabel clickedimgD = new JLabel(new ImageIcon("decrypt32.png"));
        clickedimgD.setBounds(445, 25, 32, 32);

        JButton deButton = new JButton("Decrypt File");
        deButton.setBounds(150, 210, 130, 35);
        deButton.addActionListener(this);

        warningLabelD.setBounds(250, 135, 250, 20);
        warningLabelD.setForeground(Color.RED);
        warningLabelD.setVisible(false);

        warningLabelD1.setBounds(250, 122, 250, 50);
        warningLabelD1.setForeground(Color.RED);
        warningLabelD1.setVisible(false);

        JButton chooseFileButtonD = new JButton("Choose File");
        chooseFileButtonD.setBounds(250, 100, 130, 30);
        chooseFileButtonD.addActionListener(this);

        JEditorPane insArea1 = new JEditorPane();
        insArea1.setContentType("text/html");
        insArea1.setBounds(70, 270, 460, 220);
        insArea1.setBackground(Color.gray);
        insArea1.setForeground(Color.WHITE);
        insArea1.setText(
                "<center><h2>Instructions</h2></center><br><ul><li><b>Choose File you want to Decrypt.</b></li><li><b>File should have \".fileEnc\" Extension.</b></li><li><b>Key :</b><ol><li>Do not enter Key if not used while Encrypting</li><li>Do not enter wrong key(Data might be lost permanently)</li></ol></li><li><b>Encrypted File will be replaced with Decrypted file.</b></li></ul>");
        insArea1.setEditable(false);

        dePanel.add(new FileLabel());
        dePanel.add(chooseFileButtonD);
        dePanel.add(ch2);
        dePanel.add(new KeyLabel());
        dePanel.add(warningLabelD);
        dePanel.add(warningLabelD1);
        dePanel.add(k2);
        dePanel.add(deButton);
        dePanel.add(insArea1);
        dePanel.add(clickedde);
        dePanel.add(clickedimgD);

        // Designing Backup Panel
        BaPanel bePanel = new BaPanel();

        JButton chooseFileButtonB = new JButton("Choose File");
        chooseFileButtonB.setBounds(250, 100, 130, 30);
        chooseFileButtonB.addActionListener(this);

        warningLabelB.setBounds(250, 135, 250, 20);
        warningLabelB.setForeground(Color.RED);
        warningLabelB.setVisible(false);

        JButton baButton = new JButton("Backup File");
        baButton.setBounds(200, 160, 130, 35);
        baButton.addActionListener(this);

        JEditorPane insArea2 = new JEditorPane();
        insArea2.setContentType("text/html");
        insArea2.setBounds(70, 270, 460, 220);
        insArea2.setBackground(Color.gray);
        insArea2.setForeground(Color.WHITE);
        insArea2.setText(
                "<center><h2>Instructions</h2></center><br><ul><li><b>Choose File you want to Backup.</b></li><li><b>File can have any Extension.</b></li><li><b>You can Delete Original File after the Back Up.</b></li><li><b>You can Retrive BackUp file any time.</b></li></li><li><b>BackUp File will be Stored locally in PC.</b></li></ul>");
        insArea2.setEditable(false);

        bePanel.add(new FileLabel());
        bePanel.add(chooseFileButtonB);
        bePanel.add(baButton);
        bePanel.add(warningLabelB);
        bePanel.add(ch3);
        bePanel.add(insArea2);

        // Designing Restore Panel
        RePanel rePanel = new RePanel();

        JButton chooseFileButtonR = new JButton("Choose File to Restore");
        chooseFileButtonR.setBounds(250, 100, 200, 30);
        chooseFileButtonR.addActionListener(this);

        warningLabelR1.setBounds(250, 135, 250, 20);
        warningLabelR1.setForeground(Color.RED);
        warningLabelR1.setVisible(false);

        JLabel folder = new JLabel("Folder: ");
        folder.setBounds(150, 160, 120, 30);

        JButton chooseFolder = new JButton("Choose Folder");
        chooseFolder.setBounds(250, 160, 140, 30);
        chooseFolder.addActionListener(this);

        warningLabelR2.setBounds(250, 195, 350, 20);
        warningLabelR2.setForeground(Color.RED);
        warningLabelR2.setVisible(false);

        JButton reButton = new JButton("Restore File");
        reButton.setBounds(200, 220, 130, 35);
        reButton.addActionListener(this);

        JEditorPane insArea3 = new JEditorPane();
        insArea3.setContentType("text/html");
        insArea3.setBounds(70, 270, 460, 220);
        insArea3.setBackground(Color.gray);
        insArea3.setForeground(Color.WHITE);
        insArea3.setText(
                "<center><h2>Instructions</h2></center><br><ul><li><b>Choose File you want to Restore from BackUp Folder.</b></li><li><b>You can only Restore Files which have been BackUp Before.<<li><b>You can Restore Files at any time.</b></li></ul>");
        insArea3.setEditable(false);

        rePanel.add(new FileLabel());
        rePanel.add(chooseFileButtonR);
        rePanel.add(folder);
        rePanel.add(chooseFolder);
        rePanel.add(reButton);
        rePanel.add(warningLabelR1);
        rePanel.add(warningLabelR2);
        rePanel.add(insArea3);

        pane.addTab("Encrypt", new ImageIcon("Lock.png"), enPanel);
        pane.addTab("Decrypt", new ImageIcon("Unlock.png"), dePanel);
        pane.addTab("Backup", new ImageIcon("Backup-Restore.png"), bePanel);
        pane.addTab("Restore", new ImageIcon("Backup-Restore.png"), rePanel);
        add(pane);
    }

    public static String filePath = null;
    public static String filePathRe = null;
    public static String folderPath = null;
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
                warningLabelE.setVisible(false);
                Main.ch2.setVisible(true);
                warningLabelD.setVisible(false);
                Main.ch3.setVisible(true);
                warningLabelB.setVisible(false);

            } else {
                System.out.println("the user cancelled the operation");
            }
        } else if (e.getActionCommand() == "Encrypt File") {

            if (filePath == null) {

                warningLabelE.setVisible(true);

            } else {

                String type = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
                System.out.println(type);
                String s1 = Main.k1.getText();
                long st_time = new Date().getTime();

                if (type.equals("jpg") || type.equals("png") || type.equals("mp3") || type.equals("pdf")
                        || type.equals("mp4") || type.equals("webm") || type.equals("doc")  || type.equals("xls")
                        || type.equals("docx") || type.equals("ppt") || type.equals("pptx") || type.equals("xlsx")
                        || type.equals("pub") || type.equals("one") || type.equals("jpeg")  || type.equals("gif")
                        || type.equals("bmp") || type.equals("tif") || type.equals("raw")   || type.equals("wav")
                        || type.equals("m3u") || type.equals("asx") || type.equals("avi")   || type.equals("mkv") || type.equals("mpeg") || type.equals("mpg")  || type.equals("exe") || type.equals("zip") || type.equals("odt")   || type.equals("ott") || type.equals("oth") || type.equals("odm")) {

                    if (s1.length() < 1) {
                        AESExample.EnImage(0, filePath, this);
                    } else {
                        AESExample.EnImage(Integer.parseInt(s1), filePath, this);
                    }

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
            }

        } else if (e.getActionCommand() == "Decrypt File") {
            // Logic to Decrypt File

            if (filePath == null) {

                warningLabelD.setVisible(true);

            } else if (!filePath.substring(filePath.length() - 7, filePath.length()).equals("fileEnc")) {
                System.out.println(filePath.substring(filePath.length() - 7, filePath.length()));
                warningLabelD1.setVisible(true);

            } else {

                String s1 = Main.k2.getText();
                String keyString = String.format("%-16s", s1).replace(' ', 'a');
                String type = filePath.substring(filePath.length() - 12, filePath.length());
                System.out.println(type);
                String filedata = "";
                long st_time = new Date().getTime();

                if (type.equals(".jpg.fileEnc") || type.equals(".png.fileEnc") || type.equals(".mp3.fileEnc")
                        || type.equals(".pdf.fileEnc") || type.equals(".mp4.fileEnc") || type.equals("webm.fileEnc")
                        || type.equals(".doc.fileEnc") || type.equals(".xls.fileEnc") || type.equals("docx.fileEnc")
                        || type.equals(".ppt.fileEnc") || type.equals("pptx.fileEnc") || type.equals("xlsx.fileEnc")
                        || type.equals(".pub.fileEnc") || type.equals(".one.fileEnc") || type.equals("jpeg.fileEnc")
                        || type.equals(".gif.fileEnc") || type.equals(".bmp.fileEnc") || type.equals(".tif.fileEnc")
                        || type.equals(".raw.fileEnc") || type.equals(".wav.fileEnc") || type.equals(".m3u.fileEnc") || type.equals(".asx.fileEnc") || type.equals(".avi.fileEnc") || type.equals(".mkv.fileEnc") || type.equals("mpeg.fileEnc") || type.equals(".mpg.fileEnc") || type.equals(".exe.fileEnc") || type.equals(".zip.fileEnc") || type.equals(".odt.fileEnc") || type.equals(".ott.fileEnc") || type.equals(".oth.fileEnc") || type.equals(".odm.fileEnc")) {

                    if (s1.length() < 1) {
                        AESExample.DeImage(0, filePath, this);
                    } else {
                        AESExample.DeImage(Integer.parseInt(s1), filePath, this);
                    }

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
            }

        } else if (e.getActionCommand() == "Backup File") {

            if (filePath == null) {

                warningLabelB.setVisible(true);

            } else {

                System.out.println("Inside backup file ");
                BackupRestore.BackupFile(filePath, this);
            }

        } else if (e.getActionCommand() == "Choose File to Restore") {

            JFileChooser j = new JFileChooser("/home/aryan/Desktop/sem5/Microproject/AJP/Source Code/Backup");
            int r = j.showOpenDialog(null);
            System.out.println("inside choose........");
            if (r == JFileChooser.APPROVE_OPTION) {
                filePathRe = j.getSelectedFile().getAbsolutePath();
                System.out.println("got filepath......." + filePathRe);
                warningLabelR1.setVisible(false);
            }
        } else if (e.getActionCommand() == "Choose Folder") {

            JFileChooser chooseFolderR = new JFileChooser();
            chooseFolderR.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = chooseFolderR.showOpenDialog(null);
            System.out.println("inside choose........");
            if (option == JFileChooser.APPROVE_OPTION) {
                folderPath = chooseFolderR.getSelectedFile().getAbsolutePath();
                System.out.println("got folderpath......." + folderPath);
                warningLabelR2.setVisible(false);
            }
        } else if (e.getActionCommand() == "Restore File") {

            if (filePathRe == null || folderPath == null) {

                if (filePathRe == null && folderPath == null) {

                    warningLabelR1.setVisible(true);
                    warningLabelR2.setVisible(true);

                } else if (filePathRe == null) {

                    warningLabelR1.setVisible(true);

                } else if (folderPath == null) {

                    warningLabelR2.setVisible(true);

                }

            } else {

                System.out.println("Inside Restore file ");
                BackupRestore.RestoreFile(filePathRe, folderPath, this);
            }
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
        setBounds(150, 100, 100, 20);
    }
}

class KeyLabel extends JLabel {
    KeyLabel() {
        super("Key: ");
        setBounds(150, 160, 130, 20);
    }
}

class KeyTextField extends JTextField {
    KeyTextField() {
        super(20);
        setBounds(250, 160, 120, 30);
        setToolTipText("Key should be less than 16 characters!!");
    }
}

class chooseLabel extends JLabel {
    chooseLabel() {
        super(new ImageIcon("check.png"));
        setBounds(400, 100, 30, 30);
        setVisible(false);

    }
}

class ProgressDialog extends JDialog {
    public JProgressBar jprog = new JProgressBar();

    public ProgressDialog(JFrame owner) {
        super(owner, "Progress", false);
        setSize(300, 70);
        setLocation(720, 550);
        add(jprog, BorderLayout.SOUTH);

    }
}

class OpPanel extends JPanel {
    OpPanel() {
        setLayout(null);
        JLabel choosed = new JLabel("Choose File");
        choosed.setBounds(90, 70, 100, 20);

        JLabel Entered = new JLabel("Enter Key");
        Entered.setBounds(260, 70, 100, 20);

        JLabel file = new JLabel(new ImageIcon("Files32.png"));
        file.setBounds(115, 25, 32, 32);

        JLabel key = new JLabel(new ImageIcon("Key32.png"));
        key.setBounds(285, 25, 32, 32);

        add(choosed);
        add(Entered);
        add(file);
        add(key);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        g.fillOval(100, 10, 60, 60);
        g.fillOval(270, 10, 60, 60);
        g.fillOval(430, 10, 60, 60);

        g.setColor(Color.RED);
        g.drawOval(100, 10, 60, 60);
        g.drawOval(270, 10, 60, 60);
        g.drawOval(430, 10, 60, 60);

        // g.setColor(Color.BLACK);
        // g.fillOval(155, 35, 10, 10);
        // g.fillOval(265, 35, 10, 10);
        // g.fillOval(325, 35, 10, 10);
        // g.fillOval(425, 35, 10, 10);

        g.drawLine(160, 40, 270, 40);
        g.drawLine(330, 40, 430, 40);

    }
}

class BaPanel extends JPanel {
    BaPanel() {
        setLayout(null);
        JLabel choosed = new JLabel("Choose File");
        choosed.setBounds(155, 70, 100, 20);

        JLabel BackUp = new JLabel("Click Backup");
        BackUp.setBounds(360, 70, 100, 20);

        JLabel file = new JLabel(new ImageIcon("Files32.png"));
        file.setBounds(175, 25, 32, 32);

        JLabel backup = new JLabel(new ImageIcon("backup32.png"));
        backup.setBounds(385, 25, 32, 32);

        add(choosed);
        add(BackUp);
        add(file);
        add(backup);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        g.fillOval(160, 10, 60, 60);
        g.fillOval(370, 10, 60, 60);

        g.setColor(Color.RED);
        g.drawOval(160, 10, 60, 60);
        g.drawOval(370, 10, 60, 60);

        g.drawLine(225, 40, 370, 40);

    }
}

class RePanel extends JPanel {
    RePanel() {
        setLayout(null);
        JLabel choosed = new JLabel("Choose File");
        choosed.setBounds(90, 70, 100, 20);

        JLabel Entered = new JLabel("Choose Folder");
        Entered.setBounds(250, 70, 120, 20);

        JLabel Restored = new JLabel("Click Restore");
        Restored.setBounds(410, 70, 100, 20);

        JLabel file = new JLabel(new ImageIcon("Files32.png"));
        file.setBounds(115, 25, 32, 32);

        JLabel key = new JLabel(new ImageIcon("Folder32.png"));
        key.setBounds(285, 25, 32, 32);

        JLabel restore = new JLabel(new ImageIcon("Restore32.png"));
        restore.setBounds(445, 25, 32, 32);

        add(choosed);
        add(Entered);
        add(Restored);
        add(file);
        add(key);
        add(restore);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        g.fillOval(100, 10, 60, 60);
        g.fillOval(270, 10, 60, 60);
        g.fillOval(430, 10, 60, 60);

        g.setColor(Color.RED);
        g.drawOval(100, 10, 60, 60);
        g.drawOval(270, 10, 60, 60);
        g.drawOval(430, 10, 60, 60);

        g.drawLine(160, 40, 270, 40);
        g.drawLine(330, 40, 430, 40);

    }
}
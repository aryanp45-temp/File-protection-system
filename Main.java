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
    chooseLabel ch1 = new chooseLabel();
    chooseLabel ch2 = new chooseLabel();
    chooseLabel ch3 = new chooseLabel();
    static chooseLabel ch4 = new chooseLabel();
    static chooseLabel ch5 = new chooseLabel();

    static KeyTextField k1 = new KeyTextField();
    static KeyTextField k2 = new KeyTextField();

    ProgressDialog jp = new ProgressDialog(this);
    JLabel warningLabelE = new JLabel("Please Select file to Encrypt !!!");

    JLabel warningLabelD = new JLabel("Please Select file to Decrypt !!!");
    JLabel warningLabelD1 = new JLabel(
            "<html>Selected File is not Encrypted !!!<br> Please Select encrypted File.</html>");
    JLabel warningLabelB = new JLabel("Please Select File to Backup !!");
    JLabel warningLabelB2 = new JLabel("Selected File is already in BackUp !!");
    JLabel warningLabelR1 = new JLabel("Please Select File to Restore !!");
    JLabel warningLabelR2 = new JLabel("Please Select Folder to save Restored File !!");
    static KeyWarning keyWarningLabel1 = new KeyWarning();
    static KeyWarning keyWarningLabel2 = new KeyWarning();

    Main() {
        setTitle("Secure Vault");
        setSize(600, 600);
        setLocation(610, 190);
        setResizable(false);

        JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP);
        pane.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        // Designing Encryption Panel************************************************
        OpPanel enPanel = new OpPanel();

        JButton enButton = new JButton("Encrypt File");
        enButton.setBounds(150, 210, 130, 35);
        enButton.setBorder(new RoundedBorder(20));
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
        chooseFileButton.setBorder(new RoundedBorder(20));
        chooseFileButton.addActionListener(this);

        ch1.setBounds(400, 100, 30, 30);

        // instruction for Encryption
        JEditorPane insArea = new JEditorPane();
        insArea.setContentType("text/html");
        insArea.setBounds(70, 270, 460, 220);
        insArea.setBackground(Color.gray);
        insArea.setForeground(Color.WHITE);
        insArea.setText(
                "<span style=\"color:white\"><center><h2>Instructions</h2></center><br><ul><li><b>Choose File of any Standard Extension.</b></li><li><b>Key</b><ol><li>It is for Advanced Security</li><li>Maximum Length is 16 Bytes</li><li>Please Note down the Key, If forgot can't Decrypt File</li></ol></li><li><b>Original  File will be replaced with encrypted file.</b></li></ul></span>");
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
        enPanel.add(keyWarningLabel1);

        // Designing Decryption Panel*******************************************
        OpPanel dePanel = new OpPanel();

        JLabel clickedde = new JLabel("Click Decrypt");
        clickedde.setBounds(420, 70, 100, 20);

        JLabel clickedimgD = new JLabel(new ImageIcon("decrypt32.png"));
        clickedimgD.setBounds(445, 25, 32, 32);

        JButton deButton = new JButton("Decrypt File");
        deButton.setBounds(150, 210, 130, 35);
        deButton.setBorder(new RoundedBorder(20));
        deButton.addActionListener(this);

        warningLabelD.setBounds(250, 135, 250, 20);
        warningLabelD.setForeground(Color.RED);
        warningLabelD.setVisible(false);

        warningLabelD1.setBounds(250, 122, 250, 50);
        warningLabelD1.setForeground(Color.RED);
        warningLabelD1.setVisible(false);

        JButton chooseFileButtonD = new JButton("Choose File");
        chooseFileButtonD.setBounds(250, 100, 130, 30);
        chooseFileButtonD.setBorder(new RoundedBorder(20));
        chooseFileButtonD.addActionListener(this);

        ch2.setBounds(400, 100, 30, 30);

        JEditorPane insArea1 = new JEditorPane();
        insArea1.setContentType("text/html");
        insArea1.setBounds(70, 270, 460, 230);
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
        dePanel.add(keyWarningLabel2);

        // Designing Backup Panel****************************************************
        BaPanel bePanel = new BaPanel();

        JButton chooseFileButtonB = new JButton("Choose File");
        chooseFileButtonB.setBounds(250, 100, 130, 30);
        chooseFileButtonB.setBorder(new RoundedBorder(20));
        chooseFileButtonB.addActionListener(this);

        ch3.setBounds(400, 100, 30, 30);

        warningLabelB.setBounds(250, 135, 250, 20);
        warningLabelB.setForeground(Color.RED);
        warningLabelB.setVisible(false);

        warningLabelB2.setBounds(250, 135, 300, 20);
        warningLabelB2.setForeground(Color.RED);
        warningLabelB2.setVisible(false);

        JButton baButton = new JButton("Backup File");
        baButton.setBounds(200, 160, 130, 35);
        baButton.setBorder(new RoundedBorder(20));
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
        bePanel.add(warningLabelB2);
        bePanel.add(ch3);
        bePanel.add(insArea2);

        // Designing Restore Panel************************************************
        RePanel rePanel = new RePanel();

        JButton chooseFileButtonR = new JButton("Choose File to Restore");
        chooseFileButtonR.setBounds(250, 100, 220, 30);
        chooseFileButtonR.setBorder(new RoundedBorder(20));
        chooseFileButtonR.addActionListener(this);

        ch4.setBounds(470, 100, 30, 30);

        warningLabelR1.setBounds(250, 135, 250, 20);
        warningLabelR1.setForeground(Color.RED);
        warningLabelR1.setVisible(false);

        JLabel folder = new JLabel("Folder: ");
        folder.setBounds(150, 160, 120, 30);
        folder.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

        JButton chooseFolder = new JButton("Choose Folder");
        chooseFolder.setBounds(250, 160, 160, 30);
        chooseFolder.setBorder(new RoundedBorder(20));
        chooseFolder.addActionListener(this);

        ch5.setBounds(410, 160, 30, 30);

        warningLabelR2.setBounds(250, 195, 350, 20);
        warningLabelR2.setForeground(Color.RED);
        warningLabelR2.setVisible(false);

        JButton reButton = new JButton("Restore File");
        reButton.setBounds(200, 220, 130, 35);
        reButton.setBorder(new RoundedBorder(20));
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
        rePanel.add(ch4);
        rePanel.add(ch5);

        // Designing About Panel******************************************************
        AboutPanel abPanel = new AboutPanel();

        pane.addTab("Encrypt", new ImageIcon("Lock.png"), enPanel);
        pane.addTab("Decrypt", new ImageIcon("Unlock.png"), dePanel);
        pane.addTab("Backup", new ImageIcon("Backup-Restore.png"), bePanel);
        pane.addTab("Restore", new ImageIcon("Backup-Restore.png"), rePanel);
        pane.addTab("About", new ImageIcon("about.png"), abPanel);
        add(pane);
    }

    public static String filePath = null;
    public static String filePathRe = null;
    public static String folderPath = null;
    public static String filedir = "";
    public static boolean alreadyExists = false;

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "Choose File") {
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int r = j.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                filePath = j.getSelectedFile().getAbsolutePath();
                File f = new File(filePath);
                filedir = f.getParent();
                ch1.setVisible(true);
                warningLabelE.setVisible(false);
                ch2.setVisible(true);
                warningLabelD.setVisible(false);
                ch3.setVisible(true);
                warningLabelB.setVisible(false);

                // Logic to check if File Exists in Backup
                File file = new File("/home/aryan/Desktop/sem5/Microproject/AJP/Source Code/Backup");
                String forBackupFileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
                String[] filenames;
                filenames = file.list();
                for (String string : filenames) {
                    if (forBackupFileName.equals(string)) {
                        alreadyExists = true;
                        warningLabelB2.setVisible(true);
                        break;
                    } else {
                        alreadyExists = false;
                        warningLabelB2.setVisible(false);
                    }
                }

            } else {
                System.out.println("the user cancelled the operation");
            }
        } else if (e.getActionCommand() == "Encrypt File") {

            if (filePath == null) {

                warningLabelE.setVisible(true);

            } else {

                String type = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
                String s1 = Main.k1.getText();
                long st_time = new Date().getTime();

                if (type.equals("jpg") || type.equals("png") || type.equals("mp3") || type.equals("pdf")
                        || type.equals("mp4") || type.equals("webm") || type.equals("doc") || type.equals("xls")
                        || type.equals("docx") || type.equals("ppt") || type.equals("pptx") || type.equals("xlsx")
                        || type.equals("pub") || type.equals("one") || type.equals("jpeg") || type.equals("gif")
                        || type.equals("bmp") || type.equals("tif") || type.equals("raw") || type.equals("wav")
                        || type.equals("m3u") || type.equals("asx") || type.equals("avi") || type.equals("mkv")
                        || type.equals("mpeg") || type.equals("mpg") || type.equals("exe") || type.equals("zip")
                        || type.equals("odt") || type.equals("ott") || type.equals("oth") || type.equals("odm")) {

                    if (s1.length() < 1) {
                        AESExample.EnImage(0, filePath, this);
                        filePath = null;
                    } else {
                        AESExample.EnImage(Integer.parseInt(s1), filePath, this);
                        filePath = null;
                    }

                } else {

                    String keyString = String.format("%-16s", s1).replace(' ', 'a');

                    String filedata = "";
                    try {
                        BufferedInputStream fin = new BufferedInputStream(new FileInputStream(filePath));
                        int total = fin.available();

                        jp.jprog.setMinimum(0);
                        jp.jprog.setMaximum(total);
                        jp.setTitle("Encrypting...");
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

                        String encrypString = AESExample.encrypt(filedata, keyString);

                        String Temp = filePath;
                        filePath = filePath + ".fileEnc";
                        BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(filePath));
                        fout.write(encrypString.getBytes());
                        fout.close();
                        File deleteFile = new File(Temp);
                        deleteFile.delete();
                        filePath = null;

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }

                jp.setVisible(false);
                ch1.setVisible(false);
                ch2.setVisible(false);
                ch3.setVisible(false);
                Main.k1.setText("");
                long end_time = new Date().getTime();
                JOptionPane.showMessageDialog(this,
                        "File Encrypted in " + (end_time - st_time) / 1000 + "sec\n Key Used: " + s1, "Message Box",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (e.getActionCommand() == "Decrypt File") {
            // Logic to Decrypt File

            if (filePath == null) {

                warningLabelD.setVisible(true);

            } else if (!filePath.substring(filePath.length() - 7, filePath.length()).equals("fileEnc")) {
                warningLabelD1.setVisible(true);

            } else {

                String s1 = Main.k2.getText();
                String keyString = String.format("%-16s", s1).replace(' ', 'a');
                String type = filePath.substring(filePath.length() - 12, filePath.length());
                String filedata = "";
                long st_time = new Date().getTime();

                if (type.equals(".jpg.fileEnc") || type.equals(".png.fileEnc") || type.equals(".mp3.fileEnc")
                        || type.equals(".pdf.fileEnc") || type.equals(".mp4.fileEnc") || type.equals("webm.fileEnc")
                        || type.equals(".doc.fileEnc") || type.equals(".xls.fileEnc") || type.equals("docx.fileEnc")
                        || type.equals(".ppt.fileEnc") || type.equals("pptx.fileEnc") || type.equals("xlsx.fileEnc")
                        || type.equals(".pub.fileEnc") || type.equals(".one.fileEnc") || type.equals("jpeg.fileEnc")
                        || type.equals(".gif.fileEnc") || type.equals(".bmp.fileEnc") || type.equals(".tif.fileEnc")
                        || type.equals(".raw.fileEnc") || type.equals(".wav.fileEnc") || type.equals(".m3u.fileEnc")
                        || type.equals(".asx.fileEnc") || type.equals(".avi.fileEnc") || type.equals(".mkv.fileEnc")
                        || type.equals("mpeg.fileEnc") || type.equals(".mpg.fileEnc") || type.equals(".exe.fileEnc")
                        || type.equals(".zip.fileEnc") || type.equals(".odt.fileEnc") || type.equals(".ott.fileEnc")
                        || type.equals(".oth.fileEnc") || type.equals(".odm.fileEnc")) {

                    if (s1.length() < 1) {
                        AESExample.DeImage(0, filePath, this);
                        filePath = null;
                    } else {
                        AESExample.DeImage(Integer.parseInt(s1), filePath, this);
                        filePath = null;
                    }

                } else {

                    try {

                        BufferedInputStream fin = new BufferedInputStream(new FileInputStream(filePath));
                        int total = fin.available();

                        jp.jprog.setMinimum(0);
                        jp.jprog.setMaximum(total);
                        jp.setTitle("Decrypting...");
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

                        String Temp = filePath;
                        filePath = filePath.substring(0, filePath.length() - 8);
                        BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(filePath));
                        fout.write(decryptString.getBytes());
                        fout.close();

                        File deleteFile = new File(Temp);
                        deleteFile.delete();
                        filePath = null;

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }

                jp.setVisible(false);
                ch1.setVisible(false);
                ch2.setVisible(false);
                ch3.setVisible(false);
                Main.k2.setText("");
                long end_time = new Date().getTime();
                JOptionPane.showMessageDialog(null,
                        "File Decrypted in " + (end_time - st_time) / 1000 + "sec\n Key Used: " + s1, "Message Box",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (e.getActionCommand() == "Backup File") {

            if (filePath == null) {

                warningLabelB.setVisible(true);

            } else if (alreadyExists) {
                warningLabelB.setVisible(false);
                warningLabelB2.setVisible(true);
            } else {

                BackupRestore.BackupFile(filePath, this);
                filePath = null;
                jp.setVisible(false);
                ch1.setVisible(false);
                ch2.setVisible(false);
                ch3.setVisible(false);

            }

        } else if (e.getActionCommand() == "Choose File to Restore") {
            // Enter Backup Folder path in DestinationFilePath
            JFileChooser j = new JFileChooser("/home/aryan/Desktop/sem5/Microproject/AJP/Source Code/Backup");
            int r = j.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                filePathRe = j.getSelectedFile().getAbsolutePath();
                ch4.setVisible(true);
                warningLabelR1.setVisible(false);
            }
        } else if (e.getActionCommand() == "Choose Folder") {

            JFileChooser chooseFolderR = new JFileChooser();
            chooseFolderR.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = chooseFolderR.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                folderPath = chooseFolderR.getSelectedFile().getAbsolutePath();
                ch5.setVisible(true);
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

                BackupRestore.RestoreFile(filePathRe, folderPath, this);
                filePathRe = null;
                folderPath = null;
                jp.setVisible(false);
                ch4.setVisible(false);
                ch5.setVisible(false);
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
        setBounds(150, 105, 100, 20);
        setFont(new Font("Comic Sans MS", Font.BOLD, 18));
    }
}

class KeyLabel extends JLabel {
    KeyLabel() {
        super("Key: ");
        setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        setBounds(150, 160, 130, 20);
    }
}

class KeyWarning extends JLabel {
    KeyWarning() {
        super("You Can Only Enter Numbers !!");
        setBounds(250, 192, 300, 20);
        setForeground(Color.RED);
        setVisible(false);
    }
}

class KeyTextField extends JTextField implements KeyListener {
    KeyTextField() {
        super(20);
        setBounds(250, 160, 120, 30);
        addKeyListener(this);
        setToolTipText("Key should be less than 16 characters!!");
    }

    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if (Character.isLetter(c)) {
            this.setEditable(false);
            Main.keyWarningLabel1.setVisible(true);
            Main.keyWarningLabel2.setVisible(true);
        } else {
            this.setEditable(true);
            Main.keyWarningLabel1.setVisible(false);
            Main.keyWarningLabel2.setVisible(true);
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

}

class chooseLabel extends JLabel {
    chooseLabel() {
        super(new ImageIcon("check.png"));
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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
}

class OpPanel extends JPanel {
    OpPanel() {
        setLayout(null);

        JLabel logo = new JLabel(new ImageIcon("Logo70.png"));
        logo.setBounds(4, 4, 72, 58);

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
        add(logo);
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

class BaPanel extends JPanel {
    BaPanel() {
        setLayout(null);

        JLabel logo = new JLabel(new ImageIcon("Logo70.png"));
        logo.setBounds(4, 4, 72, 58);

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
        add(logo);
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

        JLabel logo = new JLabel(new ImageIcon("Logo70.png"));
        logo.setBounds(4, 4, 72, 58);

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

        add(logo);
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

class AboutPanel extends JPanel {
    AboutPanel() {
        setLayout(null);
        JLabel logo = new JLabel(new ImageIcon("logo_black.jpeg"));
        logo.setBounds(200, 5, 200, 200);

        JLabel l1 = new JLabel("SecureVault is a small and lightweight File Protection System .");
        l1.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        l1.setBounds(40, 210, 550, 30);

        JLabel l2 = new JLabel("Created By -        Group - 12 IF5I");
        l2.setBounds(60, 320, 400, 20);
        l2.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

        l2.setForeground(Color.RED);
        JLabel l4 = new JLabel("26 Ayush Bhavsar.");
        l4.setBounds(240, 350, 400, 20);
        l4.setFont(new Font("Comic Sans MS", Font.BOLD, 17));

        JLabel l5 = new JLabel("31 Nikhil Gujar.");
        l5.setBounds(240, 380, 400, 20);
        l5.setFont(new Font("Comic Sans MS", Font.BOLD, 17));

        JLabel l3 = new JLabel("61 Aryan Patil.");
        l3.setBounds(240, 410, 400, 20);
        l3.setFont(new Font("Comic Sans MS", Font.BOLD, 17));

        JLabel l6 = new JLabel("Copyright © 2021-2022 - the SecureVault team.");
        l6.setBounds(100, 240, 450, 20);
        l6.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

        JLabel l7 = new JLabel("Credits");
        l7.setBounds(270, 280, 100, 30);
        l7.setFont(new Font("Comic Sans MS", Font.BOLD, 17));

        add(logo);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);

    }
}

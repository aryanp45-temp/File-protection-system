import java.io.*;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BackupRestore {

    public static void BackupFile(String filepath, JFrame parent) {
        // Enter Backup Folder path in DestinationFilePath
        String destfilepath = "/home/aryan/Desktop/sem5/Microproject/AJP/Source Code/Backup/"
                + filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());
        long st_time = new Date().getTime();

        try {
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(filepath));
            BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(destfilepath));

            int total = fis.available();
            ProgressDialog jp = new ProgressDialog(parent);
            jp.jprog.setMinimum(0);
            jp.jprog.setMaximum(total);
            jp.setTitle("File Backup in Progress...");
            jp.jprog.setStringPainted(true);
            jp.setVisible(true);

            int j = 1;
            int b = fis.read();

            while (b != -1) {
                fout.write((byte) b);
                b = fis.read();

                // progressbar
                jp.jprog.paintImmediately(0, 0, 1000, 100);
                jp.jprog.setValue(j);
                j++;
            }
            jp.setVisible(false);
            fis.close();
            fout.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        long end_time = new Date().getTime();
        JOptionPane.showMessageDialog(null, "File BackUp Successful \n Time Taken: " + (end_time - st_time) / 1000,
                "Message Box", JOptionPane.INFORMATION_MESSAGE);

    }

    public static void RestoreFile(String filepath, String folder, JFrame parent) {
        String destfilepath = folder + filepath.substring(filepath.lastIndexOf("/") , filepath.length());
        long st_time = new Date().getTime();

        try {
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(filepath));
            BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(destfilepath));

            int total = fis.available();
            ProgressDialog jp = new ProgressDialog(parent);
            jp.jprog.setMinimum(0);
            jp.jprog.setMaximum(total);
            jp.setTitle("File Restore in Progress...");
            jp.jprog.setStringPainted(true);
            jp.setVisible(true);

            int j = 1;
            int b = fis.read();

            while (b != -1) {
                fout.write((byte) b);
                b = fis.read();

                // progressbar
                jp.jprog.paintImmediately(0, 0, 1000, 100);
                jp.jprog.setValue(j);
                j++;
            }
            fis.close();
            fout.close();
            jp.setVisible(false);

        } catch (Exception e) {
            e.printStackTrace();
        }

        long end_time = new Date().getTime();
        JOptionPane.showMessageDialog(null, "File Restore Successful \n Time Taken: " + (end_time - st_time) / 1000+" sec","Message Box", JOptionPane.INFORMATION_MESSAGE);
    }

}
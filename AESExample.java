import java.security.Key;
import java.util.Base64;
import java.io.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;

public class AESExample {
    private static final String ALGO = "AES";
    private static byte[] keyvalue;

    public static void EnImage(int key, String filepath, JFrame p) {
        try {
            if(key==0){
                key = 1212;
            }
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(filepath));
            int total = fis.available();
            ProgressDialog jp = new ProgressDialog(p);
            jp.jprog.setMinimum(0);
            jp.jprog.setMaximum(total);
            jp.setTitle("Encrypting...");
            jp.jprog.setStringPainted(true);
            jp.setVisible(true);

            int j = 1;
            byte[] data = new byte[fis.available()];
            fis.read(data);
            int i = 0;
            for (byte b : data) {
                data[i] = (byte) (b ^ key);
                i++;

                // progressbar
                jp.jprog.paintImmediately(0, 0, 1000, 100);
                jp.jprog.setValue(j);
                j++;
            }
            fis.close();
            jp.setVisible(false);

            String Temp = filepath;
            filepath = filepath + ".fileEnc";

            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(filepath));
            fos.write(data);
            fos.close();

            File deleteFile = new File(Temp);
            deleteFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DeImage(int key, String filepath, JFrame p) {
        try {
            if(key==0){
                key = 1212;
            }
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(filepath));
            int total = fis.available();
            ProgressDialog jp = new ProgressDialog(p);
            jp.jprog.setMinimum(0);
            jp.jprog.setMaximum(total);
            jp.setTitle("Decrypting...");
            jp.jprog.setStringPainted(true);
            jp.setVisible(true);

            int j = 1;
            byte[] data = new byte[fis.available()];
            fis.read(data);
            int i = 0;
            for (byte b : data) {
                data[i] = (byte) (b ^ key);
                i++;
                // progressbar
                jp.jprog.paintImmediately(0, 0, 1000, 100);
                jp.jprog.setValue(j);
                j++;
            }
            fis.close();
            jp.setVisible(false);

            String Temp = filepath;
            filepath = filepath.substring(0, filepath.length() - 8);

            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(filepath));
            fos.write(data);
            fos.close();

            File deleteFile = new File(Temp);
            deleteFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String Data, String keyString) throws Exception {
        keyvalue = keyString.getBytes();
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] byteArr2 = encoder.encode(encVal);
        String encryptedValue = new String(byteArr2);

        return encryptedValue;
    }

    public static String decrypt(String encryptedData, String keyString) throws Exception {
        keyvalue = keyString.getBytes();
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] byteArr3 = decoder.decode(encryptedData);
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decValue = c.doFinal(byteArr3);
        String decryptedValue = new String(decValue);

        return decryptedValue;
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyvalue, ALGO);
        return key;
    }

}

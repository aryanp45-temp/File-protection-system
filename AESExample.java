import java.security.Key;
import java.util.Base64;
import java.util.logging.*;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
// import sun.misc.Base64Decoder;
// import sun.misc.Base64Encoder;

public class AESExample{
    private static final String ALGO = "AES";
    private byte[] keyvalue;

    public AESExample(String key){
        keyvalue= key.getBytes();
    }

    public String encrypt(String Data) throws Exception{
        Key key= generateKey();
        Cipher c= Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        // String encryptedValue= new Base64Decoder().encode(encVal);
        Base64.Encoder encoder = Base64.getEncoder();  
        byte[] byteArr2 = encoder.encode(encVal);  
        String encryptedValue= new String(byteArr2);

        return encryptedValue;
    }

    public String decrypt(String encryptedData) throws Exception{
        
        Base64.Decoder decoder = Base64.getDecoder();  
        byte[] byteArr3 = decoder.decode(encryptedData);
       // String dStr = new String(byteArr3); 
       // byte[] decodedVaule = dStr.getBytes(dStr);
        Key key= generateKey();
        Cipher c= Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        //byte[] decodedData= encryptedData.getBytes("UTF-8");
        byte[] decValue = c.doFinal(byteArr3);
       String decryptedValue = new String(decValue);

        return decryptedValue;
    }

    private Key generateKey() throws Exception{
        Key key = new SecretKeySpec(keyvalue, ALGO);
        return key;
    }

    public static void main(String[] args) {
        try{
            AESExample aes = new AESExample("aaaaaaaaaaaaaa");
            String encdata = aes.encrypt("brbr chaltoy ata");
            System.out.println("Encrypted Data: "+ encdata);
            String decData= aes.decrypt(encdata);
            System.out.println("Decrypted Data: "+decData);
        }catch(Exception ex){
            Logger.getLogger(AESExample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



}

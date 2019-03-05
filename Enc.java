import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class AESUtils {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static String encrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
            byte[] result = cipher.doFinal(byteContent);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
            byte[] result = cipher.doFinal(Base64.decode(content));
            return new String(result, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static SecretKeySpec getSecretKey() {
        KeyGenerator kg = null;
        String password="1231231231231231";
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kg.init(128,  random);
            SecretKey secretKey = kg.generateKey();
            System.out.println(secretKey);
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
           ex.printStackTrace();
              }

        return null;
    }

    public static void main(String[] args) {
        String s = "abcdefghijklmnop";
        System.out.println(s);
        String s1= AESUtils.encrypt(s ,KEY);
        System.out.println(s1);
        try {
            String s2= AESUtils.decryptPwd(s1,KEY);
            System.out.println(s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decryptPwd(String encryptPwd,String key) throws Exception {
        String str   = AESUtils.decrypt(encryptPwd, key);
        return str;
    }


}
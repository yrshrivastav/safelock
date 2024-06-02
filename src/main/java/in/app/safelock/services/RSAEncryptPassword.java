package in.app.safelock.services;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.stereotype.Service;

@Service
public class RSAEncryptPassword {
    private static PrivateKey privateKey;
    private static PublicKey publicKey;

    public RSAEncryptPassword() {

        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair pair = generator.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();
        } catch (Exception e) {
        }
    }

    public  String encrypt(String message) throws Exception {

        byte[] messageToBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
        return encode(encryptedBytes);

    }

    private  String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public  String decrypt(String encryptedMessage) throws Exception {
        byte[] encryptedBytes = decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
        return new String(decryptedMessage, "UTF8");
    }

    private  byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}

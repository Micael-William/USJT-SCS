import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESCrypto {

    public static SecretKey gerarChave() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        return keyGen.generateKey();
    }


    public static String criptografar(String texto, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] textoCriptografado = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(textoCriptografado);
    }

    
    public static String descriptografar(String textoCriptografado, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, chave);
        byte[] bytesDecodificados = Base64.getDecoder().decode(textoCriptografado);
        byte[] textoOriginal = cipher.doFinal(bytesDecodificados);
        return new String(textoOriginal);
    }

    public static void main(String[] args) {
        try {
            SecretKey chave = gerarChave();

            String textoOriginal = "Texto Secreto!";
            System.out.println("Texto original: " + textoOriginal);

            String textoCriptografado = criptografar(textoOriginal, chave);
            System.out.println("Texto criptografado: " + textoCriptografado);

            String textoDescriptografado = descriptografar(textoCriptografado, chave);
            System.out.println("Texto descriptografado: " + textoDescriptografado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSACrypto
{
    public static KeyPair gerarParDeChaves() throws Exception
    {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); 
        return keyGen.generateKeyPair();
    }

    public static String criptografar(String texto, PublicKey chavePublica) throws Exception
    {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, chavePublica);
        byte[] textoCriptografado = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(textoCriptografado);
    }

    public static String descriptografar(String textoCriptografado, PrivateKey chavePrivada) throws Exception
    {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, chavePrivada);
        byte[] bytesDecodificados = Base64.getDecoder().decode(textoCriptografado);
        byte[] textoOriginal = cipher.doFinal(bytesDecodificados);
        return new String(textoOriginal);
    }

    public static void main(String[] args)
    {
        try {
            KeyPair parDeChaves = gerarParDeChaves();
            PublicKey chavePublica = parDeChaves.getPublic();
            PrivateKey chavePrivada = parDeChaves.getPrivate();

            String textoOriginal = "Mensagem confidencial!";
            System.out.println("Texto original: " + textoOriginal);

            String textoCriptografado = criptografar(textoOriginal, chavePublica);
            System.out.println("Texto criptografado: " + textoCriptografado);

            String textoDescriptografado = descriptografar(textoCriptografado, chavePrivada);
            System.out.println("Texto descriptografado: " + textoDescriptografado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

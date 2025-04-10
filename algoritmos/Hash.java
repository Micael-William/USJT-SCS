import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hash
{
    public static String gerarHashSHA256(String texto)
    {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(texto.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo SHA-256 não encontrado!", e);
        }
    }

    public static void main(String[] args)
    {
        String textoOriginal = "MinhaSenhaSuperSecreta";
        String hashGerado = gerarHashSHA256(textoOriginal);
        System.out.println("Texto original: " + textoOriginal);
        System.out.println("Hash SHA-256:   " + hashGerado);
    }
}

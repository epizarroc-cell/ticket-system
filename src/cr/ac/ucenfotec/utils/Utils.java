package cr.ac.ucenfotec.utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class Utils {
    public static String[] getPropiedades() throws Exception{
        String[] propiedades = new String[5];
        Properties propiedadesAccesoBD = new Properties();
        String ruta = "src\\cr\\ac\\ucenfotec\\db.properties";
        try {
            propiedadesAccesoBD.load(new FileInputStream(ruta));
            propiedades[0] = propiedadesAccesoBD.getProperty("driver");
            propiedades[1] = propiedadesAccesoBD.getProperty("server");
            propiedades[2] = propiedadesAccesoBD.getProperty("dataBase");
            propiedades[3] = propiedadesAccesoBD.getProperty("user");
            propiedades[4] = propiedadesAccesoBD.getProperty("password");
            return propiedades;
        }catch (Exception e){
            throw e;
        }
    }

    public static String generarSalt() throws Exception {
        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Retorna salt:hash (concatenado con $) para guardar en DB
    public static String hashPassword(String password) throws Exception {
        String salt = generarSalt();
        String hash = sha256(salt + password);
        return salt + "$" + hash;
    }

    // Verifica contraseña vs valor guardado (salt$hash)
    public static boolean verificarPassword(String password, String stored) throws Exception {
        if (stored == null || !stored.contains("$")) return false;
        String[] parts = stored.split("\\$");
        String salt = parts[0];
        String hash = parts[1];
        String attempt = sha256(salt + password);
        return attempt.equals(hash);
    }

    private static String sha256(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(input.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(digest);
    }

}

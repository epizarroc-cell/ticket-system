package cr.ac.ucenfotec.bl.entities.Diccionario;

import cr.ac.ucenfotec.dl.Connector;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DAODiccionarioEmocional {

    public static boolean agregarPalabra(String palabra, String emocion) throws Exception {
        // Verificar si ya existe
        String checkQuery = "SELECT COUNT(*) AS total FROM t_palabras_emocionales WHERE palabra = ?";
        PreparedStatement checkStmt = Connector.getBdConnection().prepararStatement(checkQuery);
        checkStmt.setString(1, palabra.toLowerCase());
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next() && rs.getInt("total") > 0) {
            return false;
        }

        String insertQuery = "INSERT INTO t_palabras_emocionales (palabra, emocion) VALUES (?, ?)";
        PreparedStatement insertStmt = Connector.getBdConnection().prepararStatement(insertQuery);
        insertStmt.setString(1, palabra.toLowerCase());
        insertStmt.setString(2, emocion);
        insertStmt.executeUpdate();
        return true;
    }

    public static String buscarPalabra(String palabra) throws Exception {
        String query = "SELECT emocion FROM t_palabras_emocionales WHERE palabra = ?";
        PreparedStatement stmt = Connector.getBdConnection().prepararStatement(query);
        stmt.setString(1, palabra.toLowerCase());
        ResultSet rs = stmt.executeQuery();
        return rs.next() ? rs.getString("emocion") : null;
    }

    public static boolean actualizarPalabra(String palabra, String nuevaEmocion) throws Exception {
        String query = "UPDATE t_palabras_emocionales SET emocion = ? WHERE palabra = ?";
        PreparedStatement stmt = Connector.getBdConnection().prepararStatement(query);
        stmt.setString(1, nuevaEmocion);
        stmt.setString(2, palabra.toLowerCase());
        return stmt.executeUpdate() > 0;
    }

    public static boolean eliminarPalabra(String palabra) throws Exception {
        String query = "DELETE FROM t_palabras_emocionales WHERE palabra = ?";
        PreparedStatement stmt = Connector.getBdConnection().prepararStatement(query);
        stmt.setString(1, palabra.toLowerCase());
        return stmt.executeUpdate() > 0;
    }

    public static Map<String, String> obtenerTodas() throws Exception {
        Map<String, String> palabras = new HashMap<>();
        String query = "SELECT palabra, emocion FROM t_palabras_emocionales";
        ResultSet rs = Connector.getBdConnection().ejecutarQuery(query);
        while (rs.next()) {
            palabras.put(rs.getString("palabra"), rs.getString("emocion"));
        }
        return palabras;
    }

    public static int contarPalabras() throws Exception {
        String query = "SELECT COUNT(*) AS total FROM t_palabras_emocionales";
        ResultSet rs = Connector.getBdConnection().ejecutarQuery(query);
        return rs.next() ? rs.getInt("total") : 0;
    }
}
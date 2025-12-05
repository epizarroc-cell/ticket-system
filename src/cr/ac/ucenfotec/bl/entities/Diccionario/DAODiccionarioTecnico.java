package cr.ac.ucenfotec.bl.entities.Diccionario;

import cr.ac.ucenfotec.dl.Connector;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DAODiccionarioTecnico {

    public static boolean agregarPalabra(String palabra, String categoria) throws Exception {
        // Verificar si ya existe
        String checkQuery = "SELECT COUNT(*) AS total FROM t_palabras_tecnicas WHERE palabra = ?";
        PreparedStatement checkStmt = Connector.getBdConnection().prepararStatement(checkQuery);
        checkStmt.setString(1, palabra.toLowerCase());
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next() && rs.getInt("total") > 0) {
            return false;
        }

        String insertQuery = "INSERT INTO t_palabras_tecnicas (palabra, categoria) VALUES (?, ?)";
        PreparedStatement insertStmt = Connector.getBdConnection().prepararStatement(insertQuery);
        insertStmt.setString(1, palabra.toLowerCase());
        insertStmt.setString(2, categoria);
        insertStmt.executeUpdate();
        return true;
    }

    public static String buscarPalabra(String palabra) throws Exception {
        String query = "SELECT categoria FROM t_palabras_tecnicas WHERE palabra = ?";
        PreparedStatement stmt = Connector.getBdConnection().prepararStatement(query);
        stmt.setString(1, palabra.toLowerCase());
        ResultSet rs = stmt.executeQuery();
        return rs.next() ? rs.getString("categoria") : null;
    }

    public static boolean actualizarPalabra(String palabra, String nuevaCategoria) throws Exception {
        String query = "UPDATE t_palabras_tecnicas SET categoria = ? WHERE palabra = ?";
        PreparedStatement stmt = Connector.getBdConnection().prepararStatement(query);
        stmt.setString(1, nuevaCategoria);
        stmt.setString(2, palabra.toLowerCase());
        return stmt.executeUpdate() > 0;
    }

    public static boolean eliminarPalabra(String palabra) throws Exception {
        String query = "DELETE FROM t_palabras_tecnicas WHERE palabra = ?";
        PreparedStatement stmt = Connector.getBdConnection().prepararStatement(query);
        stmt.setString(1, palabra.toLowerCase());
        return stmt.executeUpdate() > 0;
    }

    public static Map<String, String> obtenerTodas() throws Exception {
        Map<String, String> palabras = new HashMap<>();
        String query = "SELECT palabra, categoria FROM t_palabras_tecnicas";
        ResultSet rs = Connector.getBdConnection().ejecutarQuery(query);
        while (rs.next()) {
            palabras.put(rs.getString("palabra"), rs.getString("categoria"));
        }
        return palabras;
    }

    public static int contarPalabras() throws Exception {
        String query = "SELECT COUNT(*) AS total FROM t_palabras_tecnicas";
        ResultSet rs = Connector.getBdConnection().ejecutarQuery(query);
        return rs.next() ? rs.getInt("total") : 0;
    }
}
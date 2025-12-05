package cr.ac.ucenfotec.bl.entities.Departamento;

import cr.ac.ucenfotec.dl.Connector;
import java.sql.*;

public class DAODepartamento {

    public static String insertarDepartamento(Departamento departamentoInsertar) throws Exception {
        // Verificar unicidad por nombre
        String checkQuery = "SELECT COUNT(*) AS total FROM t_departamentos WHERE nombre = ?";
        PreparedStatement checkStmt = Connector.getBdConnection().prepararStatement(checkQuery);
        checkStmt.setString(1, departamentoInsertar.getNombre());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            int total = rs.getInt("total");
            if (total > 0) {
                return "Error: Ya existe un departamento con ese nombre.\n";
            }
        }

        String insertQuery = "INSERT INTO t_departamentos(nombre, descripcion, extension) VALUES (?, ?, ?)";
        PreparedStatement insertStmt = Connector.getBdConnection().prepararStatement(insertQuery);
        insertStmt.setString(1, departamentoInsertar.getNombre());
        insertStmt.setString(2, departamentoInsertar.getDescripcion());
        insertStmt.setString(3, departamentoInsertar.getContacto());

        insertStmt.executeUpdate();
        return "El departamento se registró en la base de datos correctamente.\n";
    }

    public static String obtenerTodos() throws Exception {
        StringBuilder sb = new StringBuilder();
        String query = "SELECT * FROM t_departamentos;";
        ResultSet rs = Connector.getBdConnection().ejecutarQuery(query);
        while (rs.next()) {
            sb.append("ID: ").append(rs.getInt("id")).append(" | ");
            sb.append("Nombre: ").append(rs.getString("nombre")).append(" | ");
            sb.append("Descripción: ").append(rs.getString("descripcion")).append(" | ");
            sb.append("Contacto: ").append(rs.getString("extension")).append("\n");
        }
        if (sb.isEmpty()) {
            sb.append("No hay departamentos registrados.\n");
        }
        return sb.toString();
    }

    public static Departamento buscarPorNombre(String nombre) throws Exception {
        String query = "SELECT * FROM t_departamentos WHERE nombre = ?";
        PreparedStatement stmt = Connector.getBdConnection().prepararStatement(query);
        stmt.setString(1, nombre);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Departamento(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getString("extension")
            );
        }
        return null;
    }

    public static Departamento buscarPorId(int id) throws Exception {
        String query = "SELECT * FROM t_departamentos WHERE id = ?";
        PreparedStatement stmt = Connector.getBdConnection().prepararStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Departamento(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getString("extension")
            );
        }
        return null;
    }
}
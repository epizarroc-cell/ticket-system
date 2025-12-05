package cr.ac.ucenfotec.bl.entities.Usuario;

import cr.ac.ucenfotec.dl.Connector;
import cr.ac.ucenfotec.utils.Utils;
import java.sql.*;

public class DAOUsuario {

    public static String insertarUsuario(Usuario usuarioInsertar) throws Exception {
        // Verificar unicidad por correo
        String checkQuery = "SELECT COUNT(*) AS total FROM t_usuarios WHERE correo = ?";
        PreparedStatement checkStmt = Connector.getBdConnection().prepararStatement(checkQuery);
        checkStmt.setString(1, usuarioInsertar.getCorreoElectronico());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            int total = rs.getInt("total");
            if (total > 0) {
                return "Error: Ya existe un usuario con ese correo electrónico.\n";
            }
        }

        String saltedHash = Utils.hashPassword(usuarioInsertar.getContrasena());

        String insertQuery = "INSERT INTO t_usuarios(nombre, correo, contrasena, telefono, rol) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement insertStmt = Connector.getBdConnection().prepararStatement(insertQuery);
        insertStmt.setString(1, usuarioInsertar.getNombreCompleto());
        insertStmt.setString(2, usuarioInsertar.getCorreoElectronico());
        insertStmt.setString(3, saltedHash);
        insertStmt.setString(4, usuarioInsertar.getTelefonoContacto());
        insertStmt.setString(5, usuarioInsertar.getRol());

        insertStmt.executeUpdate();
        return "El usuario se registró en la base de datos correctamente.\n";
    }

    public static String obtenerTodos() throws Exception {
        StringBuilder sb = new StringBuilder();
        String query = "SELECT * FROM t_usuarios;";
        ResultSet rs = Connector.getBdConnection().ejecutarQuery(query);
        while (rs.next()) {
            sb.append("ID: ").append(rs.getInt("id")).append(" | ");
            sb.append("Nombre: ").append(rs.getString("nombre")).append(" | ");
            sb.append("Correo: ").append(rs.getString("correo")).append(" | ");
            sb.append("Teléfono: ").append(rs.getString("telefono")).append(" | ");
            sb.append("Rol: ").append(rs.getString("rol")).append("\n");
        }
        if (sb.isEmpty()) {
            sb.append("No hay usuarios registrados.\n");
        }
        return sb.toString();
    }

    public static Usuario buscarPorCorreo(String correo) throws Exception {
        String query = "SELECT * FROM t_usuarios WHERE correo = ?";
        PreparedStatement stmt = Connector.getBdConnection().prepararStatement(query);
        stmt.setString(1, correo);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena"),
                    rs.getString("telefono"),
                    rs.getString("rol")
            );
        }
        return null;
    }

    public static Usuario buscarPorId(int id) throws Exception {
        String query = "SELECT * FROM t_usuarios WHERE id = ?";
        PreparedStatement stmt = Connector.getBdConnection().prepararStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena"),
                    rs.getString("telefono"),
                    rs.getString("rol")
            );
        }
        return null;
    }
}
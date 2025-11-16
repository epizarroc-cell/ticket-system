package cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.dl.Connector;
import java.sql.ResultSet;

public class DAOUsuario {
    public static String query;
    public static String statement;

    public static String insertarUsuario(Usuario usuarioInsertar) throws Exception {
        // Verificar unicidad por correo
        query = "SELECT COUNT(*) AS total FROM t_usuarios WHERE correo = '" + usuarioInsertar.getCorreoElectronico() + "';";
        ResultSet rs = Connector.getBdConnection().ejecutarQuery(query);
        if (rs.next()) {
            int total = rs.getInt("total");
            if (total > 0) {
                return "Error: Ya existe un usuario con ese correo electrónico.\n";
            }
        }

        statement = "INSERT INTO t_usuarios(nombre, correo, contrasena, telefono, rol) VALUES ('" +
                usuarioInsertar.getNombreCompleto() + "','" + usuarioInsertar.getCorreoElectronico() + "','" +
                usuarioInsertar.getContrasena() + "','" + usuarioInsertar.getTelefonoContacto() + "','" +
                usuarioInsertar.getRol() + "');";
        Connector.getBdConnection().ejecutarStatement(statement);
        return "El usuario se registró en la base de datos correctamente.\n";
    }

    public static String obtenerTodos() throws Exception {
        StringBuilder sb = new StringBuilder();
        query = "SELECT * FROM t_usuarios;";
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
        query = "SELECT * FROM t_usuarios WHERE correo = '" + correo + "';";
        ResultSet rs = Connector.getBdConnection().ejecutarQuery(query);
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
        query = "SELECT * FROM t_usuarios WHERE id = " + id + ";";
        ResultSet rs = Connector.getBdConnection().ejecutarQuery(query);
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
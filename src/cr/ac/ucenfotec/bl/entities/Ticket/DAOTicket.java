package cr.ac.ucenfotec.bl.entities.Ticket;
import cr.ac.ucenfotec.dl.Connector;
import cr.ac.ucenfotec.bl.entities.Usuario.Usuario;
import cr.ac.ucenfotec.bl.entities.Departamento.Departamento;
import cr.ac.ucenfotec.bl.entities.Usuario.DAOUsuario;
import cr.ac.ucenfotec.bl.entities.Departamento.DAODepartamento;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DAOTicket {
    public static String statement;
    public static String query;

    public static String insertarTicket(Ticket ticketInsertar) throws Exception {
        statement = "INSERT INTO t_tickets(asunto, descripcion, estado, fecha_creacion, usuario_id, departamento_id) VALUES ('" +
                ticketInsertar.getAsunto() + "','" + ticketInsertar.getDescripcion() + "','" +
                ticketInsertar.getEstado() + "','" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "'," +
                ticketInsertar.getUsuario().getId() + "," + ticketInsertar.getDepartamento().getId() + ");";
        Connector.getBdConnection().ejecutarStatement(statement);
        return "El ticket se registró en la base de datos correctamente.\n";
    }

    public static String obtenerTodos() throws Exception {
        StringBuilder sb = new StringBuilder();
        query = "SELECT t.*, u.nombre as usuario_nombre, u.correo as usuario_correo, " +
                "d.nombre as departamento_nombre FROM t_tickets t " +
                "JOIN t_usuarios u ON t.usuario_id = u.id " +
                "JOIN t_departamentos d ON t.departamento_id = d.id " +
                "ORDER BY t.fecha_creacion DESC;";
        ResultSet rs = Connector.getBdConnection().ejecutarQuery(query);
        while (rs.next()) {
            sb.append("ID: ").append(rs.getInt("id")).append(" | ");
            sb.append("Asunto: ").append(rs.getString("asunto")).append(" | ");
            sb.append("Estado: ").append(rs.getString("estado")).append(" | ");
            sb.append("Usuario: ").append(rs.getString("usuario_nombre")).append(" | ");
            sb.append("Departamento: ").append(rs.getString("departamento_nombre")).append(" | ");
            sb.append("Fecha: ").append(rs.getTimestamp("fecha_creacion")).append("\n");
        }
        if (sb.isEmpty()) {
            sb.append("No hay tickets registrados.\n");
        }
        return sb.toString();
    }

    public static Ticket buscarPorId(int id) throws Exception {
        query = "SELECT t.*, u.nombre as usuario_nombre, u.correo as usuario_correo, u.telefono as usuario_telefono, u.rol as usuario_rol, " +
                "d.nombre as departamento_nombre, d.descripcion as departamento_descripcion, d.extension as departamento_extension " +
                "FROM t_tickets t " +
                "JOIN t_usuarios u ON t.usuario_id = u.id " +
                "JOIN t_departamentos d ON t.departamento_id = d.id " +
                "WHERE t.id = " + id + ";";
        ResultSet rs = Connector.getBdConnection().ejecutarQuery(query);
        if (rs.next()) {
            // Crear usuario
            Usuario usuario = new Usuario(
                    rs.getInt("usuario_id"),
                    rs.getString("usuario_nombre"),
                    rs.getString("usuario_correo"),
                    "", // contraseña no se necesita
                    rs.getString("usuario_telefono"),
                    rs.getString("usuario_rol")
            );

            // Crear departamento
            Departamento departamento = new Departamento(
                    rs.getInt("departamento_id"),
                    rs.getString("departamento_nombre"),
                    rs.getString("departamento_descripcion"),
                    rs.getString("departamento_extension")
            );

            // Crear ticket
            return new Ticket(
                    rs.getInt("id"),
                    rs.getString("asunto"),
                    rs.getString("descripcion"),
                    rs.getString("estado"),
                    rs.getTimestamp("fecha_creacion").toLocalDateTime(),
                    departamento,
                    usuario
            );
        }
        return null;
    }
}
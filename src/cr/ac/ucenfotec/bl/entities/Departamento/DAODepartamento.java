package cr.ac.ucenfotec.bl.entities.Departamento;
import cr.ac.ucenfotec.dl.Connector;
import java.sql.ResultSet;

public class DAODepartamento {

    public static String statement;
    public static String query;

    public static String insertarDepartamento(Departamento departamentoInsertar) throws Exception {
        // Verificar unicidad por nombre
        query = "SELECT COUNT(*) AS total FROM t_departamentos WHERE nombre = '" + departamentoInsertar.getNombre() + "';";
        ResultSet rs = Connector.getBdConnection().ejecutarQuery(query);
        if (rs.next()) {
            int total = rs.getInt("total");
            if (total > 0) {
                return "Error: Ya existe un departamento con ese nombre.\n";
            }
        }
        statement = "INSERT INTO t_departamentos(nombre, descripcion, extension) VALUES ('" +
                departamentoInsertar.getNombre() + "','" + departamentoInsertar.getDescripcion() + "','" + departamentoInsertar.getContacto() + "');";
        Connector.getBdConnection().ejecutarStatement(statement);
        return "El departamento se registro en la base de datos correctamente.\n";
    }

    public static String obtenerTodos() throws Exception {
        StringBuilder sb = new StringBuilder();
        query = "SELECT * FROM t_departamentos;";
        ResultSet rs = Connector.getBdConnection().ejecutarQuery(query);
        while (rs.next()) {
            sb.append("ID: ").append(rs.getInt("id")).append(" | ");
            sb.append("Nombre: ").append(rs.getString("nombre")).append(" | ");
            sb.append("Descripcion: ").append(rs.getString("descripcion")).append(" | ");
            sb.append("Contacto: ").append(rs.getString("extension")).append("\n");
        }
        if (sb.isEmpty()) {
            sb.append("No hay departamentos registrados.\n");
        }
        return sb.toString();
    }


}

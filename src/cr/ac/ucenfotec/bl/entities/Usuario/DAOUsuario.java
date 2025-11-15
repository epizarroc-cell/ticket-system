package cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.dl.Connector;

public class DAOUsuario {

    public static String query;
    public static String statement;

    public static String insertarUsuario(Usuario usuarioInsertar) throws Exception{
        statement = "INSERT INTO t_usuarios(nombre, correo, contrasena, telefono, rol) VALUES ('"+usuarioInsertar.getNombreCompleto()+"','" +usuarioInsertar.getCorreoElectronico()+"','"+usuarioInsertar.getContrasena()+"','"+usuarioInsertar.getTelefonoContacto()+"','"+usuarioInsertar.getRol()+"');";
        Connector.getBdConnection().ejecutarStatement(statement);
        return "El usuario se registro en la base de datos correctamente.\n";
    }

}

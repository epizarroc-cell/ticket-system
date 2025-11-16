package cr.ac.ucenfotec.bl.logic;
import cr.ac.ucenfotec.bl.entities.Usuario.*;

public class GestorUsuario {
    public static String agregarUsuario(String nombre, String correo, String contrasena, String telefono, String rol) throws Exception {
        Usuario nuevoUsuario = new Usuario(nombre, correo, contrasena, telefono, rol);
        return DAOUsuario.insertarUsuario(nuevoUsuario);
    }

    public static String listarUsuarios() throws Exception {
        return DAOUsuario.obtenerTodos();
    }

    public static Usuario buscarUsuarioPorCorreo(String correo) throws Exception {
        return DAOUsuario.buscarPorCorreo(correo);
    }

    public static Usuario buscarUsuarioPorId(int id) throws Exception {
        return DAOUsuario.buscarPorId(id);
    }
}
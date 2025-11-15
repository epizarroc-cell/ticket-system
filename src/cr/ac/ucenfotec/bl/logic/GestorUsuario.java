package cr.ac.ucenfotec.bl.logic;
import  cr.ac.ucenfotec.bl.entities.Usuario.*;

public class GestorUsuario {

  public static String agregarUsuario (String nombre, String correo, String contrasena, String telefono, String rol) throws Exception {
      Usuario nuevoUsuario = new Usuario(nombre ,correo, contrasena, telefono, rol);
      return DAOUsuario.insertarUsuario(nuevoUsuario);
  }

}

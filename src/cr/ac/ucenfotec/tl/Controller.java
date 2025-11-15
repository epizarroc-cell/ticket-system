package cr.ac.ucenfotec.tl;
import cr.ac.ucenfotec.bl.entities.Usuario.Usuario;
import cr.ac.ucenfotec.ui.Menu;

import java.io.IOException;
public class Controller {

    public static void procesarSeleccionMenu (Byte opcion) throws Exception{
        switch (opcion) {
            case 1:
                Menu.registrarUsuario();
                break;
            case 2:
                registrarDepartamento();
                break;
            case 3:
                registrarTicket();
                break;
            case 4:
                listarUsuarios();
                break;
            case 5:
                listarDepartamentos();
                break;
            case 6:
                listarTickets();
                break;
            case 7:
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("Opción inválida. Por favor ingrese un número válido");
        }

    }

    public static void registrarUsuario(){
        System.out.println("opcion1");
    }

    public static void registrarDepartamento(){
        System.out.println("opcion2");
    }

    public static void registrarTicket(){
        System.out.println("opcion3");
    }

    public static void listarUsuarios(){
        System.out.println("opcion4");
    }

    public static void listarDepartamentos(){
        System.out.println("opcion5");
    }

    public static void listarTickets(){
        System.out.println("opcion6");
    }

//    private static void registrarUsuario() {
//        try {
//            System.out.println("\n--- REGISTRAR USUARIO ---");
//            System.out.print("Nombre completo: ");
//            String nombre = Menu.leerTexto();
//
//            System.out.print("Correo electrónico: ");
//            String correo = Menu.leerTexto();
//
//            // Validar que no exista un usuario con el mismo correo
//            for (Usuario usuario : usuarios) {
//                if (usuario.equals(new Usuario("", correo, "", "", ""))) {
//                    System.out.println("❌ Error: Ya existe un usuario con ese correo electrónico");
//                    return;
//                }
//            }
//
//            System.out.print("Contraseña: ");
//            String contrasena = reader.readLine();
//
//            System.out.print("Teléfono de contacto (opcional): ");
//            String telefono = reader.readLine();
//
//            System.out.print("Rol (administrador, estudiante, funcionario): ");
//            String rol = reader.readLine();
//
//            Usuario nuevoUsuario = new Usuario(nombre, correo, contrasena, telefono, rol);
//            usuarios.add(nuevoUsuario);
//            System.out.println("✅ Usuario registrado exitosamente");
//
//        } catch (IOException e) {
//            System.out.println("Error al leer la entrada: " + e.getMessage());
//        }
//    }



}

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
                Menu.registrarDepartamento();
                break;
            case 3:
                Menu.registrarTicket();
                break;
            case 4:
                Menu.listarUsuarios();
                break;
            case 5:
                Menu.listarDepartamentos();
                break;
            case 6:
                Menu.listarTickets();
                break;
            case 7:
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("Opción inválida. Por favor ingrese un número válido");
        }
    }
}

package cr.ac.ucenfotec.ui;
import cr.ac.ucenfotec.bl.logic.GestorUsuario;
import cr.ac.ucenfotec.bl.logic.GestorDepartamento;
import cr.ac.ucenfotec.tl.Controller;

import cr.ac.ucenfotec.bl.entities.Usuario.Usuario;
import cr.ac.ucenfotec.bl.entities.Departamento.Departamento;
import cr.ac.ucenfotec.bl.entities.Ticket.Ticket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void mostrarMenu() throws Exception {
        byte opcion = -1;
        do {

            System.out.println("\n=== Sistema de tickete universitario ===");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Registrar Departamento");
            System.out.println("3. Registrar Ticket");
            System.out.println("4. Listar Usuarios");
            System.out.println("5. Listar Departamentos");
            System.out.println("6. Listar Tickets");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            try{
                opcion = Byte.parseByte(reader.readLine());
            }catch (Exception e){
                System.out.println("Lo sentimos, sucedio algo inesperado");
            }
            Controller.procesarSeleccionMenu(opcion);
        } while (opcion != 7);
    }

    public static void registrarUsuario() throws Exception {

            boolean usuarioValido = false;

            try {
                do {
                    System.out.println("\n--- REGISTRAR USUARIO ---");
                    System.out.print("Nombre completo: ");
                    String nombre = reader.readLine();

                    System.out.print("Correo electrónico: ");
                    String correo = reader.readLine();

                    //Validar que no exista un usuario con el mismo correo
        //            for (Usuario usuario : usuarios) {
        //                if (usuario.equals(new Usuario("", correo, "", "", ""))) {
        //                    System.out.println("❌ Error: Ya existe un usuario con ese correo electrónico");
        //                    return;
        //                }
        //            }

                    System.out.print("Contraseña: ");
                    String contrasena = reader.readLine();

                    System.out.print("Teléfono de contacto (opcional): ");
                    String telefono = reader.readLine();

                    System.out.print("Rol (administrador, estudiante, funcionario): ");
                    String rol = reader.readLine();

                    if (datosCompletos(nombre, correo, contrasena, telefono, rol)) {
                        usuarioValido = true;
                        System.out.println(GestorUsuario.agregarUsuario(nombre,correo, contrasena,telefono,rol));

                    }else {
                        System.out.println("Faltan datos por completar, Intente de nuevo");
                    }

                }while (!usuarioValido);



            } catch (IOException e) {
                System.out.println("Error al leer la entrada: " + e.getMessage());
            }


    }


    public static void registrarDepartamento() throws Exception {
        boolean ok = false;
        try {
            do {
                System.out.println("\n--- REGISTRAR DEPARTAMENTO ---");
                System.out.print("Nombre del departamento: ");
                String nombre = reader.readLine();

                System.out.print("Descripción breve: ");
                String descripcion = reader.readLine();

                System.out.print("Correo o extensión (opcional): ");
                String contacto = reader.readLine();

                if (datosCompletos(nombre, descripcion)) {
                    ok = true;
                    System.out.println(GestorDepartamento.agregarDepartamento(nombre, descripcion, contacto));
                } else {
                    System.out.println("Faltan datos obligatorios. Intente de nuevo.");
                }
            } while (!ok);
        } catch (IOException e) {
            System.out.println("Error al leer la entrada: " + e.getMessage());
        }
    }

//    public static void registrarTicket() throws Exception {
//        boolean ok = false;
//        try {
//            do {
//                System.out.println("\n--- REGISTRAR TICKET ---");
//                System.out.print("Asunto: ");
//                String asunto = reader.readLine();
//
//                System.out.print("Descripción: ");
//                String descripcion = reader.readLine();
//
//                System.out.print("Correo del usuario que reporta: ");
//                String correo = reader.readLine();
//
//                System.out.print("Departamento asociado (nombre): ");
//                String departamento = reader.readLine();
//
//                // Estado inicial "nuevo"
//                String estado = "nuevo";
//
//                if (datosCompletos(asunto, descripcion, correo, departamento)) {
//                    ok = true;
//                    System.out.println(GestorTicket.agregarTicket(asunto, descripcion, estado, correo, departamento));
//                } else {
//                    System.out.println("Faltan datos obligatorios. Intente de nuevo.");
//                }
//            } while (!ok);
//        } catch (IOException e) {
//            System.out.println("Error al leer la entrada: " + e.getMessage());
//        }
//    }

//    public static void listarUsuarios() throws Exception {
//        System.out.println("\n--- LISTA DE USUARIOS ---");
//        System.out.println(cr.ac.ucenfotec.bl.logic.GestorUsuario.listarUsuarios());
//    }

    public static void listarDepartamentos() throws Exception {
        System.out.println("\n--- LISTA DE DEPARTAMENTOS ---");
        System.out.println(cr.ac.ucenfotec.bl.logic.GestorDepartamento.listarDepartamentos());
    }

//    public static void listarTickets() throws Exception {
//        System.out.println("\n--- LISTA DE TICKETS ---");
//        System.out.println(cr.ac.ucenfotec.bl.logic.GestorTicket.listarTickets());
//    }

    public static boolean datosCompletos(String... datos) {
        for (String dato : datos) {
            if (dato == null || dato.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

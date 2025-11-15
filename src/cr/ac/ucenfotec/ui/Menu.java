package cr.ac.ucenfotec.ui;
import cr.ac.ucenfotec.bl.logic.GestorUsuario;
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
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Departamento> departamentos = new ArrayList<>();
    private static List<Ticket> tickets = new ArrayList<>();
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));;

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

    public static boolean datosCompletos(String... datos) {
        for (String dato : datos) {
            if (dato == null || dato.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static void registrarDepartamento() {
        try {
            System.out.println("\n--- REGISTRAR DEPARTAMENTO ---");
            System.out.print("Nombre: ");
            String nombre = reader.readLine();

            // Validar que no exista un departamento con el mismo nombre
            for (Departamento dept : departamentos) {
                if (dept.equals(new Departamento(nombre, "", ""))) {
                    System.out.println("❌ Error: Ya existe un departamento con ese nombre");
                    return;
                }
            }

            System.out.print("Descripción: ");
            String descripcion = reader.readLine();

            System.out.print("Contacto (opcional): ");
            String contacto = reader.readLine();

            Departamento nuevoDepartamento = new Departamento(nombre, descripcion, contacto);
            departamentos.add(nuevoDepartamento);
            System.out.println("✅ Departamento registrado exitosamente");

        } catch (IOException e) {
            System.out.println("Error al leer la entrada: " + e.getMessage());
        }
    }

    private static void registrarTicket() {
        try {
            System.out.println("\n--- REGISTRAR TICKET ---");

            // Verificar que haya usuarios y departamentos registrados
            if (usuarios.isEmpty()) {
                System.out.println("❌ Error: No hay usuarios registrados. Registre uno primero.");
                return;
            }

            if (departamentos.isEmpty()) {
                System.out.println("❌ Error: No hay departamentos registrados. Registre uno primero.");
                return;
            }

            // Seleccionar usuario
            System.out.println("Usuarios disponibles:");
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println((i + 1) + ". " + usuarios.get(i).getNombreCompleto() + " (" + usuarios.get(i).getCorreoElectronico() + ")");
            }

            System.out.print("Seleccione el número del usuario: ");
            String inputUsuario = reader.readLine();
            int numeroUsuario = Integer.parseInt(inputUsuario);

            if (numeroUsuario < 1 || numeroUsuario > usuarios.size()) {
                System.out.println("❌ Error: Número de usuario inválido");
                return;
            }

            Usuario usuarioSeleccionado = usuarios.get(numeroUsuario - 1);

            // Seleccionar departamento
            System.out.println("Departamentos disponibles:");
            for (int i = 0; i < departamentos.size(); i++) {
                System.out.println((i + 1) + ". " + departamentos.get(i).getNombre());
            }

            System.out.print("Seleccione el número del departamento: ");
            String inputDept = reader.readLine();
            int numeroDept = Integer.parseInt(inputDept);

            if (numeroDept < 1 || numeroDept > departamentos.size()) {
                System.out.println("❌ Error: Número de departamento inválido");
                return;
            }

            Departamento departamentoSeleccionado = departamentos.get(numeroDept - 1);

            System.out.print("Asunto del ticket: ");
            String asunto = reader.readLine();

            System.out.print("Descripción del problema: ");
            String descripcion = reader.readLine();

            Ticket nuevoTicket = new Ticket(asunto, descripcion, departamentoSeleccionado, usuarioSeleccionado);
            tickets.add(nuevoTicket);

            System.out.println("✅ Ticket registrado exitosamente:");
            System.out.println(nuevoTicket);

        } catch (NumberFormatException e) {
            System.out.println("Error: Por favor ingrese un número válido");
        } catch (IOException e) {
            System.out.println("Error al leer la entrada: " + e.getMessage());
        }
    }

    private static void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println((i + 1) + ". " + usuarios.get(i));
            }
        }
    }

    private static void listarDepartamentos() {
        System.out.println("\n--- LISTA DE DEPARTAMENTOS ---");
        if (departamentos.isEmpty()) {
            System.out.println("No hay departamentos registrados");
        } else {
            for (int i = 0; i < departamentos.size(); i++) {
                System.out.println((i + 1) + ". " + departamentos.get(i));
            }
        }
    }

    private static void listarTickets() {
        System.out.println("\n--- LISTA DE TICKETS ---");
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets registrados");
        } else {
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        }
    }

    public static String leerTexto() throws IOException {
        return reader.readLine();
    }

}

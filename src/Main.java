import modelo.Usuario;
import modelo.Departamento;
import modelo.Ticket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Departamento> departamentos = new ArrayList<>();
    private static List<Ticket> tickets = new ArrayList<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {
        int opcion = 0;
        do {
            try {
                System.out.println("\n=== Sistema de tickete universitario ===");
                System.out.println("1. Registrar Usuario");
                System.out.println("2. Registrar Departamento");
                System.out.println("3. Registrar Ticket");
                System.out.println("4. Listar Usuarios");
                System.out.println("5. Listar Departamentos");
                System.out.println("6. Listar Tickets");
                System.out.println("7. Salir");
                System.out.print("Seleccione una opción: ");

                String input = reader.readLine();
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        registrarUsuario();
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
                        System.out.println("Opción inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número válido");
            } catch (IOException e) {
                System.out.println("Error de entrada/salida: " + e.getMessage());
            }
        } while (opcion != 7);
    }

    private static void registrarUsuario() {
        try {
            System.out.println("\n--- REGISTRAR USUARIO ---");
            System.out.print("Nombre completo: ");
            String nombre = reader.readLine();

            System.out.print("Correo electrónico: ");
            String correo = reader.readLine();

            // Validar que no exista un usuario con el mismo correo
            for (Usuario usuario : usuarios) {
                if (usuario.equals(new Usuario("", correo, "", "", ""))) {
                    System.out.println("❌ Error: Ya existe un usuario con ese correo electrónico");
                    return;
                }
            }

            System.out.print("Contraseña: ");
            String contrasena = reader.readLine();

            System.out.print("Teléfono de contacto (opcional): ");
            String telefono = reader.readLine();

            System.out.print("Rol (administrador, estudiante, funcionario): ");
            String rol = reader.readLine();

            Usuario nuevoUsuario = new Usuario(nombre, correo, contrasena, telefono, rol);
            usuarios.add(nuevoUsuario);
            System.out.println("✅ Usuario registrado exitosamente");

        } catch (IOException e) {
            System.out.println("Error al leer la entrada: " + e.getMessage());
        }
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
}
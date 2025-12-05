package cr.ac.ucenfotec.ui;
import cr.ac.ucenfotec.bl.entities.Ticket.DAOTicket;
import cr.ac.ucenfotec.bl.logic.*;
import cr.ac.ucenfotec.bl.entities.Usuario.Usuario;
import cr.ac.ucenfotec.bl.entities.Departamento.Departamento;
import cr.ac.ucenfotec.bl.entities.Ticket.Ticket;
import cr.ac.ucenfotec.bl.entities.Analizador.AnalizadorBoW;
import cr.ac.ucenfotec.bl.entities.Diccionario.DiccionarioTecnico;
import cr.ac.ucenfotec.bl.entities.Diccionario.DiccionarioEmocional;
import cr.ac.ucenfotec.tl.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Menu {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static AnalizadorBoW analizadorBoW;

    static {
        try {
            analizadorBoW = new AnalizadorBoW();
        } catch (Exception e) {
            System.out.println("Error al cargar diccionarios: " + e.getMessage());
            analizadorBoW = null;
        }
    }

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
            System.out.println("7. Gestionar Diccionarios BoW");
            System.out.println("8. Analizar Ticket (BoW)");
            System.out.println("9. Cambiar estado de Ticket");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
            try{
                opcion = Byte.parseByte(reader.readLine());
            }catch (Exception e){
                System.out.println("Lo sentimos, sucedio algo inesperado");
            }
            Controller.procesarSeleccionMenu(opcion);
        } while (opcion != 10);
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

                System.out.print("Contraseña: ");
                String contrasena = reader.readLine();

                System.out.print("Teléfono de contacto (opcional): ");
                String telefono = reader.readLine();

                System.out.print("Rol (administrador, estudiante, funcionario): ");
                String rol = reader.readLine();

                if (datosCompletos(nombre, correo, contrasena, rol)) {
                    usuarioValido = true;
                    System.out.println(GestorUsuario.agregarUsuario(nombre, correo, contrasena, telefono, rol));
                } else {
                    System.out.println("Faltan datos por completar. Intente de nuevo.");
                }
            } while (!usuarioValido);
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

    public static void registrarTicket() throws Exception {
        boolean ok = false;
        try {
            do {
                System.out.println("\n--- REGISTRAR TICKET ---");

                // Listar usuarios
                System.out.println("Usuarios registrados:");
                System.out.println(GestorUsuario.listarUsuarios());
                System.out.print("Ingrese el correo del usuario: ");
                String correoUsuario = reader.readLine();

                Usuario usuario = GestorUsuario.buscarUsuarioPorCorreo(correoUsuario);
                if (usuario == null) {
                    System.out.println("Error: Usuario no encontrado.");
                    return;
                }

                // Listar departamentos
                System.out.println("Departamentos registrados:");
                System.out.println(GestorDepartamento.listarDepartamentos());
                System.out.print("Ingrese el nombre del departamento: ");
                String nombreDept = reader.readLine();

                Departamento departamento = GestorDepartamento.buscarDepartamentoPorNombre(nombreDept);
                if (departamento == null) {
                    System.out.println("Error: Departamento no encontrado.");
                    return;
                }

                System.out.print("Asunto del ticket: ");
                String asunto = reader.readLine();

                System.out.print("Descripción del problema: ");
                String descripcion = reader.readLine();

                if (datosCompletos(asunto, descripcion)) {
                    ok = true;
                    System.out.println(GestorTicket.agregarTicket(asunto, descripcion, departamento, usuario));
                } else {
                    System.out.println("Faltan datos obligatorios. Intente de nuevo.");
                }
            } while (!ok);
        } catch (IOException e) {
            System.out.println("Error al leer la entrada: " + e.getMessage());
        }
    }

    public static void listarUsuarios() throws Exception {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        System.out.println(GestorUsuario.listarUsuarios());
    }

    public static void listarDepartamentos() throws Exception {
        System.out.println("\n--- LISTA DE DEPARTAMENTOS ---");
        System.out.println(GestorDepartamento.listarDepartamentos());
    }

    public static void listarTickets() throws Exception {
        System.out.println("\n--- LISTA DE TICKETS ---");
        System.out.println(GestorTicket.listarTickets());
    }

    public static void cambiarEstadoTicket() throws Exception {
        System.out.println("\n--- CAMBIAR ESTADO DE TICKET ---");

        // Listar tickets
        System.out.println("Tickets registrados:");
        System.out.println(GestorTicket.listarTickets());

        System.out.print("Ingrese el ID del ticket: ");
        try {
            int ticketId = Integer.parseInt(reader.readLine());

            System.out.print("Nuevo estado (Nuevo/En progreso/Resuelto): ");
            String nuevoEstado = reader.readLine();

            // Validar estado
            if (!nuevoEstado.equalsIgnoreCase("Nuevo") &&
                    !nuevoEstado.equalsIgnoreCase("En progreso") &&
                    !nuevoEstado.equalsIgnoreCase("Resuelto")) {
                System.out.println("Error: Estado no válido. Use: Nuevo, En progreso o Resuelto");
                return;
            }

            // Cambiar estado
            System.out.println(DAOTicket.cambiarEstado(ticketId, nuevoEstado));

        } catch (NumberFormatException e) {
            System.out.println("Error: ID debe ser un número válido.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void gestionarDiccionarios() throws Exception {
        byte opcion = -1;
        do {
            System.out.println("\n=== GESTIÓN DE DICCIONARIOS BOW ===");
            System.out.println("┌─ DICCIONARIO TÉCNICO ");
            System.out.println("│ 1.  Agregar palabra técnica");
            System.out.println("│ 2.  Buscar palabra técnica");
            System.out.println("│ 3.  Listar palabras técnicas");
            System.out.println("│ 4.  Actualizar palabra técnica");
            System.out.println("│ 5.  Eliminar palabra técnica");
            System.out.println("├─ DICCIONARIO EMOCIONAL ");
            System.out.println("│ 6.  Agregar palabra emocional");
            System.out.println("│ 7.  Buscar palabra emocional");
            System.out.println("│ 8.  Listar palabras emocionales");
            System.out.println("│ 9.  Actualizar palabra emocional");
            System.out.println("│ 10. Eliminar palabra emocional");
            System.out.println("├─ ESTADÍSTICAS ");
            System.out.println("│ 11. Estadísticas de diccionarios");
            System.out.println("└─ SALIR ");
            System.out.println("│ 12. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Byte.parseByte(reader.readLine());

                switch (opcion) {
                    case 1: // Agregar palabra técnica
                        System.out.print("Palabra: ");
                        String palabraTec = reader.readLine();
                        System.out.print("Categoría: ");
                        String categoria = reader.readLine();
                        System.out.println(GestorDiccionario.agregarPalabraTecnica(palabraTec, categoria));
                        break;

                    case 2: // Buscar palabra técnica
                        System.out.print("Palabra a buscar: ");
                        String palabraBuscarTec = reader.readLine();
                        System.out.println(GestorDiccionario.buscarPalabraTecnica(palabraBuscarTec));
                        break;

                    case 3: // Listar palabras técnicas
                        System.out.println(GestorDiccionario.listarPalabrasTecnicas());
                        break;

                    case 4: // Actualizar palabra técnica
                        System.out.print("Palabra a actualizar: ");
                        String palabraActualizarTec = reader.readLine();
                        System.out.print("Nueva categoría: ");
                        String nuevaCategoria = reader.readLine();
                        System.out.println(GestorDiccionario.actualizarPalabraTecnica(palabraActualizarTec, nuevaCategoria));
                        break;

                    case 5: // Eliminar palabra técnica
                        System.out.print("Palabra a eliminar: ");
                        String palabraEliminarTec = reader.readLine();
                        System.out.print("¿Está seguro que desea eliminar '" + palabraEliminarTec + "'? (s/n): ");
                        String confirmacion = reader.readLine();
                        if (confirmacion.equalsIgnoreCase("s")) {
                            System.out.println(GestorDiccionario.eliminarPalabraTecnica(palabraEliminarTec));
                        } else {
                            System.out.println("Operación cancelada.");
                        }
                        break;

                    case 6: // Agregar palabra emocional
                        System.out.print("Palabra: ");
                        String palabraEmo = reader.readLine();
                        System.out.print("Emoción: ");
                        String emocion = reader.readLine();
                        System.out.println(GestorDiccionario.agregarPalabraEmocional(palabraEmo, emocion));
                        break;

                    case 7: // Buscar palabra emocional
                        System.out.print("Palabra a buscar: ");
                        String palabraBuscarEmo = reader.readLine();
                        System.out.println(GestorDiccionario.buscarPalabraEmocional(palabraBuscarEmo));
                        break;

                    case 8: // Listar palabras emocionales
                        System.out.println(GestorDiccionario.listarPalabrasEmocionales());
                        break;

                    case 9: // Actualizar palabra emocional
                        System.out.print("Palabra a actualizar: ");
                        String palabraActualizarEmo = reader.readLine();
                        System.out.print("Nueva emoción: ");
                        String nuevaEmocion = reader.readLine();
                        System.out.println(GestorDiccionario.actualizarPalabraEmocional(palabraActualizarEmo, nuevaEmocion));
                        break;

                    case 10: // Eliminar palabra emocional
                        System.out.print("Palabra a eliminar: ");
                        String palabraEliminarEmo = reader.readLine();
                        System.out.print("¿Está seguro que desea eliminar '" + palabraEliminarEmo + "'? (s/n): ");
                        String confirmacion2 = reader.readLine();
                        if (confirmacion2.equalsIgnoreCase("s")) {
                            System.out.println(GestorDiccionario.eliminarPalabraEmocional(palabraEliminarEmo));
                        } else {
                            System.out.println("Operación cancelada.");
                        }
                        break;

                    case 11: // Estadísticas
                        System.out.println(GestorDiccionario.obtenerEstadisticas());
                        break;

                    case 12: // Volver
                        System.out.println("Volviendo al menú principal...");
                        break;

                    default:
                        System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 12);
    }

    public static void analizarTicketBoW() throws Exception {
        System.out.println("\n--- ANÁLISIS BAG OF WORDS ---");

        // Listar tickets disponibles
        System.out.println("Tickets registrados:");
        System.out.println(GestorTicket.listarTickets());

        System.out.print("Ingrese el ID del ticket a analizar: ");
        try {
            int ticketId = Integer.parseInt(reader.readLine());

            Ticket ticket = GestorTicket.buscarTicketPorId(ticketId);
            if (ticket == null) {
                System.out.println("Error: Ticket no encontrado.");
                return;
            }

            System.out.println("\n Ticket seleccionado:");
            System.out.println("Asunto: " + ticket.getAsunto());
            System.out.println("Descripción: " + ticket.getDescripcion());

            // Realizar análisis
            AnalizadorBoW.ResultadoAnalisis resultado = analizadorBoW.analizarTicket(ticket);

            // Mostrar resultados
            System.out.println("\n RESULTADOS DEL ANÁLISIS:");

            System.out.println("\n DETECCIÓN DE EMOCIONES:");
            if (resultado.getEmociones().isEmpty()) {
                System.out.println("No se detectaron emociones específicas");
            } else {
                resultado.getEmociones().entrySet().stream()
                        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                        .forEach(entry ->
                                System.out.println("• " + entry.getKey() + ": " + entry.getValue() + " puntos")
                        );
                System.out.println("Emoción principal: " + resultado.getEmocionPrincipal());
                System.out.println("Palabras detonantes: " + String.join(", ", resultado.getPalabrasEmocionales()));
            }

            System.out.println("\n🔧 CLASIFICACIÓN TÉCNICA:");
            if (resultado.getCategorias().isEmpty()) {
                System.out.println("No se pudo determinar una categoría técnica");
            } else {
                resultado.getCategorias().entrySet().stream()
                        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                        .forEach(entry ->
                                System.out.println("• " + entry.getKey() + ": " + entry.getValue() + " puntos")
                        );
                System.out.println("Categoría sugerida: " + resultado.getCategoriaPrincipal());
                System.out.println("Palabras detonantes: " + String.join(", ", resultado.getPalabrasTecnicas()));
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: ID debe ser un número válido.");
        } catch (Exception e) {
            System.out.println("Error durante el análisis: " + e.getMessage());
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
}
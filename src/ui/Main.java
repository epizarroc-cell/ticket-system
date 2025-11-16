package ui;

import modelo.*;
import dao.*;
import controlador.*;
import vista.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Iniciando Sistema de Tickets Universitario - MVC + DAO");

        // Inicializar componentes del sistema
        Scanner scanner = new Scanner(System.in);

        // Inicializar controladores
        UsuarioController usuarioController = new UsuarioController();
        DepartamentoController departamentoController = new DepartamentoController();

        // Inicializar analizador BoW con diccionarios
        DiccionarioTecnico dicTecnico = new DiccionarioTecnico();
        DiccionarioEmocional dicEmocional = new DiccionarioEmocional();
        AnalizadorBoW analizadorBoW = new AnalizadorBoW(dicTecnico, dicEmocional);

        TicketController ticketController = new TicketController(analizadorBoW);
        DiccionarioController diccionarioController = new DiccionarioController();

        // Inicializar vistas
        UsuarioVista usuarioVista = new UsuarioVista(usuarioController, scanner);
        DepartamentoVista departamentoVista = new DepartamentoVista(departamentoController, scanner);
        TicketVista ticketVista = new TicketVista(ticketController, usuarioController, departamentoController, scanner);
        DiccionarioVista diccionarioVista = new DiccionarioVista(diccionarioController, scanner);

        // Mostrar menú principal
        mostrarMenuPrincipal(usuarioVista, departamentoVista, ticketVista, diccionarioVista, scanner);

        scanner.close();
    }

    private static void mostrarMenuPrincipal(UsuarioVista usuarioVista,
                                             DepartamentoVista departamentoVista,
                                             TicketVista ticketVista,
                                             DiccionarioVista diccionarioVista,
                                             Scanner scanner) {
        int opcion = 0;
        do {
            try {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("🎯 SISTEMA DE TICKETS UNIVERSITARIO - ARQUITECTURA MVC + DAO");
                System.out.println("=".repeat(60));
                System.out.println("1. 👥  Gestión de Usuarios");
                System.out.println("2. 🏢  Gestión de Departamentos");
                System.out.println("3. 🎫  Gestión de Tickets");
                System.out.println("4. 📚  Gestión de Diccionarios BoW");
                System.out.println("5. 🔍  Análisis de Tickets (Bag of Words)");
                System.out.println("6. 📊  Reportes y Estadísticas");
                System.out.println("7. 🚪  Salir del Sistema");
                System.out.println("=".repeat(60));
                System.out.print("Seleccione una opción: ");

                String input = scanner.nextLine();
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        usuarioVista.mostrarMenuUsuarios();
                        break;
                    case 2:
                        departamentoVista.mostrarMenuDepartamentos();
                        break;
                    case 3:
                        ticketVista.mostrarMenuTickets();
                        break;
                    case 4:
                        diccionarioVista.mostrarMenuDiccionarios();
                        break;
                    case 5:
                        ticketVista.mostrarAnalisisBoW();
                        break;
                    case 6:
                        mostrarReportes(usuarioVista, departamentoVista, ticketVista, diccionarioVista);
                        break;
                    case 7:
                        System.out.println("\n👋 ¡Gracias por usar el Sistema de Tickets Universitario!");
                        System.out.println("¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("❌ Opción inválida. Por favor, seleccione una opción del 1 al 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Por favor ingrese un número válido (1-7)");
            } catch (Exception e) {
                System.out.println("❌ Error inesperado: " + e.getMessage());
                e.printStackTrace();
            }
        } while (opcion != 7);
    }

    private static void mostrarReportes(UsuarioVista usuarioVista,
                                        DepartamentoVista departamentoVista,
                                        TicketVista ticketVista,
                                        DiccionarioVista diccionarioVista) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📊 REPORTES Y ESTADÍSTICAS DEL SISTEMA");
        System.out.println("=".repeat(50));

        // Mostrar estadísticas de cada módulo
        usuarioVista.mostrarEstadisticas();
        departamentoVista.mostrarEstadisticas();
        ticketVista.mostrarEstadisticas();
        diccionarioVista.mostrarEstadisticas();

        System.out.println("\nPresione Enter para continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            // Continuar sin esperar entrada
        }
    }
}
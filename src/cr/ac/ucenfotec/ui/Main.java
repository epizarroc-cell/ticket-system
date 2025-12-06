package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.logic.GestorUsuario;
import cr.ac.ucenfotec.bl.logic.GestorDepartamento;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Crear usuarios y departamentos por defecto
        inicializarDatosPorDefecto();

        SwingUtilities.invokeLater(() -> {
            try {
                new LoginWindow().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage());
            }
        });
    }

    private static void inicializarDatosPorDefecto() {
        try {
            // Crear administrador por defecto
            if (GestorUsuario.getListaUsuarios().isEmpty()) {
                GestorUsuario.agregarUsuario(
                        "Administrador Principal",
                        "admin@ucenfotec.ac.cr",
                        "admin123",  // Se hasheará automáticamente
                        "8888-8888",
                        "administrador"
                );

                // Crear algunos usuarios de prueba
                GestorUsuario.agregarUsuario(
                        "Juan Perez",
                        "juan.perez@ucenfotec.ac.cr",
                        "estudiante123",
                        "7777-7777",
                        "estudiante"
                );

                GestorUsuario.agregarUsuario(
                        "Maria Rodriguez",
                        "maria.rodriguez@ucenfotec.ac.cr",
                        "funcionario123",
                        "6666-6666",
                        "funcionario"
                );
            }

            // Crear departamentos por defecto
            if (GestorDepartamento.getListaDepartamentos().isEmpty()) {
                GestorDepartamento.agregarDepartamento(
                        "Soporte Técnico",
                        "Solución de problemas informáticos y técnicos",
                        "soporte@ucenfotec.ac.cr"
                );

                GestorDepartamento.agregarDepartamento(
                        "Biblioteca",
                        "Recursos bibliográficos y consultas",
                        "biblioteca@ucenfotec.ac.cr"
                );

                GestorDepartamento.agregarDepartamento(
                        "Registro",
                        "Trámites académicos y certificaciones",
                        "registro@ucenfotec.ac.cr"
                );
            }

        } catch (Exception e) {
            System.err.println("Error al inicializar datos por defecto: " + e.getMessage());
        }
    }
}
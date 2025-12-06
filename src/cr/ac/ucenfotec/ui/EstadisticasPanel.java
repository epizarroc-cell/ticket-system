package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.logic.GestorUsuario;
import cr.ac.ucenfotec.bl.logic.GestorDepartamento;
import cr.ac.ucenfotec.bl.logic.GestorTicket;
import cr.ac.ucenfotec.bl.logic.GestorDiccionario;

import javax.swing.*;
import java.awt.*;

public class EstadisticasPanel extends JPanel {
    private JTextArea txtEstadisticas;

    public EstadisticasPanel() {
        initComponents();
        cargarEstadisticas();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior con botón de actualizar
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnActualizar = new JButton("Actualizar Estadísticas");
        btnActualizar.addActionListener(e -> cargarEstadisticas());
        topPanel.add(btnActualizar);

        // Área de texto para mostrar estadísticas
        txtEstadisticas = new JTextArea(20, 60);
        txtEstadisticas.setEditable(false);
        txtEstadisticas.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtEstadisticas);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void cargarEstadisticas() {
        try {
            StringBuilder stats = new StringBuilder();
            stats.append("=== ESTADÍSTICAS DEL SISTEMA ===\n\n");

            // Estadísticas de usuarios
            stats.append("👥 USUARIOS:\n");
            stats.append("--------------\n");
            String usuarios = GestorUsuario.listarUsuarios();
            int countUsuarios = usuarios.split("\n").length;
            stats.append("Total registrados: " + countUsuarios + "\n\n");

            // Estadísticas de departamentos
            stats.append("🏢 DEPARTAMENTOS:\n");
            stats.append("------------------\n");
            String departamentos = GestorDepartamento.listarDepartamentos();
            int countDept = departamentos.split("\n").length;
            stats.append("Total registrados: " + countDept + "\n\n");

            // Estadísticas de tickets
            stats.append("🎫 TICKETS:\n");
            stats.append("-----------\n");
            String tickets = GestorTicket.listarTickets();
            int countTickets = tickets.split("\n").length;
            stats.append("Total creados: " + countTickets + "\n\n");

            // Estadísticas de diccionarios
            stats.append("📚 DICCIONARIOS BOW:\n");
            stats.append("--------------------\n");
            try {
                String dictStats = GestorDiccionario.obtenerEstadisticas();
                stats.append(dictStats);
            } catch (Exception e) {
                stats.append("No disponible en este momento\n");
            }

            txtEstadisticas.setText(stats.toString());
        } catch (Exception e) {
            txtEstadisticas.setText("Error al cargar estadísticas: " + e.getMessage());
        }
    }
}
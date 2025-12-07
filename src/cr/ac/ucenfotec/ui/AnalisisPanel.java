package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.Analizador.AnalizadorBoW;
import cr.ac.ucenfotec.bl.logic.GestorTicket;
import cr.ac.ucenfotec.bl.entities.Ticket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AnalisisPanel extends JPanel {
    private JTextArea txtResultado;
    private JComboBox<Ticket> cmbTickets;
    private AnalizadorBoW analizadorBoW;

    public AnalisisPanel() {
        initComponents();
        cargarTickets();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder("Seleccionar Ticket para Análisis"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        topPanel.add(new JLabel("Ticket:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        cmbTickets = new JComboBox<>();
        cmbTickets.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Ticket) {
                    Ticket t = (Ticket) value;
                    setText("Ticket #" + t.getId() + " - " + t.getAsunto());
                }
                return this;
            }
        });
        topPanel.add(cmbTickets, gbc);

        try {
            analizadorBoW = new AnalizadorBoW();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al inicializar el AnalizadorBoW: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 0;
        JButton btnAnalizar = new JButton("Analizar");
        btnAnalizar.addActionListener(e -> analizarTicket());
        topPanel.add(btnAnalizar, gbc);

        gbc.gridx = 3; gbc.gridy = 0;
        JButton btnActualizar = new JButton("Actualizar Lista");
        btnActualizar.addActionListener(e -> cargarTickets());
        topPanel.add(btnActualizar, gbc);

        // Área de resultados
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("Resultados del Análisis"));

        txtResultado = new JTextArea(15, 50);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
    }

    private void cargarTickets() {
        try {
            cmbTickets.removeAllItems();
            java.util.List<Ticket> tickets = GestorTicket.getListaTickets();
            for (Ticket ticket : tickets) {
                cmbTickets.addItem(ticket);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tickets: " + e.getMessage());
        }
    }

    private void analizarTicket() {
        Ticket ticket = (Ticket) cmbTickets.getSelectedItem();
        if (ticket == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un ticket para analizar");
            return;
        }

        try {
            txtResultado.setText("=== ANÁLISIS BAG OF WORDS ===\n\n");
            txtResultado.append("Ticket #" + ticket.getId() + ": " + ticket.getAsunto() + "\n");
            txtResultado.append("Fecha: " + ticket.getFechaCreacion() + "\n");
            txtResultado.append("Usuario: " + ticket.getUsuario().getNombreCompleto() + "\n");
            txtResultado.append("Departamento: " + ticket.getDepartamento().getNombre() + "\n\n");

            txtResultado.append("=== DESCRIPCIÓN ORIGINAL ===\n");
            txtResultado.append(ticket.getDescripcion() + "\n\n");

            // Verificar si el analizador está inicializado
            if (analizadorBoW == null) {
                try {
                    analizadorBoW = new AnalizadorBoW();
                } catch (Exception e) {
                    txtResultado.append("=== ERROR ===\n");
                    txtResultado.append("No se pudo inicializar el AnalizadorBoW: " + e.getMessage() + "\n");
                    return;
                }
            }

            // Ejecutar análisis
            AnalizadorBoW.ResultadoAnalisis resultado = analizadorBoW.analizarTicket(ticket);

            txtResultado.append("=== RESULTADOS DEL ANÁLISIS ===\n\n");

            // 1. DETECCIÓN DE EMOCIONES
            txtResultado.append("DETECCIÓN DE EMOCIONES:\n");
            if (resultado.getEmociones().isEmpty()) {
                txtResultado.append("  • No se detectaron emociones específicas\n");
            } else {
                // Ordenar por puntos descendentes
                resultado.getEmociones().entrySet().stream()
                        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                        .forEach(entry -> {
                            txtResultado.append("  • " + entry.getKey() + ": " + entry.getValue() + " puntos\n");
                        });
                txtResultado.append("  Emoción principal: " + resultado.getEmocionPrincipal() + "\n");

                List<String> palabrasEmocionales = resultado.getPalabrasEmocionales();
                if (!palabrasEmocionales.isEmpty()) {
                    txtResultado.append("  Palabras detonantes: " + String.join(", ", palabrasEmocionales) + "\n");
                }
            }
            txtResultado.append("\n");

            // 2. CLASIFICACIÓN TÉCNICA
            txtResultado.append("CLASIFICACIÓN TÉCNICA:\n");
            if (resultado.getCategorias().isEmpty()) {
                txtResultado.append("  • No se pudo determinar una categoría técnica\n");
            } else {
                // Ordenar por puntos descendentes
                resultado.getCategorias().entrySet().stream()
                        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                        .forEach(entry -> {
                            txtResultado.append("  • " + entry.getKey() + ": " + entry.getValue() + " puntos\n");
                        });
                txtResultado.append("  Categoría sugerida: " + resultado.getCategoriaPrincipal() + "\n");

                List<String> palabrasTecnicas = resultado.getPalabrasTecnicas();
                if (!palabrasTecnicas.isEmpty()) {
                    txtResultado.append("  Palabras clave: " + String.join(", ", palabrasTecnicas) + "\n");
                }
            }

            // 3. RESUMEN DEL ANÁLISIS
            txtResultado.append("\n=== RESUMEN DEL ANÁLISIS ===\n");
            txtResultado.append("Total emociones detectadas: " + resultado.getEmociones().size() + "\n");
            txtResultado.append("Total categorías técnicas: " + resultado.getCategorias().size() + "\n");
            txtResultado.append("Total palabras analizadas: " +
                    (resultado.getPalabrasEmocionales().size() + resultado.getPalabrasTecnicas().size()) + "\n");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en análisis: " + e.getMessage());
        }
    }
}
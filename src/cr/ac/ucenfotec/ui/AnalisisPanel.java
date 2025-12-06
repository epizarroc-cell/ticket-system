package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.logic.GestorTicket;
import cr.ac.ucenfotec.bl.entities.Ticket.Ticket;

import javax.swing.*;
import java.awt.*;

public class AnalisisPanel extends JPanel {
    private JTextArea txtResultado;
    private JComboBox<Ticket> cmbTickets;

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
            txtResultado.append("Usuario: " + ticket.getUsuario().getNombreCompleto() + "\n");  // CORREGIDO
            txtResultado.append("Departamento: " + ticket.getDepartamento().getNombre() + "\n\n");

            txtResultado.append("=== DESCRIPCIÓN ORIGINAL ===\n");
            txtResultado.append(ticket.getDescripcion() + "\n\n");

            // Aquí integrarías el AnalizadorBoW real
            txtResultado.append("=== RESULTADOS DEL ANÁLISIS ===\n");
            txtResultado.append("Esta funcionalidad requiere la implementación completa del AnalizadorBoW\n");
            txtResultado.append("Se analizarían:\n");
            txtResultado.append("1. Frecuencia de palabras\n");
            txtResultado.append("2. Detección de emociones\n");
            txtResultado.append("3. Categorización técnica\n");
            txtResultado.append("4. Palabras clave\n");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en análisis: " + e.getMessage());
        }
    }
}
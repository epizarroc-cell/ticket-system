package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.logic.GestorTicket;
import cr.ac.ucenfotec.bl.logic.GestorUsuario;
import cr.ac.ucenfotec.bl.logic.GestorDepartamento;
import cr.ac.ucenfotec.bl.entities.Usuario.Usuario;
import cr.ac.ucenfotec.bl.entities.Departamento.Departamento;
import cr.ac.ucenfotec.bl.entities.Ticket.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TicketPanel extends JPanel {
    private Usuario usuarioActual;
    private JTable tableTickets;
    private DefaultTableModel tableModel;
    private JTextField txtAsunto;
    private JTextArea txtDescripcion;
    private JComboBox<String> cmbDepartamentos;

    public TicketPanel(Usuario usuario) {
        this.usuarioActual = usuario;
        initComponents();
        cargarTickets();
        cargarDepartamentos();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior para creación de tickets
        JPanel createPanel = new JPanel(new GridBagLayout());
        createPanel.setBorder(BorderFactory.createTitledBorder("Nuevo Ticket"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Asunto
        gbc.gridx = 0; gbc.gridy = 0;
        createPanel.add(new JLabel("Asunto:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 1.0;
        txtAsunto = new JTextField(30);
        createPanel.add(txtAsunto, gbc);

        // Departamento
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; gbc.weightx = 0;
        createPanel.add(new JLabel("Departamento:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2;
        cmbDepartamentos = new JComboBox<>();
        createPanel.add(cmbDepartamentos, gbc);

        // Descripción
        gbc.gridx = 0; gbc.gridy = 2;
        createPanel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2; gbc.gridheight = 3;
        txtDescripcion = new JTextArea(5, 30);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        createPanel.add(scrollDesc, gbc);

        // Botón crear ticket
        gbc.gridx = 1; gbc.gridy = 5; gbc.gridheight = 1; gbc.anchor = GridBagConstraints.EAST;
        JButton btnCrear = new JButton("Crear Ticket");
        btnCrear.addActionListener(e -> crearTicket());
        createPanel.add(btnCrear, gbc);

        // Panel de tickets existentes
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Tickets Registrados"));

        tableModel = new DefaultTableModel(new String[]{
                "ID", "Asunto", "Departamento", "Usuario", "Estado", "Fecha"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableTickets = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableTickets);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel de acciones
        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnCambiarEstado = new JButton("Cambiar Estado");
        JButton btnAnalizar = new JButton("Analizar Ticket");

        btnActualizar.addActionListener(e -> cargarTickets());
        btnCambiarEstado.addActionListener(e -> cambiarEstado());
        btnAnalizar.addActionListener(e -> analizarTicket());

        actionPanel.add(btnActualizar);
        actionPanel.add(btnCambiarEstado);
        actionPanel.add(btnAnalizar);
        listPanel.add(actionPanel, BorderLayout.SOUTH);

        // Layout principal
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, createPanel, listPanel);
        splitPane.setDividerLocation(200);
        add(splitPane, BorderLayout.CENTER);
    }

    private void cargarDepartamentos() {
        try {
            cmbDepartamentos.removeAllItems();
            List<Departamento> departamentos = GestorDepartamento.getListaDepartamentos();
            for (Departamento dept : departamentos) {
                cmbDepartamentos.addItem(dept.getNombre());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar departamentos: " + e.getMessage());
        }
    }

    private void cargarTickets() {
        try {
            tableModel.setRowCount(0);
            List<Ticket> tickets = GestorTicket.getListaTickets();
            for (Ticket ticket : tickets) {
                tableModel.addRow(new Object[]{
                        ticket.getId(),
                        ticket.getAsunto(),
                        ticket.getDepartamento().getNombre(),
                        ticket.getUsuario().getNombreCompleto(),  // CORREGIDO: getNombreCompleto()
                        ticket.getEstado(),
                        ticket.getFechaCreacion()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tickets: " + e.getMessage());
        }
    }

    private void crearTicket() {
        try {
            String asunto = txtAsunto.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            String deptNombre = (String) cmbDepartamentos.getSelectedItem();

            if (asunto.isEmpty() || descripcion.isEmpty() || deptNombre == null) {
                JOptionPane.showMessageDialog(this, "Todos los campos son requeridos");
                return;
            }

            Departamento departamento = GestorDepartamento.buscarDepartamentoPorNombre(deptNombre);
            if (departamento == null) {
                JOptionPane.showMessageDialog(this, "Departamento no encontrado");
                return;
            }

            String resultado = GestorTicket.agregarTicket(asunto, descripcion, departamento, usuarioActual);
            JOptionPane.showMessageDialog(this, resultado);

            txtAsunto.setText("");
            txtDescripcion.setText("");
            cargarTickets();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void cambiarEstado() {
        int selectedRow = tableTickets.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un ticket primero");
            return;
        }

        int ticketId = (int) tableModel.getValueAt(selectedRow, 0);
        String[] estados = {"Nuevo", "En progreso", "Resuelto"};
        String nuevoEstado = (String) JOptionPane.showInputDialog(
                this, "Seleccione nuevo estado:", "Cambiar Estado",
                JOptionPane.QUESTION_MESSAGE, null, estados, estados[0]);

        if (nuevoEstado != null) {
            try {
                // Usar DAOTicket.cambiarEstado
                cr.ac.ucenfotec.bl.entities.Ticket.DAOTicket.cambiarEstado(ticketId, nuevoEstado);
                JOptionPane.showMessageDialog(this, "Estado actualizado exitosamente");
                cargarTickets();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void analizarTicket() {
        int selectedRow = tableTickets.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un ticket primero");
            return;
        }

        int ticketId = (int) tableModel.getValueAt(selectedRow, 0);
        try {
            Ticket ticket = GestorTicket.buscarTicketPorId(ticketId);
            if (ticket == null) {
                JOptionPane.showMessageDialog(this, "Ticket no encontrado");
                return;
            }

            // Aquí llamarías al analizador BoW
            JOptionPane.showMessageDialog(this,
                    "Análisis del ticket #" + ticketId + " en proceso...\n" +
                            "Asunto: " + ticket.getAsunto() + "\n" +
                            "Descripción: " + ticket.getDescripcion(),
                    "Análisis BoW",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en análisis: " + e.getMessage());
        }
    }
}
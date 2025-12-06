package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.logic.GestorDepartamento;
import cr.ac.ucenfotec.bl.entities.Departamento.Departamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DepartamentoPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTextField txtNombre, txtDescripcion, txtContacto;

    public DepartamentoPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new java.awt.BorderLayout());
        add(new JLabel("Panel de Departamentos - En construcción"), BorderLayout.CENTER);

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Registrar Departamento"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        txtNombre = new JTextField(25);
        formPanel.add(txtNombre, gbc);

        // Descripción
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        formPanel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2;
        txtDescripcion = new JTextField(25);
        formPanel.add(txtDescripcion, gbc);

        // Contacto
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Contacto:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2;
        txtContacto = new JTextField(25);
        formPanel.add(txtContacto, gbc);

        // Botones
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 3;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Guardar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnGuardar.addActionListener(e -> guardarDepartamento());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnLimpiar);
        formPanel.add(buttonPanel, gbc);

        // Panel de tabla
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Departamentos Registrados"));

        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Descripción", "Contacto"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JButton btnActualizar = new JButton("Actualizar Lista");
        btnActualizar.addActionListener(e -> cargarDepartamentos());
        tablePanel.add(btnActualizar, BorderLayout.SOUTH);

        // Dividir pantalla
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tablePanel);
        splitPane.setDividerLocation(350);

        add(splitPane, BorderLayout.CENTER);
    }

    private void cargarDepartamentos() {
        try {
            tableModel.setRowCount(0);
            List<Departamento> departamentos = GestorDepartamento.getListaDepartamentos();
            for (Departamento dept : departamentos) {
                tableModel.addRow(new Object[]{
                        dept.getId(),
                        dept.getNombre(),
                        dept.getDescripcion(),
                        dept.getContacto()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar departamentos: " + e.getMessage());
        }
    }

    private void guardarDepartamento() {
        try {
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            String contacto = txtContacto.getText().trim();

            if (nombre.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y descripción son requeridos");
                return;
            }

            String resultado = GestorDepartamento.agregarDepartamento(nombre, descripcion, contacto);
            JOptionPane.showMessageDialog(this, resultado);
            limpiarFormulario();
            cargarDepartamentos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtContacto.setText("");
    }
}
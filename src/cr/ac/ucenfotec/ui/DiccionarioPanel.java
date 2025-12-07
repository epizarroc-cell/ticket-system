package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.logic.GestorDiccionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class DiccionarioPanel extends JPanel {
    private JTabbedPane tabbedPane;
    private DefaultTableModel modelTecnico, modelEmocional;

    public DiccionarioPanel() {
        initComponents();
        cargarDiccionarios();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tabbedPane = new JTabbedPane();

        // Pestaña Diccionario Técnico
        JPanel panelTecnico = crearPanelDiccionarioTecnico();
        tabbedPane.addTab("Técnico", panelTecnico);

        // Pestaña Diccionario Emocional
        JPanel panelEmocional = crearPanelDiccionarioEmocional();
        tabbedPane.addTab("Emocional", panelEmocional);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel crearPanelDiccionarioTecnico() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Agregar Palabra Técnica"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtPalabra = new JTextField(20);
        JTextField txtCategoria = new JTextField(20);
        JButton btnAgregar = new JButton("Agregar");

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Palabra:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(txtPalabra, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(txtCategoria, gbc);

        gbc.gridx = 1; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        btnAgregar.addActionListener(e -> {
            try {
                String resultado = GestorDiccionario.agregarPalabraTecnica(
                        txtPalabra.getText().trim(),
                        txtCategoria.getText().trim()
                );
                JOptionPane.showMessageDialog(this, resultado);
                txtPalabra.setText("");
                txtCategoria.setText("");
                cargarDiccionarios();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
        formPanel.add(btnAgregar, gbc);

        // Tabla
        modelTecnico = new DefaultTableModel(new String[]{"Palabra", "Categoría"}, 0);
        JTable table = new JTable(modelTecnico);
        JScrollPane scrollPane = new JScrollPane(table);

        // Botones de acción
        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton btnEliminar = new JButton("Eliminar Seleccionada");
        JButton btnActualizar = new JButton("Actualizar");

        btnEliminar.addActionListener(e -> eliminarPalabraTecnica(table));
        btnActualizar.addActionListener(e -> cargarDiccionarios());

        actionPanel.add(btnEliminar);
        actionPanel.add(btnActualizar);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelDiccionarioEmocional() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Similar al panel técnico pero para palabras emocionales
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Agregar Palabra Emocional"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtPalabra = new JTextField(20);
        JTextField txtEmocion = new JTextField(20);
        JButton btnAgregar = new JButton("Agregar");

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Palabra:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(txtPalabra, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Emoción:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(txtEmocion, gbc);

        gbc.gridx = 1; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        btnAgregar.addActionListener(e -> {
            try {
                String resultado = GestorDiccionario.agregarPalabraEmocional(
                        txtPalabra.getText().trim(),
                        txtEmocion.getText().trim()
                );
                JOptionPane.showMessageDialog(this, resultado);
                txtPalabra.setText("");
                txtEmocion.setText("");
                cargarDiccionarios();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
        formPanel.add(btnAgregar, gbc);

        modelEmocional = new DefaultTableModel(new String[]{"Palabra", "Emoción"}, 0);
        JTable table = new JTable(modelEmocional);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton btnEliminar = new JButton("Eliminar Seleccionada");
        JButton btnActualizar = new JButton("Actualizar");

        btnEliminar.addActionListener(e -> eliminarPalabraEmocional(table));
        btnActualizar.addActionListener(e -> cargarDiccionarios());

        actionPanel.add(btnEliminar);
        actionPanel.add(btnActualizar);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

//    private void cargarDiccionarios() {
//        try {
//            // Cargar palabras técnicas
//            modelTecnico.setRowCount(0);
//            String listaTecnica = GestorDiccionario.listarPalabrasTecnicas();
//            String[] lineas = listaTecnica.split("\n");
//            for (String linea : lineas) {
//                if (linea.contains(":")) {
//                    String[] partes = linea.split(":");
//                    if (partes.length >= 2) {
//                        modelTecnico.addRow(new String[]{partes[0].trim(), partes[1].trim()});
//                    }
//                }
//            }
//
//            // Cargar palabras emocionales
//            modelEmocional.setRowCount(0);
//            String listaEmocional = GestorDiccionario.listarPalabrasEmocionales();
//            lineas = listaEmocional.split("\n");
//            for (String linea : lineas) {
//                if (linea.contains(":")) {
//                    String[] partes = linea.split(":");
//                    if (partes.length >= 2) {
//                        modelEmocional.addRow(new String[]{partes[0].trim(), partes[1].trim()});
//                    }
//                }
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error al cargar diccionarios: " + e.getMessage());
//        }
//    }

    private void cargarDiccionarios() {
        try {

            modelTecnico.setRowCount(0);
            List<String[]> palabrasTecnicas = GestorDiccionario.listarPalabrasTecnicasUI();

            if (palabrasTecnicas.isEmpty()) {
                // Agregar una fila indicando que no hay datos
                modelTecnico.addRow(new String[]{"No hay palabras técnicas", ""});
            } else {
                for (String[] palabra : palabrasTecnicas) {
                    modelTecnico.addRow(palabra);
                }
            }


            modelEmocional.setRowCount(0);
            List<String[]> palabrasEmocionales = GestorDiccionario.listarPalabrasEmocionalesUI();

            if (palabrasEmocionales.isEmpty()) {
                // Agregar una fila indicando que no hay datos
                modelEmocional.addRow(new String[]{"No hay palabras emocionales", ""});
            } else {
                for (String[] palabra : palabrasEmocionales) {
                    modelEmocional.addRow(palabra);
                }
            }



        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar diccionarios: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPalabraTecnica(JTable table) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una palabra para eliminar");
            return;
        }

        String palabra = (String) modelTecnico.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Eliminar la palabra '" + palabra + "'?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String resultado = GestorDiccionario.eliminarPalabraTecnica(palabra);
                JOptionPane.showMessageDialog(this, resultado);
                cargarDiccionarios();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void eliminarPalabraEmocional(JTable table) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una palabra para eliminar");
            return;
        }

        String palabra = (String) modelEmocional.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Eliminar la palabra '" + palabra + "'?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String resultado = GestorDiccionario.eliminarPalabraEmocional(palabra);
                JOptionPane.showMessageDialog(this, resultado);
                cargarDiccionarios();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }
}
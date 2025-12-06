package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.logic.GestorUsuario;
import cr.ac.ucenfotec.bl.entities.Usuario.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UsuarioPanel extends JPanel {
    private JTable tableUsuarios;
    private DefaultTableModel tableModel;
    private JTextField txtNombre, txtCorreo, txtTelefono;
    private JPasswordField txtContrasena;
    private JComboBox<String> cmbRol;

    public UsuarioPanel() {
        initComponents();
        cargarUsuarios();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de formulario (izquierda)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Registrar Usuario"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre completo
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre completo:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        txtNombre = new JTextField(20);
        formPanel.add(txtNombre, gbc);

        // Correo electrónico
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Correo electrónico:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        txtCorreo = new JTextField(20);
        formPanel.add(txtCorreo, gbc);

        // Contraseña
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        txtContrasena = new JPasswordField(20);
        formPanel.add(txtContrasena, gbc);

        // Teléfono contacto
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Teléfono contacto:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        txtTelefono = new JTextField(20);
        formPanel.add(txtTelefono, gbc);

        // Rol
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Rol:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        cmbRol = new JComboBox<>(new String[]{"estudiante", "funcionario", "administrador"});
        formPanel.add(cmbRol, gbc);

        // Botones
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Guardar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnGuardar.addActionListener(e -> guardarUsuario());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnLimpiar);
        formPanel.add(buttonPanel, gbc);

        // Panel de tabla (derecha)
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Usuarios Registrados"));

        tableModel = new DefaultTableModel(new String[]{
                "ID", "Nombre Completo", "Correo Electrónico", "Teléfono Contacto", "Rol"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableUsuarios = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableUsuarios);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Botón de actualizar
        JButton btnActualizar = new JButton("Actualizar Lista");
        btnActualizar.addActionListener(e -> cargarUsuarios());
        tablePanel.add(btnActualizar, BorderLayout.SOUTH);

        // Dividir pantalla
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tablePanel);
        splitPane.setDividerLocation(400);

        add(splitPane, BorderLayout.CENTER);
    }

    private void cargarUsuarios() {
        try {
            tableModel.setRowCount(0);
            List<Usuario> usuarios = GestorUsuario.getListaUsuarios();
            for (Usuario usuario : usuarios) {
                tableModel.addRow(new Object[]{
                        usuario.getId(),
                        usuario.getNombreCompleto(),
                        usuario.getCorreoElectronico(),
                        usuario.getTelefonoContacto(),
                        usuario.getRol()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarUsuario() {
        try {
            String nombre = txtNombre.getText().trim();
            String correo = txtCorreo.getText().trim();
            String contrasena = new String(txtContrasena.getPassword());
            String telefono = txtTelefono.getText().trim();
            String rol = (String) cmbRol.getSelectedItem();

            if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Nombre completo, correo electrónico y contraseña son requeridos",
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String resultado = GestorUsuario.agregarUsuario(nombre, correo, contrasena, telefono, rol);
            JOptionPane.showMessageDialog(this, resultado);
            limpiarFormulario();
            cargarUsuarios();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
        txtTelefono.setText("");
        cmbRol.setSelectedIndex(0);
    }
}
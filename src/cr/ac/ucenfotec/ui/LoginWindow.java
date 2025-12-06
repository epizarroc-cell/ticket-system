package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.logic.GestorUsuario;
import cr.ac.ucenfotec.bl.entities.Usuario.Usuario;
import cr.ac.ucenfotec.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnLogin, btnRegistrar;
    private JComboBox<String> cmbRol;

    public LoginWindow() {
        initComponents();
        setTitle("Sistema de Ticketing - Login");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de título
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Sistema de Ticketing Universitario");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Correo
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Correo:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        txtCorreo = new JTextField(20);
        formPanel.add(txtCorreo, gbc);

        // Contraseña
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        formPanel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2;
        txtContrasena = new JPasswordField(20);
        formPanel.add(txtContrasena, gbc);

        // Rol (para registro)
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Rol (registro):"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2;
        cmbRol = new JComboBox<>(new String[]{"estudiante", "funcionario", "administrador"});
        formPanel.add(cmbRol, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnLogin = new JButton("Iniciar Sesión");
        btnRegistrar = new JButton("Registrar Usuario");

        btnLogin.addActionListener(e -> login());
        btnRegistrar.addActionListener(e -> registrarUsuario());

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegistrar);

        // Ensamblar
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void login() {
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());

        try {
            Usuario usuario = GestorUsuario.buscarUsuarioPorCorreo(correo);
            if (usuario != null && Utils.verificarPassword(contrasena, usuario.getContrasena())) {
                JOptionPane.showMessageDialog(this, "¡Bienvenido " + usuario.getNombreCompleto() + "!");
                new MainWindow(usuario).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarUsuario() {
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());
        String rol = (String) cmbRol.getSelectedItem();

        // Solicitar nombre y teléfono
        String nombre = JOptionPane.showInputDialog(this, "Ingrese su nombre completo:");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre es requerido");
            return;
        }

        String telefono = JOptionPane.showInputDialog(this, "Teléfono (opcional):");

        try {
            String resultado = GestorUsuario.agregarUsuario(nombre, correo, contrasena, telefono, rol);
            JOptionPane.showMessageDialog(this, resultado);
            txtCorreo.setText("");
            txtContrasena.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
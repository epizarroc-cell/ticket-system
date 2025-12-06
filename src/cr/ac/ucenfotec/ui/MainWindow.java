package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.Usuario.Usuario;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private Usuario usuario;
    private JTabbedPane tabbedPane;

    public MainWindow(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
        setTitle("Sistema de Ticketing - Usuario: " + usuario.getNombreCompleto());  // CORREGIDO
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Crear barra de menú
        JMenuBar menuBar = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> System.exit(0));
        menuArchivo.add(itemSalir);

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAcerca = new JMenuItem("Acerca de");
        itemAcerca.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Sistema de Ticketing Universitario\nVersión 1.0",
                        "Acerca de",
                        JOptionPane.INFORMATION_MESSAGE));
        menuAyuda.add(itemAcerca);

        menuBar.add(menuArchivo);
        menuBar.add(menuAyuda);
        setJMenuBar(menuBar);

        // Panel con pestañas
        tabbedPane = new JTabbedPane();

        // Solo administradores pueden gestionar usuarios y departamentos
        if (usuario.getRol().equalsIgnoreCase("administrador")) {
            tabbedPane.addTab("Usuarios", new UsuarioPanel());
            tabbedPane.addTab("Departamentos", new DepartamentoPanel());
        }

        // Todos pueden usar estas pestañas
        tabbedPane.addTab("Tickets", new TicketPanel(usuario));
        tabbedPane.addTab("Diccionarios", new DiccionarioPanel());
        tabbedPane.addTab("Análisis", new AnalisisPanel());
        tabbedPane.addTab("Estadísticas", new EstadisticasPanel());

        // Panel de información del usuario
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.add(new JLabel("Usuario: " + usuario.getNombreCompleto() + " (" + usuario.getRol() + ")"));  // CORREGIDO

        // Layout principal
        setLayout(new BorderLayout());
        add(userPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }
}
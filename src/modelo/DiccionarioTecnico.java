package modelo;

import java.util.Map;

public class DiccionarioTecnico extends DiccionarioBase {

    public DiccionarioTecnico() {
        super("Diccionario Técnico");
        inicializarPalabrasPorDefecto();
    }

    private void inicializarPalabrasPorDefecto() {
        // Categoría: Redes
        agregarPalabra("wifi", "Redes");
        agregarPalabra("internet", "Redes");
        agregarPalabra("conexion", "Redes");
        agregarPalabra("red", "Redes");
        agregarPalabra("router", "Redes");
        agregarPalabra("modem", "Redes");
        agregarPalabra("lan", "Redes");
        agregarPalabra("inalambrico", "Redes");

        // Categoría: Impresoras
        agregarPalabra("impresora", "Impresoras");
        agregarPalabra("imprimir", "Impresoras");
        agregarPalabra("tinta", "Impresoras");
        agregarPalabra("cartucho", "Impresoras");
        agregarPalabra("papel", "Impresoras");
        agregarPalabra("escáner", "Impresoras");
        agregarPalabra("multifuncional", "Impresoras");

        // Categoría: Software
        agregarPalabra("software", "Software");
        agregarPalabra("aplicacion", "Software");
        agregarPalabra("programa", "Software");
        agregarPalabra("instalar", "Software");
        agregarPalabra("actualizacion", "Software");
        agregarPalabra("virus", "Software");
        agregarPalabra("antivirus", "Software");

        // Categoría: Hardware
        agregarPalabra("hardware", "Hardware");
        agregarPalabra("computadora", "Hardware");
        agregarPalabra("portatil", "Hardware");
        agregarPalabra("monitor", "Hardware");
        agregarPalabra("teclado", "Hardware");
        agregarPalabra("mouse", "Hardware");
        agregarPalabra("disco", "Hardware");
        agregarPalabra("memoria", "Hardware");

        // Categoría: Cuentas
        agregarPalabra("usuario", "Cuentas");
        agregarPalabra("contraseña", "Cuentas");
        agregarPalabra("login", "Cuentas");
        agregarPalabra("cuenta", "Cuentas");
        agregarPalabra("acceso", "Cuentas");
        agregarPalabra("registro", "Cuentas");
    }

    public String obtenerCategoria(String palabra) {
        return buscarPalabra(palabra);
    }

    public void mostrarCategorias() {
        System.out.println("\nCategorías técnicas disponibles:");
        System.out.println("- Redes");
        System.out.println("- Impresoras");
        System.out.println("- Software");
        System.out.println("- Hardware");
        System.out.println("- Cuentas");
    }
}
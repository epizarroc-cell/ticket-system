package dao.memoria;

import dao.interfaces.IDiccionarioDAO;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DiccionarioTecnicoDAOMemoria implements IDiccionarioDAO {
    private final Map<String, String> palabras = new ConcurrentHashMap<>();

    public DiccionarioTecnicoDAOMemoria() {
        inicializarPalabrasPorDefecto();
    }

    private void inicializarPalabrasPorDefecto() {
        // Categoría: Redes
        agregarPalabra("wifi", "Redes");
        agregarPalabra("internet", "Redes");
        agregarPalabra("conexion", "Redes");
        agregarPalabra("red", "Redes");
        agregarPalabra("router", "Redes");

        // Categoría: Impresoras
        agregarPalabra("impresora", "Impresoras");
        agregarPalabra("imprimir", "Impresoras");
        agregarPalabra("tinta", "Impresoras");
        agregarPalabra("cartucho", "Impresoras");

        // Categoría: Software
        agregarPalabra("software", "Software");
        agregarPalabra("aplicacion", "Software");
        agregarPalabra("programa", "Software");
        agregarPalabra("instalar", "Software");

        // Categoría: Hardware
        agregarPalabra("hardware", "Hardware");
        agregarPalabra("computadora", "Hardware");
        agregarPalabra("portatil", "Hardware");
        agregarPalabra("monitor", "Hardware");

        // Categoría: Cuentas
        agregarPalabra("usuario", "Cuentas");
        agregarPalabra("contraseña", "Cuentas");
        agregarPalabra("login", "Cuentas");
        agregarPalabra("cuenta", "Cuentas");
    }

    @Override
    public void agregarPalabra(String palabra, String valor) {
        palabras.put(palabra.toLowerCase(), valor);
    }

    @Override
    public String buscarPalabra(String palabra) {
        return palabras.get(palabra.toLowerCase());
    }

    @Override
    public boolean actualizarPalabra(String palabra, String nuevoValor) {
        if (palabras.containsKey(palabra.toLowerCase())) {
            palabras.put(palabra.toLowerCase(), nuevoValor);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarPalabra(String palabra) {
        return palabras.remove(palabra.toLowerCase()) != null;
    }

    @Override
    public Map<String, String> obtenerTodasLasPalabras() {
        return new HashMap<>(palabras);
    }

    @Override
    public boolean existePalabra(String palabra) {
        return palabras.containsKey(palabra.toLowerCase());
    }

    @Override
    public int obtenerCantidadPalabras() {
        return palabras.size();
    }
}
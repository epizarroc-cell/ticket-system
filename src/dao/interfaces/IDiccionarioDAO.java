package dao.interfaces;

import java.util.Map;

public interface IDiccionarioDAO {
    // CRUD operations
    void agregarPalabra(String palabra, String valor);
    String buscarPalabra(String palabra);
    boolean actualizarPalabra(String palabra, String nuevoValor);
    boolean eliminarPalabra(String palabra);

    // Query operations
    Map<String, String> obtenerTodasLasPalabras();
    boolean existePalabra(String palabra);
    int obtenerCantidadPalabras();
}
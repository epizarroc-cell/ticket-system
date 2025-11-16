package modelo;

import java.util.HashMap;
import java.util.Map;

public abstract class DiccionarioBase {
    protected Map<String, String> palabras;
    protected String tipo;

    public DiccionarioBase(String tipo) {
        this.palabras = new HashMap<>();
        this.tipo = tipo;
    }

    // CREATE - Agregar palabra
    public boolean agregarPalabra(String palabra, String valor) {
        if (palabra == null || palabra.trim().isEmpty()) {
            return false;
        }
        String palabraNormalizada = normalizarPalabra(palabra);
        if (palabras.containsKey(palabraNormalizada)) {
            return false; // Palabra ya existe
        }
        palabras.put(palabraNormalizada, valor);
        return true;
    }

    // READ - Buscar palabra
    public String buscarPalabra(String palabra) {
        if (palabra == null) return null;
        return palabras.get(normalizarPalabra(palabra));
    }

    // UPDATE - Actualizar palabra
    public boolean actualizarPalabra(String palabra, String nuevoValor) {
        if (palabra == null || palabra.trim().isEmpty()) {
            return false;
        }
        String palabraNormalizada = normalizarPalabra(palabra);
        if (!palabras.containsKey(palabraNormalizada)) {
            return false; // Palabra no existe
        }
        palabras.put(palabraNormalizada, nuevoValor);
        return true;
    }

    // DELETE - Eliminar palabra
    public boolean eliminarPalabra(String palabra) {
        if (palabra == null) return false;
        return palabras.remove(normalizarPalabra(palabra)) != null;
    }

    // LISTAR - Obtener todas las palabras
    public Map<String, String> getTodasLasPalabras() {
        return new HashMap<>(palabras);
    }

    public boolean existePalabra(String palabra) {
        if (palabra == null) return false;
        return palabras.containsKey(normalizarPalabra(palabra));
    }

    public int getCantidadPalabras() {
        return palabras.size();
    }

    public String getTipo() {
        return tipo;
    }

    private String normalizarPalabra(String palabra) {
        return palabra.toLowerCase().trim();
    }

    @Override
    public String toString() {
        return tipo + " - " + palabras.size() + " palabras";
    }
}
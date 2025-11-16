package cr.ac.ucenfotec.bl.entities.Diccionario;

import java.util.HashMap;
import java.util.Map;

public abstract class DiccionarioBase {
    protected Map<String, String> palabras;
    protected String tipo;

    public DiccionarioBase(String tipo) {
        this.palabras = new HashMap<>();
        this.tipo = tipo;
    }

    public boolean agregarPalabra(String palabra, String valor) {
        if (palabra == null || palabra.trim().isEmpty()) {
            return false;
        }
        String palabraNormalizada = palabra.toLowerCase().trim();
        if (palabras.containsKey(palabraNormalizada)) {
            return false;
        }
        palabras.put(palabraNormalizada, valor);
        return true;
    }

    public String buscarPalabra(String palabra) {
        if (palabra == null) return null;
        return palabras.get(palabra.toLowerCase().trim());
    }

    public boolean actualizarPalabra(String palabra, String nuevoValor) {
        if (palabra == null || palabra.trim().isEmpty()) {
            return false;
        }
        String palabraNormalizada = palabra.toLowerCase().trim();
        if (!palabras.containsKey(palabraNormalizada)) {
            return false;
        }
        palabras.put(palabraNormalizada, nuevoValor);
        return true;
    }

    public boolean eliminarPalabra(String palabra) {
        if (palabra == null) return false;
        return palabras.remove(palabra.toLowerCase().trim()) != null;
    }

    public Map<String, String> getTodasLasPalabras() {
        return new HashMap<>(palabras);
    }

    public boolean existePalabra(String palabra) {
        if (palabra == null) return false;
        return palabras.containsKey(palabra.toLowerCase().trim());
    }

    public int getCantidadPalabras() {
        return palabras.size();
    }

    public String getTipo() {
        return tipo;
    }
}
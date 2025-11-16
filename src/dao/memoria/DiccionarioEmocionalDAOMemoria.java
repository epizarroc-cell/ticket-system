package dao.memoria;

import dao.interfaces.IDiccionarioDAO;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DiccionarioEmocionalDAOMemoria implements IDiccionarioDAO {
    private final Map<String, String> palabras = new ConcurrentHashMap<>();

    public DiccionarioEmocionalDAOMemoria() {
        inicializarPalabrasPorDefecto();
    }

    private void inicializarPalabrasPorDefecto() {
        // Emoción: Frustración
        agregarPalabra("enojado", "Frustración");
        agregarPalabra("frustrado", "Frustración");
        agregarPalabra("molesto", "Frustración");
        agregarPalabra("irritado", "Frustración");

        // Emoción: Urgencia
        agregarPalabra("urgente", "Urgencia");
        agregarPalabra("inmediato", "Urgencia");
        agregarPalabra("rápido", "Urgencia");
        agregarPalabra("importante", "Urgencia");

        // Emoción: Positivo
        agregarPalabra("gracias", "Positivo");
        agregarPalabra("perfecto", "Positivo");
        agregarPalabra("excelente", "Positivo");
        agregarPalabra("bueno", "Positivo");

        // Emoción: Negativo
        agregarPalabra("problema", "Negativo");
        agregarPalabra("mal", "Negativo");
        agregarPalabra("terrible", "Negativo");
        agregarPalabra("horrible", "Negativo");

        // Emoción: Neutral
        agregarPalabra("consultar", "Neutral");
        agregarPalabra("pregunta", "Neutral");
        agregarPalabra("información", "Neutral");
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
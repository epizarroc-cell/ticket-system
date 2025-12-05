package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Diccionario.DAODiccionarioTecnico;
import cr.ac.ucenfotec.bl.entities.Diccionario.DAODiccionarioEmocional;
import java.util.Map;

public class GestorDiccionario {

    // Métodos para diccionario técnico
    public static String agregarPalabraTecnica(String palabra, String categoria) throws Exception {
        boolean exito = DAODiccionarioTecnico.agregarPalabra(palabra, categoria);
        return exito ? "✓ Palabra técnica agregada exitosamente" : "✗ Error: La palabra ya existe";
    }

    public static String buscarPalabraTecnica(String palabra) throws Exception {
        String categoria = DAODiccionarioTecnico.buscarPalabra(palabra);
        return categoria != null ? "✓ Palabra encontrada - Categoría: " + categoria : "✗ Palabra no encontrada";
    }

    public static String actualizarPalabraTecnica(String palabra, String nuevaCategoria) throws Exception {
        // Primero verificar que existe
        String categoriaActual = DAODiccionarioTecnico.buscarPalabra(palabra);
        if (categoriaActual == null) {
            return "✗ Error: La palabra no existe";
        }

        boolean exito = DAODiccionarioTecnico.actualizarPalabra(palabra, nuevaCategoria);
        return exito ? "✓ Palabra técnica actualizada exitosamente" : "✗ Error al actualizar";
    }

    public static String eliminarPalabraTecnica(String palabra) throws Exception {
        // Primero verificar que existe
        String categoria = DAODiccionarioTecnico.buscarPalabra(palabra);
        if (categoria == null) {
            return "✗ Error: La palabra no existe";
        }

        boolean exito = DAODiccionarioTecnico.eliminarPalabra(palabra);
        return exito ? "✓ Palabra técnica eliminada exitosamente" : "✗ Error al eliminar";
    }

    public static String listarPalabrasTecnicas() throws Exception {
        Map<String, String> palabras = DAODiccionarioTecnico.obtenerTodas();
        if (palabras.isEmpty()) {
            return "ℹ️ No hay palabras en el diccionario técnico";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("📋 PALABRAS TÉCNICAS (").append(palabras.size()).append("):\n");
        int i = 1;
        for (Map.Entry<String, String> entry : palabras.entrySet()) {
            sb.append(i).append(". ").append(entry.getKey()).append(" → ").append(entry.getValue()).append("\n");
            i++;
        }
        return sb.toString();
    }

    // Métodos para diccionario emocional
    public static String agregarPalabraEmocional(String palabra, String emocion) throws Exception {
        boolean exito = DAODiccionarioEmocional.agregarPalabra(palabra, emocion);
        return exito ? "✓ Palabra emocional agregada exitosamente" : "✗ Error: La palabra ya existe";
    }

    public static String buscarPalabraEmocional(String palabra) throws Exception {
        String emocion = DAODiccionarioEmocional.buscarPalabra(palabra);
        return emocion != null ? "✓ Palabra encontrada - Emoción: " + emocion : "✗ Palabra no encontrada";
    }

    public static String actualizarPalabraEmocional(String palabra, String nuevaEmocion) throws Exception {
        // Primero verificar que existe
        String emocionActual = DAODiccionarioEmocional.buscarPalabra(palabra);
        if (emocionActual == null) {
            return "✗ Error: La palabra no existe";
        }

        boolean exito = DAODiccionarioEmocional.actualizarPalabra(palabra, nuevaEmocion);
        return exito ? "✓ Palabra emocional actualizada exitosamente" : "✗ Error al actualizar";
    }

    public static String eliminarPalabraEmocional(String palabra) throws Exception {
        // Primero verificar que existe
        String emocion = DAODiccionarioEmocional.buscarPalabra(palabra);
        if (emocion == null) {
            return "✗ Error: La palabra no existe";
        }

        boolean exito = DAODiccionarioEmocional.eliminarPalabra(palabra);
        return exito ? "✓ Palabra emocional eliminada exitosamente" : "✗ Error al eliminar";
    }

    public static String listarPalabrasEmocionales() throws Exception {
        Map<String, String> palabras = DAODiccionarioEmocional.obtenerTodas();
        if (palabras.isEmpty()) {
            return "ℹ️ No hay palabras en el diccionario emocional";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("📋 PALABRAS EMOCIONALES (").append(palabras.size()).append("):\n");
        int i = 1;
        for (Map.Entry<String, String> entry : palabras.entrySet()) {
            sb.append(i).append(". ").append(entry.getKey()).append(" → ").append(entry.getValue()).append("\n");
            i++;
        }
        return sb.toString();
    }

    public static String obtenerEstadisticas() throws Exception {
        int tecnicas = DAODiccionarioTecnico.contarPalabras();
        int emocionales = DAODiccionarioEmocional.contarPalabras();
        return "Diccionario Técnico: " + tecnicas + " palabras\n" +
                "Diccionario Emocional: " + emocionales + " palabras\n" +
                "Total: " + (tecnicas + emocionales) + " palabras";
    }
}
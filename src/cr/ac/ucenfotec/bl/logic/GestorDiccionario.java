package cr.ac.ucenfotec.bl.logic;
import cr.ac.ucenfotec.bl.entities.Diccionario.DiccionarioTecnico;
import cr.ac.ucenfotec.bl.entities.Diccionario.DiccionarioEmocional;
import java.util.Map;

public class GestorDiccionario {
    private static DiccionarioTecnico dicTecnico = new DiccionarioTecnico();
    private static DiccionarioEmocional dicEmocional = new DiccionarioEmocional();

    // Métodos para diccionario técnico
    public static String agregarPalabraTecnica(String palabra, String categoria) {
        boolean exito = dicTecnico.agregarPalabra(palabra, categoria);
        return exito ? "Palabra técnica agregada exitosamente" : "Error: La palabra ya existe";
    }

    public static String buscarPalabraTecnica(String palabra) {
        String categoria = dicTecnico.buscarPalabra(palabra);
        return categoria != null ? "Categoría: " + categoria : "Palabra no encontrada";
    }

    public static String actualizarPalabraTecnica(String palabra, String nuevaCategoria) {
        boolean exito = dicTecnico.actualizarPalabra(palabra, nuevaCategoria);
        return exito ? "Palabra actualizada exitosamente" : "Error: Palabra no encontrada";
    }

    public static String eliminarPalabraTecnica(String palabra) {
        boolean exito = dicTecnico.eliminarPalabra(palabra);
        return exito ? "Palabra eliminada exitosamente" : "Error: Palabra no encontrada";
    }

    public static String listarPalabrasTecnicas() {
        Map<String, String> palabras = dicTecnico.getTodasLasPalabras();
        if (palabras.isEmpty()) {
            return "No hay palabras en el diccionario técnico";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Palabras técnicas (").append(palabras.size()).append("):\n");
        int i = 1;
        for (Map.Entry<String, String> entry : palabras.entrySet()) {
            sb.append(i).append(". ").append(entry.getKey()).append(" → ").append(entry.getValue()).append("\n");
            i++;
        }
        return sb.toString();
    }

    // Métodos para diccionario emocional (similares)
    public static String agregarPalabraEmocional(String palabra, String emocion) {
        boolean exito = dicEmocional.agregarPalabra(palabra, emocion);
        return exito ? "Palabra emocional agregada exitosamente" : "Error: La palabra ya existe";
    }

    public static String buscarPalabraEmocional(String palabra) {
        String emocion = dicEmocional.buscarPalabra(palabra);
        return emocion != null ? "Emoción: " + emocion : "Palabra no encontrada";
    }

    public static String listarPalabrasEmocionales() {
        Map<String, String> palabras = dicEmocional.getTodasLasPalabras();
        if (palabras.isEmpty()) {
            return "No hay palabras en el diccionario emocional";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Palabras emocionales (").append(palabras.size()).append("):\n");
        int i = 1;
        for (Map.Entry<String, String> entry : palabras.entrySet()) {
            sb.append(i).append(". ").append(entry.getKey()).append(" → ").append(entry.getValue()).append("\n");
            i++;
        }
        return sb.toString();
    }

    public static String obtenerEstadisticas() {
        return "Diccionario Técnico: " + dicTecnico.getCantidadPalabras() + " palabras\n" +
                "Diccionario Emocional: " + dicEmocional.getCantidadPalabras() + " palabras\n" +
                "Total: " + (dicTecnico.getCantidadPalabras() + dicEmocional.getCantidadPalabras()) + " palabras";
    }
}
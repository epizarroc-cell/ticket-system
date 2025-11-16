package modelo;

import java.util.*;
import java.text.Normalizer;

public class AnalizadorBoW {
    private DiccionarioTecnico dicTecnico;
    private DiccionarioEmocional dicEmocional;
    private Set<String> stopWords;

    public AnalizadorBoW(DiccionarioTecnico dicTecnico, DiccionarioEmocional dicEmocional) {
        this.dicTecnico = dicTecnico;
        this.dicEmocional = dicEmocional;
        inicializarStopWords();
    }

    private void inicializarStopWords() {
        this.stopWords = new HashSet<>(Arrays.asList(
                // Artículos y preposiciones
                "el", "la", "los", "las", "un", "una", "unos", "unas",
                "de", "del", "al", "a", "en", "por", "para", "con", "sin",
                "sobre", "bajo", "entre", "hacia", "desde", "hasta",

                // Conjunciones
                "y", "o", "pero", "aunque", "porque", "si", "como",

                // Pronombres
                "que", "cual", "quien", "cuyo", "cuya", "cuyos", "cuyas",
                "este", "esta", "estos", "estas", "ese", "esa", "esos", "esas",
                "aquel", "aquella", "aquellos", "aquellas",

                // Verbos comunes
                "es", "son", "era", "eran", "ser", "estar", "está", "están",
                "tiene", "tienen", "había", "han", "hacer", "hace", "hacen",

                // Adverbios y otras palabras comunes
                "muy", "mucho", "poco", "más", "menos", "tan", "tanto",
                "aquí", "allí", "ahora", "luego", "siempre", "nunca",
                "también", "solo", "solamente", "ya", "todavía", "aún"
        ));
    }

    /**
     * PREPROCESAMIENTO: Normaliza el texto
     */
    public String preprocesarTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "";
        }

        // 1. Convertir a minúsculas
        texto = texto.toLowerCase();

        // 2. Eliminar tildes y diacríticos
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        // 3. Eliminar símbolos y caracteres especiales, mantener letras, números y espacios
        texto = texto.replaceAll("[^a-z0-9\\s]", "");

        // 4. Eliminar espacios múltiples
        texto = texto.replaceAll("\\s+", " ").trim();

        return texto;
    }

    /**
     * TOKENIZACIÓN: Divide el texto en palabras individuales
     */
    public List<String> tokenizar(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(texto.split("\\s+"));
    }

    /**
     * ELIMINACIÓN DE STOPWORDS: Remueve palabras comunes sin significado
     */
    public List<String> eliminarStopWords(List<String> tokens) {
        List<String> tokensFiltrados = new ArrayList<>();
        for (String token : tokens) {
            // Mantener tokens que no sean stopwords y tengan longitud > 2
            if (!stopWords.contains(token) && token.length() > 2) {
                tokensFiltrados.add(token);
            }
        }
        return tokensFiltrados;
    }

    /**
     * VECTORIZACIÓN: Convierte tokens en vector de frecuencias
     */
    public Map<String, Integer> vectorizar(List<String> tokens) {
        Map<String, Integer> vector = new HashMap<>();
        for (String token : tokens) {
            vector.put(token, vector.getOrDefault(token, 0) + 1);
        }
        return vector;
    }

    /**
     * ANÁLISIS COMPLETO BoW de un ticket
     */
    public ResultadoAnalisis analizarTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("El ticket no puede ser nulo");
        }

        String descripcion = ticket.getDescripcion();
        System.out.println("🔍 Analizando descripción: " + descripcion);

        // 1. PREPROCESAMIENTO
        String textoPreprocesado = preprocesarTexto(descripcion);
        System.out.println("📝 Texto preprocesado: " + textoPreprocesado);

        // 2. TOKENIZACIÓN
        List<String> tokens = tokenizar(textoPreprocesado);
        System.out.println("🔤 Tokens: " + tokens);

        // 3. ELIMINACIÓN STOPWORDS
        List<String> tokensFiltrados = eliminarStopWords(tokens);
        System.out.println("🎯 Tokens filtrados: " + tokensFiltrados);

        // 4. VECTORIZACIÓN
        Map<String, Integer> vector = vectorizar(tokensFiltrados);
        System.out.println("📊 Vector TF: " + vector);

        // 5. DETECCIÓN DE EMOCIONES
        Map<String, Integer> puntajesEmociones = new HashMap<>();
        List<String> palabrasEmocionalesDetectadas = new ArrayList<>();

        for (String palabra : vector.keySet()) {
            String emocion = dicEmocional.obtenerEmocion(palabra);
            if (emocion != null) {
                int frecuencia = vector.get(palabra);
                puntajesEmociones.put(emocion, puntajesEmociones.getOrDefault(emocion, 0) + frecuencia);
                palabrasEmocionalesDetectadas.add(palabra + "(" + emocion + ")");
            }
        }

        // 6. CLASIFICACIÓN TÉCNICA
        Map<String, Integer> puntajesCategorias = new HashMap<>();
        List<String> palabrasTecnicasDetectadas = new ArrayList<>();

        for (String palabra : vector.keySet()) {
            String categoria = dicTecnico.obtenerCategoria(palabra);
            if (categoria != null) {
                int frecuencia = vector.get(palabra);
                puntajesCategorias.put(categoria, puntajesCategorias.getOrDefault(categoria, 0) + frecuencia);
                palabrasTecnicasDetectadas.add(palabra + "(" + categoria + ")");
            }
        }

        return new ResultadoAnalisis(
                puntajesEmociones,
                puntajesCategorias,
                palabrasEmocionalesDetectadas,
                palabrasTecnicasDetectadas
        );
    }

    /**
     * Clase para encapsular los resultados del análisis
     */
    public static class ResultadoAnalisis {
        private Map<String, Integer> emociones;
        private Map<String, Integer> categorias;
        private List<String> palabrasEmocionales;
        private List<String> palabrasTecnicas;

        public ResultadoAnalisis(Map<String, Integer> emociones, Map<String, Integer> categorias,
                                 List<String> palabrasEmocionales, List<String> palabrasTecnicas) {
            this.emociones = emociones;
            this.categorias = categorias;
            this.palabrasEmocionales = palabrasEmocionales;
            this.palabrasTecnicas = palabrasTecnicas;
        }

        // Getters
        public Map<String, Integer> getEmociones() { return new HashMap<>(emociones); }
        public Map<String, Integer> getCategorias() { return new HashMap<>(categorias); }
        public List<String> getPalabrasEmocionales() { return new ArrayList<>(palabrasEmocionales); }
        public List<String> getPalabrasTecnicas() { return new ArrayList<>(palabrasTecnicas); }

        public String getEmocionPrincipal() {
            return obtenerMaximo(emociones);
        }

        public String getCategoriaPrincipal() {
            return obtenerMaximo(categorias);
        }

        private String obtenerMaximo(Map<String, Integer> mapa) {
            return mapa.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("No determinado");
        }
    }
}
package cr.ac.ucenfotec.bl.entities.Analizador;

import cr.ac.ucenfotec.bl.entities.Ticket.Ticket;
import cr.ac.ucenfotec.bl.entities.Diccionario.DAODiccionarioTecnico;
import cr.ac.ucenfotec.bl.entities.Diccionario.DAODiccionarioEmocional;
import java.util.*;
import java.text.Normalizer;

public class AnalizadorBoW {
    private Map<String, String> dicTecnico;
    private Map<String, String> dicEmocional;
    private Set<String> stopWords;

    public AnalizadorBoW() throws Exception {
        cargarDiccionarios();
        inicializarStopWords();
    }

    private void cargarDiccionarios() throws Exception {
        this.dicTecnico = DAODiccionarioTecnico.obtenerTodas();
        this.dicEmocional = DAODiccionarioEmocional.obtenerTodas();
    }

    private void inicializarStopWords() {
        this.stopWords = new HashSet<>(Arrays.asList(
                // Artículos
                "el", "la", "los", "las", "un", "una", "unos", "unas", "lo", "al", "del",
                // Preposiciones
                "a", "ante", "bajo", "cabe", "con", "contra", "de", "desde", "en", "entre",
                "hacia", "hasta", "para", "por", "según", "sin", "so", "sobre", "tras",
                // Conjunciones
                "y", "e", "ni", "que", "o", "u", "pero", "mas", "aunque", "sino", "como",
                // Pronombres
                "yo", "tú", "él", "ella", "nosotros", "vosotros", "ellos", "ellas",
                "me", "te", "se", "nos", "os", "le", "les", "lo", "la", "los", "las",
                // Adverbios comunes
                "no", "si", "ya", "también", "muy", "mucho", "poco", "tan", "así",
                "aquí", "allí", "ahora", "después", "antes", "siempre", "nunca",
                // Verbos comunes (infinitivos)
                "ser", "estar", "haber", "tener", "hacer", "poder", "decir", "ir",
                "ver", "dar", "saber", "querer", "llegar", "pasar", "deber", "poner",
                "parecer", "quedar", "creer", "hablar", "llevar", "dejar", "seguir",
                "encontrar", "llamar", "venir", "pensar", "salir", "volver", "tomar",
                "conocer", "vivir", "sentir", "tratar", "mirar", "contar", "empezar",
                "esperar", "buscar", "existir", "entrar", "trabajar", "escribir",
                "perder", "producir", "ocurrir", "entender", "pedir", "recibir",
                "recordar", "terminar", "permitir", "aparecer", "conseguir",
                "comenzar", "servir", "sacar", "necesitar", "mantener", "resultar",
                "leer", "caer", "cambiar", "presentar", "crear", "abrir", "considerar",
                "oír", "acabar", "convertir", "ganar", "formar", "traer", "partir",
                "morir", "aceptar", "realizar", "suponer", "comprender", "lograr",
                "explicar", "preguntar", "tocar", "reconocer", "estudiar", "alcanzar",
                "nacer", "dirigir", "correr", "utilizar", "pagar", "ayudar", "gustar",
                "jugar", "escuchar", "cumplir", "ofrecer", "descubrir", "levantar",
                "intentar", "usar", "decidir", "aplicar", "consistir", "cerrar"
        ));
    }

    public String preprocesarTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "";
        }

        texto = texto.toLowerCase();
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        texto = texto.replaceAll("[^a-z0-9\\s]", "");
        texto = texto.replaceAll("\\s+", " ").trim();

        return texto;
    }

    public List<String> tokenizar(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(texto.split("\\s+"));
    }

    public List<String> eliminarStopWords(List<String> tokens) {
        List<String> tokensFiltrados = new ArrayList<>();
        for (String token : tokens) {
            if (!stopWords.contains(token) && token.length() > 2) {
                tokensFiltrados.add(token);
            }
        }
        return tokensFiltrados;
    }

    public Map<String, Integer> vectorizar(List<String> tokens) {
        Map<String, Integer> vector = new HashMap<>();
        for (String token : tokens) {
            vector.put(token, vector.getOrDefault(token, 0) + 1);
        }
        return vector;
    }



    public ResultadoAnalisis analizarTicket(Ticket ticket) {
        String descripcion = ticket.getDescripcion();
        String textoPreprocesado = preprocesarTexto(descripcion);
        List<String> tokens = tokenizar(textoPreprocesado);
        List<String> tokensFiltrados = eliminarStopWords(tokens);
        Map<String, Integer> vector = vectorizar(tokensFiltrados);

        // Análisis emocional
        Map<String, Integer> emociones = new HashMap<>();
        List<String> palabrasEmocionales = new ArrayList<>();

        for (String palabra : vector.keySet()) {
            String emocion = dicEmocional.get(palabra);
            if (emocion != null) {
                int frecuencia = vector.get(palabra);
                emociones.put(emocion, emociones.getOrDefault(emocion, 0) + frecuencia);
                palabrasEmocionales.add(palabra + "(" + emocion + ")");
            }
        }

        // Análisis técnico
        Map<String, Integer> categorias = new HashMap<>();
        List<String> palabrasTecnicas = new ArrayList<>();

        for (String palabra : vector.keySet()) {
            String categoria = dicTecnico.get(palabra);
            if (categoria != null) {
                int frecuencia = vector.get(palabra);
                categorias.put(categoria, categorias.getOrDefault(categoria, 0) + frecuencia);
                palabrasTecnicas.add(palabra + "(" + categoria + ")");
            }
        }

        return new ResultadoAnalisis(emociones, categorias, palabrasEmocionales, palabrasTecnicas);
    }

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
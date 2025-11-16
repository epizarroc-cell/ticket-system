package modelo;

public class DiccionarioEmocional extends DiccionarioBase {

    public DiccionarioEmocional() {
        super("Diccionario Emocional");
        inicializarPalabrasPorDefecto();
    }

    private void inicializarPalabrasPorDefecto() {
        // Emoción: Frustración
        agregarPalabra("enojado", "Frustración");
        agregarPalabra("frustrado", "Frustración");
        agregarPalabra("molesto", "Frustración");
        agregarPalabra("irritado", "Frustración");
        agregarPalabra("enfadado", "Frustración");
        agregarPalabra("disgustado", "Frustración");

        // Emoción: Urgencia
        agregarPalabra("urgente", "Urgencia");
        agregarPalabra("inmediato", "Urgencia");
        agregarPalabra("rápido", "Urgencia");
        agregarPalabra("importante", "Urgencia");
        agregarPalabra("prioridad", "Urgencia");
        agregarPalabra("crítico", "Urgencia");

        // Emoción: Positivo
        agregarPalabra("gracias", "Positivo");
        agregarPalabra("perfecto", "Positivo");
        agregarPalabra("excelente", "Positivo");
        agregarPalabra("bueno", "Positivo");
        agregarPalabra("genial", "Positivo");
        agregarPalabra("fantástico", "Positivo");
        agregarPalabra("agradecido", "Positivo");

        // Emoción: Negativo
        agregarPalabra("problema", "Negativo");
        agregarPalabra("mal", "Negativo");
        agregarPalabra("terrible", "Negativo");
        agregarPalabra("horrible", "Negativo");
        agregarPalabra("pésimo", "Negativo");
        agregarPalabra("desastroso", "Negativo");

        // Emoción: Neutral
        agregarPalabra("consultar", "Neutral");
        agregarPalabra("pregunta", "Neutral");
        agregarPalabra("información", "Neutral");
        agregarPalabra("duda", "Neutral");
    }

    public String obtenerEmocion(String palabra) {
        return buscarPalabra(palabra);
    }

    public void mostrarEmociones() {
        System.out.println("\nEmociones detectables:");
        System.out.println("- Frustración");
        System.out.println("- Urgencia");
        System.out.println("- Positivo");
        System.out.println("- Negativo");
        System.out.println("- Neutral");
    }
}
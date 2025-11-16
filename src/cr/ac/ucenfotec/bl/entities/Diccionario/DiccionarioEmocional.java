package cr.ac.ucenfotec.bl.entities.Diccionario;

public class DiccionarioEmocional extends DiccionarioBase {

    public DiccionarioEmocional() {
        super("Diccionario Emocional");
        inicializarPalabrasPorDefecto();
    }

    private void inicializarPalabrasPorDefecto() {
        agregarPalabra("enojado", "Frustración");
        agregarPalabra("frustrado", "Frustración");
        agregarPalabra("urgente", "Urgencia");
        agregarPalabra("importante", "Urgencia");
        agregarPalabra("gracias", "Positivo");
        agregarPalabra("excelente", "Positivo");
        agregarPalabra("problema", "Negativo");
        agregarPalabra("mal", "Negativo");
        agregarPalabra("consultar", "Neutral");
    }
}
package cr.ac.ucenfotec.bl.entities.Diccionario;

public class DiccionarioTecnico extends DiccionarioBase {

    public DiccionarioTecnico() {
        super("Diccionario Técnico");
        inicializarPalabrasPorDefecto();
    }

    private void inicializarPalabrasPorDefecto() {
        agregarPalabra("wifi", "Redes");
        agregarPalabra("internet", "Redes");
        agregarPalabra("conexion", "Redes");
        agregarPalabra("impresora", "Impresoras");
        agregarPalabra("imprimir", "Impresoras");
        agregarPalabra("software", "Software");
        agregarPalabra("hardware", "Hardware");
        agregarPalabra("usuario", "Cuentas");
        agregarPalabra("contraseña", "Cuentas");
        agregarPalabra("login", "Cuentas");
    }
}
package controlador;

import dao.factory.DAOFactory;
import dao.interfaces.IDiccionarioDAO;
import java.util.Map;

public class DiccionarioController {
    private final IDiccionarioDAO dicTecnicoDAO;
    private final IDiccionarioDAO dicEmocionalDAO;

    public DiccionarioController() {
        this.dicTecnicoDAO = DAOFactory.getDiccionarioTecnicoDAO();
        this.dicEmocionalDAO = DAOFactory.getDiccionarioEmocionalDAO();
    }

    // Métodos para diccionario técnico
    public boolean agregarPalabraTecnica(String palabra, String categoria) {
        dicTecnicoDAO.agregarPalabra(palabra, categoria);
        return true;
    }

    public String buscarPalabraTecnica(String palabra) {
        return dicTecnicoDAO.buscarPalabra(palabra);
    }

    public boolean actualizarPalabraTecnica(String palabra, String nuevaCategoria) {
        return dicTecnicoDAO.actualizarPalabra(palabra, nuevaCategoria);
    }

    public boolean eliminarPalabraTecnica(String palabra) {
        return dicTecnicoDAO.eliminarPalabra(palabra);
    }

    public Map<String, String> obtenerTodasPalabrasTecnicas() {
        return dicTecnicoDAO.obtenerTodasLasPalabras();
    }

    // Métodos para diccionario emocional
    public boolean agregarPalabraEmocional(String palabra, String emocion) {
        dicEmocionalDAO.agregarPalabra(palabra, emocion);
        return true;
    }

    public String buscarPalabraEmocional(String palabra) {
        return dicEmocionalDAO.buscarPalabra(palabra);
    }

    public boolean actualizarPalabraEmocional(String palabra, String nuevaEmocion) {
        return dicEmocionalDAO.actualizarPalabra(palabra, nuevaEmocion);
    }

    public boolean eliminarPalabraEmocional(String palabra) {
        return dicEmocionalDAO.eliminarPalabra(palabra);
    }

    public Map<String, String> obtenerTodasPalabrasEmocionales() {
        return dicEmocionalDAO.obtenerTodasLasPalabras();
    }

    public int obtenerEstadisticasTecnicas() {
        return dicTecnicoDAO.obtenerCantidadPalabras();
    }

    public int obtenerEstadisticasEmocionales() {
        return dicEmocionalDAO.obtenerCantidadPalabras();
    }
}
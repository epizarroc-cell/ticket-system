package dao.factory;

import dao.interfaces.*;
import dao.memoria.*;

public class DAOFactory {
    private static IUsuarioDAO usuarioDAO = null;
    private static IDepartamentoDAO departamentoDAO = null;
    private static ITicketDAO ticketDAO = null;
    private static IDiccionarioDAO diccionarioTecnicoDAO = null;
    private static IDiccionarioDAO diccionarioEmocionalDAO = null;

    public static IUsuarioDAO getUsuarioDAO() {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAOMemoria();
        }
        return usuarioDAO;
    }

    public static IDepartamentoDAO getDepartamentoDAO() {
        if (departamentoDAO == null) {
            departamentoDAO = new DepartamentoDAOMemoria();
        }
        return departamentoDAO;
    }

    public static ITicketDAO getTicketDAO() {
        if (ticketDAO == null) {
            ticketDAO = new TicketDAOMemoria();
        }
        return ticketDAO;
    }

    public static IDiccionarioDAO getDiccionarioTecnicoDAO() {
        if (diccionarioTecnicoDAO == null) {
            diccionarioTecnicoDAO = new DiccionarioTecnicoDAOMemoria();
        }
        return diccionarioTecnicoDAO;
    }

    public static IDiccionarioDAO getDiccionarioEmocionalDAO() {
        if (diccionarioEmocionalDAO == null) {
            diccionarioEmocionalDAO = new DiccionarioEmocionalDAOMemoria();
        }
        return diccionarioEmocionalDAO;
    }

    // Método para resetear todos los DAOs (útil para testing)
    public static void reset() {
        usuarioDAO = null;
        departamentoDAO = null;
        ticketDAO = null;
        diccionarioTecnicoDAO = null;
        diccionarioEmocionalDAO = null;
    }
}
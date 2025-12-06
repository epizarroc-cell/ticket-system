package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Ticket.*;
import cr.ac.ucenfotec.bl.entities.Usuario.Usuario;
import cr.ac.ucenfotec.bl.entities.Departamento.Departamento;

import java.util.ArrayList;
import java.util.List;

public class GestorTicket {
    public static String agregarTicket(String asunto, String descripcion, Departamento departamento, Usuario usuario) throws Exception {
        Ticket ticket = new Ticket(asunto, descripcion, departamento, usuario);
        return DAOTicket.insertarTicket(ticket);
    }

    public static String listarTickets() throws Exception {
        return DAOTicket.obtenerTodos();
    }

    public static Ticket buscarTicketPorId(int id) throws Exception {
        return DAOTicket.buscarPorId(id);
    }

    public static String cambiarEstadoTicket(int id, String nuevoEstado) throws Exception {
        return DAOTicket.cambiarEstado(id, nuevoEstado);
    }
    public static List<Ticket> getListaTickets() throws Exception {

        List<Ticket> tickets = new ArrayList<>();
        return tickets;
    }
}
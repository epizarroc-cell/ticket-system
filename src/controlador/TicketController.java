package controlador;

import dao.factory.DAOFactory;
import dao.interfaces.ITicketDAO;
import modelo.Ticket;
import modelo.Usuario;
import modelo.Departamento;
import modelo.AnalizadorBoW;
import modelo.AnalizadorBoW.ResultadoAnalisis;
import java.util.List;
import java.util.Optional;

public class TicketController {
    private final ITicketDAO ticketDAO;
    private final AnalizadorBoW analizadorBoW;

    public TicketController(AnalizadorBoW analizadorBoW) {
        this.ticketDAO = DAOFactory.getTicketDAO();
        this.analizadorBoW = analizadorBoW;
    }

    public Ticket registrarTicket(String asunto, String descripcion, Departamento departamento, Usuario usuario) {
        Ticket ticket = new Ticket(asunto, descripcion, departamento, usuario);
        ticketDAO.crear(ticket);
        return ticket;
    }

    public List<Ticket> obtenerTodosLosTickets() {
        return ticketDAO.obtenerTodos();
    }

    public Optional<Ticket> buscarTicketPorId(int id) {
        return ticketDAO.buscarPorId(id);
    }

    public List<Ticket> obtenerTicketsPorUsuario(int usuarioId) {
        return ticketDAO.obtenerPorUsuario(usuarioId);
    }

    public List<Ticket> obtenerTicketsPorDepartamento(int departamentoId) {
        return ticketDAO.obtenerPorDepartamento(departamentoId);
    }

    public void actualizarTicket(Ticket ticket) {
        ticketDAO.actualizar(ticket);
    }

    public ResultadoAnalisis analizarTicket(int ticketId) {
        Optional<Ticket> ticketOpt = ticketDAO.buscarPorId(ticketId);
        if (ticketOpt.isPresent()) {
            return analizadorBoW.analizarTicket(ticketOpt.get());
        }
        throw new IllegalArgumentException("Ticket no encontrado con ID: " + ticketId);
    }

    public int obtenerTotalTickets() {
        return ticketDAO.obtenerTodos().size();
    }

    public long obtenerTicketsPorEstado(String estado) {
        return ticketDAO.obtenerTodos().stream()
                .filter(ticket -> estado.equals(ticket.getEstado()))
                .count();
    }
}
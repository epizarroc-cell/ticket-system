package dao.memoria;

import dao.interfaces.ITicketDAO;
import modelo.Ticket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TicketDAOMemoria implements ITicketDAO {
    private final Map<Integer, Ticket> tickets = new ConcurrentHashMap<>();
    private final AtomicInteger contadorId = new AtomicInteger(1);

    @Override
    public void crear(Ticket ticket) {
        int id = contadorId.getAndIncrement();
        ticket.setId(id);
        tickets.put(id, ticket);
    }

    @Override
    public Optional<Ticket> buscarPorId(int id) {
        return Optional.ofNullable(tickets.get(id));
    }

    @Override
    public List<Ticket> obtenerTodos() {
        return new ArrayList<>(tickets.values());
    }

    @Override
    public List<Ticket> obtenerPorUsuario(int usuarioId) {
        return tickets.values().stream()
                .filter(ticket -> ticket.getUsuario().getId() == usuarioId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> obtenerPorDepartamento(int departamentoId) {
        return tickets.values().stream()
                .filter(ticket -> ticket.getDepartamento().getId() == departamentoId)
                .collect(Collectors.toList());
    }

    @Override
    public void actualizar(Ticket ticket) {
        if (tickets.containsKey(ticket.getId())) {
            tickets.put(ticket.getId(), ticket);
        }
    }

    @Override
    public void eliminar(int id) {
        tickets.remove(id);
    }

    @Override
    public int obtenerSiguienteId() {
        return contadorId.get();
    }
}
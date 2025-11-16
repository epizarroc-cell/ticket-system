package dao.interfaces;

import modelo.Ticket;
import java.util.List;
import java.util.Optional;

public interface ITicketDAO {
    // CREATE
    void crear(Ticket ticket);

    // READ
    Optional<Ticket> buscarPorId(int id);
    List<Ticket> obtenerTodos();
    List<Ticket> obtenerPorUsuario(int usuarioId);
    List<Ticket> obtenerPorDepartamento(int departamentoId);

    // UPDATE
    void actualizar(Ticket ticket);

    // DELETE
    void eliminar(int id);

    // VALIDACIONES
    int obtenerSiguienteId();
}
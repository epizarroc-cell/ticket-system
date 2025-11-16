package cr.ac.ucenfotec.bl.entities.Ticket;
import cr.ac.ucenfotec.bl.entities.Departamento.Departamento;
import cr.ac.ucenfotec.bl.entities.Usuario.Usuario;
import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private String asunto;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private Departamento departamento;
    private Usuario usuario;


    // Constructor sin ID (para crear nuevos usuarios)
    public Ticket(String asuntoObjeto, String descripcionObjeto, Departamento departamentoObjeto, Usuario usuarioObjeto) {
        asunto = asuntoObjeto;
        descripcion = descripcionObjeto;
        estado = "Nuevo";
        fechaCreacion = LocalDateTime.now();
        departamento = departamentoObjeto;
        usuario = usuarioObjeto;
    }
    // Constructor con ID (para cuando se recupera de la BD)
    public Ticket(int idObjeto, String asuntoObjeto, String descripcionObjeto, String estadoObjeto,
                  LocalDateTime fechaCreacionObjeto, Departamento departamentoObjeto, Usuario usuarioObjeto) {
        id = idObjeto;
        asunto = asuntoObjeto;
        descripcion = descripcionObjeto;
        estado = estadoObjeto;
        fechaCreacion = fechaCreacionObjeto;
        departamento = departamentoObjeto;
        usuario = usuarioObjeto;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    @Override
    public String toString() {
        return "Ticket #" + id +
                "\nAsunto: " + asunto +
                "\nEstado: " + estado +
                "\nDepartamento: " + departamento.getNombre() +
                "\nUsuario: " + usuario.getNombreCompleto() +
                "\nFecha: " + fechaCreacion + "\n";
    }
}
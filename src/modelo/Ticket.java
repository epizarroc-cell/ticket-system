package modelo;
import java.time.LocalDateTime;
public class Ticket {

    //Atributos
    private static int contadorId = 1;

    private int id;
    private String asunto;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCracion;
    private Departamento departamento;
    private Usuario usuario;

    //Metodo
    //COnstructor

    public Ticket(String asuntoObjeto, String descripcionObjeto, Departamento departamentoObjeto, Usuario usuarioObjeto){

        id = contadorId++;
        asunto = asuntoObjeto;
        estado = "Nuevo";
        fechaCracion = LocalDateTime.now();
        departamento = departamentoObjeto;
        usuario = usuarioObjeto;
    }

    //Getters

    public int getId(){
        return id;
    }
    public String getAsunto(){ return asunto;}
    public String getDescripcion(){
        return  descripcion;
    }
    public String getEstado(){
        return  estado;
    }
    public LocalDateTime getFechaCracion(){
        return fechaCracion;
    }
    public Departamento getDepartamento(){
        return departamento;
    }
    public Usuario getUsuario(){ return usuario;}

    //Setter
    public void setAsusto(String nuevoAsunto){
        asunto = nuevoAsunto;
    }
    public void setDescripcion(String nuevaDescipcion){
        descripcion = nuevaDescipcion;
    }
    public void setEstado(String nuevoEstado){
        estado = nuevoEstado;
    }
    public void setDepartamento(Departamento nuevoDepartamento){
        departamento = nuevoDepartamento;
    }
    public void setUsuario(Usuario nuevoUsuario) {usuario = nuevoUsuario;}

    //ToString
    public String toString(){
        return "Ticket #" + id +
                "\nAsunto: " + asunto +
                "\nEstando: " + estado +
                "\nDepartamento: " + departamento.getNombre() +
                "\nFecha: " + fechaCracion + ".\n";

    }

}

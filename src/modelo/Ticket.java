package modelo;
import java.time.LocalDateTime;
public class Ticket {

    //Atributos
    private static int contadorId = 1;

    private int id;
    private String asusto;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCracion;
    private Departamento departamento;

    //Metodo
    //COnstructor

    public Ticket(String asuntoObjeto, String descripcionObjeto, Departamento departamentoObjeto){
        id = contadorId++;
        asusto = asuntoObjeto;
        descripcion = descripcionObjeto;
        estado = "Nuevo";
        fechaCracion = LocalDateTime.now();
        departamento = departamentoObjeto;
    }

    //Getters

    public int getId(){
        return id;
    }
    public String getAsusto(){
        return asusto;
    }
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

    //Setter
    public void setAsusto(String nuevoAsunto){
        asusto = nuevoAsunto;
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

    //ToString
    public String toString(){
        return "Ticket #" + id +
                "\nAsunto: " + asusto +
                "\nEstando: " + estado +
                "\nDepartamento: " + departamento.getNombre() +
                "\nFecha: " + fechaCracion + ".\n";

    }

}

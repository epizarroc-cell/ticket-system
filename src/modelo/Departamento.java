package modelo;

public class Departamento {
    //Atributo
    private String nombre;
    private String descripcion;
    private String contacto;

    //Metodo
    //Constructor
    public Departamento(String nombreObjeto, String descripcionObjeto,String contactoObjeto) {
        nombre = nombreObjeto;
        descripcion = descripcionObjeto;
        contacto = contactoObjeto;
    }
    //Getters
    public String getNombre(){
        return nombre;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public String getContacto(){
        return contacto;
    }
    //Setters
    public void setNombre(String nuevoNombre){
        nombre = nuevoNombre;
    }
    public void setDescripcion(String nuevaDescripcion){
        descripcion = nuevaDescripcion;
    }
    public void setContacto(String nuevoContacto){
        contacto = nuevoContacto;
    }

    //Metodo equals
    public boolean equals(Departamento departamentoComparar){
        return  nombre.equals(departamentoComparar.getNombre());
    }
    //Metodo toString
    public String toString(){
        return "Departamento: " + nombre +
                "\nDescripcion: " + descripcion +
                "\nContacto: " + contacto + ".\n";
    }
}

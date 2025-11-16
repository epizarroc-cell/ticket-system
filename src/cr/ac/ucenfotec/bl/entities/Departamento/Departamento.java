package cr.ac.ucenfotec.bl.entities.Departamento;

public class Departamento {
    private int id;
    private String nombre;
    private String descripcion;
    private String contacto;


    // Constructor sin ID (para crear nuevos usuarios)
    public Departamento(String nombreObjeto, String descripcionObjeto, String contactoObjeto) {
        nombre = nombreObjeto;
        descripcion = descripcionObjeto;
        contacto = contactoObjeto;
    }
    // Constructor con ID (para cuando se recupera de la BD)
    public Departamento(int idObjeto, String nombreObjeto, String descripcionObjeto, String contactoObjeto) {
        id = idObjeto;
        nombre = nombreObjeto;
        descripcion = descripcionObjeto;
        contacto = contactoObjeto;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int nuevaId) { id = nuevaId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nuevoNombre) { nombre = nuevoNombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String nuevaDescripcion) { descripcion = nuevaDescripcion; }
    public String getContacto() { return contacto; }
    public void setContacto(String nuevoContacto) { contacto = nuevoContacto; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Departamento that = (Departamento) obj;
        return nombre.equals(that.nombre);
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }
}
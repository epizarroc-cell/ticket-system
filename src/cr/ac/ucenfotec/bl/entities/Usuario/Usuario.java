package cr.ac.ucenfotec.bl.entities.Usuario;

public class Usuario {
    private int idUsuario;
    private String nombreCompleto;
    private String correoElectronico;
    private String contrasenaUsuario;
    private String telefonoContacto;
    private String rolUsuario;

    // Constructor sin ID (para crear nuevos usuarios)
    public Usuario(String nombre, String correo, String contrasena, String telefono, String rol) {
        nombreCompleto = nombre;
        correoElectronico = correo;
        contrasenaUsuario = contrasena;
        telefonoContacto = telefono;
        rolUsuario = rol;
    }
    // Constructor con ID (para cuando se recupera de la BD)
    public Usuario(int id, String nombre, String correo, String contrasena, String telefono, String rol) {
        idUsuario= id;
        nombreCompleto = nombre;
        correoElectronico = correo;
        contrasenaUsuario = contrasena;
        telefonoContacto = telefono;
        rolUsuario = rol;
    }

    // Getters y Setters
    public int getId() { return idUsuario; }
    public void setId(int id) { idUsuario = id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombre) { nombreCompleto = nombre; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correo) { correoElectronico = correo; }
    public String getContrasena() { return contrasenaUsuario; }
    public void setContrasena(String contrasena) { contrasenaUsuario = contrasena; }
    public String getTelefonoContacto() { return telefonoContacto; }
    public void setTelefonoContacto(String telefono) { telefonoContacto = telefono; }
    public String getRol() { return rolUsuario; }
    public void setRol(String rol) { rolUsuario = rol; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + idUsuario +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                ", rol='" + rolUsuario + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return correoElectronico.equals(usuario.correoElectronico);
    }
}
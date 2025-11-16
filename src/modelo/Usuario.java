package modelo;

public class Usuario {


    private String nombreCompleto;
    private String correoElectronico; // Único
    private String contrasenaUsuario;
    private String telefonoContacto;  // Opcional
    private String rolUsuario;               // administrador, estudiante, funcionario


    public Usuario(String nombre, String correo, String contrasena,
                   String telefono, String rol) {
        nombreCompleto = nombre;
        correoElectronico = correo;
        contrasenaUsuario = contrasena;
        telefonoContacto = telefono;
        rolUsuario = rol;
    }


    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombre) {
        nombreCompleto = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasena() {
        return contrasenaUsuario;
    }

    public void setContrasena(String contrasena) {
        contrasenaUsuario = contrasena;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefono) {
        telefonoContacto = telefono;
    }

    public String getRol() {
        return rolUsuario;
    }

    public void setRol(String rol) {
        rolUsuario = rol;
    }

    public String toString() {
        return "Usuario{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                ", rol='" + rolUsuario + '\'' +
                '}';
    }
}


package cr.ac.ucenfotec.bl.logic;
import cr.ac.ucenfotec.bl.entities.Departamento.*;

public class GestorDepartamento {
    public static String agregarDepartamento(String nombre, String descripcion, String contacto) throws Exception {
        Departamento d = new Departamento(nombre, descripcion, contacto);
        return DAODepartamento.insertarDepartamento(d);
    }

    public static String listarDepartamentos() throws Exception {
        return DAODepartamento.obtenerTodos();
    }

    public static Departamento buscarDepartamentoPorNombre(String nombre) throws Exception {
        return DAODepartamento.buscarPorNombre(nombre);
    }

    public static Departamento buscarDepartamentoPorId(int id) throws Exception {
        return DAODepartamento.buscarPorId(id);
    }
}
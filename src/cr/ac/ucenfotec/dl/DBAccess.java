package cr.ac.ucenfotec.dl;

import java.sql.*;

public class DBAccess {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;


    public DBAccess(String direccion, String usuario, String contrasenia) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(direccion, usuario, contrasenia);
    }

    public void ejecutarStatement(String pStatement) throws SQLException{
        statement = connection.createStatement();
        statement.executeUpdate(pStatement);
    }
}

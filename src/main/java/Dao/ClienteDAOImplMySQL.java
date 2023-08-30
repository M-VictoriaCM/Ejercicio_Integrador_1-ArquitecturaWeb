package Dao;

import Modelo.Cliente;
import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDAOImplMySQL implements ClienteDao{
    private Connection connection;

    public ClienteDAOImplMySQL(Connection connection){
        this.connection = connection;
    }

    public void crear_tabla() {
        try {
            Statement stmt = this.connection.createStatement();
            String sql = "CREATE TABLE Cliente("+
                        "idCliente INT AUTO_INCREMENT PRIMARY KEY,"+
                        "nombre VARCHAR(255),"+
                        "email VARCHAR(255))";
            stmt.executeUpdate(sql);
            ConnectionFactory.getInstance().disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertar(Cliente cliente) {
        //Codigo para insertar

    }
}

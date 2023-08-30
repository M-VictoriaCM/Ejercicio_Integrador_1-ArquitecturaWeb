package Dao;

import Modelo.Cliente;
import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        try {
            String sql ="INSERT INTO Cliente(nombre, email) VALUE (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getEmail());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

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
                        "idCliente INT PRIMARY KEY,"+
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
            String sql ="INSERT INTO Cliente(idCliente,nombre, email) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cliente.getIdCliente());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getEmail());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

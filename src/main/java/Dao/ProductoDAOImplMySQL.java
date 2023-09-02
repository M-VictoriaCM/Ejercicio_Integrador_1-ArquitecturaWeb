package Dao;

import Modelo.Producto;
import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductoDAOImplMySQL implements ProductoDao{
    private Connection connection;

    public ProductoDAOImplMySQL (Connection connection){
        this.connection = connection;
    }

    public void crear_tabla() {
        try {
            Statement stmt = this.connection.createStatement();
            String sql = "CREATE TABLE Producto(" +
                        "idProducto INT PRIMARY KEY," +
                        " nombre VARCHAR(255)," +
                        " valor FLOAT)";
            stmt.executeUpdate(sql);
            ConnectionFactory.getInstance().disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertar(Producto producto) {
        try {
            String sql ="INSERT INTO Producto(idProducto,nombre,valor) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, producto.getIdProducto());
            preparedStatement.setString(2, producto.getNombre());
            preparedStatement.setFloat(3, producto.getValor());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

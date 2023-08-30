package Dao;

import Modelo.Producto;
import connection.ConnectionFactory;

import java.sql.Connection;
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
                        "idProducto INT AUTO_INCREMENT PRIMARY KEY," +
                        " nombre VARCHAR(255)," +
                        " valor FLOAT)";
            stmt.executeUpdate(sql);
            ConnectionFactory.getInstance().disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertar(Producto producto) {

    }
}

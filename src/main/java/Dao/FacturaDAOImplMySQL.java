package Dao;

import Modelo.Factura;
import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FacturaDAOImplMySQL implements FacturaDao{
    private Connection connection;

    public FacturaDAOImplMySQL(Connection connection){
        this.connection = connection;
    }
    public void crear_tabla() {
        try {
            Statement stmt = this.connection.createStatement();
            String sql = "CREATE TABLE Factura("+
                        "idFactura INT AUTO_INCREMENT PRIMARY KEY,"+
                        "idCliente INT,"+
                        "FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente))";
            stmt.executeUpdate(sql);
            ConnectionFactory.getInstance().disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertar(Factura factura) {
        try {
            String sql ="INSERT INTO Factura(idCliente) VALUE (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, factura.getIdCliente());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

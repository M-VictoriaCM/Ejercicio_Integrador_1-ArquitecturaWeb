package Dao;

import Modelo.FacturaProducto;
import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FacturaProductoDaoImplMySQL implements FacturaProductoDao{
    private Connection connection;

    public FacturaProductoDaoImplMySQL(Connection connection){
        this.connection = connection;
    }

    public void crear_tabla() {
        try {
            Statement stmt = this.connection.createStatement();
            String sql = "CREATE TABLE Factura_Producto("+
                        "idFactura INT,"+
                        "idProducto INT,"+
                        "cantidad  INT,"+
                        "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura),"+
                        "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
            stmt.executeUpdate(sql);
            ConnectionFactory.getInstance().disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertar(FacturaProducto facturaProducto) {

    }
}

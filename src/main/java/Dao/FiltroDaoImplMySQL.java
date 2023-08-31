package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FiltroDaoImplMySQL implements FiltroDao {
    private Connection connection;

    public FiltroDaoImplMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void productoMayorRecaudacionVentas() {
        try {
            String sql = "SELECT p.nombre as nombreProducto, SUM(fp.cantidad) * p.valor as cantidadRecaudada, p.idProducto" +
                    " FROM producto p" +
                    " JOIN factura_producto fp ON (p.idProducto = fp.idProducto)" +
                    " GROUP BY p.idProducto" +
                    " ORDER BY cantidadRecaudada DESC" +
                    " LIMIT 1";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idProducto = resultSet.getInt("p.idProducto");
                String nombreProducto = resultSet.getString("nombreProducto");
                double cantidadRecaudada = resultSet.getDouble("cantidadRecaudada");
                System.out.println("id Producto: "+ idProducto+ ", Nombre del Producto: " + nombreProducto + ", Cantidad Recaudada: $" + cantidadRecaudada);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

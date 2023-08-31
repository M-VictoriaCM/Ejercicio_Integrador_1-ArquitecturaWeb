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

    @Override
    public void listadeClientesPorMayorFacturacion() {
        try {
            String sql = "select c.idCliente, c.nombre,SUM(fp.cantidad*p.valor) AS total_facturado\n" +
                    "from cliente c \n" +
                    "inner join factura f \n" +
                    "on c.idCliente = f.idCliente \n" +
                    "inner join factura_producto fp \n" +
                    "on f.idFactura = fp.idFactura\n" +
                    "inner join producto p \n" +
                    "on fp.idProducto = p.idProducto\n" +
                    "GROUP BY c.nombre\n" +
                    "ORDER BY total_facturado desc";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idCliente = resultSet.getInt("c.idCliente");
                String nombreCliente = resultSet.getString("c.nombre");
                double totalFacturado = resultSet.getDouble("total_facturado");
                System.out.println("id Cliente: " + idCliente + ", Nombre del cliente: " + nombreCliente + ", Total Facturado: $" + totalFacturado);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

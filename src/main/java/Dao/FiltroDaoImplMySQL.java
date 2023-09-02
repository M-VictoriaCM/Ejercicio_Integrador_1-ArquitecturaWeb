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
            String sql = "SELECT p.idProducto, p.nombre, SUM(fp.cantidad * p.valor) as cantidadRecaudada" +
                    " FROM producto p" +
                    " JOIN factura_producto fp ON (p.idProducto = fp.idProducto)" +
                    " GROUP BY p.idProducto, p.nombre" +
                    " ORDER BY cantidadRecaudada DESC";


            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idProducto = resultSet.getInt("idProducto");
                String nombreProducto = resultSet.getString("nombre");
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
                    "GROUP BY c.nombre, c.idCliente\n" +
                    "ORDER BY total_facturado desc";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idCliente = resultSet.getInt("idCliente");
                String nombreCliente = resultSet.getString("nombre");
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

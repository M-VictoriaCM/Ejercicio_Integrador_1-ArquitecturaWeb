package Main;

import Dao.DAOFactory;
import Dao.FiltroDaoImplMySQL;
import Modelo.Cliente;
import Modelo.Factura;
import Modelo.FacturaProducto;
import Modelo.Producto;
import connection.ConnectionFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import java.io.File;
import java.io.FileReader;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        /*~~~~~~~~~~~~~~~~~~ Creación de las tablas ~~~~~~~~~~~~~~~~~~
        DAOFactory.getClienteDao(ConnectionFactory.MYSQL).crear_tabla();
        DAOFactory.getProductoDao(ConnectionFactory.MYSQL).crear_tabla();
        DAOFactory.getFacturaDao(ConnectionFactory.MYSQL).crear_tabla();
        DAOFactory.getFacturaProductoDao(ConnectionFactory.MYSQL).crear_tabla();*/


         /*~~~~~~~~~~~~~~~~~~~~~~ Carga de datos ~~~~~~~~~~~~~~~~~~~~
        cargarDatoCSV("clientes.csv", ConnectionFactory.getInstance().connection());
        cargarDatoCSV("productos.csv", ConnectionFactory.getInstance().connection());
        cargarDatoCSV("facturas.csv", ConnectionFactory.getInstance().connection());
        cargarDatoCSV("facturas-productos.csv", ConnectionFactory.getInstance().connection());*/

        /*~~~~~~~~~~~~~~~~~~~~~~ Filtro ~~~~~~~~~~~~~~~~~~~~*/
        DAOFactory.getFiltro(ConnectionFactory.MYSQL).productoMayorRecaudacionVentas();
        DAOFactory.getFiltro(ConnectionFactory.MYSQL).listadeClientesPorMayorFacturacion();
    }

    private static void cargarDatoCSV(String nombreArchivo, Connection connection) {
        try{
            File archivo = new File("src/main/java/dataset/"+nombreArchivo);

            String pathArchivo = archivo.getPath();
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(pathArchivo));

            for(CSVRecord row: parser){
                //Lleno la tabla depende del archivo pasado
                switch (nombreArchivo){
                    case "clientes.csv":
                        int idCliente = Integer.parseInt(row.get("idCliente"));
                        String nombre = row.get("nombre");
                        String email = row.get("email");
                        DAOFactory.getClienteDao(ConnectionFactory.MYSQL).insertar(new Cliente(idCliente, nombre, email));
                        break;
                    case "facturas.csv":
                        int idFactura = Integer.parseInt(row.get("idFactura"));
                        int idClien = Integer.parseInt(row.get("idCliente"));
                        DAOFactory.getFacturaDao(ConnectionFactory.MYSQL).insertar(new Factura(idFactura, idClien));
                        break;
                    case "facturas-productos.csv":
                        int idFac = Integer.parseInt(row.get("idFactura"));
                        int idProduc = Integer.parseInt(row.get("idProducto"));
                        int cant = Integer.parseInt(row.get("cantidad"));
                        DAOFactory.getFacturaProductoDao(ConnectionFactory.MYSQL).insertar(new FacturaProducto(idFac,idProduc,cant));
                        break;
                    case "productos.csv":
                        int idProducto = Integer.parseInt(row.get("idProducto"));
                        String nom = row.get("nombre");
                        float valor = Float.parseFloat(row.get("valor"));
                        DAOFactory.getProductoDao(ConnectionFactory.MYSQL).insertar(new Producto(idProducto,nom,valor));
                        break;
                    default:
                        System.out.println("ruta no encontrada");
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
   /* SELECT p.nombre as nombreProducto , SUM(fp.cantidad) * p.valor as cantidadRecaudada from producto p join factura_producto fp on (p.idProducto = fp.idProducto)
        group by p.nombre
        order by cantidadRecaudada desc
        limit 1*/
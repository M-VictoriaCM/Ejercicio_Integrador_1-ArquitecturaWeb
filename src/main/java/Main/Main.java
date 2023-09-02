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

        /*~~~~~~~~~~~~~~~~~~ Creación del esquema MYSQL~~~~~~~~~~~~~~~~~~*/
        DAOFactory.getCrearEsquema(ConnectionFactory.MYSQL);
        /*~~~~~~~~~~~~~~~~~~ Creación de las tablas MYSQL~~~~~~~~~~~~~~~~~~*/
        DAOFactory.getClienteDao(ConnectionFactory.MYSQL).crear_tabla();
        DAOFactory.getProductoDao(ConnectionFactory.MYSQL).crear_tabla();
        DAOFactory.getFacturaDao(ConnectionFactory.MYSQL).crear_tabla();
        DAOFactory.getFacturaProductoDao(ConnectionFactory.MYSQL).crear_tabla();

        /*~~~~~~~~~~~~~~~~~~ Creación de las tablas DERBY~~~~~~~~~~~~~~~~~~*/
        DAOFactory.getClienteDao(ConnectionFactory.DERBY).crear_tabla();
        DAOFactory.getProductoDao(ConnectionFactory.DERBY).crear_tabla();
        DAOFactory.getFacturaDao(ConnectionFactory.DERBY).crear_tabla();
        DAOFactory.getFacturaProductoDao(ConnectionFactory.DERBY).crear_tabla();

         /*~~~~~~~~~~~~~~~~~~~~~~ Carga de datos MYSQL~~~~~~~~~~~~~~~~~~~~*/
        cargarDatoCSVMYSQL("clientes.csv", ConnectionFactory.getInstance().connection());
        cargarDatoCSVMYSQL("productos.csv", ConnectionFactory.getInstance().connection());
        cargarDatoCSVMYSQL("facturas.csv", ConnectionFactory.getInstance().connection());
        cargarDatoCSVMYSQL("facturas-productos.csv", ConnectionFactory.getInstance().connection());

        /*~~~~~~~~~~~~~~~~~~~~~~ Carga de datos DERBY~~~~~~~~~~~~~~~~~~~~*/
        cargarDatoCSVDERBY("clientes.csv", ConnectionFactory.getInstance().connection());
        cargarDatoCSVDERBY("productos.csv", ConnectionFactory.getInstance().connection());
        cargarDatoCSVDERBY("facturas.csv", ConnectionFactory.getInstance().connection());
        cargarDatoCSVDERBY("facturas-productos.csv", ConnectionFactory.getInstance().connection());

        /*~~~~~~~~~~~~~~~~~~~~~~ Filtro MYSQL~~~~~~~~~~~~~~~~~~~~*/
        System.out.println("FILTRO MYSQL");
        System.out.println("Producto que mas recaudo");
        DAOFactory.getFiltro(ConnectionFactory.MYSQL).productoMayorRecaudacionVentas();
        System.out.println("");
        System.out.println("Lista de clientes ordenada por facturacion");
        DAOFactory.getFiltro(ConnectionFactory.MYSQL).listadeClientesPorMayorFacturacion();
        System.out.println("");
        /*~~~~~~~~~~~~~~~~~~~~~~ Filtro DERBY~~~~~~~~~~~~~~~~~~~~*/
        System.out.println("FILTRO DERBY");
        System.out.println("Producto que mas recaudo");
        DAOFactory.getFiltro(ConnectionFactory.DERBY).productoMayorRecaudacionVentas();
        System.out.println("");
        System.out.println("Lista de clientes ordenada por facturacion");
        DAOFactory.getFiltro(ConnectionFactory.DERBY).listadeClientesPorMayorFacturacion();
    }
    /*~~~~~~~~~~~~~~~~~~~~~~ CARGA DE DATOS MYSQL ~~~~~~~~~~~~~~~~~~~~*/
    private static void cargarDatoCSVMYSQL(String nombreArchivo, Connection connection) {
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
    /*~~~~~~~~~~~~~~~~~~~~~~ CARGA DE DATOS DERBY ~~~~~~~~~~~~~~~~~~~~*/
    private static void cargarDatoCSVDERBY(String nombreArchivo, Connection connection) {
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
                        DAOFactory.getClienteDao(ConnectionFactory.DERBY).insertar(new Cliente(idCliente, nombre, email));
                        break;
                    case "facturas.csv":
                        int idFactura = Integer.parseInt(row.get("idFactura"));
                        int idClien = Integer.parseInt(row.get("idCliente"));
                        DAOFactory.getFacturaDao(ConnectionFactory.DERBY).insertar(new Factura(idFactura, idClien));
                        break;
                    case "productos.csv":
                        int idProducto = Integer.parseInt(row.get("idProducto"));
                        String nom = row.get("nombre");
                        float valor = Float.parseFloat(row.get("valor"));
                        DAOFactory.getProductoDao(ConnectionFactory.DERBY).insertar(new Producto(idProducto,nom,valor));
                        break;
                    case "facturas-productos.csv":
                        int idFac = Integer.parseInt(row.get("idFactura"));
                        int idProduc = Integer.parseInt(row.get("idProducto"));
                        int cant = Integer.parseInt(row.get("cantidad"));
                        DAOFactory.getFacturaProductoDao(ConnectionFactory.DERBY).insertar(new FacturaProducto(idFac,idProduc,cant));
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

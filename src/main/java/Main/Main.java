package Main;

import Dao.DAOFactory;
import connection.ConnectionFactory;

public class Main {
    public static void main(String[] args) {
        /*~~~~~~~~~~~~~~~~~~ Creación de las tablas ~~~~~~~~~~~~~~~~~~*/
        DAOFactory.getClienteDao(ConnectionFactory.MYSQL).crear_tabla();
        DAOFactory.getProductoDao(ConnectionFactory.MYSQL).crear_tabla();
        DAOFactory.getFacturaDao(ConnectionFactory.MYSQL).crear_tabla();
        DAOFactory.getFacturaProductoDao(ConnectionFactory.MYSQL).crear_tabla();
         /*~~~~~~~~~~~~~~~~~~~~~~ Carga de datos ~~~~~~~~~~~~~~~~~~~~*/
         //cargarDatoCSV("clientes.csv", ConnectionFactory.getInstance().connection());
    }

    private static void cargarDatoCSV(String nombreArchivo, Connection connection) {
        try{
            File archivo = new File("./dataset/", nombreArchivo);
            //System.out.println("ruta"+archivo);
            String pathArchivo = archivo.getPath();
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(pathArchivo));

            for(CSVRecord row: parser){
                //Logica de la tabla cliente
                if(nombreArchivo.equals("clientes.csv")){
                    int idCliente = Integer.parseInt(row.get("idCliente"));
                    String nombre = row.get("nombre");
                    String email = row.get("email");

                    DAOFactory.getClienteDao(ConnectionFactory.MYSQL).insertar(new Cliente(idCliente, nombre, email));
                }
                //Else if para  las demas tablas
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

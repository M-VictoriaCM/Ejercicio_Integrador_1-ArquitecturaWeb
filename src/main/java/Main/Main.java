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
    }
}

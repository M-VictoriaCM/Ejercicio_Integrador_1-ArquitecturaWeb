package Dao;

import connection.ConnectionFactory;

import java.sql.Connection;

public class DAOFactory {
    public DAOFactory() {
    }
    public static ClienteDao getClienteDao(String type){
        if(type.equals("mysql")){
            Connection connection =ConnectionFactory.getInstance().connect(ConnectionFactory.MYSQL);
            return new ClienteDAOImplMySQL(connection);
        }else if(type.equals("derby")){
            Connection connection =ConnectionFactory.getInstance().connect(ConnectionFactory.DERBY);
            return new ClienteDAOImplMySQL(connection);
        }else{
            throw new IllegalArgumentException("El tipo"+type+" no es valido");
        }
    }
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static ProductoDao getProductoDao(String type){
        if(type.equals("mysql")){
            Connection connection =ConnectionFactory.getInstance().connect(ConnectionFactory.MYSQL);
            return new ProductoDAOImplMySQL(connection);
        }else if(type.equals("derby")){
            Connection connection =ConnectionFactory.getInstance().connect(ConnectionFactory.DERBY);
            return new ProductoDAOImplMySQL(connection);
        }else{
            throw new IllegalArgumentException("El tipo"+type+" no es valido");
        }
    }
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static FacturaDao getFacturaDao(String type){
        if(type.equals("mysql")){
            Connection connection =ConnectionFactory.getInstance().connect(ConnectionFactory.MYSQL);
            return new FacturaDAOImplMySQL(connection);
        }else if(type.equals("derby")){
            Connection connection =ConnectionFactory.getInstance().connect(ConnectionFactory.DERBY);
            return new FacturaDAOImplMySQL(connection);
        }else{
            throw new IllegalArgumentException("El tipo"+type+" no es valido");
        }
    }
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static FacturaProductoDao getFacturaProductoDao(String type){
        if(type.equals("mysql")){
            Connection connection =ConnectionFactory.getInstance().connect(ConnectionFactory.MYSQL);
            return new FacturaProductoDaoImplMySQL(connection);
        }else if(type.equals("derby")){
            Connection connection =ConnectionFactory.getInstance().connect(ConnectionFactory.DERBY);
            return new FacturaProductoDaoImplMySQL(connection);
        }else{
            throw new IllegalArgumentException("El tipo"+type+" no es valido");
        }
    }
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static FiltroDao getFiltro(String type){
        if(type.equals("mysql")){
            Connection connection =ConnectionFactory.getInstance().connect(ConnectionFactory.MYSQL);
            return new FiltroDaoImplMySQL(connection);
        }else if(type.equals("derby")){
            Connection connection =ConnectionFactory.getInstance().connect(ConnectionFactory.DERBY);
            return new FiltroDaoImplMySQL(connection);
        }else{
            throw new IllegalArgumentException("El tipo"+type+" no es valido");
        }
    }

    public static void getCrearEsquema(String type) {
        if(type.equals("mysql")){
            ConnectionFactory.getInstance().crearEsquema();
        }
    }
}

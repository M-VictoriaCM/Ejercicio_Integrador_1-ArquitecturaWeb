package connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory  {
        public static final String MYSQL = "mysql";
        public static final String DERBY = "derby";

        private String URL = "jdbc:mysql://localhost:3306/";
        private String nombreDB = "integrador1_mysql";
        private String user = "root";
        private String pass = "root";
        private static ConnectionFactory instance = new ConnectionFactory();
        private java.sql.Connection connection;

        private ConnectionFactory() {
        }

        public static ConnectionFactory getInstance() {
            return instance;
        }
    /*~~~~~~~~~~~~~~~~~~ Conexión a la DB ~~~~~~~~~~~~~~~~~~*/
        public java.sql.Connection connect(String type) {
            if (this.connection != null) {
                this.disconnect();
            }
            if (type.equals(MYSQL)) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    this.connection = DriverManager.getConnection(URL+nombreDB, user, pass);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (type.equals(DERBY)) {
                try {
                    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                    this.connection = DriverManager.getConnection("jdbc:derby:integrador1_derby;create=true");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return this.connection;
        }

        public java.sql.Connection connection() {
            return this.connection;
        }
    /*~~~~~~~~~~~~~~~~~~ Creación del esquema MYSQL~~~~~~~~~~~~~~~~~~*/
        public void crearEsquema(){
            try {
                this.connection = DriverManager.getConnection(URL,user,pass);
                Statement statement = connection.createStatement();
                String obtenerBasesDeDatos = "SHOW DATABASES";
                ResultSet resultSet = statement.executeQuery(obtenerBasesDeDatos);
                boolean baseDeDatosExistente = false;
                while (resultSet.next()){
                    String nombreBaseDatos = resultSet.getString(1);
                    if (nombreBaseDatos.equals(nombreDB)){
                        baseDeDatosExistente=true;
                        break;
                    }
                }
                if (!baseDeDatosExistente){
                    String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS "+nombreDB;
                    statement.executeUpdate(createDatabaseSQL);
                    System.out.println("Esquema creado exitosamente.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    /*~~~~~~~~~~~~~~~~~~ Desconexión de la DB ~~~~~~~~~~~~~~~~~~*/
        public void disconnect() {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
}

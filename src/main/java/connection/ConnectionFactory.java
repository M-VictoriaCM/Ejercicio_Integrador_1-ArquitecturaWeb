package connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Esta clase proporciona una conexión a bases de datos MySQL o Derby
 * y facilita la creación de esquemas en bases de datos MySQL.
 */
public class ConnectionFactory  {
        /**
         * Tipo de base de datos MySQL.
         */
        public static final String MYSQL = "mysql";
        /**
         * Tipo de base de datos Derby.
         */
        public static final String DERBY = "derby";

        private String URL = "jdbc:mysql://localhost:3306/";
        private String nombreDB = "integrador1_mysql";
        private String user = "root";
        private String pass = "root";


        private static ConnectionFactory instance = new ConnectionFactory();
        private java.sql.Connection connection;

        private ConnectionFactory() {
        }

        /**
         * Obtiene una instancia única de la fábrica de conexiones.
         *
         * @return La instancia de ConnectionFactory.
         */
        public static ConnectionFactory getInstance() {
            return instance;
        }
    /*~~~~~~~~~~~~~~~~~~ Conexión a la DB ~~~~~~~~~~~~~~~~~~*/

        /**
         * Conecta a la base de datos especificada por el tipo.
         *
         * @param type El tipo de base de datos (ConnectionFactory.MYSQL o ConnectionFactory.DERBY).
         * @return Una conexión a la base de datos.
         */
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

        /**
         * Obtiene la conexión actual a la base de datos.
         *
         * @return La conexión a la base de datos.
         */
        public java.sql.Connection connection() {
            return this.connection;
        }
    /*~~~~~~~~~~~~~~~~~~ Creación del esquema MYSQL~~~~~~~~~~~~~~~~~~*/

        /**
         * Crea el esquema de la base de datos MySQL si no existe.
         */
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

        /**
         * Desconecta la base de datos si está conectada.
         */
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

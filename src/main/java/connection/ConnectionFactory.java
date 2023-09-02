package connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory  {
        public static final String MYSQL = "mysql";
        public static final String DERBY = "derby";

    private static ConnectionFactory instance = new ConnectionFactory();
        private java.sql.Connection connection;

        private ConnectionFactory() {
        }

        public static ConnectionFactory getInstance() {
            return instance;
        }

        public java.sql.Connection connect(String type) {

            if (this.connection != null) {
                this.disconnect();
            }

            if (type.equals(MYSQL)) {
                if (this.connection==null){
                    try {
                        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
                        Statement statement = connection.createStatement();
                        String createDatabaseSQL = "CREATE DATABASE integrador1_mysql";
                        statement.executeUpdate(createDatabaseSQL);
                        System.out.println("Esquema creado exitosamente.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/integrador1_mysql", "root", "root");
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

package connection;

import java.sql.DriverManager;
import java.sql.SQLException;

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
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/integrador1", "root", "root");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (type.equals(DERBY)) {
                try {
                    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                    this.connection = DriverManager.getConnection("jdbc:derby:integrador2;create=true");
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

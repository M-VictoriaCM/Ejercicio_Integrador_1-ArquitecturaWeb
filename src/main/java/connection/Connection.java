package connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
        public static final String MYSQL = "mysql";

        private static Connection instance = new Connection();
        private java.sql.Connection connection;

        private Connection() {
        }

        public static Connection getInstance() {
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

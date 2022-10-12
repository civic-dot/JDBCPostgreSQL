import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FBConnection {
    public static Properties properties = new Properties();
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("FireBird JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return null;
        }
        connection = null;

        try {
            connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.login"), properties.getProperty("db.password"));

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }
        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
        return connection;
    }
}

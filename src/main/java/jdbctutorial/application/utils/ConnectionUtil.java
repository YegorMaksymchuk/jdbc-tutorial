package jdbctutorial.application.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static final String DRIVER = ConfigUtil.getProp("driver"); //"com.mysql.jdbc.Driver";
    public static final String URL = ConfigUtil.getProp("url");       //"jdbc:mysql://localhost:3306/jdbc_tutorial";
    public static final String USER = ConfigUtil.getProp("user");     //"root";
    public static final String PASS = ConfigUtil.getProp("pass");     //"root";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

package shoppingCart.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
    public static Connection con = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            if (con == null) {
                // Correct driver class name
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Connection URL, username, and password
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_cart", "root", "0110");
                System.out.println("Connected");
            }
            return con;
        } catch (Exception ex) {
            // Log the error message
            System.out.println("Connection error: " + ex.getMessage());
            throw ex; // Rethrow the exception to handle it properly
        }
    }
}

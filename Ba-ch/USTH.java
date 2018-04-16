package USTHEquipmentSQL;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import jjava.sql.Statement;

public class Main {

public class SQLiteJDBCDriverConnection {
    public static void connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:C://USTH/USTH.db";
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

public static void createNewDatabase(String fileName) {
 
        String url = "jdbc:sqlite:C://USTH/" + fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

public static void createNewTable() {
        String url = "jdbc:sqlite:C://USTH/USTH.db";

	String sql = "CREATE TABLE IF NOT EXISTS equipment (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	quantity real\n"
		+ "     manufacturer text\n"
		+ "     year int\n"
                + ");";
	try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

public static void main(String[] args) {
        connect();
	createNewDatabase("USTH.db");
	createNewTable();
    }
}

}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class DB {
    public static void NewDatabase(String fileName) {

        String url = "jdbc:sqlite:C:/sqlite/" + fileName;
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
        String url = "jdbc:sqlite:C://sqlite/database.db";

        String sql = "CREATE TABLE IF NOT EXISTS Equipment (\n"
                + "	id text PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	manufacture text,\n"
                + " description text,\n"
                + " year integer,\n"
                + " type integer,\n"
                + " quantity integer\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String args[]){
        NewDatabase("database.db");
        createNewTable();
    }
}

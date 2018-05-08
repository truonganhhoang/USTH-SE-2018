import java.net.URL;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;

public class Equipment {

    private static Connection conn = null;

    private static Connection getConnection() {
        String url = "jdbc:sqlite:C://sqlite/database.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }

    public static void insert(String id,String name, String manufacture,String description, int year, int type, int quantity ) {
        String sql = "INSERT INTO Equipment(id,name,manufacture,description,year,type,quantity) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, manufacture);
            pstmt.setString(4, description);
            pstmt.setInt(5, year);
            pstmt.setInt(6, type);
            pstmt.setInt(7, quantity);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("ID Existed, try another ID");
        }
    }

    public static void read() {
        String sql = "SELECT id, name, manufacture, description, year, type, quantity FROM Equipment";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            DBTablePrinter.printTable(conn, "Equipment");
            System.out.println("Type 0 = Projector\t Type 1 = Monitor \t Type 2 = PC \t Type 3 = Laptop \t Type 4 = Table \t Type 5 = Telescope \n" );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("There is no existing item in database");
        }
    }

    public static void update() {
        String sql = "UPDATE Equipment SET name = ? , "
                + "manufacture = ?,"
                + "description = ?,"
                + "year = ? ,"
                + "type = ?,"
                + "quantity = ?"
                + " WHERE id = ?";
        String id;
        String name;
        String manufacture;
        String description;
        int year;
        int type;
        int quantity;

        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Input ID");
        id = sc.next();
        System.out.println("Input name");
        name = sc.next();
        System.out.println("Input manufacture");
        manufacture = sc.next();
        System.out.println("Input description");
        description = sc.next();
        System.out.println("Input year");
        try {
            year = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Since this item doesn't have a manufactured date, it is set to 1970 by default");
            year = 1970;
        }

        System.out.println("Input type");
        System.out.println("Type 0 = Projector\t Type 1 = Monitor \t Type 2 = PC \t Type 3 = Laptop \t Type 4 = Table \t Type 5 = Telescope \n" );
        int iType;
        try{
            iType = sc.nextInt();
        }catch (InputMismatchException ex){
            System.out.println("An item must have a type, It is set to PROJECTOR by default");
            iType = 0;
        }

        System.out.println("Input Quantity");
        try{
            quantity = sc.nextInt();
        }catch (InputMismatchException ex){
            System.out.println("Quantity is set to 1 by default");
            quantity =1;
        }
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(7, id);
            pstmt.setString(1, name);
            pstmt.setString(2, manufacture);
            pstmt.setString(3, description);
            pstmt.setInt(4, year);
            pstmt.setInt(5, iType);
            pstmt.setInt(6, quantity);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void delete() {
        String sql = "DELETE FROM Equipment WHERE id = ?";
        String id;
        String name;
        String manufacture;
        String description;
        int year;
        int type;
        int quantity;

        Scanner sc = new Scanner(System.in).useDelimiter("\n");

        System.out.println("Input id to delete");
        id = sc.next();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("The database is either busy or locked, try again");
        }

        System.out.println("Deleted ID");
    }

    public enum EquipmentType {
        PROJECTOR,
        MONITOR,
        PC,
        LAPTOP,
        TABLE,
        TELESCOPE
    }

    public static void showHint() {
        System.out.println("Press 1 to input data");
        System.out.println("Press 2 to access database");
        System.out.println("Press 3 to edit database");
        System.out.println("Press 4 to delete an item from database");
        System.out.println("Press other buttons to escape");
    }

    public static void InputEquipment() {
        String id, description, manufacture, name;
        int year;
        int quantity;
        EquipmentType type;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Input id");
        id = sc.next();
        System.out.println("Input name");
        name = sc.next();
        System.out.println("Input manufacture");
        manufacture = sc.next();
        System.out.println("Input description");
        description = sc.next();
        System.out.println("Input year");
        try {
            year = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Since this item doesn't have a manufactured date, it is set to 1970 by default");
            year = 1970;
        }

        System.out.println("Input type");
        System.out.println("Type 0 = Projector\t Type 1 = Monitor \t Type 2 = PC \t Type 3 = Laptop \t Type 4 = Table \t Type 5 = Telescope \n" );
        int iType;
        try {
            iType = sc.nextInt();
        } catch (InputMismatchException ex) {
            iType = 0;
        }
        switch (iType) {
            case 0:
                type = EquipmentType.PROJECTOR;
                break;

            case 1:
                type = EquipmentType.MONITOR;
                break;

            case 2:
                type = EquipmentType.PC;
                break;

            case 3:
                type = EquipmentType.LAPTOP;
                break;
            case 4:
                type = EquipmentType.TABLE;
                break;
            case 5:
                type = EquipmentType.TELESCOPE;
                break;

            default:
                type = EquipmentType.PROJECTOR;
        }
        System.out.println("Input quantity");
        try {
            quantity = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Quantity is set to 1 by default");
            quantity = 1;
        }
        insert(id,name,manufacture,description,year,iType,quantity);
    }

    public static void main(String args[]) {
        showHint();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String res = sc.next();
            switch (res) {
                case "1":
                    InputEquipment();
                    showHint();
                    continue;
                case "2":
                    read();
                    showHint();
                    continue;
                case "3":
                    update();
                    showHint();
                    continue;
                case "4":
                    delete();
                    showHint();
                    continue;
            default:
                break;
        }
        break;
        }

    }

}

package productmanagement;

import java.sql.*;
import java.util.Scanner;

public class ProductPriceUpdater {

    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_db","root", "lalli@2007");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        PreparedStatement ps1 =con.prepareStatement("SELECT * FROM products WHERE id=?");
        ps1.setInt(1, id);
        ResultSet rs = ps1.executeQuery();

        while (rs.next()) {
            System.out.println("Current Price: " + rs.getDouble("price"));

            System.out.print("Enter New Price: ");
            double price = sc.nextDouble();

            PreparedStatement ps2 = con.prepareStatement( "UPDATE products SET price=? WHERE id=?");
            ps2.setDouble(1, price);
            ps2.setInt(2, id);
            ps2.executeUpdate();

            System.out.println("Price updated successfully");
        }

        con.close();
    }
}

package jdbc;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class Product {
    public static void main(String[] args) throws SQLException {
        Dotenv dotenv = Dotenv.load();
        Connection connection = DriverManager.getConnection(dotenv.get("JDBC_URL"),dotenv.get("JDBC_USER"), dotenv.get("JDBC_PASSWORD"));
//        Statement statement = connection.createStatement();
//        String sql ="create table product(id INT,pname VARCHAR(255) ,quantity INT,price INT,delivery VARCHAR(300));";
//        boolean res = statement.execute(sql);

//        Statement statement = connection.createStatement();
//        String insertQuery = "insert into product(id,pname,quantity,price,delivery) values(1,'pods',1,200,'jp nagar')";
//        statement.addBatch(insertQuery);
//        String updateQuery = "update product set delivery ='uppal' where id=1; ";
//        statement.addBatch(updateQuery);
//        int[] arr = statement.executeBatch();
//        System.out.println("number of query affected "+arr.length);
        insert(connection);
    }

    public static void insert(Connection connection) throws SQLException {
        String sql = "insert into product(id,pname,quantity,price,delivery) values(?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        System.out.println("enter the number of products insertion :");
        Scanner scanner = new Scanner(System.in);
        int numbers = scanner.nextInt();
        for (int i=0; i<numbers; i++){
            System.out.print("Enter product ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter product name: ");
            String product = scanner.nextLine();
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            System.out.print("Enter price: ");
            int price = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter delivery location: ");
            String delivery = scanner.nextLine();
            statement.setInt(1, id);
            statement.setString(2, product);
            statement.setInt(3, quantity);
            statement.setInt(4, price);
            statement.setString(5, delivery);
            statement.executeUpdate();
            System.out.println("Inserted product with id: " + id);
        }
    }


}

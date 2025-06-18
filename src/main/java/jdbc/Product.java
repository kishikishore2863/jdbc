package jdbc;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
    }

//    public void insert(Connection connection) throws SQLException {
//        String sql = "insert into product(id,pname,quantity,price,delivery) values(?,?,?,?)";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        System.out.println("enter the number of products insertion :");
//        Scanner scanner = new Scanner(System.in);
//        int numbers = scanner.nextInt();
//        for (int i=0; i<numbers; i++){
//            String sql = "insert into product(id,pname,quantity,price,delivery) values(?,?,?,?)";
//            int id = scanner.nextInt();
//            String product = scanner.nextLine();
//            int quantity =scanner.nextInt();
//            int price =scanner.nextInt();
//            String delivery = scanner.nextLine();
//            statement.setInt()
//        }
//
//
//    }


}

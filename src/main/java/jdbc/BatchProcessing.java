package jdbc;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessing {
    public static void main(String[] args) throws SQLException {
        Dotenv dotenv = Dotenv.load();
        Connection connection = DriverManager.getConnection(dotenv.get("JDBC_URL"),dotenv.get("JDBC_USER"),dotenv.get("JDBC_PASSWORD"));

        Statement statement = connection.createStatement();
        String insertQuery = "insert into student (id, name, dob) values (108, 'kishore', '2003-06-28')";
        statement.addBatch(insertQuery);
        String updateQuery = "update student set name ='kishikish' where id=108";
        statement.addBatch(updateQuery);
        int[] arr = statement.executeBatch();
        System.out.println("the number of rows affected "+arr.length);
    }
}

package jdbc;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class Retrive {
    public static void main(String[] args) throws SQLException {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("JDBC_URL");
        String user = dotenv.get("JDBC_USER");
        String password = dotenv.get("JDBC_PASSWORD");
        Connection connection = DriverManager.getConnection(url,user,password);

        Statement statement = connection.createStatement();
        String query = "select * from student";
        ResultSet res = statement.executeQuery(query);

        while (res.next()){
            System.out.println(res.getInt(1));
            System.out.println(res.getString(2));
            System.out.println(res.getDate(3));
        }


    }
}

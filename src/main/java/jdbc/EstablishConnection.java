package jdbc;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class EstablishConnection {
    public static void main(String[] args) {
        try {
            Dotenv dotenv = Dotenv.load();

            String url = dotenv.get("JDBC_URL");
            String user = dotenv.get("JDBC_USER");
            String password = dotenv.get("JDBC_PASSWORD");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("connection Success");

//            Statement statement = connection.createStatement();
//            String sql = "create table student(id int ,name varchar(10),dob date)";
//            boolean res = statement.execute(sql);

//            Statement statement = connection.createStatement();
//            String sql = "alter table student add COLUMN address varchar(10)";
//            boolean a = statement.execute(sql);

//            Statement statement = connection.createStatement();
//            String sql = "ALTER TABLE student DROP COLUMN address";
//            boolean a = statement.execute(sql);

//            Statement statement = connection.createStatement();
//            String sql = "insert into student values(101,'kishi','2003-06-28');";
//            boolean res = statement.execute(sql);

//            Statement statement = connection.createStatement();
//            String sql = "insert into student values(102,'akash','2003-07-29');";
//            boolean res = statement.execute(sql);

//            Statement statement = connection.createStatement();
//            String sql = "insert into student values(103,'suman','2003-06-28');";
//            boolean res = statement.execute(sql);
//            System.out.println(res);

//            Statement statement = connection.createStatement();
//            String sql = "update student set name = 'siya' where id = 103";
//            int a = statement.executeUpdate(sql);
//            System.out.println(a);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

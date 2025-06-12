package MetaData;

import jdbc.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scroll {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con = JDBC.getConnection();
        String sql = "Select * from employee";
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = stmt.executeQuery(sql);
        resultSet.afterLast();
        while (resultSet.previous() == true){
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("department"));
            System.out.println(resultSet.getString("company"));
        }
    }
}

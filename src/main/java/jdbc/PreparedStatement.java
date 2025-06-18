package jdbc;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PreparedStatement {
    public static void main(String[] args) throws SQLException {
        Dotenv dotenv = Dotenv.load();
        Connection connection = DriverManager.getConnection(dotenv.get("JDBC_URL"),dotenv.get("JDBC_USER"),dotenv.get("JDBC_PASSWORD"));

        java.sql.PreparedStatement statement = connection.prepareStatement("insert into student values(?,?,?)");
        statement.setInt(1,104);
        statement.setString(2,"kavaya");
        statement.setDate(3, java.sql.Date.valueOf("2003-09-19"));

        statement.setInt(1,105);
        statement.setString(2,"king");
        statement.setDate(3, java.sql.Date.valueOf("2003-09-19"));

        statement.setInt(1,106);
        statement.setString(2,"harry");
        statement.setDate(3, java.sql.Date.valueOf("2003-09-19"));

        statement.setInt(1,107);
        statement.setString(2,"jhon");
        statement.setDate(3, java.sql.Date.valueOf("2003-09-19"));

        int res = statement.executeUpdate();

    }
}

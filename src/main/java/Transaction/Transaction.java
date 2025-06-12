package Transaction;

import jdbc.JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = JDBC.getConnection();
        String query1 = " insert into employee(name,department,company,salary,phoneNumber)values('chandru','software Engineer','tcs',50000,1234567890)";
        String query2 = " insert into employee(name,department,company,salary,phoneNumber)values('kavya','software Engineer','tcs',100000,1234567890)";
        String query3 = " insert into employee(name,department,company,salary,phoneNumber)values('badboy','software Engineer','tcs',50000,1234567890)";

        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        statement.addBatch(query1);
        statement.addBatch(query2);
        statement.addBatch(query3);
        statement.executeBatch();
        connection.commit();

    }
}

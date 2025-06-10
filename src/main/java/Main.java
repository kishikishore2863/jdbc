import jdbc.JDBC;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection con = JDBC.getConnection();
            JDBC.createEmployeeTable(con);
            JDBC.insertEmployee(con, "harry", "developer", "wipro", 60000, "8888888888");
            JDBC.updatePhoneNumberById(con, 1, "9999999999");
            JDBC.fetchEmployeeById(con, 1);
            JDBC.fetchEmployeesByCompany(con, "tcs");
            JDBC.fetchAllEmployees(con);
            JDBC.insertMultipleEmployees(con);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package jdbc;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.Scanner;

public class JDBC {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("JDBC_URL");
        String user = dotenv.get("JDBC_USER");
        String password = dotenv.get("JDBC_PASSWORD");
        Connection con = DriverManager.getConnection(url, user, password);

        if (con == null) {
            System.out.println("Connection is not established");
        } else {
            System.out.println("Connection is established");
        }
        return con;
    }

    public static void createEmployeeTable(Connection con) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS employee ("
                   + "id INT PRIMARY KEY AUTO_INCREMENT, "
                   + "name VARCHAR(100) NOT NULL, "
                   + "department VARCHAR(100), "
                   + "company VARCHAR(100), "
                   + "salary DOUBLE, "
                   + "phoneNumber VARCHAR(15))";
        try (java.sql.Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Employee table created or already exists.");
        }
    }

    public static void insertEmployee(Connection con, String name, String department, String company, double salary, String phoneNumber) throws SQLException {
        String sql = "INSERT INTO employee (name, department, company, salary, phoneNumber) VALUES (?, ?, ?, ?, ?)";
        try (java.sql.PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, department);
            pstmt.setString(3, company);
            pstmt.setDouble(4, salary);
            pstmt.setString(5, phoneNumber);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " employee(s) inserted.");
        }
    }

    public static void dropEmployeeTable(Connection con) throws SQLException {
        String sql = "DROP TABLE IF EXISTS employee";
        try (java.sql.Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Employee table dropped if it existed.");
        }
    }

    public static void updateAllEmployeeSalaries(Connection con, double increment) throws SQLException {
        String sql = "UPDATE employee SET salary = salary + ?";
        try (java.sql.PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDouble(1, increment);
            int rows = pstmt.executeUpdate();
            System.out.println("Updated salary for " + rows + " employee(s).");
        }
    }

    public static void fetchAllEmployees(Connection con) throws SQLException {
        String sql = "SELECT * FROM employee";
        try (java.sql.Statement stmt = con.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                String company = rs.getString("company");
                double salary = rs.getDouble("salary");
                String phoneNumber = rs.getString("phoneNumber");
                System.out.printf("ID: %d, Name: %s, Department: %s, Company: %s, Salary: %.2f, PhoneNumber: %s%n",
                                  id, name, department, company, salary, phoneNumber);
            }
        }
    }

    public static void fetchEmployeeById(Connection con, int employeeId) throws SQLException {
        String sql = "SELECT * FROM employee WHERE id = ?";
        try (java.sql.PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String department = rs.getString("department");
                    String company = rs.getString("company");
                    double salary = rs.getDouble("salary");
                    String phoneNumber = rs.getString("phoneNumber");
                    System.out.printf("ID: %d, Name: %s, Department: %s, Company: %s, Salary: %.2f, PhoneNumber: %s%n",
                                      employeeId, name, department, company, salary, phoneNumber);
                } else {
                    System.out.println("No employee found with ID: " + employeeId);
                }
            }
        }
    }

    public static void fetchEmployeesByCompany(Connection con, String companyName) throws SQLException {
        String sql = "SELECT * FROM employee WHERE company = ?";
        try (java.sql.PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, companyName);
            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String department = rs.getString("department");
                    double salary = rs.getDouble("salary");
                    String phoneNumber = rs.getString("phoneNumber");
                    System.out.printf("ID: %d, Name: %s, Department: %s, Company: %s, Salary: %.2f, PhoneNumber: %s%n",
                                      id, name, department, companyName, salary, phoneNumber);
                    found = true;
                }
                if (!found) {
                    System.out.println("No employees found for company: " + companyName);
                }
            }
        }
    }

    public static void updatePhoneNumberById(Connection con, int employeeId, String newPhoneNumber) throws SQLException {
        String sql = "UPDATE employee SET phoneNumber = ? WHERE id = ?";
        try (java.sql.PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, newPhoneNumber);
            pstmt.setInt(2, employeeId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Phone number updated for employee ID: " + employeeId);
            } else {
                System.out.println("No employee found with ID: " + employeeId);
            }
        }
    }

    public static void insertMultipleEmployees(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of employees to insert: ");
        int n = Integer.parseInt(scanner.nextLine());


        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for employee " + (i + 1));
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Department: ");
            String department = scanner.nextLine();
            System.out.print("Company: ");
            String company = scanner.nextLine();
            System.out.print("Salary: ");
            double salary = Double.parseDouble(scanner.nextLine());
            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();

            insertEmployee(con, name, department, company, salary, phoneNumber);
        }
    }

    public static void view(String tableName) throws SQLException, ClassNotFoundException {
       Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from "+tableName+";");
        ResultSetMetaData metaData = resultSet.getMetaData();

        System.out.println(resultSet);

        int numberOfColumn = metaData.getColumnCount();
        for (int i=1; i<=numberOfColumn; i++){
            System.out.printf("%-20s",metaData.getColumnName(i));
        }
        System.out.println();

        while (resultSet.next()){
            for (int i=1; i<=numberOfColumn; i++){
                System.out.printf("%-20s",resultSet.getString(i));
            }
            System.out.println();
        }

        int [] arr = {1,2,3,4,5,6};
        for(int i= arr.length-1; i>=0; i--){
            System.out.println(arr[i]);
        }
    }


}
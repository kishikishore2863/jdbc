package jdbc;

import java.sql.*;

public class StatementsCreation {
    static Connection connection;

    static {
        try {
            connection = JDBC.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public StatementsCreation() throws SQLException, ClassNotFoundException {
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
         JDBC.view("employee");

    }

//    private static void view(String tableName) throws SQLException {
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from "+tableName+";");
//
//        ResultSetMetaData metaData = resultSet.getMetaData();
//
//        int numberColumn = metaData.getColumnCount();
//
//        for (int i=1; i<=numberColumn; i++){
//            System.out.printf("%-20s",metaData.getColumnName(i));
//        }
//        System.out.println();
//
//        while (resultSet.next()){
//            for (int i=1; i<=numberColumn; i++){
//                System.out.printf("%-20s",resultSet.getString(i));
//            }
//            System.out.println();
//        }
//
//    }
//

}

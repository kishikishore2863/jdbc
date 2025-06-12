package MetaData;

import jdbc.JDBC;

import java.sql.*;
import java.util.Scanner;

public class Meta {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con = JDBC.getConnection();

        String query ="Select * from employee where id=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        Scanner scanner =new Scanner(System.in);
        pstmt.setInt(1,scanner.nextInt());

        ResultSet res = pstmt.executeQuery();



        System.out.println("------------------");
        ResultSetMetaData metaData = res.getMetaData();
        int count = metaData.getColumnCount();
        for(int i=1; i<=count; i++){
            System.out.println(metaData.getColumnClassName(i));
            System.out.println(metaData.getColumnTypeName(i));
            System.out.println(metaData.getColumnName(i));
            System.out.println(metaData.getSchemaName(i));
            System.out.println(metaData.isNullable(i));
            System.out.println("------------------");
        }

    }



}

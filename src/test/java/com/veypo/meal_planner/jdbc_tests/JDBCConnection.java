package com.veypo.meal_planner.jdbc_tests;
import com.veypo.meal_planner.utilities.DB_Utils;
import org.junit.jupiter.api.Test;
import java.sql.*;

public class JDBCConnection {

    /*
        // DriverManager Class getConnection method will help to connect to database :
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);  // via Connection String = dbUrl, dbUsername, dbPassword

        // It helps us execute queries :
        Statement statement = connection.createStatement();

        // ResultSet will store data after execution. It stores only data (there is no table info ) :
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments WHERE manager_id IS NOT null ");
        // resultSet.getInt(index)  --> returns integer. Indexes start from 1 in SQL. They refer to column position.

        // DatabaseMetaData  --> This is information about database itself :
        DatabaseMetaData dbMetaData = connection.getMetaData();

        System.out.println(dbMetaData.getUserName());
        System.out.println(dbMetaData.getDatabaseProductName());
        System.out.println(dbMetaData.getDatabaseProductVersion());
        System.out.println(dbMetaData.getDriverName());
        System.out.println(dbMetaData.getDriverVersion());

        // ResultSetMetaData --> provides information about table upper side (columnNames, columnCount)
        ResultSetMetaData rsmd = rs.getMetaData();

        // How many columns do we have
        int columnCount = rsmd.getColumnCount();
        System.out.println(columnCount);

        // get me column name from index 2
        System.out.println(rsmd.getColumnName(2));
        // it will return provided columnIndex name
     */

    @Test
    public void test1() {
        DB_Utils.createConnection();
        ResultSet rs = DB_Utils.runQuery("SELECT * FROM accounts");
        int rowCount = DB_Utils.getRowCount();
        System.out.println("rowCount = " + rowCount);
    }
}

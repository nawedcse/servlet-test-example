/*
 *
 */
package com.test.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
    public static Connection getConnection() throws SQLException { // making a static connection,shares to all classes
        Connection con = null; // creating connection
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");

        }
        catch (final Exception e) {
            con.close();
            // throws an error if at all its unable to create an connection
            System.out.println(e);
        }
        return con; // we return the connection and we can get the connection wherever needed.
    }
}

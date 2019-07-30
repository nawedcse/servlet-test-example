/*
 *
 */
package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.dbconnection.Dbconnection;
import com.test.model.User;
import com.test.model.UserPojo;

public class UserDAO {
    // private final String jdbcURL = "jdbc:postgresql://localhost:5432/postgres?useSSL=false";
    // private final String jdbcUsername = "postgres";
    // private final String jdbcPassword = "admin";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES " + " (?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

    public UserDAO() {
    }

    // protected Connection getConnection() {
    // Connection connection = null;
    // try {
    // Class.forName("org.postgresql.Driver");
    // System.out.println("Helloooo Driver are connected ");
    // connection = DriverManager.getConnection(this.jdbcURL, this.jdbcUsername, this.jdbcPassword);
    // }
    // catch (final SQLException e) {
    // e.printStackTrace();
    // }
    // catch (final ClassNotFoundException e) {
    // e.printStackTrace();
    // }
    // return connection;
    // }

    public void insertUser(final User user) throws SQLException {
        System.out.println(UserDAO.INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Dbconnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UserDAO.INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }
        catch (final SQLException e) {
            printSQLException(e);
        }
    }

    public User selectUser(final int id) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = Dbconnection.getConnection();
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(UserDAO.SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            final ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                final String name = rs.getString("name");
                final String email = rs.getString("email");
                final String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        }
        catch (final SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List<User> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        final List<User> users = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = Dbconnection.getConnection();

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(UserDAO.SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            final ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                final int id = rs.getInt("id");
                final String name = rs.getString("name");
                final String email = rs.getString("email");
                final String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        }
        catch (final SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteUser(final int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Dbconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(UserDAO.DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(final User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Dbconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(UserDAO.UPDATE_USERS_SQL);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(final SQLException ex) {
        for (final Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    // Registration user with Save Method ..............

    public static int save(final UserPojo pObject) {
        int flag = 0;
        try {
            final Connection con = Dbconnection.getConnection(); // getting the connection method here from dbconnection
            final PreparedStatement ps = con.prepareStatement("insert into reginfo values(?,?,?,?);");
            ps.setInt(1, pObject.getId());
            ps.setString(2, pObject.getUsername());// sending up the values received from user to the database table
            ps.setString(3, pObject.getPass());
            ps.setString(4, pObject.getEmail());
            flag = ps.executeUpdate(); // value changes if it is executed
            con.close();
        }
        catch (final Exception e) {
            System.out.println(e);
        }
        return flag; // returns greater than zero if inserted into database
    }

}

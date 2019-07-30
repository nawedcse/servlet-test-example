/*
 *
 */
package com.test.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.test.dbconnection.Dbconnection;
import com.test.model.UserPojo;

public class UserLoginValidate {
    public static List<UserPojo> getUsers(final int id, final String password) {
        final List<UserPojo> list = new ArrayList<>();
        // take a list to store the values which are in db
        try {
            final Connection con = Dbconnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("select * from reginfo where id=? and pass=?");
            ps.setInt(1, id);
            ps.setString(2, password);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                final UserPojo pObject = new UserPojo();
                pObject.setId(rs.getInt(1));
                pObject.setUsername(rs.getString(2)); // if the values exist in db then
                pObject.setPass(rs.getString(3)); // set them to setters and getters and them to list and return the list finally
                pObject.setEmail(rs.getString(4));
                list.add(pObject);
            }
            con.close();
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        return list; // returns the list
    }
}

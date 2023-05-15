package org.douban.dao;

import org.douban.model.User;
import org.douban.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    public User createUser(User user) throws SQLException {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("INSERT INTO users(user_name,password) VALUES (?, ?);");
        ) {
            st.setString(1, user.getUserName());
            st.setString(2, user.getPassword());
            st.executeUpdate();
            return user;
        }
    }
}

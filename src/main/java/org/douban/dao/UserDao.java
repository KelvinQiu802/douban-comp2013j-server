package org.douban.dao;

import org.douban.model.User;
import org.douban.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User createUserRecord(User user) throws SQLException {
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

    public User loginUser(User user) throws SQLException {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("SELECT user_name, password FROM users WHERE user_name " +
                        "= ?;");
        ) {
            st.setString(1, user.getUserName());
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                if (rs.getFetchSize() != 0) {
                    rs.next();
                } else {
                    return null;
                }
                String userName = rs.getString("user_name");
                String password = rs.getString("password");
                if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }
}

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
                PreparedStatement st = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE user_name " +
                        "= ? && password = ?;");
        ) {
            st.setString(1, user.getUserName());
            st.setString(2, user.getPassword());
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                rs.next();
                int count = rs.getInt(1);
                if (count == 0) {
                    return null;
                }
                return user;
            }
        }
    }
}

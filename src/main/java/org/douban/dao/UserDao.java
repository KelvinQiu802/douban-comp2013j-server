package org.douban.dao;

import org.douban.model.User;
import org.douban.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public List<String> getAllUserNames() throws SQLException {
        List<String> users = new ArrayList<>();
        try (
                Connection conn = DBUtils.connectToDB();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT user_name FROM users;");
        ) {
            while (rs.next()) {
                users.add(rs.getString("user_name"));
            }
            return users;
        }
    }

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
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count == 0) {
                        return null;
                    }
                    return user;
                }
                return null;
            }
        }
    }
}

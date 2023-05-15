package org.douban.dao;

import com.mysql.cj.protocol.Resultset;
import org.douban.model.User;
import org.douban.util.DBUtils;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public User userLogin(User user) throws SQLException{
        try (
            Connection conn = DBUtils.connectToDB();
            PreparedStatement st=conn.prepareStatement("SELECT user_name, password FROM users WHERE user_name=?;");
        ){
            st.setString(1,user.getUserName());
            ResultSet rs=st.executeQuery();
            rs.next();
            if(rs.getString("user_name")!=null){
                if(rs.getString("user_name").equals(user.getUserName())||rs.getString("password").equals(user.getPassword())){
                    return user;
                }
            }

        }
        return null;
    }
}

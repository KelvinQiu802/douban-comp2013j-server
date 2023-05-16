package org.douban.dao;

import org.douban.model.BookMark;
import org.douban.util.DBUtils;

import java.sql.*;

public class BookMarkDao {
    public BookMark createBookMark(BookMark bookMark) throws SQLException {
        try (
            Connection conn = DBUtils.connectToDB();
            PreparedStatement st = conn.prepareStatement("INSERT INTO bookmarks(user_name, movie_id, status) VALUES (?, ?, ?);");
        ) {
            System.out.println("Connect to the database");
            st.setString(1, bookMark.getUserName());
            st.setInt(2, bookMark.getMovieId());
            st.setString(3, String.valueOf(bookMark.getStatus()));
            st.executeUpdate();
            return bookMark;
        }
    }
}

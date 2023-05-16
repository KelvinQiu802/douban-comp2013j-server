package org.douban.dao;

import org.douban.model.Bookmark;
import org.douban.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookmarkDao {
    public Bookmark createBookMark(Bookmark bookMark) throws SQLException {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("INSERT INTO bookmarks(user_name, movie_id, status) VALUES (?, ?, ?);");
        ) {
            st.setString(1, bookMark.getUserName());
            st.setInt(2, bookMark.getMovieId());
            st.setString(3, String.valueOf(bookMark.getStatus()));
            st.executeUpdate();
            return bookMark;
        }
    }
}

package org.douban.dao;

import org.douban.model.Bookmark;
import org.douban.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookmarkDao {
    public Bookmark createBookmark(Bookmark bookmark) throws SQLException {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("INSERT INTO bookmarks(user_name, movie_id, status) VALUES (?, ?, ?);");
        ) {
            st.setString(1, bookmark.getUserName());
            st.setInt(2, bookmark.getMovieId());
            st.setString(3, String.valueOf(bookmark.getStatus()));
            st.executeUpdate();
            return bookmark;
        }
    }

    public Bookmark updateBookmark(Bookmark bookmark) throws SQLException{
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("UPDATE bookmarks SET status = ? where user_name = ? AND movie_id = ?;");
        ) {
            st.setString(1, String.valueOf(bookmark.getStatus()));
            st.setString(2, bookmark.getUserName());
            st.setInt(3, bookmark.getMovieId());
            st.executeUpdate();
            return bookmark;
        }
    }
}

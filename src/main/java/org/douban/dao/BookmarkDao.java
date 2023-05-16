package org.douban.dao;

import org.douban.model.Bookmark;
import org.douban.model.BookmarkStatus;
import org.douban.model.Movie;
import org.douban.model.User;
import org.douban.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public Bookmark updateBookmark(Bookmark bookmark) throws SQLException {
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

    public List<Bookmark> getBookmark(String userName) throws SQLException{
        List<Bookmark> bookmarks=new ArrayList<>();
        try (
                Connection conn=DBUtils.connectToDB();
                PreparedStatement st=conn.prepareStatement("SELECT * FROM bookmarks where user_name=? ;");
                ){
            st.setString(1,userName);
            try (ResultSet rs=st.executeQuery()){
                while(rs.next()){
                    bookmarks.add(constructBookmark(rs));
                }
            }
        }
        return bookmarks;
    }

    private Bookmark constructBookmark(ResultSet rs) throws SQLException {
        return new Bookmark(rs.getString("user_name"),rs.getInt("movie_id"),BookmarkStatus.valueOf(rs.getString("status")));
    }
}

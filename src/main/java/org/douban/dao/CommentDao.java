package org.douban.dao;

import org.douban.model.Comment;
import org.douban.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    public Comment createComment(Comment comment) throws SQLException {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("INSERT INTO comments (user_name, movie_id, " +
                        "content, time) VALUES (?, ?, ?, ?);");
        ) {
            st.setString(1, comment.getUserName());
            st.setInt(2, comment.getMovieId());
            st.setString(3, comment.getContent());
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            return comment;
        }
    }


    public void deleteComment(int id) throws SQLException {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("DELETE FROM comments WHERE comment_id = ?;");
        ) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }

    public List<Comment> getCommentByMovieId(int movieId) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM comments WHERE movie_id = ?;");
        ) {
            st.setInt(1, movieId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    comments.add(constructComment(rs));
                }
                return comments;
            }
        }
    }

    public Comment getCommentById(int id) throws SQLException {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM comments WHERE comment_id = ?;");
        ) {
            st.setInt(1, id);
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                if (rs.next()) {
                    return constructComment(rs);
                }
            }
            return null;
        }
    }

    /***
     * A helper method to create comment object.
     * @param rs Result Set
     * @return Comment Object
     * @throws SQLException SQLException
     */
    private Comment constructComment(ResultSet rs) throws SQLException {
        return new Comment(rs.getInt("comment_id"), rs.getString("user_name"),
                rs.getInt("movie_id"), rs.getString("content"),
                rs.getTimestamp("time").toString());
    }
}





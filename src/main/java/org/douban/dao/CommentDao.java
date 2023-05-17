package org.douban.dao;

import org.douban.model.Comment;
import org.douban.util.DBUtils;

import java.sql.*;

public class CommentDao {

    public Comment createComment(Comment comment) throws SQLException {
        try (
            Connection conn = DBUtils.connectToDB();
            PreparedStatement st = conn.prepareStatement("INSERT INTO comments (comment_id, user_name, movie_id, content, time) VALUES (?, ?, ?, ?, ?);");
        ) {
            st.setInt(1, comment.getCommentId());
            st.setString(2, comment.getUserName());
            st.setInt(3, comment.getMovieId());
            st.setString(4, comment.getContent());
            st.setDate(5, new Date(System.currentTimeMillis()));
            st.executeUpdate();
            return comment;
        }
    }
}

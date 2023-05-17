package org.douban.dao;

import org.douban.model.Comment;
import org.douban.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

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
}

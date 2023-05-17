package org.douban.dao;

import org.douban.model.CommentVote;
import org.douban.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentVoteDao {
    public CommentVote createVote(CommentVote vote) throws SQLException {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("INSERT INTO commentvotes (user_name, comment_id, " +
                        "status) VALUES (?, ?, ?);");
        ) {
            st.setString(1, vote.getUserName());
            st.setInt(2, vote.getCommentId());
            st.setString(3, vote.getStatus().toString());
            st.executeUpdate();
            return vote;
        }
    }

    public CommentVote updateVote(CommentVote vote) throws SQLException {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("UPDATE commentvotes SET status = ? WHERE " +
                        "user_name = ? AND comment_id = ?;");
        ) {
            st.setString(1, vote.getStatus().toString());
            st.setString(2, vote.getUserName());
            st.setInt(3, vote.getCommentId());
            st.executeUpdate();
            return vote;
        }
    }
}

package org.douban.dao;

import org.douban.model.CommentVote;
import org.douban.model.VoteStatus;
import org.douban.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<CommentVote> getVotes(int id) throws SQLException {
        List<CommentVote> votes = new ArrayList<>();
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM commentvotes WHERE comment_id = ?;");
        ) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    votes.add(new CommentVote(rs.getString("user_name"), id,
                            VoteStatus.valueOf(rs.getString("status"))));
                }
                return votes;
            }
        }
    }

    public void deleteVote(String userName, int id) throws SQLException {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("DELETE FROM commentvotes WHERE comment_id = ? AND " +
                        "user_name = ?;");
        ) {
            st.setInt(1, id);
            st.setString(2, userName);
            st.executeUpdate();
        }
    }
}

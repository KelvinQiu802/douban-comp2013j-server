package org.douban.dao;

import org.douban.model.Score;
import org.douban.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScoreDao {
    public Score createScoreRecord(Score score) throws SQLException {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("INSERT INTO scores (user_name, movie_id, score) " +
                        "VALUES (?, ?, ?);");
        ) {
            st.setString(1, score.getUserName());
            st.setInt(2, score.getMovieId());
            st.setInt(3, score.getScore());
            st.executeUpdate();
            return score;
        }
    }

    public Score updateScoreRecord(Score score) throws SQLException {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("UPDATE scores SET score = ? WHERE user_name = ? AND " +
                        "movie_id = ?;");
        ) {
            st.setInt(1, score.getScore());
            st.setString(2, score.getUserName());
            st.setInt(3, score.getMovieId());
            st.executeUpdate();
            return score;
        }
    }
}

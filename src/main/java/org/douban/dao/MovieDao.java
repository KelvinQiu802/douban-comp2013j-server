package org.douban.dao;

import org.douban.model.Movie;
import org.douban.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
    public List<Movie> getTopTenMovies() throws SQLException {
        List<Movie> result = new ArrayList<>();
        try (
                Connection conn = DBUtils.connectToDB();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM movies LIMIT 10;");
        ) {
            while (rs.next()) {
                result.add(constructMovie(rs));
            }
        }
        return result;
    }

    public Movie getMovieById(int id) throws SQLException {
        try (
                Connection conn = DBUtils.connectToDB();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM movies WHERE movie_id = ?;");
        ) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                rs.next();
                return constructMovie(rs);
            }
        }
    }

    /***
     * A helper method to create movie object.
     * @param rs Result Set
     * @return Movie Object
     * @throws SQLException SQLException
     */
    private Movie constructMovie(ResultSet rs) throws SQLException {
        return new Movie(rs.getInt("movie_id"), rs.getString("country"),
                rs.getString("intro"), rs.getString("movie_title"),
                rs.getString("starring"), rs.getString("language"),
                rs.getString("directedBy"), rs.getString("runtime"),
                rs.getString("release_date"), rs.getString("genre"),
                rs.getString("img_url"), rs.getString("abstract"),
                rs.getDouble("score"));
    }
}

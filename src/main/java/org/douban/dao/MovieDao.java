package org.douban.dao;

import org.douban.model.Movie;
import org.douban.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

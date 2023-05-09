package org.douban.controller;

import io.javalin.http.Context;
import org.douban.dao.MovieDao;

import java.sql.SQLException;

public class MovieController {
    private MovieDao movieDao;

    public MovieController() {
        movieDao = new MovieDao();
    }

    public void topTenMovies(Context ctx) {
        try {
            ctx.json(movieDao.getTopTenMovies());
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        }
    }
}

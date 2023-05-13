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

    public void movieById(Context ctx) {
        try {
            String idStr = ctx.pathParam("id");
            int id = Integer.parseInt(idStr);
            ctx.json(movieDao.getMovieById(id));
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        } catch (NumberFormatException e) {
            ctx.result("Invalid Input Number").status(400);
        }
    }

    public void movieUpdateScore(Context ctx) {
        try {
            String idStr = ctx.pathParam("id");
            int id = Integer.parseInt(idStr);
            String scoreStr = ctx.pathParam("score");
            double score = Double.parseDouble(scoreStr);
            ctx.json(movieDao.updateMovieScoreById(id, score));
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        } catch (NumberFormatException e) {
            ctx.result("Invalid Input Number").status(400);
        }
    }
}

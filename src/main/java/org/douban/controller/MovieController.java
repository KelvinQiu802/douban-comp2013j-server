package org.douban.controller;

import io.javalin.http.Context;
import org.douban.dao.MovieDao;
import org.douban.util.ParamUtil;

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

    public void movieByPage(Context ctx) {
        try {
            int page = ParamUtil.queryIntParamWithDefault(ctx, "page", 1);
            int limit = ParamUtil.queryIntParamWithDefault(ctx, "limit", 15);
            if (page <= 0) {
                ctx.result("Page Number Starts from One").status(400);
            }
            ctx.json(movieDao.getMoviesByPage(page, limit));
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        } catch (NumberFormatException e) {
            ctx.result("Invalid Input Number").status(400);
        }
    }

    public void movieCount(Context ctx) {
        try {
            ctx.json(movieDao.getMovieCount());
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        }
    }

    public void updateMovieScore(Context ctx) {
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

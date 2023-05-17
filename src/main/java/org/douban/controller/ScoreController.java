package org.douban.controller;

import io.javalin.http.Context;
import org.douban.dao.ScoreDao;
import org.douban.model.Score;

import java.sql.SQLException;

public class ScoreController {
    private ScoreDao scoreDao;

    public ScoreController() {
        scoreDao = new ScoreDao();
    }

    public void scoresOfMovie(Context ctx) {
        try {
            String idStr = ctx.pathParam("movieId");
            int id = Integer.parseInt(idStr);
            ctx.json(scoreDao.getScoresByMovie(id));
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        } catch (NumberFormatException e) {
            ctx.result("Invalid Input").status(400);
        }
    }

    public void createScore(Context ctx) {
        try {
            String userName = ctx.pathParam("userName");
            String idStr = ctx.pathParam("movieId");
            String scoreStr = ctx.pathParam("score");
            Score score = new Score(userName, Integer.parseInt(idStr), Integer.parseInt(scoreStr));
            ctx.json(scoreDao.createScoreRecord(score));
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        } catch (NumberFormatException e) {
            ctx.result("Invalid Input").status(400);
        }
    }

    public void updateScore(Context ctx) {
        try {
            String userName = ctx.pathParam("userName");
            String idStr = ctx.pathParam("movieId");
            String scoreStr = ctx.pathParam("score");
            Score score = new Score(userName, Integer.parseInt(idStr), Integer.parseInt(scoreStr));
            ctx.json(scoreDao.updateScoreRecord(score));
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        } catch (NumberFormatException e) {
            ctx.result("Invalid Input").status(400);
        }
    }
}

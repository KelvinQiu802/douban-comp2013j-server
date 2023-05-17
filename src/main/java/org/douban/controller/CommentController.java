package org.douban.controller;

import io.javalin.http.Context;
import org.douban.dao.CommentDao;
import org.douban.model.Comment;

import java.sql.SQLException;

public class CommentController {

    CommentDao commentDao = new CommentDao();

    public void createComment(Context ctx) {
        try {
            Comment comment = ctx.bodyAsClass(Comment.class);
            Comment created = commentDao.createComment(comment);
            ctx.json(created);
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        }
    }

    public void deleteComment(Context ctx) {
        try {
            String commentIdStr = ctx.pathParam("commentId");
            int commentId = Integer.parseInt(commentIdStr);
            commentDao.deleteComment(commentId);
            ctx.result("Success").status(200);
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        } catch (NumberFormatException e) {
            ctx.result("Invalid Input").status(400);
        }
    }

    public void commentByMovieId(Context ctx) {
        try {
            String movieIdStr = ctx.pathParam("movieId");
            int movieId = Integer.parseInt(movieIdStr);
            ctx.json(commentDao.getCommentByMovieId(movieId));
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        } catch (NumberFormatException e) {
            ctx.result("Invalid Input Number").status(400);
        }
    }

    public void getCommentById(Context ctx) {
        try {
            String commentIdStr = ctx.pathParam("commentId");
            int commentId = Integer.parseInt(commentIdStr);
            ctx.json(commentDao.getCommentById(commentId));
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        } catch (NumberFormatException e) {
            ctx.result("Invalid Input Number").status(400);
        }
    }
}

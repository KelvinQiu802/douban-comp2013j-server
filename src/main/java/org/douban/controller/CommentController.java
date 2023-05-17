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
            e.printStackTrace();
            ctx.result("Internal Server Error").status(500);
        }
    }
}

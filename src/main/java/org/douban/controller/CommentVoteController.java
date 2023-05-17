package org.douban.controller;

import io.javalin.http.Context;
import org.douban.dao.CommentVoteDao;
import org.douban.model.CommentVote;
import org.douban.model.VoteStatus;

import java.sql.SQLException;

public class CommentVoteController {
    private CommentVoteDao commentVoteDao;

    public CommentVoteController() {
        this.commentVoteDao = new CommentVoteDao();
    }

    public void createVote(Context ctx) {
        try {

            String userName = ctx.pathParam("userName");
            String commentIdStr = ctx.pathParam("commentId");
            String status = ctx.pathParam("status");
            int commentId = Integer.parseInt(commentIdStr);
            CommentVote vote = new CommentVote(userName, commentId, VoteStatus.valueOf(status));
            ctx.json(commentVoteDao.createVote(vote));
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        } catch (NumberFormatException e) {
            ctx.result("Invalid Input").status(400);
        }
    }
}

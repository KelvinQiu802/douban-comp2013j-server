package org.douban.controller;

import io.javalin.http.Context;
import org.douban.dao.BookmarkDao;
import org.douban.model.Bookmark;
import org.douban.model.BookmarkStatus;

import java.sql.SQLException;

public class BookmarkController {
    private BookmarkDao bookmarkDao;

    public BookmarkController() {
        bookmarkDao = new BookmarkDao();
    }

    public void createBookmark(Context ctx) {
        try {
            String userStr = ctx.pathParam("userName");
            String movieIdStr = ctx.pathParam("movieId");
            int movieId = Integer.parseInt(movieIdStr);
            String statusStr = ctx.pathParam("status");
            Bookmark bookmark = new Bookmark(userStr, movieId, BookmarkStatus.valueOf(statusStr));
            ctx.json(bookmarkDao.createBookmark(bookmark));
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        } catch (NumberFormatException e) {
            ctx.result("Invalid Input").status(400);
        }
    }

    public void updateBookmark(Context ctx) {
        try {
            String userStr = ctx.pathParam("userName");
            String movieIdStr = ctx.pathParam("movieId");
            int movieId = Integer.parseInt(movieIdStr);
            String statusStr = ctx.pathParam("status");
            Bookmark bookmark = new Bookmark(userStr, movieId, BookmarkStatus.valueOf(statusStr));
            ctx.json(bookmarkDao.updateBookmark(bookmark));
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        } catch (NumberFormatException e) {
            ctx.result("Invalid Input").status(400);
        }
    }
}

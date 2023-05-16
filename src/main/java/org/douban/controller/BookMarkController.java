package org.douban.controller;

import io.javalin.http.Context;
import org.douban.dao.BookMarkDao;
import org.douban.model.BookMark;
import org.douban.model.BookMarkStatus;

import java.sql.SQLException;

public class BookMarkController {
    private BookMarkDao bookMarkDao;

    public BookMarkController() {
        bookMarkDao = new BookMarkDao();
    }

    public void createBookMark(Context ctx) {
        try {
            String userStr = ctx.pathParam("userName");
            String movieIdStr = ctx.pathParam("movieId");
            int movieId = Integer.parseInt(movieIdStr);
            String statusStr = ctx.pathParam("status");
            BookMark bookMark = new BookMark(userStr, movieId, BookMarkStatus.valueOf(statusStr));
            ctx.json(bookMarkDao.createBookMark(bookMark));
        } catch (SQLException e) {
            e.printStackTrace();
            ctx.result("Internal Server Error").status(500);
        }
    }
}

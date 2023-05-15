package org.douban.controller;

import io.javalin.http.Context;
import org.douban.dao.UserDao;
import org.douban.model.User;

import java.sql.SQLException;

public class UserController {
    private UserDao userDao;

    public UserController() {
        userDao = new UserDao();
    }

    public void allUsers(Context ctx) {
        try {
            ctx.json(userDao.getAllUserNames());
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        }
    }

    public void signupUser(Context ctx) {
        try {
            User user = ctx.bodyAsClass(User.class);
            User created = userDao.createUserRecord(user);
            ctx.json(created);
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        }
    }

    public void loginUser(Context ctx) {
        try {
            User user = ctx.bodyAsClass(User.class);
            User logined = userDao.loginUser(user);
            if (logined != null) {
                ctx.json(logined);
            } else {
                ctx.result("User name or password is wrong").status(401);
            }
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        }
    }
}

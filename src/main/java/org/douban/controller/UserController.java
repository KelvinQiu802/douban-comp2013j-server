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

    public void createUser(Context ctx) {
        try {
            User user = ctx.bodyAsClass(User.class);
            User created = userDao.createUser(user);
            ctx.json(created);
        } catch (SQLException e) {
            ctx.result("Internal Server Error").status(500);
        }
    }

    public void userLogin(Context ctx){
        try {
            User user=ctx.bodyAsClass(User.class);
            User loginUser=userDao.userLogin(user);
            ctx.json(loginUser);
        }catch (SQLException e){
            ctx.result("User name or password is wrong").status(401);
        }
    }
}

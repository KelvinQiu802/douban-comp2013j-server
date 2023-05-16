package org.douban;

import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import org.douban.controller.BookmarkController;
import org.douban.controller.MovieController;
import org.douban.controller.UserController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(
                config -> {
                    config.plugins.enableCors(cors -> {
                        cors.add(CorsPluginConfig::anyHost);
                    });
                }
        );

        MovieController movieController = new MovieController();
        UserController userController = new UserController();
        BookmarkController bookMarkController = new BookmarkController();

        app.get("/api/test", movieController::topTenMovies);

        app.get("/api/movies", movieController::movieByPage);

        app.get("/api/movies/count", movieController::movieCount);

        app.get("/api/movies/{id}", movieController::movieById);

        app.put("/api/movies/{id}/{score}", movieController::updateMovieScore);

        app.post("/api/users", userController::signupUser);

        app.post("/api/users/login", userController::loginUser);

        app.get("/api/users", userController::allUsers);

        app.post("/api/bookmarks/{userName}/{movieId}/{status}", bookMarkController::createBookmark);

        app.put("/api/bookmarks/{userName}/{movieId}/{status}", bookMarkController::updateBookmark);

        app.get("/api/bookmarks/{userName}",bookMarkController::getBookmark);

        app.start(7070);
    }
}
package org.douban;

import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import org.douban.controller.*;

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
        BookmarkController bookmarkController = new BookmarkController();
        ScoreController scoreController = new ScoreController();
        CommentController commentController = new CommentController();

        app.get("/api/test", movieController::topTenMovies);

        app.get("/api/movies", movieController::movieByPage);

        app.get("/api/movies/count", movieController::movieCount);

        app.get("/api/movies/{id}", movieController::movieById);

        app.post("/api/users", userController::signupUser);

        app.post("/api/users/login", userController::loginUser);

        app.get("/api/users", userController::allUsers);

        app.post("/api/bookmarks/{userName}/{movieId}/{status}", bookmarkController::createBookmark);

        app.put("/api/bookmarks/{userName}/{movieId}/{status}", bookmarkController::updateBookmark);

        app.get("/api/bookmarks/{userName}", bookmarkController::getBookmarksByUsername);

        app.delete("/api/bookmarks/{userName}/{movieId}", bookmarkController::deleteBookmark);

        app.post("/api/scores/{userName}/{movieId}/{score}", scoreController::createScore);

        app.put("/api/scores/{userName}/{movieId}/{score}", scoreController::updateScore);

        app.get("/api/scores/{movieId}", scoreController::scoresOfMovie);

        app.delete("/api/scores/{userName}/{movieId}", scoreController::deleteScore);

        app.post("/api/comments", commentController::createComment);

        app.delete("/api/comments/{commentId}", commentController::deleteComment);

         app.get("/api/comments/{commentId}", commentController::getCommentById);

         app.get("/api/comments/movie/{movieId}", commentController::commentByMovieId);

        app.start(7070);
    }
}

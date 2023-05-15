package org.douban;

import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import org.douban.controller.MovieController;

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

        app.get("/api/test", movieController::topTenMovies);

        app.get("/api/movies", movieController::movieByPage);

        app.get("/api/movies/count", movieController::movieCount);

        app.get("/api/movies/{id}", movieController::movieById);

        app.put("/api/movies/{id}/{score}", movieController::updateMovieScore);

        app.post("/api/users", movieController::createUser);

        app.start(7070);
    }
}
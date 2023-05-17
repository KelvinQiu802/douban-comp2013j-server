package org.douban.model;

public class Score {
    private String userName;
    private int movieId;
    private double score;

    public Score(String userName, int movieId, double score) {
        this.userName = userName;
        this.movieId = movieId;
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public int getMovieId() {
        return movieId;
    }

    public double getScore() {
        return score;
    }
}

package org.douban.model;

public class Bookmark {
    private String userName;
    private int movieId;
    private BookmarkStatus status;

    public Bookmark(String userName, int movieId, BookmarkStatus status) {
        this.userName = userName;
        this.movieId = movieId;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public int getMovieId() {
        return movieId;
    }

    public BookmarkStatus getStatus() {
        return status;
    }
}

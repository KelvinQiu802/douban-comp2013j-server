package org.douban.model;

public class Comment {
    private int commentId;
    private String userName;
    private int movieId;
    private String content;

    public Comment(String userName, int movieId, String content) {
        this.userName = userName;
        this.movieId = movieId;
        this.content = content;
    }

    public Comment() {
    }

    public int getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getUserName() {
        return userName;
    }
}

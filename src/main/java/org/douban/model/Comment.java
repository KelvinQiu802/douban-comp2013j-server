package org.douban.model;

import java.sql.Date;

public class Comment {
    private int commentId;
    private String userName;
    private int movieId;
    private String content;

    public Comment(int commentId, String userName, int movieId, String content) {
        this.commentId = Integer.parseInt(null);
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

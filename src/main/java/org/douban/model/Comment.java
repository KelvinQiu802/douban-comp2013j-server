package org.douban.model;

import java.util.Date;

public class Comment {
    private int commentId;
    private String userName;
    private int movieId;
    private String content;

    private Date date;

    public Comment(int commentId, String userName, int movieId, String content, Date date) {
        this.commentId = commentId;
        this.userName = userName;
        this.movieId = movieId;
        this.content = content;
        this.date = date;
    }

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

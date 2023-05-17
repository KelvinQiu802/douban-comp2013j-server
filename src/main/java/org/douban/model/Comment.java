package org.douban.model;

public class Comment {
    private int commentId;
    private String userName;
    private int movieId;
    private String content;
    private String time;

    public Comment(int commentId, String userName, int movieId, String content, String time) {
        this.commentId = commentId;
        this.userName = userName;
        this.movieId = movieId;
        this.content = content;
        this.time = time;
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

    public String getTime() {
        return time;
    }
}

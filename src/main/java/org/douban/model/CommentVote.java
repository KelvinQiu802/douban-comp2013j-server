package org.douban.model;

public class CommentVote {
    private String userName;
    private int commentId;
    private VoteStatus status;

    public CommentVote(String userName, int commentId, VoteStatus status) {
        this.userName = userName;
        this.commentId = commentId;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public int getCommentId() {
        return commentId;
    }

    public VoteStatus getStatus() {
        return status;
    }
}

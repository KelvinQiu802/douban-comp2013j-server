package org.douban.model;

public class BookMark {
    private String userName;
    private int movieId;
    private BookMarkStatus status;

    public BookMark(String user_name, int movie_id, BookMarkStatus status){
        this.userName = user_name;
        this.movieId = movie_id;
        this.status = status;
    }

    public String getUserName(){
        return userName;
    }

    public int getMovieId(){
        return movieId;
    }

    public BookMarkStatus getStatus(){
        return status;
    }
}

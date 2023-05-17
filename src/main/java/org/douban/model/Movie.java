package org.douban.model;

public class Movie {
    private int movieId;
    private String country;
    private String intro;
    private String movieTitle;
    private String starring;
    private String language;
    private String director;
    private String runtime;
    private String releaseDate;
    private String genre;
    private String imgUrl;
    private String abstractInfo;

    public Movie(int movieId, String country, String intro, String movieTitle,
                 String starring, String language, String director, String runtime,
                 String releaseDate, String genre, String imgUrl, String abstractInfo) {
        this.movieId = movieId;
        this.country = country;
        this.intro = intro;
        this.movieTitle = movieTitle;
        this.starring = starring;
        this.language = language;
        this.director = director;
        this.runtime = runtime;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.imgUrl = imgUrl;
        this.abstractInfo = abstractInfo;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getCountry() {
        return country;
    }

    public String getIntro() {
        return intro;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getStarring() {
        return starring;
    }

    public String getLanguage() {
        return language;
    }

    public String getDirector() {
        return director;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getAbstractInfo() {
        return abstractInfo;
    }
}

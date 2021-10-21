package com.example.f21comp1011gctest1student;

public class NetflixShow {
    private String showId;
    private String title;
    private String type;
    private String director;
    private String cast;
    private String rating;


    public NetflixShow(String showId, String title, String type, String director, String cast, String rating) {
        setShowId(showId);
        setTitle(title);
        setType(type);
        setDirector(director);
        setCast(cast);
        setRating(rating);


    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}


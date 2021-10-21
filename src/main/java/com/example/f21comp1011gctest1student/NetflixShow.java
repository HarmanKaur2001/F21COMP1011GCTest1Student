package com.example.f21comp1011gctest1student;

import java.util.Arrays;
import java.util.List;

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
        if (showId.startsWith("s"))
            this.showId = showId;
        else
            throw new IllegalArgumentException("START with s only");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.length()>=2)
        this.title = title;
        else
            throw new IllegalArgumentException("it should be more than 2");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        List<String> types = Arrays.asList("TV Show","Movie");
        if (types.contains(type))
            this.type = type;
        else
            throw new IllegalArgumentException("Type should be like this");
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        if (director.length ()>= 2)
            this.director = director;
        else
            throw new IllegalArgumentException("the director should hold more than 2 words");
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        if (cast.length()>=5)
            this.cast = cast;
        else
            throw new IllegalArgumentException("cast should more than 5 words");
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        List<String> ratings = Arrays.asList("All ratings","PG-13","R","TV-14","TV-G","TV-MA","TV-Y","TV-Y7");
        if (ratings.contains(rating))
            this.rating = rating;
        else
            throw new IllegalArgumentException("rating should include this only");
    }
}


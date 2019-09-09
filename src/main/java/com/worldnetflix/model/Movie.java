package com.worldnetflix.model;

import java.util.ArrayList;

public class Movie {

    private String image;
    private String title;
    private String netflixId;

    private String released;
    private String rating;
    private ArrayList<String> countries;

    public String getNetflixId() {
        return netflixId;
    }

    public void setNetflixId(String netflixId) {
        this.netflixId = netflixId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", netflixId='" + netflixId + '\'' +
                ", released='" + released + '\'' +
                ", rating='" + rating + '\'' +
                ", countries=" + countries +
                '}';
    }
}

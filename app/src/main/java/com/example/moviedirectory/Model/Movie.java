package com.example.moviedirectory.Model;

import java.io.Serializable;

public class Movie implements Serializable {
    private static final long id=1L;

    private String title;
    private String director;
    private String year;
    private String runtime;
    private String imdbID;
    private String poster;
    private String genre;
    private String writer;
    private String actors;
    private String plots;
    private String rating;
    private String dvdRelease;
    private String productionCompany;
    private String country;
    private String awards;
    private String tvRated;
    private String movieType;

    public Movie() {
    }

    public static long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlots() {
        return plots;
    }

    public void setPlots(String plots) {
        this.plots = plots;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDvdRelease() {
        return dvdRelease;
    }

    public void setDvdRelease(String dvdRelease) {
        this.dvdRelease = dvdRelease;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getTvRated() {
        return tvRated;
    }

    public void setTvRated(String tvRated) {
        this.tvRated = tvRated;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }
}

package com.example.moviebooking;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private String showtime;
    private double rating;
    private String summary;

    public Movie(int id, String title, String genre, String showtime, double rating, String summary) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.showtime = showtime;
        this.rating = rating;
        this.summary = summary;
    }

    // Getters
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getShowtime() { return showtime; }
    public double getRating() { return rating; }
    public String getSummary() { return summary; }

    @Override
    public String toString() {
        return title; // This will be used in the dropdown
    }
}
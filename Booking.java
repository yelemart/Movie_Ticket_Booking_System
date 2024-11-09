package com.example.moviebooking;

public class Booking {
    private Movie movie;
    private String userName;
    private String[] selectedSeats;

    public Booking(Movie movie, String userName, String[] selectedSeats) {
        this.movie = movie;
        this.userName = userName;
        this.selectedSeats = selectedSeats;
    }

    // Getters and toString method
}

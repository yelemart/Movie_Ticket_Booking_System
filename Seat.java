package com.example.moviebooking;

public class Seat {
    private String label;
    private boolean isAvailable;

    public Seat(String label) {
        this.label = label;
        this.isAvailable = true; // Default to available
    }

    public String getLabel() {
        return label;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

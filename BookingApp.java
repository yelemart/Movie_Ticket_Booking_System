package com.example.moviebooking;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BookingApp {
    private static Seat[] seats; // Array of Seat objects
    private static Random random = new Random(); // Random instance for seat availability
    private static Movie[] movies; // Array of Movie objects
    private static JCheckBox[] seatCheckboxes; // Declare seatCheckboxes at the class level

    public static void main(String[] args) {
        // Initialize movies
        movies = new Movie[]{
                new Movie(1, "Venom: The Last Dance", "Sci-Fi", "18:00", 8.2, "Eddie Brock and Venom must make a devastating decision."),
                new Movie(2, "The Wild Robot", "Animation", "20:00", 8.5, "Shipwrecked on a deserted island, a robot named Roz must learn to adapt to its new surroundings."),
                new Movie(3, "We Live in Time", "Romance", "22:00", 7.4, "Almut and Tobias are brought together by a surprise encounter that changes their lives.")
        };

        JFrame frame = new JFrame("Movie Booking System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JComboBox<Movie> movieDropdown = new JComboBox<>(movies);
        JLabel movieLabel = new JLabel("Select Movie:");
        movieLabel.setBounds(20, 20, 150, 25);
        movieDropdown.setBounds(150, 20, 200, 25);

        // Add ActionListener to show movie details when a movie is selected
        movieDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Movie selectedMovie = (Movie) movieDropdown.getSelectedItem();
                if (selectedMovie != null) {
                    showMovieDetails(selectedMovie);
                    randomizeSeatAvailability(seatCheckboxes); // Randomize seats when a movie is selected
                }
            }
        });

        // Email input
        JLabel emailLabel = new JLabel("Enter Your Email:");
        emailLabel.setBounds(20, 60, 150, 25);
        JTextField emailField = new JTextField(20);
        emailField.setBounds(150, 60, 200, 25);

        // Create an array of Seat objects
        seats = new Seat[10]; // Array for 10 seats
        String[] seatLabels = {"Seat A1", "Seat A2", "Seat A3", "Seat A4", "Seat A5",
                "Seat B1", "Seat B2", "Seat B3", "Seat B4", "Seat B5"};

        // Initialize Seat objects
        for (int i = 0; i < seats.length; i++) {
            seats[i] = new Seat(seatLabels[i]);
        }

        // Create checkboxes for seat selection
        seatCheckboxes = new JCheckBox[10]; // Initialize seatCheckboxes here
        for (int i = 0; i < seatCheckboxes.length; i++) {
            seatCheckboxes[i] = new JCheckBox(seats[i].getLabel());
            seatCheckboxes[i].setBounds(20 + (i % 5) * 120, 100 + (i / 5) * 30, 100, 25);
            seatCheckboxes[i].setSelected(false); // Uncheck by default
            frame.add(seatCheckboxes[i]); // Add seat checkboxes to the frame
        }

        JButton bookButton = new JButton("Book Ticket");
        bookButton.setBounds(150, 150, 150, 30);

        // Action Listener for booking
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMovie = ((Movie) movieDropdown.getSelectedItem()).getTitle();
                String userEmail = emailField.getText(); // Get the email from the text field

                // Collect selected seats
                StringBuilder selectedSeats = new StringBuilder("Selected Seats: ");
                boolean hasSelectedSeats = false;

                for (int i = 0; i < seatCheckboxes.length; i++) {
                    if (seatCheckboxes[i].isSelected() && seats[i].isAvailable()) {
                        selectedSeats.append(seats[i].getLabel()).append(" ");
                        hasSelectedSeats = true;
                    }
                }

                // Show a confirmation message with a thank you note
                if (hasSelectedSeats) {
                    // Proceed to payment
                    Payment payment = new Payment();
                    boolean paymentSuccess = payment.processPayment(userEmail); // Simulate payment processing

                    if (paymentSuccess) {
                        JOptionPane.showMessageDialog(frame, "Thank you for your booking!" +
                                "\nBooking for " + selectedMovie +
                                "\nCustomer Email: " + userEmail + "\n" + selectedSeats.toString());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Payment failed. Please try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select at least one available seat.");
                }

                // Clear the email field and uncheck seats for the next booking
                emailField.setText("");
                for (JCheckBox seatCheckbox : seatCheckboxes) {
                    seatCheckbox.setSelected(false); // Uncheck all seats
                }
            }
        });

        frame.add(movieLabel);
        frame.add(movieDropdown);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(bookButton);

        frame.setVisible(true); // Make the frame visible

        // Initial randomization of seats
        randomizeSeatAvailability(seatCheckboxes);
    }

    private static void showMovieDetails(Movie movie) {
        String message = "<html><b>Title:</b> " + movie.getTitle() +
                "<br><b>Genre:</b> " + movie.getGenre() +
                "<br><b>Showtime:</b> " + movie.getShowtime() +
                "<br><b>Rating:</b> " + movie.getRating() +
                "<br><b>Summary:</b> " + movie.getSummary() + "</html>";
        JOptionPane.showMessageDialog(null, message, "Movie Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void randomizeSeatAvailability(JCheckBox[] seatCheckboxes) {
        for (int i = 0; i < seats.length; i++) {
            boolean isAvailable = random.nextBoolean(); // Randomly assign availability
            seats[i].setAvailable(isAvailable); // Set availability in Seat object
            seatCheckboxes[i].setEnabled(isAvailable); // Enable or disable checkbox based on availability
            seatCheckboxes[i].setSelected(false); // Uncheck all seats when randomizing
        }
    }
}

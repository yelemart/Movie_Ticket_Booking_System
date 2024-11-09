package com.example.moviebooking;

import javax.swing.*;

public class Payment {
    public boolean processPayment(String email) {
        // Display payment options
        String[] paymentOptions = {"Credit Card", "PayPal", "Apple Pay", "Google Pay"};
        String selectedOption = (String) JOptionPane.showInputDialog(
                null,
                "Choose a payment method:",
                "Payment Options",
                JOptionPane.QUESTION_MESSAGE,
                null,
                paymentOptions,
                paymentOptions[0] // Default selection
        );

        if (selectedOption != null) {
            // Simulate payment processing logic based on the selected option
            // In a real application, you would integrate with a payment gateway here
            System.out.println("Processing payment via: " + selectedOption);
            return true; // Simulate successful payment
        } else {
            return false; // User canceled the payment selection
        }
    }
}

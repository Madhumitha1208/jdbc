package simple;

import java.awt.*;
import java.awt.event.*;

public class awtzz {

    public static void main(String[] args) {
        // Create frame
        Frame frame = new Frame("Login");

        // Create label, text field, and button components
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        TextField usernameField = new TextField();
        TextField passwordField = new TextField();
          // To hide password input
        Button loginButton = new Button("Login");

        // Set layout manager to null for custom positioning
        frame.setLayout(null);

        // Set bounds for components
        usernameLabel.setBounds(50, 100, 100, 30);
        usernameField.setBounds(150, 100, 200, 30);
        passwordLabel.setBounds(50, 150, 100, 30);
        passwordField.setBounds(150, 150, 200, 30);
        loginButton.setBounds(150, 200, 100, 30);

        // Add components to the frame
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);

        // Frame settings
        frame.setSize(400, 300);
        frame.setVisible(true);

        // Close window when clicking close button
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Button click action
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                // Basic login check (you can replace with actual logic)
                if (username.equals("svhec") && password.equals("aids")) {
                    System.out.println("Login Successful!");
                } else {
                    System.out.println("Login Failed!");
                }
            }
        });
    }
}

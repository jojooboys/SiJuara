/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.LoginFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jorda
 */

public class signupButton implements ActionListener {
    private JFrame frame;
    private javax.swing.JTextField fullnameField;
    private javax.swing.JTextField usernameField;
    private javax.swing.JTextField emailField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField confirmPasswordField;

    public signupButton(JFrame frame, javax.swing.JTextField fullnameField, javax.swing.JTextField usernameField, javax.swing.JTextField emailField, javax.swing.JPasswordField passwordField, javax.swing.JPasswordField confirmPasswordField) {
        this.frame = frame;
        this.fullnameField = fullnameField;
        this.usernameField = usernameField;
        this.emailField = emailField;
        this.passwordField = passwordField;
        this.confirmPasswordField = confirmPasswordField;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String fullname = fullnameField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        Integer admin = 0;

        if (AuthController.validateSignup(fullname, username, email, password, confirmPassword, admin)) {
            JOptionPane.showMessageDialog(frame, "Signup successful!");
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            loginFrame.pack();
            loginFrame.setLocationRelativeTo(null);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Signup failed. Please check your inputs.");
        }
    }
}


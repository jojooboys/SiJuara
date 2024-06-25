/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import Model.UserDao;
import View.DashboardAdmin;
import View.DashboardClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jorda
 */

public class loginButton implements ActionListener {
    private JFrame frame;
    private javax.swing.JTextField usernameField;
    private javax.swing.JPasswordField passwordField;

    public loginButton(JFrame frame, javax.swing.JTextField usernameField, javax.swing.JPasswordField passwordField) {
        this.frame = frame;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        UserDao userDao = new UserDao();
        User user = AuthController.validateLogin(username, password);

        if (user != null) {
            if (user.isAdmin() == 1) {  // Check admin value
                DashboardAdmin adminDashboard = new DashboardAdmin(user);
                adminDashboard.setVisible(true);
                adminDashboard.pack();
                adminDashboard.setLocationRelativeTo(null);
            } else if (user.isAdmin() == 0) {  // Check admin value
                DashboardClient clientDashboard = new DashboardClient(user);
                clientDashboard.setVisible(true);
                clientDashboard.pack();
                clientDashboard.setLocationRelativeTo(null);
            }
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password.");
        }
    }
}


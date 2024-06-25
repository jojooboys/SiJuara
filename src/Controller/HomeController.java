/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Database;
import Model.User;
import Model.UserDao;
import View.DashboardAdmin;
import View.DashboardClient;
import View.HomePanel;
import View.ProfilPanel;
import com.sun.jdi.connect.spi.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jorda
 */

public class HomeController {
    private HomePanel homePanel;
    private User user;
    private DashboardClient dashboardClient;
    private DashboardAdmin dashboardAdmin;

    public HomeController(HomePanel homePanel, User user, DashboardAdmin dashboardAdmin) {
        this.homePanel = homePanel;
        this.user = user;
        this.dashboardAdmin = dashboardAdmin;
    }
    
    public HomeController(HomePanel homePanel, User user, DashboardClient dashboardClient) {
        this.homePanel = homePanel;
        this.user = user;
        this.dashboardClient = dashboardClient;
    }

    public void updateFullname(String newFullname) {
        // Implementasi untuk memperbarui fullname di database
        String sql = "UPDATE users SET fullname = ? WHERE username = ?";
        try (java.sql.Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newFullname);
            pstmt.setString(2, user.getUsername());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                // Update berhasil, perbarui tampilan
                user.setFullname(newFullname); // Update object User
                homePanel.displayFullname(user); // Refresh display
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayFullname() {
        String fullname = UserDao.getFullname(user);
        if (fullname != null) {
            homePanel.setFullname(fullname);
        } else {
            homePanel.setFullname("User");
        }
    }

    public void switchToProfilPanel() {
        ProfilPanel profilPanel = new ProfilPanel(homePanel.getContentScrollPane(), user, dashboardClient);
        homePanel.getContentScrollPane().setViewportView(profilPanel);
    }
    
    public class profilButtonListener implements ActionListener {
        private HomePanel homePanel;

        public profilButtonListener(HomePanel homePanel) {
            this.homePanel = homePanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switchToProfilPanel();
        }
    }
}
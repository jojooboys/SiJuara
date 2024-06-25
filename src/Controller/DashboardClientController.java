/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import View.DaftarUKMPanel;
import View.DashboardClient;
import View.HomePanel;
import View.LoginFrame;
import View.ProfilPanel;
import View.akunPanel;
import View.listdaftarPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author jorda
 */

public class DashboardClientController {

    private DashboardClient dashboardClient;
    private User user;

    public DashboardClientController(DashboardClient dashboardClient, User user) {
        this.dashboardClient = dashboardClient;
        this.user = user;
    }

    public void switchToPanel(JPanel panel) {
        dashboardClient.getContentScrollPane().setViewportView(panel);
    }

    public class homeButton implements java.awt.event.ActionListener {
        private DashboardClientController controller;

        public homeButton(DashboardClientController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            homeButtonActionPerformed(evt);
        }

        private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
            controller.switchToPanel(new HomePanel(controller.dashboardClient.getContentScrollPane(), controller.user, controller.dashboardClient));
        }
    }

    public class profilButton implements java.awt.event.ActionListener {
        private DashboardClientController controller;

        public profilButton(DashboardClientController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            profilButtonActionPerformed(evt);
        }

        private void profilButtonActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
            controller.switchToPanel(new ProfilPanel(controller.dashboardClient.getContentScrollPane(), controller.user));
        }
    }
    
    public class daftarUKMButton implements java.awt.event.ActionListener {
        private DashboardClientController controller;

        public daftarUKMButton(DashboardClientController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            daftarUKMButtonActionPerformed(evt);
        }

        private void daftarUKMButtonActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
            controller.switchToPanel(new DaftarUKMPanel(controller.dashboardClient.getContentScrollPane(), controller.user));
        }
    }
    
    public class listdaftarButton implements java.awt.event.ActionListener {
        private DashboardClientController controller;

        public listdaftarButton(DashboardClientController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            listdaftarButtonActionPerformed(evt);
        }

        private void listdaftarButtonActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
            controller.switchToPanel(new listdaftarPanel(controller.dashboardClient.getContentScrollPane(), controller.user));
        }
    }
    
    
    public class akunButton implements java.awt.event.ActionListener {
        private DashboardClientController controller;

        public akunButton(DashboardClientController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            akunButtonActionPerformed(evt);
        }

        private void akunButtonActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
            controller.switchToPanel(new akunPanel(controller.dashboardClient.getContentScrollPane(), controller.user));
        }
    }
    
    public class LogoutButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(dashboardClient, "Anda yakin ingin Logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                dashboardClient.dispose();
                new LoginFrame().setVisible(true);
            }
        }
    }
}

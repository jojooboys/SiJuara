/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import View.DaftarUKMPanel;
import View.DashboardAdmin;
import View.HomePanel;
import View.LoginFrame;
import View.ProfilPanel;
import View.akunAdminPanel;
import View.listdaftarPanel;
import View.cetakDataAdminPanel;
import View.listdaftarAdminPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author jorda
 */

public class DashboardAdminController {

    private DashboardAdmin dashboardAdmin;
    private User user;

    public DashboardAdminController(DashboardAdmin dashboardAdmin, User user) {
        this.dashboardAdmin = dashboardAdmin;
        this.user = user;
    }

    public void switchToPanel(JPanel panel) {
        dashboardAdmin.getContentScrollPane().setViewportView(panel);
    }

    public class homeButton implements java.awt.event.ActionListener {
        private DashboardAdminController controller;

        public homeButton(DashboardAdminController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            homeButtonActionPerformed(evt);
        }

        private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
            controller.switchToPanel(new HomePanel(controller.dashboardAdmin.getContentScrollPane(), controller.user, controller.dashboardAdmin));
        }
    }

    public class profilButton implements java.awt.event.ActionListener {
        private DashboardAdminController controller;

        public profilButton(DashboardAdminController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            profilButtonActionPerformed(evt);
        }

        private void profilButtonActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
            controller.switchToPanel(new ProfilPanel(controller.dashboardAdmin.getContentScrollPane(), controller.user));
        }
    }
    
    public class daftarUKMButton implements java.awt.event.ActionListener {
        private DashboardAdminController controller;

        public daftarUKMButton(DashboardAdminController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            daftarUKMButtonActionPerformed(evt);
        }

        private void daftarUKMButtonActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
            controller.switchToPanel(new DaftarUKMPanel(controller.dashboardAdmin.getContentScrollPane(), controller.user));
        }
    }
    
    public class listdaftarButton implements java.awt.event.ActionListener {
        private DashboardAdminController controller;

        public listdaftarButton(DashboardAdminController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            listdaftarButtonActionPerformed(evt);
        }

        private void listdaftarButtonActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
            controller.switchToPanel(new listdaftarAdminPanel(controller.dashboardAdmin.getContentScrollPane(), controller.user));
        }
    }
    
    public class terdaftarButton implements java.awt.event.ActionListener {
        private DashboardAdminController controller;

        public terdaftarButton(DashboardAdminController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            terdaftarButtonActionPerformed(evt);
        }

        private void terdaftarButtonActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
            controller.switchToPanel(new cetakDataAdminPanel(controller.dashboardAdmin.getContentScrollPane(), controller.user));
        }
    }
    
    public class akunButton implements java.awt.event.ActionListener {
        private DashboardAdminController controller;

        public akunButton(DashboardAdminController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            akunButtonActionPerformed(evt);
        }

        private void akunButtonActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
            controller.switchToPanel(new akunAdminPanel(controller.dashboardAdmin.getContentScrollPane(), controller.user));
        }
    }
    
    public class LogoutButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(dashboardAdmin, "Anda yakin ingin Logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                dashboardAdmin.dispose();
                new LoginFrame().setVisible(true);
            }
        }
    }
}

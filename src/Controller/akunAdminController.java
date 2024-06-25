/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import Model.UserDao;
import View.akunAdminPanel;
import View.akunPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jorda
 */

public class akunAdminController {
    private UserDao userDao;

    public akunAdminController() {
        this.userDao = new UserDao();
    }

    public class gantiPasswordCheckBoxListener implements ActionListener {
        private akunAdminPanel view;

        public gantiPasswordCheckBoxListener(akunAdminController controller, akunAdminPanel view) {
            this.view = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            view.togglePasswordFields(view.isGantiPasswordChecked());
        }
    }

    public class submitButtonListener implements ActionListener {
        private akunAdminPanel view;

        public submitButtonListener(akunAdminController controller, akunAdminPanel view) {
            this.view = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            view.handleSubmit();
        }
    }

    public void handleSubmit(akunAdminPanel view, User user) {
        String oldUsername = user.getUsername();
        String fullname = view.getFullnameField().getText(); // Ambil nilai fullname
        String username = view.getUsernameField().getText();
        String email = view.getEmailField().getText();
        String currentPassword = new String(view.getPasswordField().getPassword());
        boolean isPasswordChange = view.isGantiPasswordChecked();

        if (userDao.isValidPassword(user, currentPassword)) {
            boolean updateResult;
            if (isPasswordChange) {
                String newPassword = new String(view.getNewPasswordField().getPassword());
                String confirmPassword = new String(view.getConfirmNewPasswordField().getPassword());

                if (!newPassword.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(view, "Password baru tidak cocok!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                updateResult = userDao.updateAllUsernames(oldUsername, username, email, fullname, newPassword); // Sertakan fullname
            } else {
                updateResult = userDao.updateAllUsernamesWithoutPassword(oldUsername, username, email, fullname); // Sertakan fullname
            }

            if (updateResult) {
                JOptionPane.showMessageDialog(view, "Data berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                user.setUsername(username); // Update username di objek user
                user.setEmail(email); // Update email di objek user
                user.setFullname(fullname); // Update fullname di objek user
            } else {
                JOptionPane.showMessageDialog(view, "Gagal memperbarui data!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Password saat ini salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Profil;
import Model.UserDao;
import View.ProfilPanel;
import Database.Database;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jorda
 */

public class ProfilController {

    private UserDao userDao;

    public ProfilController() {
        this.userDao = new UserDao();
    }

    public Profil getProfil(String username) {
        String sql = "SELECT u.fullname, p.username, p.kelas, p.jenis_kelamin, p.alamat FROM users u JOIN profil p ON u.username = p.username WHERE u.username = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("fullname");
                String kelas = rs.getString("kelas");
                String jenisKelamin = rs.getString("jenis_kelamin");
                String alamat = rs.getString("alamat");
                return new Profil(fullname, username, kelas, jenisKelamin, alamat);
            } else {
                System.out.println("No data found for username: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertProfil(Profil profil) {
        String sql = "INSERT INTO profil (fullname, kelas, jenis_kelamin, alamat) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, profil.getFullname());
            pstmt.setString(2, profil.getKelas());
            pstmt.setString(3, profil.getJenisKelamin());
            pstmt.setString(4, profil.getAlamat());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean saveOrUpdateProfil(Profil profil) {
        String selectSql = "SELECT username FROM profil WHERE username = ?";
        try (Connection conn = Database.connect();
             PreparedStatement selectPstmt = conn.prepareStatement(selectSql)) {
            selectPstmt.setString(1, profil.getUsername());
            ResultSet rs = selectPstmt.executeQuery();
            if (rs.next()) {
                // Data exists, perform update
                String updateProfilSql = "UPDATE profil SET kelas = ?, jenis_kelamin = ?, alamat = ? WHERE username = ?";
                String updateUserSql = "UPDATE users SET fullname = ? WHERE username = ?";
                try (PreparedStatement updateProfilPstmt = conn.prepareStatement(updateProfilSql);
                     PreparedStatement updateUserPstmt = conn.prepareStatement(updateUserSql)) {
                    conn.setAutoCommit(false); // Start transaction

                    // Update profil table
                    updateProfilPstmt.setString(1, profil.getKelas());
                    updateProfilPstmt.setString(2, profil.getJenisKelamin());
                    updateProfilPstmt.setString(3, profil.getAlamat());
                    updateProfilPstmt.setString(4, profil.getUsername());
                    int affectedRowsProfil = updateProfilPstmt.executeUpdate();

                    // Update users table
                    updateUserPstmt.setString(1, profil.getFullname());
                    updateUserPstmt.setString(2, profil.getUsername());
                    int affectedRowsUser = updateUserPstmt.executeUpdate();

                    if (affectedRowsProfil > 0 && affectedRowsUser > 0) {
                        conn.commit(); // Commit transaction if both updates are successful
                        return true;
                    } else {
                        conn.rollback(); // Rollback transaction if any update fails
                        return false;
                    }
                } catch (SQLException e) {
                    conn.rollback(); // Rollback transaction on error
                    e.printStackTrace();
                    return false;
                } finally {
                    conn.setAutoCommit(true); // Restore auto-commit mode
                }
            } else {
                // Data does not exist, perform insert
                return insertProfil(profil);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt, ProfilPanel panel) {
        String username = panel.getUser().getUsername();
        String fullname = panel.getTampilfullname().getText();
        String kelas = panel.getKelasField().getText();
        String jenisKelamin = (String) panel.getJenisKelaminBox().getSelectedItem();
        String alamat = panel.getAlamatTextArea().getText();

        // Update or insert data in the database
        Profil updatedProfil = new Profil(fullname, username, kelas, jenisKelamin, alamat);
        boolean isSaved = saveOrUpdateProfil(updatedProfil);

        if (isSaved) {
            JOptionPane.showMessageDialog(panel, "Data berhasil disimpan.");
        } else {
            JOptionPane.showMessageDialog(panel, "Gagal menyimpan data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void showdataButtonActionPerformed(java.awt.event.ActionEvent evt, ProfilPanel panel) {
        Profil profil = getProfil(panel.getUser().getUsername());
        if (profil != null) {
            panel.getTampilfullname().setText(profil.getFullname());
            panel.getTampilUsername().setText(profil.getUsername());
            panel.getKelasField().setText(profil.getKelas());
            panel.getJenisKelaminBox().setSelectedItem(profil.getJenisKelamin());
            panel.getAlamatTextArea().setText(profil.getAlamat());
        } else {
            JOptionPane.showMessageDialog(panel, "Data tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public class UpdateButtonListener implements ActionListener {
        private ProfilController controller;
        private ProfilPanel panel;
        
        public UpdateButtonListener(ProfilController controller, ProfilPanel panel) {
            this.controller = controller;
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            controller.updateButtonActionPerformed(evt, panel);
        }
    }

    public class ShowDataButtonListener implements ActionListener {
        private ProfilController controller;
        private ProfilPanel panel;

        public ShowDataButtonListener(ProfilController controller, ProfilPanel panel) {
            this.controller = controller;
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            controller.showdataButtonActionPerformed(evt, panel);
        }
    }
}

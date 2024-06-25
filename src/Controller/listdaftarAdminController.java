/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.listdaftarAdminPanel;
import Model.User;
import Database.Database;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author jorda
 */

public class listdaftarAdminController {
    private listdaftarAdminPanel view;
    private User user;

    public listdaftarAdminController(listdaftarAdminPanel view, User user) {
        this.view = view;
        this.user = user;
        view.addController(this);
    }

    public class TerimaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateStatus(1); // Diterima
        }
    }

    public class TolakButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateStatus(2); // Ditolak
        }
    }

    public class TundaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateStatus(0); // Pending/Tunda
        }
    }

    private void updateStatus(int status) {
        JTable table = view.getJTable();
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Please select a row first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String username = (String) table.getValueAt(selectedRow, 2);
        String ukm = (String) table.getValueAt(selectedRow, 4);

        String sql = "UPDATE formUKM SET status = ? WHERE username = ? AND ukm = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, status);
            pstmt.setString(2, username);
            pstmt.setString(3, ukm);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Status updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                view.loadTableData(); // Reload table data
            } else {
                JOptionPane.showMessageDialog(view, "Failed to update status.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
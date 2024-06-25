/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Database;
import Model.User;
import View.DaftarUKMPanel;
import View.listdaftarPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorda
 */


public class listdaftarController {
    private listdaftarPanel view;
    private User user;

    public listdaftarController(listdaftarPanel view, User user) {
        this.view = view;
        this.user = user;
        this.view.addController(this); // Menambahkan controller ke view
    }

    public class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            updateButtonActionPerformed(evt);
        }
    }

    public class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            deleteButtonActionPerformed(evt);
        }
    }

    private void updateButtonActionPerformed(ActionEvent evt) {
        JTable jTable1 = (JTable) view.getJTable();
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            String ukm = (String) jTable1.getValueAt(selectedRow, 0);
            String keterangan = (String) jTable1.getValueAt(selectedRow, 1);
            String motivasi = (String) jTable1.getValueAt(selectedRow, 2);

            Object prestasiObj = jTable1.getValueAt(selectedRow, 3);
            byte[] prestasi = null;
            if (prestasiObj instanceof ImageIcon) {
                prestasi = view.imageIconToByteArray((ImageIcon) prestasiObj);
            } else if (prestasiObj instanceof byte[]) {
                prestasi = (byte[]) prestasiObj;
            }

            DaftarUKMPanel daftarUKMPanel = new DaftarUKMPanel(view.getContentScrollPane(), user);
            view.getContentScrollPane().setViewportView(daftarUKMPanel);

            daftarUKMPanel.getUkmField().setText(ukm);
            daftarUKMPanel.getKeteranganField().setText(keterangan);
            daftarUKMPanel.getMotivasiTextArea().setText(motivasi);
            if (prestasi != null) {
                try {
                    File tempFile = File.createTempFile("prestasi_", ".tmp");
                    FileOutputStream fos = new FileOutputStream(tempFile);
                    fos.write(prestasi);
                    fos.close();
                    daftarUKMPanel.setPrestasiFilePath(tempFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteButtonActionPerformed(ActionEvent evt) {
        JTable jTable1 = (JTable) view.getJTable();
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            String ukm = (String) jTable1.getValueAt(selectedRow, 0);
            String sql = "DELETE FROM formUKM WHERE username = ? AND ukm = ?";
            try (java.sql.Connection conn = Database.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, ukm);
                pstmt.executeUpdate();
                view.loadTableData();
                JOptionPane.showMessageDialog(view, "Data successfully deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(view, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


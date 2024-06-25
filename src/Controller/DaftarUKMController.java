/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Database;
import View.DaftarUKMPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author jorda
 */

public class DaftarUKMController {
    
    public DaftarUKMController() {
    }

    private byte[] readFileToByteArray(String filePath) throws IOException {
        File file = new File(filePath);
        return Files.readAllBytes(file.toPath());
    }

    public class OpenButtonListener implements ActionListener {
        private DaftarUKMPanel panel;

        public OpenButtonListener(DaftarUKMPanel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    String extension = getExtension(f);
                    return extension.equals("jpeg") || extension.equals("jpg") || extension.equals("png");
                }

                @Override
                public String getDescription() {
                    return "Image Files (*.jpeg, *.jpg, *.png)";
                }

                private String getExtension(File f) {
                    String name = f.getName();
                    int lastIndex = name.lastIndexOf('.');
                    return lastIndex == -1 ? "" : name.substring(lastIndex + 1).toLowerCase();
                }
            });

            int returnValue = fileChooser.showOpenDialog(panel);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                panel.getFileTextField().setText(selectedFile.getAbsolutePath());
                panel.getFileTextField().setEditable(false);
            }
        }
    }

    public class KirimButtonListener implements ActionListener {
        private DaftarUKMPanel panel;

        public KirimButtonListener(DaftarUKMPanel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String ukm = panel.getUkmField().getText();
            String keterangan = panel.getKeteranganField().getText();
            String motivasi = panel.getMotivasiTextArea().getText();
            String filePath = panel.getFileTextField().getText();
            String username = panel.getUser().getUsername();

            if (ukm.isEmpty() || motivasi.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String selectSql = "SELECT id FROM formUKM WHERE username = ? AND ukm = ?";
            String insertSql = "INSERT INTO formUKM (username, ukm, keterangan, motivasi, prestasi, status) VALUES (?, ?, ?, ?, ?, 0)";
            String updateSql = "UPDATE formUKM SET keterangan = ?, motivasi = ?, prestasi = ? WHERE id = ?";

            try (Connection conn = Database.connect();
                 PreparedStatement selectStmt = conn.prepareStatement(selectSql);
                 PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                 PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

                selectStmt.setString(1, username);
                selectStmt.setString(2, ukm);
                ResultSet rs = selectStmt.executeQuery();
                
                byte[] fileContent = null;
                if (!filePath.isEmpty()) {
                    fileContent = readFileToByteArray(filePath);
                }

                if (rs.next()) {
                    int id = rs.getInt("id");
                    updateStmt.setString(1, keterangan);
                    updateStmt.setString(2, motivasi);
                    if (fileContent != null) {
                        updateStmt.setBytes(3, fileContent);
                    } else {
                        updateStmt.setNull(3, java.sql.Types.BLOB);
                    }
                    updateStmt.setInt(4, id);
                    updateStmt.executeUpdate();
                    JOptionPane.showMessageDialog(panel, "Data successfully updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    insertStmt.setString(1, username);
                    insertStmt.setString(2, ukm);
                    insertStmt.setString(3, keterangan);
                    insertStmt.setString(4, motivasi);
                    if (fileContent != null) {
                        insertStmt.setBytes(5, fileContent);
                    } else {
                        insertStmt.setNull(5, java.sql.Types.BLOB);
                    }
                    insertStmt.executeUpdate();
                    JOptionPane.showMessageDialog(panel, "Data successfully submitted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }

                panel.resetFields();
            } catch (SQLException | IOException e) {
                JOptionPane.showMessageDialog(panel, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
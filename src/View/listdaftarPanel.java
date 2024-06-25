/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.ImageRenderer;
import Controller.listdaftarController;
import Database.Database;
import Model.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import javax.swing.JTable;

/**
 *
 * @author jorda
 */
public class listdaftarPanel extends javax.swing.JPanel {

    private DashboardClient dashboardClient;
    private User user;
    private JScrollPane contentScrollPane;
    private listdaftarController controller;

    public listdaftarPanel(JScrollPane contentScrollPane, User user) {
        initComponents();
        this.contentScrollPane = contentScrollPane;
        this.user = user;
        customizeTable();
        loadTableData();
        controller = new listdaftarController(this, user); // Menghubungkan controller
    }

    public void addController(listdaftarController controller) {
        updateButton.addActionListener(controller.new UpdateButtonListener());
        deleteButton.addActionListener(controller.new DeleteButtonListener());
    }
    
    public void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing data

        String sql = "SELECT ukm, keterangan, motivasi, prestasi, status FROM formUKM WHERE username = ?";
        try (java.sql.Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String ukm = rs.getString("ukm");
                String keterangan = rs.getString("keterangan");
                String motivasi = rs.getString("motivasi");
                byte[] prestasi = rs.getBytes("prestasi");
                int status = rs.getInt("status");

                // Convert status to descriptive text
                String statusText;
                switch (status) {
                    case 1:
                        statusText = "Diterima";
                        break;
                    case 2:
                        statusText = "Ditolak";
                        break;
                    default:
                        statusText = "Menunggu/Pending";
                        break;
                }

                // Convert prestasi byte array to ImageIcon
                ImageIcon imageIcon = null;
                if (prestasi != null && prestasi.length > 0) {
                    imageIcon = new ImageIcon(prestasi);
                }
                model.addRow(new Object[]{ukm, keterangan, motivasi, imageIcon, statusText});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void customizeTable() {
        jTable1.setRowHeight(100); // Set row height to display images properly
        jTable1.getColumnModel().getColumn(3).setCellRenderer(new ImageRenderer()); // Set custom renderer for "Prestasi" column

        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(Color.BLUE);
        header.setForeground(Color.WHITE);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
    }

    public byte[] imageIconToByteArray(ImageIcon icon) {
        BufferedImage bufferedImage = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB
        );
        icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    // Getter methods for fields
    public JScrollPane getContentScrollPane() {
        return contentScrollPane;
    }

    public User getUser() {
        return user;
    }

    public JTable getJTable() {
        return jTable1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        backgroundPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 153));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        backgroundPanel.setBackground(new java.awt.Color(255, 255, 153));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        jLabel2.setText("List Form yang Anda Kirim");

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "UKM", "Keterangan", "Motivasi", "Prestasi", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        updateButton.setBackground(new java.awt.Color(51, 102, 255));
        updateButton.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("Update");

        deleteButton.setBackground(new java.awt.Color(255, 51, 51));
        deleteButton.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete");

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel2))
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(updateButton)
                        .addGap(50, 50, 50)
                        .addComponent(deleteButton))
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel2)
                .addGap(44, 44, 44)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton)
                    .addComponent(deleteButton))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(backgroundPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
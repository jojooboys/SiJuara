/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.ImageRenderer;
import Controller.cetakDataAdminController;
import Database.Database;
import Model.Profil;
import Model.User;
import Model.UserDao;
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
import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author jorda
 */
public class cetakDataAdminPanel extends javax.swing.JPanel {

    private cetakDataAdminController controller;
    private JScrollPane contentScrollPane;
    private User user;
    private Profil profil;
    private DashboardClient dashboardClient;

    public cetakDataAdminPanel(JScrollPane contentScrollPane, User user) {
        this(contentScrollPane, user, null); // Delegate to the other constructor
    }

    public cetakDataAdminPanel(JScrollPane contentScrollPane, User user, DashboardClient dashboardClient) {
        initComponents();
        this.contentScrollPane = contentScrollPane;
        this.user = user;
        this.dashboardClient = dashboardClient;
        this.controller = new cetakDataAdminController(this, user, dashboardClient, contentScrollPane);
        this.profil = UserDao.getProfil(user);
        customizeTable();
        loadTableData();
        addActionListener();
    }

    private void addActionListener() {
        cetakButton.addActionListener(controller.new cetakButtonListener(this));
        cetakCSVButton.addActionListener(controller.new cetakCSVButtonListener(this));
        cetakExcelButton.addActionListener(controller.new cetakExcelButtonListener(this));
    }


    public void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing data

        String sql = "SELECT u.fullname, p.kelas, u.username, u.email, f.ukm, f.keterangan, f.motivasi, f.prestasi, f.status " +
                     "FROM formUKM f " +
                     "JOIN users u ON f.username = u.username " +
                     "JOIN profil p ON u.username = p.username";
        try (java.sql.Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String fullname = rs.getString("fullname");
                String kelas = rs.getString("kelas");
                String username = rs.getString("username");
                String email = rs.getString("email");
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
                model.addRow(new Object[]{fullname, kelas, username, email, ukm, keterangan, motivasi, imageIcon, statusText});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void customizeTable() {
        jTable1.setRowHeight(100); // Set row height to display images properly
        jTable1.getColumnModel().getColumn(7).setCellRenderer(new ImageRenderer()); // Set custom renderer for "Prestasi" column

        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(Color.BLUE);
        header.setForeground(Color.WHITE);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
    }
 

    public class CustomTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cell.setFont(new Font("SansSerif", Font.PLAIN, 18)); // Set font and text size
            return cell;
        }
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

    public JPanel getCetakPanel() {
        return cetakPanel;
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

        jScrollPane1 = new javax.swing.JScrollPane();
        backgroundPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cetakPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cetakButton = new javax.swing.JButton();
        cetakCSVButton = new javax.swing.JButton();
        cetakExcelButton = new javax.swing.JButton();

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        backgroundPanel.setBackground(new java.awt.Color(255, 255, 153));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        cetakPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 1, 48)); // NOI18N
        jLabel1.setText("Si Juara");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/winner100px.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Logo STIS.png"))); // NOI18N

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nama Lengkap", "Kelas", "NIM", "Email", "UKM", "Keterangan", "Motivasi", "Prestasi", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout cetakPanelLayout = new javax.swing.GroupLayout(cetakPanel);
        cetakPanel.setLayout(cetakPanelLayout);
        cetakPanelLayout.setHorizontalGroup(
            cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cetakPanelLayout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(186, 186, 186)
                .addComponent(jLabel3)
                .addGap(277, 277, 277))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cetakPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 986, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
        );
        cetakPanelLayout.setVerticalGroup(
            cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cetakPanelLayout.createSequentialGroup()
                .addGroup(cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cetakPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2))
                    .addGroup(cetakPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cetakPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(cetakPanel);

        cetakButton.setBackground(new java.awt.Color(153, 255, 153));
        cetakButton.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        cetakButton.setText("Cetak Halaman");

        cetakCSVButton.setBackground(new java.awt.Color(51, 51, 255));
        cetakCSVButton.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        cetakCSVButton.setForeground(new java.awt.Color(255, 255, 255));
        cetakCSVButton.setText("Cetak Tabel (CSV)");

        cetakExcelButton.setBackground(new java.awt.Color(51, 51, 255));
        cetakExcelButton.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        cetakExcelButton.setForeground(new java.awt.Color(255, 255, 255));
        cetakExcelButton.setText("Cetak Tabel (Excel)");

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(cetakButton)
                .addGap(53, 53, 53)
                .addComponent(cetakCSVButton)
                .addGap(42, 42, 42)
                .addComponent(cetakExcelButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cetakButton)
                    .addComponent(cetakCSVButton)
                    .addComponent(cetakExcelButton))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(backgroundPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton cetakButton;
    private javax.swing.JButton cetakCSVButton;
    private javax.swing.JButton cetakExcelButton;
    private javax.swing.JPanel cetakPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

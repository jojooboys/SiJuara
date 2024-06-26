/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.ProfilController;
import Model.User;
import Model.UserDao;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author jorda
 */

public class ProfilPanel extends javax.swing.JPanel {
    private JScrollPane contentScrollPane;
    private User user;
    private ProfilController controller;

    public ProfilPanel(JScrollPane contentScrollPane, User user, DashboardClient dashboardClient) {
        initComponents();
        this.contentScrollPane = contentScrollPane;
        this.user = user;
        this.controller = new ProfilController();
        addActionListener();
        defaultFields();
    }
    
    public ProfilPanel(JScrollPane contentScrollPane, User user) {
        initComponents();
        this.contentScrollPane = contentScrollPane;
        this.user = user;
        this.controller = new ProfilController();
        addActionListener();
        defaultFields();
    }

    private void addActionListener() {
        updateButton.addActionListener(controller.new UpdateButtonListener(controller, this));
        showdataButton.addActionListener(controller.new ShowDataButtonListener(controller, this));
    }
    
    // Method to default fields
    public void defaultFields() {
        String fullname = UserDao.getFullname(user);
        String username = UserDao.getUsername(user);
        tampilfullname.setText(fullname);
        tampilUsername.setText(username);
    }

    public User getUser() {
        return user;
    }

    public JTextField getKelasField() {
        return kelasField;
    }

    public JTextArea getAlamatTextArea() {
        return alamatTextArea;
    }

    public JComboBox<String> getJenisKelaminBox() {
        return jenisKelaminBox;
    }

    public JTextPane getTampilfullname() {
        return tampilfullname;
    }

    public JTextPane getTampilUsername() {
        return tampilUsername;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entryProfilScrollPane = new javax.swing.JScrollPane();
        entryProfilPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nimScrollPane = new javax.swing.JScrollPane();
        tampilUsername = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        namaScrollPane = new javax.swing.JScrollPane();
        tampilfullname = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        kelasField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jenisKelaminBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        alamatScrollPane = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        alamatTextArea = new javax.swing.JTextArea();
        updateButton = new javax.swing.JButton();
        showdataButton = new javax.swing.JButton();

        entryProfilScrollPane.setBackground(new java.awt.Color(255, 255, 153));
        entryProfilScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        entryProfilScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        entryProfilPanel.setBackground(new java.awt.Color(255, 255, 153));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel1.setText("Data Diri");

        nimScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tampilUsername.setEditable(false);
        tampilUsername.setBackground(new java.awt.Color(204, 204, 204));
        tampilUsername.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tampilUsername.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tampilUsername.setFocusable(false);
        nimScrollPane.setViewportView(tampilUsername);

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel2.setText("Nama Lengkap:");

        namaScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tampilfullname.setBackground(new java.awt.Color(255, 255, 255));
        tampilfullname.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tampilfullname.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        namaScrollPane.setViewportView(tampilfullname);

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel3.setText("NIM:");

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel4.setText("Kelas:");

        kelasField.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        kelasField.setText("Isikan Nama Kelas");

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel5.setText("Jenis Kelamin:");

        jenisKelaminBox.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jenisKelaminBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel6.setText("Alamat:");

        alamatTextArea.setColumns(20);
        alamatTextArea.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        alamatTextArea.setRows(5);
        alamatTextArea.setText("Isikan Alamat Rumah/Kos/Kontrakan");
        jScrollPane2.setViewportView(alamatTextArea);

        alamatScrollPane.setViewportView(jScrollPane2);

        updateButton.setBackground(new java.awt.Color(51, 102, 255));
        updateButton.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("Update");

        showdataButton.setBackground(new java.awt.Color(102, 255, 102));
        showdataButton.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        showdataButton.setText("Lihat Data");

        javax.swing.GroupLayout entryProfilPanelLayout = new javax.swing.GroupLayout(entryProfilPanel);
        entryProfilPanel.setLayout(entryProfilPanelLayout);
        entryProfilPanelLayout.setHorizontalGroup(
            entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entryProfilPanelLayout.createSequentialGroup()
                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entryProfilPanelLayout.createSequentialGroup()
                        .addGap(399, 399, 399)
                        .addComponent(jLabel1))
                    .addGroup(entryProfilPanelLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel6)
                        .addGap(38, 38, 38)
                        .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(entryProfilPanelLayout.createSequentialGroup()
                                .addComponent(updateButton)
                                .addGap(76, 76, 76)
                                .addComponent(showdataButton))
                            .addComponent(alamatScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, entryProfilPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(38, 38, 38)
                        .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jenisKelaminBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nimScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kelasField, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        entryProfilPanelLayout.setVerticalGroup(
            entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entryProfilPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(60, 60, 60)
                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entryProfilPanelLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel2))
                    .addGroup(entryProfilPanelLayout.createSequentialGroup()
                        .addComponent(nimScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(namaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(27, 27, 27)
                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kelasField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(29, 29, 29)
                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jenisKelaminBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(34, 34, 34)
                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(alamatScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(125, 125, 125)
                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showdataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(462, Short.MAX_VALUE))
        );

        entryProfilScrollPane.setViewportView(entryProfilPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(entryProfilScrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(entryProfilScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane alamatScrollPane;
    private javax.swing.JTextArea alamatTextArea;
    private javax.swing.JPanel entryProfilPanel;
    private javax.swing.JScrollPane entryProfilScrollPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jenisKelaminBox;
    private javax.swing.JTextField kelasField;
    private javax.swing.JScrollPane namaScrollPane;
    private javax.swing.JScrollPane nimScrollPane;
    private javax.swing.JButton showdataButton;
    private javax.swing.JTextPane tampilUsername;
    private javax.swing.JTextPane tampilfullname;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}

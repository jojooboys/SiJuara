/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.ProfilController;
import Controller.akunAdminController;
import Model.User;
import Model.UserDao;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jorda
 */

public class akunAdminPanel extends javax.swing.JPanel {
    private JScrollPane contentScrollPane;
    private User user;
    private akunAdminController controller;
    private UserDao userDao;

    public akunAdminPanel(JScrollPane contentScrollPane, User user) {
        initComponents();
        this.contentScrollPane = contentScrollPane;
        this.user = user;
        this.controller = new akunAdminController();
        this.userDao = new UserDao();
        addActionListener();
        defaultFields();
    }
    
    private void addActionListener() {
        gantiPasswordCheckBox.addActionListener(controller.new gantiPasswordCheckBoxListener(controller, this));
        submitButton.addActionListener(controller.new submitButtonListener(controller, this));
    }

    public boolean isGantiPasswordChecked() {
        return gantiPasswordCheckBox.isSelected();
    }

    public JTextField getFullnameField() {
        return fullnameField;
    }
    
    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JPasswordField getNewPasswordField() {
        return newPasswordField;
    }

    public JPasswordField getConfirmNewPasswordField() {
        return confirmNewPasswordField;
    }

    public void togglePasswordFields(boolean isEnabled) {
        newPasswordField.setEnabled(isEnabled);
        confirmNewPasswordField.setEnabled(isEnabled);
    }

    public void defaultFields() {
        String fullname = UserDao.getFullname(user);
        String username = UserDao.getUsername(user);
        String email = UserDao.getEmail(user);
        
        fullnameField.setText(fullname);
        usernameField.setText(username);
        emailField.setText(email);
        newPasswordField.setEnabled(false);
        confirmNewPasswordField.setEnabled(false);
    }

    public void handleSubmit() {
        controller.handleSubmit(this, user);
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
        jLabel2 = new javax.swing.JLabel();
        fullnameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        newPasswordField = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        confirmNewPasswordField = new javax.swing.JPasswordField();
        gantiPasswordCheckBox = new javax.swing.JCheckBox();
        submitButton = new javax.swing.JButton();

        entryProfilScrollPane.setBackground(new java.awt.Color(255, 255, 153));
        entryProfilScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        entryProfilScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        entryProfilPanel.setBackground(new java.awt.Color(255, 255, 153));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel1.setText("Data Akun");

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel2.setText("Nama Lengkap:");

        fullnameField.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel3.setText("Username/NIM:");

        usernameField.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel4.setText("Email:");

        emailField.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel5.setText("Password sekarang:");

        passwordField.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel6.setText("Password Baru:");

        newPasswordField.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel7.setText("Confirm Password:");

        confirmNewPasswordField.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N

        gantiPasswordCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gantiPasswordCheckBox.setText("Ganti Password?");

        submitButton.setBackground(new java.awt.Color(51, 102, 255));
        submitButton.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        submitButton.setText("Submit");

        javax.swing.GroupLayout entryProfilPanelLayout = new javax.swing.GroupLayout(entryProfilPanel);
        entryProfilPanel.setLayout(entryProfilPanelLayout);
        entryProfilPanelLayout.setHorizontalGroup(
            entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entryProfilPanelLayout.createSequentialGroup()
                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, entryProfilPanelLayout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(fullnameField))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, entryProfilPanelLayout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(entryProfilPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(206, 206, 206))
                            .addGroup(entryProfilPanelLayout.createSequentialGroup()
                                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(30, 30, 30)
                                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(submitButton)
                                    .addComponent(gantiPasswordCheckBox)
                                    .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(newPasswordField)
                                        .addComponent(confirmNewPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(entryProfilPanelLayout.createSequentialGroup()
                                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2))
                                .addGap(30, 30, 30)
                                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(usernameField)
                                    .addComponent(emailField)
                                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(399, Short.MAX_VALUE))
        );
        entryProfilPanelLayout.setVerticalGroup(
            entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entryProfilPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fullnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(30, 30, 30)
                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(gantiPasswordCheckBox)
                .addGap(18, 18, 18)
                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(entryProfilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(confirmNewPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(643, Short.MAX_VALUE))
        );

        entryProfilScrollPane.setViewportView(entryProfilPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(entryProfilScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(entryProfilScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmNewPasswordField;
    private javax.swing.JTextField emailField;
    private javax.swing.JPanel entryProfilPanel;
    private javax.swing.JScrollPane entryProfilScrollPane;
    private javax.swing.JTextField fullnameField;
    private javax.swing.JCheckBox gantiPasswordCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField newPasswordField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
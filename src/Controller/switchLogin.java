/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.LoginFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author jorda
 */
public class switchLogin implements ActionListener {
    private JFrame frame;

    public switchLogin(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        LoginFrame LoginFrame = new LoginFrame();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null); 
        frame.dispose();
    }
}

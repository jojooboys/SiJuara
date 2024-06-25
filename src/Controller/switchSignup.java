/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.SignupFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author jorda
 */

public class switchSignup implements ActionListener {
    private JFrame frame;

    public switchSignup(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        SignupFrame signUpFrame = new SignupFrame();
        signUpFrame.setVisible(true);
        signUpFrame.pack();
        signUpFrame.setLocationRelativeTo(null); 
        frame.dispose();
    }
}


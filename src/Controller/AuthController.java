/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import Model.UserDao;
import java.util.regex.Pattern;

/**
 *
 * @author jorda
 */

public class AuthController {

    public static User validateLogin(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }
        UserDao userDao = new UserDao();
        return userDao.authenticateUser(username, password);
    }


    public static boolean validateSignup(String fullname, String username, String email, String password, String confirmPassword, int admin) {
        if (fullname.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }

        if (!isValidEmail(email)) {
            return false;
        }

        if (password.length() < 6) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            return false;
        }

        UserDao userDao = new UserDao();
        User user = new User(fullname, username, email, password, admin);
        return userDao.registerUser(user);
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
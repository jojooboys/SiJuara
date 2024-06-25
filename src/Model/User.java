/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author jorda
 */

public class User {
    private int id;
    private String fullname;
    private String username;
    private String email;
    private String password;
    private int admin;

    public User(String fullname, String username, String email, String password, int admin) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    // Getters
    public int getId() {
       return id;
    }
    
    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public int isAdmin() {
        return admin;
    }

    // Setters
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }  
}
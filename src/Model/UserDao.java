/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Database.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorda
 */

public class UserDao {
    private List<User> users = new ArrayList<>();

    public static boolean addUser(String fullname, String username, String email, String password) {
        String query = "INSERT INTO users (fullname, username, email, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, fullname);
            pstmt.setString(2, username);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUser(String username) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("fullname"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("admin"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public User getUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("fullname"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("admin"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User authenticateUser(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(
                    rs.getString("fullname"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getInt("admin")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    public boolean registerUser(User user) {
        String insertUserSQL = "INSERT INTO users (fullname, username, email, password, admin) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(insertUserSQL)) {
            pstmt.setString(1, user.getFullname());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.isAdmin());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
        public static String getFullname(User user) {
        // Implementasi untuk mendapatkan fullname dari database
        String fullname = null;
        String sql = "SELECT fullname FROM users WHERE username = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                fullname = rs.getString("fullname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullname;
    }
    
    public static Integer getId(User user) {
        String sql = "SELECT id FROM users WHERE username = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public static String getUsername(User user) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String getEmail(User user) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getEmail());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String getKelas(Profil profil) {
        String sql = "SELECT * FROM profil WHERE kelas = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, profil.getKelas());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("kelas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String getJenisKelamin(Profil profil) {
        String sql = "SELECT * FROM profil WHERE jenis_kelamin = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, profil.getJenisKelamin());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("jenis_kelamin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String getAlamat(Profil profil) {
        String sql = "SELECT * FROM profil WHERE alamat = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, profil.getAlamat());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("alamat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateUser(User user, String newUsername, String newEmail, String newPassword) {
        String updateUsersSQL = "UPDATE users SET username = ?, email = ?, password = ? WHERE username = ?";
        String updateProfilSQL = "UPDATE profil SET username = ? WHERE username = ?";
        String updateFormUKMSQL = "UPDATE formUKM SET username = ? WHERE username = ?";

        try (Connection conn = Database.connect()) {
            conn.setAutoCommit(false); // Mulai transaksi

            try (PreparedStatement pstmtUsers = conn.prepareStatement(updateUsersSQL);
                 PreparedStatement pstmtProfil = conn.prepareStatement(updateProfilSQL);
                 PreparedStatement pstmtFormUKM = conn.prepareStatement(updateFormUKMSQL)) {

                // Update tabel users
                pstmtUsers.setString(1, newUsername);
                pstmtUsers.setString(2, newEmail);
                pstmtUsers.setString(3, newPassword);
                pstmtUsers.setString(4, user.getUsername());
                pstmtUsers.executeUpdate();

                // Update tabel profil
                pstmtProfil.setString(1, newUsername);
                pstmtProfil.setString(2, user.getUsername());
                pstmtProfil.executeUpdate();

                // Update tabel formUKM
                pstmtFormUKM.setString(1, newUsername);
                pstmtFormUKM.setString(2, user.getUsername());
                pstmtFormUKM.executeUpdate();

                conn.commit(); // Komit transaksi
                return true;
            } catch (SQLException e) {
                conn.rollback(); // Rollback jika terjadi kesalahan
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true); // Kembalikan ke mode auto-commit
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public static Profil getProfil(User user) {
        Profil profil = null;
        String sql = "SELECT * FROM Profil WHERE username = ?";
        
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getUsername());
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String username = rs.getString("username");
                String kelas = rs.getString("kelas");
                String jenisKelamin = rs.getString("jenis_kelamin");
                String alamat = rs.getString("alamat");
                
                profil = new Profil(username, kelas, jenisKelamin, alamat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return profil;
    }
    
    public boolean addProfil(Profil profil) {
        String insertSQL = "INSERT INTO profil (username, kelas, jenis_kelamin, alamat) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, profil.getUsername());
            pstmt.setString(2, profil.getKelas());
            pstmt.setString(3, profil.getJenisKelamin());
            pstmt.setString(4, profil.getAlamat());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean isValidPassword(User user, String password) {
        boolean isValid = false;
        String sql = "SELECT password FROM users WHERE username = ?";
        
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, user.getUsername());
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                isValid = storedPassword.equals(password); // Implementasi ini bisa disesuaikan dengan enkripsi password yang digunakan
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }
    
    public boolean updateUserWithoutPassword(User user, String newUsername, String newEmail) {
        String updateUsersSQL = "UPDATE users SET username = ?, email = ? WHERE username = ?";
        String updateProfilSQL = "UPDATE profil SET username = ? WHERE username = ?";
        String updateFormUKMSQL = "UPDATE formUKM SET username = ? WHERE username = ?";

        try (Connection conn = Database.connect()) {
            conn.setAutoCommit(false); // Mulai transaksi

            try (PreparedStatement pstmtUsers = conn.prepareStatement(updateUsersSQL);
                 PreparedStatement pstmtProfil = conn.prepareStatement(updateProfilSQL);
                 PreparedStatement pstmtFormUKM = conn.prepareStatement(updateFormUKMSQL)) {

                // Update tabel users
                pstmtUsers.setString(1, newUsername);
                pstmtUsers.setString(2, newEmail);
                pstmtUsers.setString(3, user.getUsername());
                pstmtUsers.executeUpdate();

                // Update tabel profil
                pstmtProfil.setString(1, newUsername);
                pstmtProfil.setString(2, user.getUsername());
                pstmtProfil.executeUpdate();

                // Update tabel formUKM
                pstmtFormUKM.setString(1, newUsername);
                pstmtFormUKM.setString(2, user.getUsername());
                pstmtFormUKM.executeUpdate();

                conn.commit(); // Komit transaksi
                return true;
            } catch (SQLException e) {
                conn.rollback(); // Rollback jika terjadi kesalahan
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true); // Kembalikan ke mode auto-commit
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean updateUserWithPassword(User user, String username, String email, String newPassword, Integer id) {
        String updateSQL = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, newPassword);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateAllUsernames(String oldUsername, String newUsername, String newEmail, String newPassword) {
        String updateUsersSQL = "UPDATE users SET username = ?, email = ?, password = ? WHERE username = ?";
        String updateProfilSQL = "UPDATE profil SET username = ? WHERE username = ?";
        String updateFormUKMSQL = "UPDATE formUKM SET username = ? WHERE username = ?";

        try (Connection conn = Database.connect()) {
            conn.setAutoCommit(false); // Mulai transaksi

            try (PreparedStatement pstmtUsers = conn.prepareStatement(updateUsersSQL);
                 PreparedStatement pstmtProfil = conn.prepareStatement(updateProfilSQL);
                 PreparedStatement pstmtFormUKM = conn.prepareStatement(updateFormUKMSQL)) {

                // Update tabel users
                pstmtUsers.setString(1, newUsername);
                pstmtUsers.setString(2, newEmail);
                pstmtUsers.setString(3, newPassword);
                pstmtUsers.setString(4, oldUsername);
                pstmtUsers.executeUpdate();

                // Update tabel profil
                pstmtProfil.setString(1, newUsername);
                pstmtProfil.setString(2, oldUsername);
                pstmtProfil.executeUpdate();

                // Update tabel formUKM
                pstmtFormUKM.setString(1, newUsername);
                pstmtFormUKM.setString(2, oldUsername);
                pstmtFormUKM.executeUpdate();

                conn.commit(); // Komit transaksi
                return true;
            } catch (SQLException e) {
                conn.rollback(); // Rollback jika terjadi kesalahan
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true); // Kembalikan ke mode auto-commit
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAllUsernamesWithoutPassword(String oldUsername, String newUsername, String newEmail) {
        String updateUsersSQL = "UPDATE users SET username = ?, email = ? WHERE username = ?";
        String updateProfilSQL = "UPDATE profil SET username = ? WHERE username = ?";
        String updateFormUKMSQL = "UPDATE formUKM SET username = ? WHERE username = ?";

        try (Connection conn = Database.connect()) {
            conn.setAutoCommit(false); // Mulai transaksi

            try (PreparedStatement pstmtUsers = conn.prepareStatement(updateUsersSQL);
                 PreparedStatement pstmtProfil = conn.prepareStatement(updateProfilSQL);
                 PreparedStatement pstmtFormUKM = conn.prepareStatement(updateFormUKMSQL)) {

                // Update tabel users
                pstmtUsers.setString(1, newUsername);
                pstmtUsers.setString(2, newEmail);
                pstmtUsers.setString(3, oldUsername);
                pstmtUsers.executeUpdate();

                // Update tabel profil
                pstmtProfil.setString(1, newUsername);
                pstmtProfil.setString(2, oldUsername);
                pstmtProfil.executeUpdate();

                // Update tabel formUKM
                pstmtFormUKM.setString(1, newUsername);
                pstmtFormUKM.setString(2, oldUsername);
                pstmtFormUKM.executeUpdate();

                conn.commit(); // Komit transaksi
                return true;
            } catch (SQLException e) {
                conn.rollback(); // Rollback jika terjadi kesalahan
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true); // Kembalikan ke mode auto-commit
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAllUsernames(String oldUsername, String newUsername, String newEmail, String newFullname, String newPassword) {
        String updateUsersSQL = "UPDATE users SET username = ?, email = ?, fullname = ?, password = ? WHERE username = ?";
        String updateProfilSQL = "UPDATE profil SET username = ? WHERE username = ?";
        String updateFormUKMSQL = "UPDATE formUKM SET username = ? WHERE username = ?";

        try (Connection conn = Database.connect()) {
            conn.setAutoCommit(false); // Mulai transaksi

            try (PreparedStatement pstmtUsers = conn.prepareStatement(updateUsersSQL);
                 PreparedStatement pstmtProfil = conn.prepareStatement(updateProfilSQL);
                 PreparedStatement pstmtFormUKM = conn.prepareStatement(updateFormUKMSQL)) {

                // Update tabel users
                pstmtUsers.setString(1, newUsername);
                pstmtUsers.setString(2, newEmail);
                pstmtUsers.setString(3, newFullname);
                pstmtUsers.setString(4, newPassword);
                pstmtUsers.setString(5, oldUsername);
                pstmtUsers.executeUpdate();

                // Update tabel profil
                pstmtProfil.setString(1, newUsername);
                pstmtProfil.setString(2, oldUsername);
                pstmtProfil.executeUpdate();

                // Update tabel formUKM
                pstmtFormUKM.setString(1, newUsername);
                pstmtFormUKM.setString(2, oldUsername);
                pstmtFormUKM.executeUpdate();

                conn.commit(); // Komit transaksi
                return true;
            } catch (SQLException e) {
                conn.rollback(); // Rollback jika terjadi kesalahan
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true); // Kembalikan ke mode auto-commit
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAllUsernamesWithoutPassword(String oldUsername, String newUsername, String newEmail, String newFullname) {
        String updateUsersSQL = "UPDATE users SET username = ?, email = ?, fullname = ? WHERE username = ?";
        String updateProfilSQL = "UPDATE profil SET username = ? WHERE username = ?";
        String updateFormUKMSQL = "UPDATE formUKM SET username = ? WHERE username = ?";

        try (Connection conn = Database.connect()) {
            conn.setAutoCommit(false); // Mulai transaksi

            try (PreparedStatement pstmtUsers = conn.prepareStatement(updateUsersSQL);
                 PreparedStatement pstmtProfil = conn.prepareStatement(updateProfilSQL);
                 PreparedStatement pstmtFormUKM = conn.prepareStatement(updateFormUKMSQL)) {

                // Update tabel users
                pstmtUsers.setString(1, newUsername);
                pstmtUsers.setString(2, newEmail);
                pstmtUsers.setString(3, newFullname);
                pstmtUsers.setString(4, oldUsername);
                pstmtUsers.executeUpdate();

                // Update tabel profil
                pstmtProfil.setString(1, newUsername);
                pstmtProfil.setString(2, oldUsername);
                pstmtProfil.executeUpdate();

                // Update tabel formUKM
                pstmtFormUKM.setString(1, newUsername);
                pstmtFormUKM.setString(2, oldUsername);
                pstmtFormUKM.executeUpdate();

                conn.commit(); // Komit transaksi
                return true;
            } catch (SQLException e) {
                conn.rollback(); // Rollback jika terjadi kesalahan
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true); // Kembalikan ke mode auto-commit
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.*;

/**
 *
 * @author jorda
 */

public class Database {
    private static final String DB_URL = "jdbc:sqlite:E:/01 Perkuliahan STIS/Semester 4/PBO/Praktik/Project UAS PBO/UASpbo.db";

    public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA journal_mode=WAL;");
        }
        return conn;
    }
}


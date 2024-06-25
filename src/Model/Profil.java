/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author jorda
 */

public class Profil {
    private String fullname;
    private String username;
    private String kelas;
    private String jenisKelamin;
    private String alamat;

    public Profil(String fullname, String username, String kelas, String jenisKelamin, String alamat) {
        this.fullname = fullname;
        this.username = username;
        this.kelas = kelas;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
    }
    
    public Profil(String username, String kelas, String jenisKelamin, String alamat) {
        this.username = username;
        this.kelas = kelas;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
    }
    
    // Getters
    
     public String getFullname() {
        return fullname;
    }
    
    public String getUsername() {
        return username;
    }

    public String getKelas() {
        return kelas;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    // Setters
    
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
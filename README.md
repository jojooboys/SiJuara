# Si Juara (Sistem Jalur masUk Aktifitas luaR Akademik)

## Deskripsi Proyek
Si Juara adalah aplikasi Java yang dibuat menggunakan Apache NetBeans sebagai bagian dari Ujian Akhir Semester (UAS) mata kuliah Pemrograman Berorientasi Objek. Aplikasi ini memiliki dua jenis login: 
- **Client**: Dapat mendaftar Unit Kegiatan Mahasiswa (UKM) melalui form yang ada di aplikasi serta bisa mengupdate atau menghapus form yang dia kirim.
- **Admin**: Bisa menerima, menunda, dan menolak status form pendaftaran, serta mencetak seluruh form yang ada di database menjadi PDF, CSV, dan XLSX.

## Fitur
- Form pendaftaran UKM tersimpan dalam database SQLite bernama `UASpbo.db`.
- CRUD form untuk client.
- Ekspor data form ke PDF menggunakan iTextPDF.
- Ekspor data tabel ke CSV dan XLSX.

## Instruksi Instalasi
Saat ini, proyek ini belum dibuild ke dalam format executable (EXE atau JAR). Untuk menjalankan proyek:
1. Clone repositori ini.
2. Buka proyek menggunakan Apache NetBeans.
3. Jalankan proyek dari Apache NetBeans.

## Cara Penggunaan
1. Jalankan aplikasi.
2. **Login**: Masukkan kredensial jika sudah memiliki akun yang tersimpan di database.
3. **Sign Up**: Daftar akun baru jika belum memiliki akun di database.
4. Setelah login, client dapat mengisi, memperbarui, atau menghapus form pendaftaran UKM mereka sendiri.
5. Admin dapat mengelola status pendaftaran dan mengekspor data.

## Kontak
Jika ada pertanyaan atau membutuhkan bantuan, silakan hubungi:
- Email: [jordandwifebri@gmail.com](mailto:jordandwifebri@gmail.com)
